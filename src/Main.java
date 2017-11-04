import Controller.Controller;
import Model.Employee;
import Model.TCPServer;
import Repository.Repository;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++){
//            new Thread(new HelloThread(i)).start();
//        }
        Repository repo = new Repository("myparking.db");
//        Controller ctrl = new Controller(repo);
//        for (Employee e : ctrl.getParkingSpots())
//            System.out.println(e);

        TCPServer server = new TCPServer(repo,1234);
        new Thread(server).start();
        //server.run();
        System.out.println("da");
    }
}
