package Model;

import Controller.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class PeopleInZoneThread implements Runnable{
    private Socket connectionSocket;
    private String input_email;
    private String input_password;
    private Controller ctrl;

    public PeopleInZoneThread(Socket connectionSocket, String input_email, String input_password, Controller ctrl) {
        this.connectionSocket = connectionSocket;
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
            else {
                ArrayList<Employee> zoneResult = ctrl.getParkingSpotsFromZone(found.getNeighbourhood());
                for (Employee emp : zoneResult)
                    if (emp.getId() != found.getId())
                        result.add(emp.getName() + "," + emp.getSurname() + "," + emp.getEmail());

            }

            ObjectOutputStream objectOutput = new ObjectOutputStream(connectionSocket.getOutputStream());
            objectOutput.writeObject(result);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
