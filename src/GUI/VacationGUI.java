package GUI;

import javax.swing.*;
import java.awt.*;

public class VacationGUI {
    VacationGUI() {
        JFrame frame = new JFrame("Edit person");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setPreferredSize(new Dimension(320, 240));

        JLabel vacationStartLabel = new JLabel("Start of vacation:");
        JLabel vacationEndLabel = new JLabel("End of vacation:");
        frame.add(vacationStartLabel);
        frame.add(vacationEndLabel);
        vacationStartLabel.setBounds(20, 20, 100, 20);
        vacationEndLabel.setBounds(20, 60, 100, 20);

        JTextField vacationStartTextField = new JTextField();
        JTextField vacationEndTextField = new JTextField();
        frame.add(vacationStartTextField);
        frame.add(vacationEndTextField);
        vacationStartTextField.setBounds(140, 60, 80, 20);
        vacationEndTextField.setBounds(140, 20, 80, 20);

        JButton okButton = new JButton("Ok");
        okButton.addActionListener((e) -> {
            // do something
            frame.dispose();
        });
        frame.add(okButton);
        okButton.setBounds(20, 100, 60, 20);

        frame.pack();
        frame.setVisible(true);
    }
}
