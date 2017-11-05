package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MapThread implements Runnable{
    private Socket connectionSocket;

    public MapThread(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {

        try{
            String matrix = "";

            BufferedReader br = new BufferedReader(new FileReader("parkingmatrix.txt"));

            String line = br.readLine();
            while (line != null) {
                matrix = matrix +  line + "\n";
                line=br.readLine();
            }
            br.close();

            System.out.println(matrix);

            ArrayList<String> result = new ArrayList<>();
            result.add(matrix);

            ObjectOutputStream objectOutput = new ObjectOutputStream(connectionSocket.getOutputStream());
            objectOutput.writeObject(result);

        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
