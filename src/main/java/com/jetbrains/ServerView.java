package com.jetbrains;

import com.jetbrains.Controller.Controller;
import com.jetbrains.Model.Employee;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


public class ServerView extends HorizontalLayout {
    private Controller ctrl;

    public ServerView(Controller ctrl){
        this.ctrl = ctrl;

        //Sidebar

        //List of people
        Grid<Employee> grid = new Grid<>();
        grid.setItems(ctrl.getAll());
        grid.addColumn(Employee::getName).setCaption("Name");
        grid.addColumn(Employee::getSurname).setCaption("Surname");
        grid.addColumn(Employee::getEmail).setCaption("email");

        //First Row of buttons
        HorizontalLayout firstRowBtn = new HorizontalLayout();
        Button addButton = new Button("Add employee");
        Button updButton = new Button("Update employee");
        Button delButton = new Button("Delete employee");

        firstRowBtn.addComponent(addButton);
        firstRowBtn.addComponent(updButton);
        firstRowBtn.addComponent(delButton);

        //Panel to keep everything centered
        final Panel srvSidePanel = new Panel("Server Side");
        this.addComponent(srvSidePanel);

        final FormLayout srvSideLayout = new FormLayout();
        srvSideLayout.setMargin(true);
        srvSideLayout.addComponent(grid);
        srvSideLayout.addComponent(firstRowBtn);

        srvSidePanel.setContent(srvSideLayout);
        srvSidePanel.setSizeFull();
        this.setSizeFull();
        this.setComponentAlignment(srvSidePanel, Alignment.MIDDLE_CENTER);
        srvSidePanel.setWidth(null);
    }

}

//
//public class ServerView extends VerticalLayout {
//    private Controller ctrl;
//    public ServerView(Controller ctrl){
//        this.ctrl = ctrl;
//
//        //Sidebar
//
//        //List of people
//        Grid<Employee> grid = new Grid<>();
//        grid.setItems(ctrl.getAll());
//        grid.addColumn(Employee::getName).setCaption("Name");
//        grid.addColumn(Employee::getSurname).setCaption("Surname");
//        grid.addColumn(Employee::getEmail).setCaption("email");
//
//        //First Row of buttons
//        HorizontalLayout firstRowBtn = new HorizontalLayout();
//        Button addButton = new Button("Add employee");
//        Button updButton = new Button("Update employee");
//        Button delButton = new Button("Delete employee");
//
//        firstRowBtn.addComponent(addButton);
//        firstRowBtn.addComponent(updButton);
//        firstRowBtn.addComponent(delButton);
//
//        //Panel to keep everything centered
//        final Panel srvSidePanel = new Panel("Server Side");
//        this.addComponent(srvSidePanel);
//
//        final FormLayout srvSideLayout = new FormLayout();
//        srvSideLayout.setMargin(true);
//        srvSideLayout.addComponent(grid);
//        srvSideLayout.addComponent(firstRowBtn);
//
//        srvSidePanel.setContent(srvSideLayout);
//        srvSidePanel.setSizeFull();
//        this.setSizeFull();
//        this.setComponentAlignment(srvSidePanel, Alignment.MIDDLE_CENTER);
//        srvSidePanel.setWidth(null);
//    }
//
//}
