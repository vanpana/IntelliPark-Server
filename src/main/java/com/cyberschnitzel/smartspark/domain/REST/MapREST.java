package com.cyberschnitzel.smartspark.domain.REST;



import com.cyberschnitzel.smartspark.Controller.Controller;
import com.cyberschnitzel.smartspark.Repository.CarPoolRepository;
import com.cyberschnitzel.smartspark.Repository.NotificationRepository;
import com.cyberschnitzel.smartspark.Repository.Repository;
import com.cyberschnitzel.smartspark.Repository.VacationRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Path("/getMap")
public class MapREST {
    @GET
    @Path("/{email},{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMsg(@PathParam("email") String email, @PathParam("password") String password) {
        //TODO: repetitive code here, maybe replace with a new class
        String output = "[";

        Controller ctrl = new Controller(new Repository("resources/myparking.db"),
                new NotificationRepository("resources/myparking.db"),
                new VacationRepository("resources/myparking.db"),
                new CarPoolRepository("resources/myparking.db"));

        if (ctrl.checkLogin(email, password)){
            try {
                BufferedReader br = new BufferedReader(new FileReader("parkingmatrix.txt"));

                String line = br.readLine();
                while (line != null) {
                    output = output + "[" + line + "]";
                    line = br.readLine();
                }
                br.close();
            }
            catch (IOException ioe){
                System.out.println("Map request exception: " + ioe.getMessage());
                return "[request error, try again]";
            }

        }
        else output = "[bad login]";

        output += "]";

        return output;
    }
}
