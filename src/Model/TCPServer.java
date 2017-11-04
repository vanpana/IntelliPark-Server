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

    public TCPServer(int port){
        try {
            this.sock = new ServerSocket(port);
        }
        catch (IOException e){
            System.out.println("Can't create socket on specified port!");
        }
    }

    private void analyze(ArrayList<String> command){
        if (command.size() == 0){
            System.out.println("No command provided!");
            return;
        }

        String action = command.get(0);
        if (action.equals("login")){
            if (command.size() == 3){
                new Thread(new LoginThread(connectionSocket, command.get(1), command.get(2), ctrl)).start();
            }
            else System.out.println("Received bad format!");

            //TODO: login thread
        }

        if (action.equals("showSpot")){
            //TODO: Check if password is correct
            if (command.size() == 3){
                new Thread(new ParkingThread(connectionSocket, command.get(1), ctrl)).start();
            }
            else System.out.println("Received bad format!");
        }
    }

    public void run(){
        while (true) {
            try{
                connectionSocket = sock.accept();
                System.out.println("Client connected!");
                ObjectInputStream objectInput = new ObjectInputStream(connectionSocket.getInputStream());
                ArrayList<String> message = new ArrayList<>();

                try {
                    Object object = objectInput.readObject();
                    message =  (ArrayList<String>) object;
                    System.out.println(message);
                    analyze(message);


//                    ArrayList<String> modifiedMessage = new ArrayList<>();
//                    for (String msg : message){
//                        modifiedMessage.add(msg.toUpperCase());
//                    }
//
//                    ObjectOutputStream objectOutput = new ObjectOutputStream(connectionSocket.getOutputStream());
//                    objectOutput.writeObject(modifiedMessage);

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

}
