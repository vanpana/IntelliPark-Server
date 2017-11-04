import Controller.Controller;
import Model.Employee;
import Model.TCPServer;
import Repository.*;
import View.Matrix;

import java.io.File;
import java.util.ArrayList;

import static java.lang.Thread.sleep;


public class Main {
    public static void main(String[] args) {
        File f = new File("parkingmatrix.txt");

        if (!f.exists()){
            Matrix mtx = new Matrix();
            mtx.createAndShowGUI();
            while(!f.exists()){
                try{
                    sleep(1);
                }
                catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        Repository repo = new Repository("myparking.db");

        NotificationRepository notifrepo = new NotificationRepository();


        ArrayList<String> notif = new ArrayList<>();
        notif.add("requestRide");
        notif.add("van.panaite@gmail.com");
        notif.add("user@example.com");
        notifrepo.add(notif);

        notif = new ArrayList<>();
        notif.add("requestRide");
        notif.add("van.panaite@gmail.com");
        notif.add("user@example.com");
        notifrepo.add(notif);

        Controller ctrl = new Controller(repo, notifrepo);

        TCPServer server = new TCPServer(ctrl,1234);
        new Thread(server).start();
    }
}
