package com.cyberschnitzel.smartspark.Repository;



import com.cyberschnitzel.smartspark.domain.Entities.Carpool;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class CarPoolRepository {
    private String filename;
    private Connection conn = null;
    private Statement stmt = null;

    private void connectDB(){
        File f = new File(filename);
        if(f.exists() && !f.isDirectory()) {
            try {
                Class.forName("org.sqlite.JDBC");
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
            System.out.println("Error closing Carpooling connection");
        }
    }

    public CarPoolRepository(String filename) {
        this.filename = filename;
    }

    public void add(Carpool cp){
        try{
            connectDB();

            String query =  "INSERT INTO Carpooling " +
                    String.format("VALUES (%d, %d)",
                            cp.getDriver_id(),
                            cp.getPassenger_id());

            stmt.execute(query);

            disconnectDB();

        }
        catch (SQLException ex){
            disconnectDB();
            System.out.print("CarPool SQL: ");
            System.out.println(ex.getMessage());
        }
    }

    private ArrayList<Carpool> getCarpools(ResultSet rs){
        ArrayList<Carpool> items = new ArrayList<>();

        try
        {
            while (rs.next()) {
                int driver_id = rs.getInt("driver_id");
                int passenger_id = rs.getInt("passenger_id");

                items.add(new Carpool(driver_id, passenger_id));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return items;
    }

    public int getCarPoolDriverID(int id){
        for (Carpool cp : getAll()){
            if (cp.getPassenger_id() == id) return cp.getDriver_id();
        }
        return -1;
    }

    public ArrayList<Carpool> getAll(){
        ArrayList<Carpool> items = new ArrayList<>();
        try{
            connectDB();

            String query = "SELECT * FROM Carpooling";
            ResultSet rs = stmt.executeQuery(query);
            items = getCarpools(rs);
            rs.close();

            disconnectDB();
        }
        catch (SQLException exc){
            disconnectDB();
            System.out.println(exc.getMessage());
        }
        System.out.println(items);
        return items;
    }

}
