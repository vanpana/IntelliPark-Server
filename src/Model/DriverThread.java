package Model;

import Controller.Controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class DriverThread implements Runnable {
    private Socket connectionSocket;
    private int driver_id;
    private Controller ctrl;

    public DriverThread(Socket connectionSocket, int driver_id, Controller ctrl) {
        this.connectionSocket = connectionSocket;
        this.driver_id = driver_id;
        System.out.print("Driver in thread is: ");
        System.out.println(driver_id);
        this.ctrl = ctrl;
    }

    @Override
    public void run() {
        ArrayList<String> result = new ArrayList<>();
        Employee found = ctrl.getEmployee(driver_id);

        try {
            if (found == null) { result.add("none"); result.add("none"); }

            result.add(found.getName());
            result.add(found.getSurname());

            System.out.println(result);

            ObjectOutputStream objectOutput = new ObjectOutputStream(connectionSocket.getOutputStream());
            objectOutput.writeObject(result);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
