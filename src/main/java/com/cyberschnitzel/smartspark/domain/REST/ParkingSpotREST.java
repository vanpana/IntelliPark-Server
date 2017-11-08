package com.cyberschnitzel.smartspark.domain.REST;

import com.cyberschnitzel.smartspark.Controller.Controller;
import com.cyberschnitzel.smartspark.Repository.CarPoolRepository;
import com.cyberschnitzel.smartspark.Repository.NotificationRepository;
import com.cyberschnitzel.smartspark.Repository.Repository;
import com.cyberschnitzel.smartspark.Repository.VacationRepository;
import com.cyberschnitzel.smartspark.domain.Entities.Employee;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/getParkingSpot")
public class ParkingSpotREST
{
    @GET
    @Path("/{email},{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMsg(@PathParam("email") String email, @PathParam("password") String password) {
        String output;

        Controller ctrl = new Controller(new Repository("resources/myparking.db"),
                new NotificationRepository("resources/myparking.db"),
                new VacationRepository("resources/myparking.db"),
                new CarPoolRepository("resources/myparking.db"));

        if (ctrl.checkLogin(email, password)) {
            int counter = 0;
            for (Employee emp : ctrl.getParkingSpots()) {
                if (emp.getEmail().equals(email))
                    break;
                counter++;
            }

            output = "[" + counter + "]";
        }
        else output = "[bad login]";

        return output;

    }
}
