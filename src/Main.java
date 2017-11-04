import Controller.Controller;
import Model.Employee;
import Model.TCPServer;
import Repository.Repository;
import View.Matrix;

import java.io.File;

import static java.lang.Thread.sleep;


public class Main {
    public static boolean file_exists = false;

    public static void main(String[] args) {

        File f = new File("parkingmatrix.txt");

        if (!f.exists()){
            Matrix mtx = new Matrix();
            mtx.createAndShowGUI();
            while(!f.exists()){
                try{
                    sleep(1);}
                catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        Repository repo = new Repository("myparking.db");
        repo.add(repo.getEmployee("van.panaite@gmail.com"));
//        Controller ctrl = new Controller(repo);
//        for (Employee e : ctrl.getParkingSpots())
//            System.out.println(e);

        TCPServer server = new TCPServer(repo,1234);
        new Thread(server).start();


//        for (int i = 0; i < 10; i++){
//            new Thread(new HelloThread(i)).start();
//        }

        //server.run();

    }
}
