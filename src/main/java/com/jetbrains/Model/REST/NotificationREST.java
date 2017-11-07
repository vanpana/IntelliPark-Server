package com.jetbrains.Model.REST;

import com.jetbrains.Controller.Controller;
import com.jetbrains.Repository.CarPoolRepository;
import com.jetbrains.Repository.NotificationRepository;
import com.jetbrains.Repository.Repository;
import com.jetbrains.Repository.VacationRepository;

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