package Repository;

import Model.Employee;

import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class Repository {
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
            System.out.println("Error closing Repository connection");
        }
    }

    public Repository(String filename) {
        this.filename = filename;
    }

    public void add(Employee e)
    {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        try{
            connectDB();

            String query =  "INSERT INTO Employee " +
                    String.format("VALUES (%d,\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',%f,%d,%d)",
                            e.getId(),
                            e.getName(),
                            e.getSurname(),
                            e.getEmail(),
                            e.getPassword(),
                            e.getCar_plate(),
                            e.getNeighbourhood(),
                            format.format(e.getEmploy_date()),
                            e.getMultiplier(),
                            e.getParking_spot(),
                            (e.isIs_sharing()) ? 1 : 0);
            stmt.executeUpdate(query);

            disconnectDB();

        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }


    }

    private ArrayList<Employee> getEmployees(ResultSet rs){
        ArrayList<Employee> items = new ArrayList<>();

        try
        {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String car_plate = rs.getString("carplate");
                String neighbourhood = rs.getString("neighbourhood");
                String tempdate = rs.getString("date");
                // new Date(Integer.parseInt(tempdate[2]), Integer.parseInt(tempdate[1]), Integer.parseInt(tempdate[0]));
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
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

                items.add(createEmployeeFromData(id, name, surname, email, password, car_plate, neighbourhood, date, multiplier,
                        parking_spot, is_sharing));

            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return items;
    }

    private Employee getEmployee(ResultSet rs){
        return getEmployees(rs).get(0);
    }

    private Employee createEmployeeFromData(int id, String name, String surname, String email, String password,
                                           String car_plate, String neighbourhood, Date employ_date,  float multiplier, int parking_spot,
                                           boolean is_sharing){
        return new Employee(id, name, surname, email, password, car_plate, neighbourhood, employ_date, multiplier, parking_spot, is_sharing);
    }

    public ArrayList<Employee> getAll(){
        ArrayList<Employee> items = new ArrayList<>();
        try{
            connectDB();

            String query = "SELECT * FROM Employee";
            ResultSet rs = stmt.executeQuery(query);
            items = getEmployees(rs);
            rs.close();

            disconnectDB();
        }
        catch (SQLException exc){
            System.out.println(exc.getMessage());
        }
        return items;
    }

    public Employee getEmployee(String email){
        Employee e = null;

        try{
            connectDB();

            String query = "SELECT * FROM Employee WHERE email = \'" + email + "\'";
            ResultSet rs = stmt.executeQuery(query);
            e = getEmployee(rs);

            rs.close();

            disconnectDB();
        }
        catch (SQLException exc){
            System.out.println(exc.getMessage());
        }

        return e;
    }


}
