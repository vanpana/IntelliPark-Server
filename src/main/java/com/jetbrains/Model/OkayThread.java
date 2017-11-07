package com.jetbrains.Model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class OkayThread implements Runnable {
    private Socket connectionSocket;

    public OkayThread(Socket connectionSocket){
        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        try{
            ArrayList<String> result = new ArrayList<>();
            result.add("okay");
            ObjectOutputStream objectOutput = new ObjectOutputStream(connectionSocket.getOutputStream());
            objectOutput.writeObject(result);
        }
        catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }

    }
}
