package View.GUI;

import Controller.*;
import Model.Employee;
import Model.TCPServer;
import View.MaterialUI.GUITheme;
import View.MaterialUI.MaterialButton;
import View.MaterialUI.MaterialLookAndFeel;
import javafx.animation.Animation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class TableGUI {
    private JTable table;
    private DefaultTableModel model;
    private List<String> listOfPeople = new ArrayList<>();
    private Controller ctrl;
    private TCPServer server;

    public TableGUI(Controller ctrl) {
        MaterialLookAndFeel ui = new MaterialLookAndFeel (GUITheme.LIGHT_THEME);
        try {
            UIManager.setLookAndFeel (ui.getName());
        }
        catch (UnsupportedLookAndFeelException|ClassNotFoundException|InstantiationException|IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        this.ctrl = ctrl;

        JFrame frame = new JFrame("Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setPreferredSize(new Dimension(1920, 1080));
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);

        String[] column = {"Name"};
        model = new DefaultTableModel(column, 0);
        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };



        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(200, 100, 1520, 580);
        pane.setVisible(true);
        frame.add(pane);

        JButton addPersonButton = new MaterialButton("Add person");
        addPersonButton.addActionListener((e) -> {
            new AddGUI(ctrl, this);
            displayTable();
        });
        frame.add(addPersonButton);
        addPersonButton.setBounds(740, 720, 120, 20);

        JButton editPersonButton = new MaterialButton("Edit person");
        editPersonButton.addActionListener((e) -> {
            new EditGUI();
            displayTable();
        });
        frame.add(editPersonButton);
        editPersonButton.setBounds(900, 720, 120, 20);

        JButton deletePersonButton = new MaterialButton("Delete person");
        deletePersonButton.addActionListener((e) -> {
            //TODO: Delete
        });
        frame.add(deletePersonButton);
        deletePersonButton.setBounds(1060, 720, 120, 20);

        JButton addVacationButton = new MaterialButton("Add vacation");
        addVacationButton.addActionListener((e) -> {
            new VacationGUI(ctrl);
        });
        frame.add(addVacationButton);
        addVacationButton.setBounds(740, 760, 120, 20);

        JButton startConnectionButton = new MaterialButton("Start connection");
        JButton stopConnectionButton = new MaterialButton("Stop connection");
        stopConnectionButton.setVisible(false);
        stopConnectionButton.setEnabled(false);

        startConnectionButton.setBounds(1060, 760, 160, 20);
        stopConnectionButton.setBounds(1060, 800, 160, 20);
        startConnectionButton.addActionListener((e) -> {
            System.out.println("Starting server...");
            server = new TCPServer(ctrl,1234);
            new Thread(server).start();

            startConnectionButton.setVisible(false);
            startConnectionButton.setEnabled(false);

            stopConnectionButton.setVisible(true);
            stopConnectionButton.setEnabled(true);
        });
        frame.add(startConnectionButton);


        stopConnectionButton.addActionListener((e) -> {
            System.out.println("Stopping server...");
            server.close();
            startConnectionButton.setVisible(true);
            startConnectionButton.setEnabled(true);

            stopConnectionButton.setVisible(false);
            stopConnectionButton.setEnabled(false);
        });
        frame.add(stopConnectionButton);

        JLabel multiplierLabel = new JLabel("Multiplier:");
        frame.add(multiplierLabel);
        multiplierLabel.setBounds(740, 800, 60, 20);

        JTextField multiplierTextField = new JTextField();
        frame.add(multiplierTextField);
        multiplierTextField.setBounds(820, 800, 40, 20);

        displayTable();

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0) {
                    String email = table.getModel().getValueAt(row, 2).toString();
                    multiplierTextField.setText(String.valueOf(ctrl.getEmployee(email).getMultiplier()));

                }
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    public void displayTable() {
        String[] column = {"Name","Surname","E-Mail"};
        model = new DefaultTableModel(column, 0);
        table.setModel(model);

        for (Employee emp : ctrl.getAll()) {
            Object[] row = {emp.getName(), emp.getSurname(), emp.getEmail()};
            model.addRow(row);
        }
    }

//    public static void main(String[] args) {
//        new TableGUI();
//    }
}