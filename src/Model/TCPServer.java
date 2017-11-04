package Model;

import Repository.Repository;

import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer {
    private Repository repo;
    private ServerSocket sock;
    private Socket connectionSocket;

    public TCPServer(Repository repo, int port) {
        this.repo = repo;
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
                Employee found = repo.getEmployee(command.get(1));
                if (found != null) new Thread(new LoginThread(connectionSocket, command.get(1), command.get(2), found)).run();
            }
            else System.out.println("Received bad format!");

            //TODO: login thread
        }

//        if (action.equals("showSpot")){
//            //TODO: Spot thread
//        }
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
