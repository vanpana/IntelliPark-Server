package com.jetbrains.Model;


import com.jetbrains.Controller.Controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ParkingThread implements Runnable {
    private Socket connectionSocket;
    private String input_email;
    private Controller ctrl;

    public ParkingThread(Socket connectionSocket, String input_email, Controller ctrl) {
        this.connectionSocket = connectionSocket;
        this.input_email = input_email;
        this.ctrl = ctrl;
    }

    @Override
    public void run() {
        ArrayList<String> result = new ArrayList<>();
        Employee found = ctrl.getEmployee(input_email);
        ArrayList<Employee> sorted = ctrl.getParkingSpots();

        try {
            if (found == null) result.add("0");
            else{
                int counter = 0;
                for (Employee e : sorted) {
                    if (e.getId() == found.getId()) break;
                    counter++;
                }
                if (counter <= ctrl.getTotalParkingSpots()) result.add(String.valueOf(counter));
                else result.add(String.valueOf(0));
            }

            ObjectOutputStream objectOutput = new ObjectOutputStream(connectionSocket.getOutputStream());
            objectOutput.writeObject(result);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}

