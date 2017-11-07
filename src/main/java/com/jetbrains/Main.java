package com.jetbrains;


import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

import java.awt.*;

import static java.lang.Thread.sleep;


public class Main extends UI {
    @Override
    protected void init(VaadinRequest request) {
        System.out.println("Login ui enabling");
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        LoginUI loginui = new LoginUI();
        setContent(loginui);
    }

    //
    @WebServlet(urlPatterns = "/*", name = "Parking server", asyncSupported = true)
    @VaadinServletConfiguration(ui = Main.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
    }

//    public static void main(String[] args) {
//        File f = new File("parkingmatrix.txt");
//
//        if (!f.exists()){
//            Matrix mtx = new Matrix();
//            mtx.createAndShowGUI();
//            while(!f.exists()){
//                try{
//                    sleep(1);
//                }
//                catch (InterruptedException e){
//                    System.out.println(e.getMessage());
//                }
//            }
//        }
//
//

//
//        TableGUI gui = new TableGUI(ctrl);
//    }
}
