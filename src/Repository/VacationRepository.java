package Repository;

import Model.Vacation;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class VacationRepository {
    private String filename;
    private Connection conn = null;
    private Statement stmt = null;

    public VacationRepository(String filename) {
        this.filename = filename;
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

    public void add(Vacation v){
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        try{
            String query =  "INSERT INTO Vacation " +
                    String.format("VALUES (%d, \'%s\',\'%s\',%d",
                            v.getId(),
                            format.format(v.getStart_date()),
                            format.format(v.getEnd_date()),
                            v.getEmployee_id());

            stmt.executeUpdate(query);

            conn.commit();

        }
        catch (SQLException ex){
            System.out.print("Vacation SQL: ");
            System.out.println(ex.getMessage());
        }
    }
}
