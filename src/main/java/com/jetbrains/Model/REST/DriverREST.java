package com.jetbrains.Model.REST;

import com.jetbrains.Controller.Controller;
import com.jetbrains.Model.Employee;
import com.jetbrains.Repository.CarPoolRepository;
import com.jetbrains.Repository.NotificationRepository;
import com.jetbrains.Repository.Repository;
import com.jetbrains.Repository.VacationRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getDriver")
public class DriverREST {
    @GET
    @Path("/{email},{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMsg(@PathParam("email") String email, @PathParam("password") String password) {
        String output = "[";

        Controller ctrl = new Controller(new Repository("resources/myparking.db"),
                new NotificationRepository("resources/myparking.db"),
                new VacationRepository("resources/myparking.db"),
                new CarPoolRepository("resources/myparking.db"));

        if (ctrl.checkLogin(email, password)) {
            int driver_id = ctrl.getCarPoolDriverId(ctrl.getEmployee(email).getId());
            if (driver_id == -1) output += "none";
            else {
                Employee found = ctrl.getEmployee(driver_id);

                if (found == null) output += "none";
                else
                    output += found.getName() + " " + found.getSurname();
                output += "]";
            }
        }
        else output = "[bad login]";

        return output;

    }
}
