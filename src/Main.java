import Controller.Controller;
import Model.Employee;
import Model.TCPServer;
import Repository.*;
import View.GUI.TableGUI;
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
        repo.add(repo.getEmployee("van.panaite@gmail.com"));

        NotificationRepository notifrepo = new NotificationRepository("myparking.db");
        VacationRepository vacrepo = new VacationRepository("myparking.db");



        Controller ctrl = new Controller(repo, notifrepo, vacrepo);

        TableGUI gui = new TableGUI(ctrl);
    }
}
