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
import java.util.ArrayList;

@Path("/getNotifications")
public class NotificationREST {
    @GET
    @Path("/{email},{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMsg(@PathParam("email") String email, @PathParam("password") String password) {
        String output = "[";

        Controller ctrl = new Controller(new Repository("resources/myparking.db"),
                new NotificationRepository("resources/myparking.db"),
                new VacationRepository("resources/myparking.db"),
                new CarPoolRepository("resources/myparking.db"));

        if (ctrl.checkLogin(email, password))
            for (ArrayList<String> al : ctrl.getNotifications(email)) output += al;
        else output = "[bad login]";

        output += "]";

        return output;
    }
}