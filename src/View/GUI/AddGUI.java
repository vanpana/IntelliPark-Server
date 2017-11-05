package View.GUI;

import Controller.Controller;
import View.MaterialUI.GUITheme;
import View.MaterialUI.MaterialButton;
import View.MaterialUI.MaterialLookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddGUI {
    Controller ctrl;
    TableGUI tg;
    AddGUI(Controller ctrl, TableGUI tg) {
        this.ctrl = ctrl;
        this.tg = tg;
        MaterialLookAndFeel ui = new MaterialLookAndFeel (GUITheme.LIGHT_THEME);
        JFrame frame = new JFrame("Add person");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setPreferredSize(new Dimension(640, 600));

        JLabel idLabel = new JLabel("ID:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel surnameLabel = new JLabel("Surname:");
        JLabel emailLabel = new JLabel("E-mail:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel carPlateLabel = new JLabel("Car plate:");
        JLabel cartierLabel = new JLabel("Cartier:");
        JLabel dateLabel = new JLabel("Date:");
        JLabel multiplierLabel = new JLabel("Multiplier:");
        JLabel parkingSpotLabel = new JLabel("Parking spot:");
        JLabel isSharingLabel = new JLabel("Is sharing:");
        JLabel familyLabel = new JLabel("Family with:");
        frame.add(idLabel);
        frame.add(nameLabel);
        frame.add(surnameLabel);
        frame.add(emailLabel);
        frame.add(passwordLabel);
        frame.add(carPlateLabel);
        frame.add(cartierLabel);
        frame.add(dateLabel);
        frame.add(multiplierLabel);
        frame.add(parkingSpotLabel);
        frame.add(isSharingLabel);
        frame.add(familyLabel);
        idLabel.setBounds(20, 20, 100, 20); //TODO
        nameLabel.setBounds(20, 60, 100, 20);
        surnameLabel.setBounds(20, 100, 100, 20);
        emailLabel.setBounds(20, 140, 100, 20);
        passwordLabel.setBounds(20, 180, 100, 20);
        carPlateLabel.setBounds(20, 220, 100, 20);
        cartierLabel.setBounds(20, 260, 100, 20);
        dateLabel.setBounds(20, 300, 100, 20);
        multiplierLabel.setBounds(20, 340, 100, 20);
        parkingSpotLabel.setBounds(20, 380, 100, 20);
        isSharingLabel.setBounds(20, 420, 100, 20);
        familyLabel.setBounds(20, 460, 100, 20);

        JTextField idTextField = new JTextField();
        JTextField nameTextField = new JTextField();
        JTextField surnameTextField = new JTextField();
        JTextField emailTextField = new JTextField();
        JTextField passwordTextField = new JTextField();
        JTextField carPlateTextField = new JTextField();
        JTextField cartierTextField = new JTextField();
        JTextField dateTextField = new JTextField();
        JTextField multiplierTextField = new JTextField();
        JTextField parkingSpotTextField = new JTextField();
        JTextField isSharingTextField = new JTextField();
        JTextField familyTextField = new JTextField();
        frame.add(idTextField);
        frame.add(nameTextField);
        frame.add(surnameTextField);
        frame.add(emailTextField);
        frame.add(passwordTextField);
        frame.add(carPlateTextField);
        frame.add(cartierTextField);
        frame.add(dateTextField);
        frame.add(multiplierTextField);
        frame.add(parkingSpotTextField);
        frame.add(isSharingTextField);
        frame.add(familyTextField);
        idTextField.setBounds(120, 20, 100, 20); //TODO
        nameTextField.setBounds(120, 60, 100, 20);
        surnameTextField.setBounds(120, 100, 100, 20);
        emailTextField.setBounds(120, 140, 100, 20);
        passwordTextField.setBounds(120, 180, 100, 20);
        carPlateTextField.setBounds(120, 220, 100, 20);
        cartierTextField.setBounds(120, 260, 100, 20);
        dateTextField.setBounds(120, 300, 100, 20);
        multiplierTextField.setBounds(120, 340, 100, 20);
        parkingSpotTextField.setBounds(120, 380, 100, 20);
        isSharingTextField.setBounds(120, 420, 100, 20);
        familyTextField.setBounds(120, 460, 100, 20);

        JButton okButton = new MaterialButton("Ok");
        okButton.addActionListener((e) -> {
//            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//            try {
//                Date date = df.parse(dateTextField.getText());
//                ctrl.addEmployee(
//                        Integer.parseInt(idTextField.getText()),
//                        nameTextField.getText(),
//                        surnameTextField.getText(),
//                        emailTextField.getText(),
//                        passwordTextField.getText(),
//                        carPlateTextField.getText(),
//                        cartierTextField.getText(),
//                        date,
//                        1.0,
//                        false,
//                        0);
//                tg.displayTable();
//                frame.dispose();
//            }
//            catch (ParseException pe){
//                System.out.println(pe);
//            }

        });
        frame.add(okButton);
        okButton.setBounds(20, 500, 60, 20);

        frame.pack();
        frame.setVisible(true);
    }

}