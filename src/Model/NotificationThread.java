package Model;

import Controller.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class NotificationThread implements Runnable {
    private Socket connectionSocket;
    private ObjectOutputStream objectOutput;
    private String input_email;
    private String input_password;
    private Controller ctrl;

    public NotificationThread(Socket connectionSocket, String input_email, String input_password, Controller ctrl) {
        this.connectionSocket = connectionSocket;
        this.objectOutput = null;
        this.input_email = input_email;
        this.input_password = input_password;
        this.ctrl = ctrl;
    }

    public NotificationThread(Socket connectionSocket, ObjectOutputStream objectOutput, String input_email, String input_password, Controller ctrl) {
        this.connectionSocket = connectionSocket;
        this.objectOutput = objectOutput;
        this.input_email = input_email;
        this.input_password = input_password;
        this.ctrl = ctrl;
    }

    @Override
    public void run() {
        ArrayList<ArrayList<String>> notifications = ctrl.getNotifications(input_email);

        try{
            if (objectOutput == null) objectOutput = new ObjectOutputStream(connectionSocket.getOutputStream());
            objectOutput.writeInt(notifications.size());

            for (ArrayList<String> al : notifications){
                System.out.print("Notif is: ");
                System.out.println(al);
                objectOutput.writeObject(al);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
