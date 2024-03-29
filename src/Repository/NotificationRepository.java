package Repository;

import Model.Employee;
import Model.Notification;
import Model.Vacation;

import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/*
"rideRequested",driveremail,email"
"rideAccepted",email,driveremail"
"rideDenied,email,driveremail"
 */

public class NotificationRepository {
    private String filename;
    private Connection conn = null;
    private Statement stmt = null;

    private void connectDB(){
        File f = new File(filename);
        if(f.exists() && !f.isDirectory()) {
            try {
                Class.forName("org.sqlite.JDBC");
                //conn = DriverManager.getConnection("jdbc:sqlite:" + this.filename);
                conn = DriverManager.getConnection("jdbc:sqlite:/Users/vanpana/Documents/IntelliJ/IntelliPark-Server/myparking.db");
                conn.setAutoCommit(false);
                stmt = conn.createStatement();
            } catch (SQLException |ClassNotFoundException e) {
                System.out.print("Vacation SQL: ");
                System.out.println(e.getMessage());
            }
        }
        else System.out.println("File does not exist");
    }

    private void disconnectDB(){
        try{
            if (!conn.isClosed()) {
                conn.commit();
                conn.close();
            }
        }
        catch (SQLException e){
            System.out.println("Error closing Notification connection");
        }
    }

    public NotificationRepository(String filename) {
        this.filename = filename;

    }

    public boolean idExists(int id){
        for (Notification n : getAllNotifications()){
            if (n.getId() == id) return false;
        }
        return true;
    }

    public void add(ArrayList<String> params){
        add(params.get(0), params.get(1), params.get(2));
    }

    public void add(String notification, String toWho, String fromWhom){
        int id = ThreadLocalRandom.current().nextInt(0, 2000);
        add(new Notification(id, notification, toWho, fromWhom));
    }

    public void add(Notification n){
        try{
            connectDB();

            String query =  "INSERT INTO Notification " +
                    String.format("VALUES (%d, \'%s\',\'%s\',\'%s\')",
                            n.getId(),
                            n.getNotification(),
                            n.getToWho(),
                            n.getFromWhom());

            System.out.println(query);
            stmt.executeUpdate(query);

            disconnectDB();

        }
        catch (SQLException ex){
            disconnectDB();
            System.out.print("Notification SQL: ");
            System.out.println(ex.getMessage());
        }
    }

    public void del(int id){
        try{
            connectDB();
            String query =  "DELETE FROM Notification WHERE id = " +
                    String.valueOf(id);

            stmt.execute(query);

            disconnectDB();

        }
        catch (SQLException ex){
            disconnectDB();
            System.out.print("Notification SQL: ");
            System.out.println(ex.getMessage());
        }
    }

    private ArrayList<Notification> getNotifications(ResultSet rs){
        ArrayList<Notification> items = new ArrayList<>();

        try
        {
            while (rs.next()) {
                int id = rs.getInt("id");
                String notification = rs.getString("notification");
                String toWho = rs.getString("toWho");
                String fromWhom = rs.getString("fromWhom");

                items.add(createNotificationFromData(id, notification, toWho, fromWhom));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return items;
    }

    private Notification createNotificationFromData(int id, String notification, String toWho, String fromWhom){
        return new Notification(id, notification, toWho, fromWhom);
    }

    public Notification getNotification(int id){
        for (Notification notif : getAllNotifications()){
            if (notif.getId() == id) return notif;
        }
        return null;
    }

    public ArrayList<Notification> getAllNotifications(){
        ArrayList<Notification> items = new ArrayList<>();
        try{
            connectDB();

            String query = "SELECT * FROM Notification";
            ResultSet rs = stmt.executeQuery(query);
            items = getNotifications(rs);

            rs.close();
            disconnectDB();
        }
        catch (SQLException exc){
            disconnectDB();
            System.out.println(exc.getMessage());
        }
        return items;
    }

    public ArrayList<ArrayList<String>> getAll(){
        ArrayList<Notification> items = new ArrayList<>();
        ArrayList<ArrayList<String>> notifications = new ArrayList<>();
        try{
            connectDB();

            String query = "SELECT * FROM Notification";
            ResultSet rs = stmt.executeQuery(query);
            items = getNotifications(rs);

            for (Notification notif : items){
                ArrayList<String> temp = new ArrayList<>();
                temp.add(notif.getNotification());
                temp.add(String.valueOf(notif.getId()));

                if (notif.getNotification().equals("isVacation")){
                    DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                    temp.add(df.format(new VacationRepository("myparking.db").getVacationById(
                            new Repository("myparking.db").getEmployee(notif.getFromWhom()).getId()).getStart_date()));
                    temp.add(df.format(new VacationRepository("myparking.db").getVacationById(
                            new Repository("myparking.db").getEmployee(notif.getFromWhom()).getId()).getEnd_date()));
                }
                else{
                    temp.add(new Repository("myparking.db").getEmployee(notif.getFromWhom()).getName());
                    temp.add(new Repository("myparking.db").getEmployee(notif.getFromWhom()).getSurname());
                }
//                temp.add(notif.getToWho());
//                temp.add(notif.getFromWhom());
                notifications.add(temp);
            }
            rs.close();

            disconnectDB();
        }
        catch (SQLException exc){
            disconnectDB();
            System.out.println(exc.getMessage());
        }
        return notifications;
    }

}
