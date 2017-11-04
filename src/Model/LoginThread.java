package Model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class LoginThread implements Runnable {
    private Socket connectionSocket;
    private String input_email;
    private String input_password;
    private Employee found;

    public LoginThread(Socket cs, String input_email, String input_password, Employee found) {
        this.connectionSocket = cs;
        this.input_email = input_email;
        this.input_password = input_password;
        this.found = found;
    }

    @Override
    public void run() {
        ArrayList<String> result = new ArrayList<>();
        try {
            if (found.getPassword().equals(input_password)) result.add("true");
            else result.add("false");

            ObjectOutputStream objectOutput = new ObjectOutputStream(connectionSocket.getOutputStream());
            objectOutput.writeObject(result);


        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
