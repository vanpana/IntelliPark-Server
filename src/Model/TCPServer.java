package Model;

import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer {
    private ServerSocket sock;

    public TCPServer(ServerSocket sock) {
        this.sock = sock;
    }

    public TCPServer(int port){
        try {
            this.sock = new ServerSocket(port);
        }
        catch (IOException e){
            System.out.println("Can't create socket on specified port!");
        }
    }

    public void run(){
        while (true) {
            try{
                Socket connectionSocket = sock.accept();
                System.out.println("Client connected!");
                ObjectInputStream objectInput = new ObjectInputStream(connectionSocket.getInputStream());
                ArrayList<String> message = new ArrayList<>();

                try {
                    Object object = objectInput.readObject();
                    message =  (ArrayList<String>) object;

                    System.out.println(message);
                    ArrayList<String> modifiedMessage = new ArrayList<>();
                    for (String msg : message){
                        modifiedMessage.add(msg.toUpperCase());
                    }

                    ObjectOutputStream objectOutput = new ObjectOutputStream(connectionSocket.getOutputStream());
                    objectOutput.writeObject(modifiedMessage);

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
