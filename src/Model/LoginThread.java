package Model;

import Repository.Repository;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class LoginThread implements Runnable {
    private Socket connectionSocket;
    private String input_email;
    private String input_password;
    private Employee found;
    private Repository repo;

    public LoginThread(Socket cs, String input_email, String input_password, Repository repo) {
        this.connectionSocket = cs;
        this.input_email = input_email;
        this.input_password = input_password;
        this.repo = repo;
    }

    @Override
    public void run() {
        ArrayList<String> result = new ArrayList<>();
        Employee found = repo.getEmployee(input_email);

        try {
            if (found == null) result.add("false");
            else{
                if (found.getPassword().equals(input_password)) result.add("true");
                else result.add("false");
            }

            ObjectOutputStream objectOutput = new ObjectOutputStream(connectionSocket.getOutputStream());
            objectOutput.writeObject(result);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
