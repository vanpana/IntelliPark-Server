package View.GUI;

import javax.swing.*;
import java.awt.*;

public class EditGUI {
    EditGUI() {
        JFrame frame = new JFrame("Edit person");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setPreferredSize(new Dimension(640, 480));

        JLabel nameLabel = new JLabel("Name:");
        JLabel surnameLabel = new JLabel("Surname:");
        JLabel emailLabel = new JLabel("E-mail:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel carPlateLabel = new JLabel("Car plate:");
        JLabel dateLabel = new JLabel("Date:");
        JLabel multiplierLabel = new JLabel("Multiplier:");
        JLabel parkingSpotLabel = new JLabel("Parking spot:");
        JLabel isSharingLabel = new JLabel("Is sharing:");
        frame.add(nameLabel);
        frame.add(surnameLabel);
        frame.add(emailLabel);
        frame.add(passwordLabel);
        frame.add(carPlateLabel);
        frame.add(dateLabel);
        frame.add(multiplierLabel);
        frame.add(parkingSpotLabel);
        frame.add(isSharingLabel);
        nameLabel.setBounds(20, 20, 100, 20);
        surnameLabel.setBounds(20, 60, 100, 20);
        emailLabel.setBounds(20, 100, 100, 20);
        passwordLabel.setBounds(20, 140, 100, 20);
        carPlateLabel.setBounds(20, 180, 100, 20);
        dateLabel.setBounds(20, 220, 100, 20);
        multiplierLabel.setBounds(20, 260, 100, 20);
        parkingSpotLabel.setBounds(20, 300, 100, 20);
        isSharingLabel.setBounds(20, 340, 100, 20);

        JTextField nameTextField = new JTextField();
        JTextField surnameTextField = new JTextField();
        JTextField emailTextField = new JTextField();
        JTextField passwordTextField = new JTextField();
        JTextField carPlateTextField = new JTextField();
        JTextField dateTextField = new JTextField();
        JTextField multiplierTextField = new JTextField();
        JTextField parkingSpotTextField = new JTextField();
        JTextField isSharingTextField = new JTextField();
        frame.add(nameTextField);
        frame.add(surnameTextField);
        frame.add(emailTextField);
        frame.add(passwordTextField);
        frame.add(carPlateTextField);
        frame.add(dateTextField);
        frame.add(multiplierTextField);
        frame.add(parkingSpotTextField);
        frame.add(isSharingTextField);
        nameTextField.setBounds(120, 20, 100, 20);
        surnameTextField.setBounds(120, 60, 100, 20);
        emailTextField.setBounds(120, 100, 100, 20);
        passwordTextField.setBounds(120, 140, 100, 20);
        carPlateTextField.setBounds(120, 180, 100, 20);
        dateTextField.setBounds(120, 220, 100, 20);
        multiplierTextField.setBounds(120, 260, 100, 20);
        parkingSpotTextField.setBounds(120, 300, 100, 20);
        isSharingTextField.setBounds(120, 340, 100, 20);

        JButton okButton = new JButton("Ok");
        okButton.addActionListener((e) -> {
            // do something
            frame.dispose();
        });
        frame.add(okButton);
        okButton.setBounds(20, 380, 60, 20);

        frame.pack();
        frame.setVisible(true);
    }
}
