package com.jetbrains.Model;


import com.jetbrains.Controller.Controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class LoginThread implements Runnable {
    private Socket connectionSocket;
    private String input_email;
    private String input_password;
    private Controller ctrl;

    public LoginThread(Socket cs, String input_email, String input_password, Controller ctrl) {
        this.connectionSocket = cs;
        this.input_email = input_email;
        this.input_password = input_password;
        this.ctrl = ctrl;
    }

    @Override
    public void run() {
        ArrayList<String> result = new ArrayList<>();
        Employee found = ctrl.getEmployee(input_email);

        try {
            if (found == null) result.add("false");
            else{
                if (found.getPassword().equals(input_password)) result.add("true");
                else result.add("false");
            }


            ObjectOutputStream objectOutput = new ObjectOutputStream(connectionSocket.getOutputStream());
            objectOutput.writeObject(result);
            System.out.println(objectOutput.toString());
            //objectOutput.close();

            if (result.get(0).equals("true"))
            {
                new Thread(new NotificationThread(connectionSocket, objectOutput, input_email, input_password, ctrl)).start();
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
