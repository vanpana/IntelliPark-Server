package com.jetbrains.Repository;

import com.jetbrains.Model.Vacation;

import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VacationRepository {
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
            System.out.println("Error closing Vacation connection");
        }
    }

    public VacationRepository(String filename) {
        this.filename = filename;
    }

    public void add(Vacation v){
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        try{
            connectDB();

            String query =  "INSERT INTO Vacation " +
                    String.format("VALUES (\'%s\',\'%s\',%d)",
                            format.format(v.getStart_date()),
                            format.format(v.getEnd_date()),
                            v.getEmployee_id());

            stmt.executeUpdate(query);

            disconnectDB();

        }
        catch (SQLException ex){
            disconnectDB();
            System.out.print("Vacation SQL: ");
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Vacation> getVacations(ResultSet rs){
        ArrayList<Vacation> items = new ArrayList<>();

        try
        {
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            while (rs.next()) {
                String start_date = rs.getString("start_date");
                String end_date = rs.getString("end_date");
                int id = rs.getInt("employee_id");

                try{
                    items.add(new Vacation(df.parse(start_date), df.parse(end_date), id));
                }
                catch (ParseException pe){
                    System.out.println(pe.getMessage());
                }

            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return items;
    }

    public Vacation getVacationById(int id){
        Vacation vac = null;
        try{
            connectDB();

            String query = "SELECT * FROM Vacation WHERE employee_id = " + String.valueOf(id);
            ResultSet rs = stmt.executeQuery(query);
            vac = getVacations(rs).get(0);

            rs.close();
            disconnectDB();
        }
        catch (SQLException exc){
            disconnectDB();
            System.out.println(exc.getMessage());
        }
        return vac;
    }

    public ArrayList<Vacation> getAllVacations(){
        ArrayList<Vacation> items = new ArrayList<>();
        try{
            connectDB();

            String query = "SELECT * FROM Vacation";
            ResultSet rs = stmt.executeQuery(query);
            items = getVacations(rs);

            rs.close();
            disconnectDB();
        }
        catch (SQLException exc){
            disconnectDB();
            System.out.println(exc.getMessage());
        }
        return items;
    }
}
