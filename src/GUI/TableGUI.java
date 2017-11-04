package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class TableGUI {
    private JTable table;
    private DefaultTableModel model;
    private List<String> listOfPeople = new ArrayList<>();

    private TableGUI() {
        listOfPeople.add("Eric Roca");
        listOfPeople.add("Norbert Herciu");
        listOfPeople.add("Dorinel Panaite");
        listOfPeople.add("Catalin Podariu");

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

        JButton addPersonButton = new JButton("Add person");
        addPersonButton.addActionListener((e) -> {
            new AddGUI();
            displayTable();
        });
        frame.add(addPersonButton);
        addPersonButton.setBounds(740, 720, 120, 20);

        JButton editPersonButton = new JButton("Edit person");
        editPersonButton.addActionListener((e) -> {
            new EditGUI();
            displayTable();
        });
        frame.add(editPersonButton);
        editPersonButton.setBounds(900, 720, 120, 20);

        JButton deletePersonButton = new JButton("Delete person");
        deletePersonButton.addActionListener((e) -> {
            //do something
        });
        frame.add(deletePersonButton);
        deletePersonButton.setBounds(1060, 720, 120, 20);

        JButton addVacationButton = new JButton("Add vacation");
        addVacationButton.addActionListener((e) -> {
            new VacationGUI();
        });
        frame.add(addVacationButton);
        addVacationButton.setBounds(740, 760, 120, 20);

        JButton startConnectionButton = new JButton("Start connection");
        startConnectionButton.setBounds(1060, 760, 160, 20);
        startConnectionButton.addActionListener((e) -> {
            //do something
        });
        frame.add(startConnectionButton);

        JLabel multiplierLabel = new JLabel("Multiplier:");
        frame.add(multiplierLabel);
        multiplierLabel.setBounds(740, 800, 60, 20);

        JTextField multiplierTextField = new JTextField();
        frame.add(multiplierTextField);
        multiplierTextField.setBounds(820, 800, 40, 20);

        displayTable();

        frame.pack();
        frame.setVisible(true);
    }

    private void displayTable() {
        String[] column = {"Name"};
        model = new DefaultTableModel(column, 0);
        table.setModel(model);

        for (String person : listOfPeople) {
            Object[] row = {person};
            model.addRow(row);
        }
    }

    public static void main(String[] args) {
        new TableGUI();
    }
}