package View.GUI;

import View.MaterialUI.MaterialButton;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Controller.*;

public class VacationGUI {
    private Controller ctrl;

    VacationGUI(Controller ctrl) {
        this.ctrl = ctrl;
        JFrame frame = new JFrame("Edit person");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setPreferredSize(new Dimension(320, 240));

        JLabel vacationStartLabel = new JLabel("Start of vacation:");
        JLabel vacationEndLabel = new JLabel("End of vacation:");
        JLabel vacationPersonLabel = new JLabel("Employee ID:");

        frame.add(vacationStartLabel);
        frame.add(vacationEndLabel);
        frame.add(vacationPersonLabel);
        vacationStartLabel.setBounds(20, 20, 100, 20);
        vacationEndLabel.setBounds(20, 60, 100, 20);
        vacationPersonLabel.setBounds(20, 100, 100, 20);

        JTextField vacationStartTextField = new JTextField();
        JTextField vacationEndTextField = new JTextField();
        JTextField vacationPersonTextField = new JTextField();
        frame.add(vacationStartTextField);
        frame.add(vacationEndTextField);
        frame.add(vacationPersonTextField);
        vacationStartTextField.setBounds(140, 60, 80, 20);
        vacationEndTextField.setBounds(140, 20, 80, 20);
        vacationPersonTextField.setBounds(140, 100, 80, 20);

        JButton okButton = new MaterialButton("Ok");
        okButton.addActionListener((e) -> {
            DateFormat todayFormat = new SimpleDateFormat("dd.MM.yyyy");
            try{
                Date start = todayFormat.parse(vacationStartTextField.getText());
                Date end = todayFormat.parse(vacationEndTextField.getText());
                int id = Integer.parseInt(vacationPersonTextField.getText());

                ctrl.addVacation(start, end, id);
                ArrayList<String> vacNotif = new ArrayList<>();
                vacNotif.add("isVacation");
                vacNotif.add(ctrl.getEmployee(id).getEmail());
                vacNotif.add(ctrl.getEmployee(id).getEmail());
                ctrl.addNotification(vacNotif);
            }
            catch (ParseException pe){
                System.out.println(pe.getMessage());
            }
            frame.dispose();
        });
        frame.add(okButton);
        okButton.setBounds(20, 140, 60, 20);

        frame.pack();
        frame.setVisible(true);
    }
}
