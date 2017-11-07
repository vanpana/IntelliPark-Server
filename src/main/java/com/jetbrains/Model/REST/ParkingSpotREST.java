package com.jetbrains.Model.REST;

import com.jetbrains.Controller.Controller;
import com.jetbrains.Model.Employee;
import com.jetbrains.Repository.CarPoolRepository;
import com.jetbrains.Repository.NotificationRepository;
import com.jetbrains.Repository.Repository;
import com.jetbrains.Repository.VacationRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
