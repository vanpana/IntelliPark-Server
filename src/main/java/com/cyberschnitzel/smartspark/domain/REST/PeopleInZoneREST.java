package com.cyberschnitzel.smartspark.domain.REST;

import com.cyberschnitzel.smartspark.Controller.Controller;
import com.cyberschnitzel.smartspark.Repository.CarPoolRepository;
import com.cyberschnitzel.smartspark.Repository.NotificationRepository;
import com.cyberschnitzel.smartspark.Repository.Repository;
import com.cyberschnitzel.smartspark.Repository.VacationRepository;
import com.cyberschnitzel.smartspark.domain.Entities.Employee;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getPeopleInZone")
public class PeopleInZoneREST {
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
            ArrayList<Employee> peopleInZone = ctrl.getParkingSpotsFromZone(ctrl.getEmployee(email).getNeighbourhood());
            int counter = 0;
            for (Employee emp : peopleInZone){
                output = output  +  "[" + emp.getName() + " " + emp.getSurname() + "]";
                if (counter != peopleInZone.size() - 1)
                    output = output + ",";
                counter++;
            }

            output += "]";
        }
        else output = "[bad login]";

        return output;
    }
}
