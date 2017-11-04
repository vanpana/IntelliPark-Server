package Repository;

import Model.Employee;

import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Repository {
    private String filename;
    private Connection conn = null;
    private Statement stmt = null;

    public Repository(String filename) {
        this.filename = filename;
        File f = new File(filename);
        if(f.exists() && !f.isDirectory()) {
            // do something

            try {
                Class.forName("org.sqlite.JDBC");
                //conn = DriverManager.getConnection("jdbc:sqlite:" + this.filename);
                conn = DriverManager.getConnection("jdbc:sqlite:/Users/vanpana/Documents/IntelliJ/IntelliPark-Server/myparking.db");
                conn.setAutoCommit(false);
                stmt = conn.createStatement();
            } catch (SQLException|ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        else System.out.println("File does not exist");
    }

    private Employee getEmployee(ResultSet rs){
        try{
            //TODO: Wtf is dis while
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String car_plate = rs.getString("carplate");
                String tempdate = rs.getString("date");
                // new Date(Integer.parseInt(tempdate[2]), Integer.parseInt(tempdate[1]), Integer.parseInt(tempdate[0]));
                DateFormat format = new SimpleDateFormat("dd.MM.YYYY", Locale.ENGLISH);
                Date date = new Date();
                try {
                    date = format.parse(tempdate);
                }
                catch (ParseException e){
                    System.out.println(e.getMessage());
                }
                float multiplier = rs.getFloat("multiplier");
                int parking_spot = rs.getInt("parkingspot");
                int tempshare = rs.getInt("issharing");
                boolean is_sharing = false;
                if (tempshare == 1) is_sharing = true;

                return new Employee(id, name, surname, email, password, car_plate, date, multiplier, parking_spot, is_sharing);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Employee getEmployee(String email){
        Employee e = null;

        try{
            String query = "SELECT * FROM Employee WHERE email = \'" + email + "\'";
            ResultSet rs = stmt.executeQuery(query);
            e = getEmployee(rs);

            rs.close();
        }
        catch (SQLException exc){
            System.out.println(exc.getMessage());
        }

        return e;
    }

    public void close(){
        try{
            stmt.close();
            conn.commit();
            conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
