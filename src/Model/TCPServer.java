package Model;

import Controller.Controller;
import Repository.Repository;

import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer implements Runnable{
    private Controller ctrl;
    private ServerSocket sock;
    private Socket connectionSocket;

    public TCPServer(Controller ctrl, int port) {
        this.ctrl = ctrl;
        try {
            this.sock = new ServerSocket(port);
        }
        catch (IOException e){
            System.out.println("Can't create socket on specified port!");
        }
    }

    private void analyze(ArrayList<String> command) {
        if (command.size() == 0) {
            System.out.println("No command provided!");
            return;
        }

        String action = command.get(0);
        if (action.equals("login")) {
            if (command.size() == 3) {
                new Thread(new LoginThread(connectionSocket, command.get(1), command.get(2), ctrl)).start();
            } else System.out.println("Received bad format!");
        } else if (action.equals("showSpot")) {
            //TODO: Check if password is correct
            if (command.size() == 3) {
                new Thread(new ParkingThread(connectionSocket, command.get(1), ctrl)).start();
            } else System.out.println("Received bad format!");
        }
        else if (action.equals("requestRide")) {
            //TODO: Check if password is correct
            if (command.size() == 4) {
                ArrayList<String> newcommand = new ArrayList<>();
                newcommand.add(command.get(0));
                newcommand.add(command.get(3));
                newcommand.add(command.get(1));
                ctrl.addNotification(newcommand);
                //ctrl.delNotification(Integer.parseInt(command.get(1)));
                ctrl.addCarRequest(ctrl.getEmployee(command.get(1)).getId(), ctrl.getEmployee(command.get(3)).getId());
                new Thread(new OkayThread(connectionSocket)).start();
            }
        } else if (action.equals("acceptRide") || action.equals("rejectRide")) {
            if (command.size() == 2) {

                ArrayList<String> newcommand = new ArrayList<>();
                newcommand.add(command.get(0));
                newcommand.add(ctrl.getNotification(Integer.parseInt(command.get(1))).getFromWhom());
                newcommand.add(ctrl.getNotification(Integer.parseInt(command.get(1))).getToWho());

                ctrl.addCarpool(ctrl.getEmployee(ctrl.getNotification(Integer.parseInt(command.get(1))).getToWho()).getId(),
                        ctrl.getEmployee(ctrl.getNotification(Integer.parseInt(command.get(1))).getFromWhom()).getId());

                ctrl.addNotification(newcommand);
                new Thread(new OkayThread(connectionSocket)).start();

                if (action.equals("acceptRide")){
                    int id = ctrl.getEmployee(ctrl.getNotification(Integer.parseInt(command.get(1))).getFromWhom()).getId();
                    float multiplier = ctrl.getEmployee(ctrl.getNotification(Integer.parseInt(command.get(1))).getFromWhom()).getMultiplier()
                            + (float) 0.05;
                    ctrl.updateMultiplier(id, multiplier);


                    id = ctrl.getEmployee(ctrl.getNotification(Integer.parseInt(command.get(1))).getToWho()).getId();
                    multiplier = ctrl.getEmployee(ctrl.getNotification(Integer.parseInt(command.get(1))).getToWho()).getMultiplier()
                            + (float) 0.05;
                    ctrl.updateMultiplier(id, multiplier);

                }

                ctrl.delNotification(Integer.parseInt(command.get(1)));

            }
        } else if (action.equals("getNotifications")) {
            if (command.size() == 3) {
                new Thread(new NotificationThread(connectionSocket, command.get(1), command.get(2), ctrl)).start();
            }
        }

        else if (action.equals("getPeopleInZone")){
            if (command.size() == 3){
                if (ctrl.getCarPoolDriverId(ctrl.getEmployee(command.get(1)).getId()) == -1)
                    new Thread(new PeopleInZoneThread(connectionSocket, command.get(1), command.get(2), ctrl)).start();
            }
        }

        else if (action.equals("seen")){
            if (command.size() == 2){
                ctrl.delNotification(Integer.parseInt(command.get(1)));
            }
        }

        else if (action.equals("myDriver")){
            if (command.size() == 3){
                new Thread(new DriverThread(connectionSocket, ctrl.getCarPoolDriverId(ctrl.getEmployee(command.get(1)).getId()), ctrl)).start();
            }
        }

        else if (action.equals("rejectSpot")){
            if (command.size() == 3)
                ctrl.addRejectedSpot(ctrl.getEmployee(command.get(1)).getId());
        }
    }

    public void run(){
        while (true) {
            try{
                connectionSocket = sock.accept();
                System.out.println("Client connected!");
                ObjectInputStream objectInput = new ObjectInputStream(connectionSocket.getInputStream());
                ArrayList<String> message;

                try {
                    Object object = objectInput.readObject();
                    message =  (ArrayList<String>) object;
                    System.out.println(message);
                    analyze(message);
                } catch (ClassNotFoundException e) {
                    System.out.println("The list has not come from the client");
                    e.printStackTrace();
                }

            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }

        }
    }

    public void close(){

    }
}
