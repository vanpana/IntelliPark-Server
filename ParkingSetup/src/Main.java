import javax.swing.*;
import java.awt.*;

public class Main {

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Setup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setPreferredSize(new Dimension(1920, 1080));
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);

        JLabel rowLabel = new JLabel("Number of rows:");
        JLabel columnLabel = new JLabel("Number of columns:");
        frame.add(rowLabel);
        frame.add(columnLabel);
        rowLabel.setBounds(20, 20, 120, 20);
        columnLabel.setBounds(20, 60, 120, 20);

        JTextField rowField = new JTextField();
        JTextField columnField = new JTextField();
        frame.add(rowField);
        frame.add(columnField);
        rowField.setBounds(160, 20, 40, 20);
        columnField.setBounds(160, 60, 40, 20);

        JButton okButton = new JButton("Generate");
        okButton.addActionListener((e) -> {
            int rows = Integer.parseInt(rowField.getText());
            int columns = Integer.parseInt(columnField.getText());

            CanvasPanel canvas = new CanvasPanel(rows, columns);
            frame.add(canvas);
            canvas.setBounds(300, 160, 1600, 900);
            canvas.setVisible(true);
            canvas.updateGraphics();
        });
        frame.add(okButton);
        okButton.setBounds(20, 100, 100, 20);

        frame.pack();
        frame.setVisible(true);
    }

    private static class CanvasPanel extends JPanel {
        private int rows;
        private int columns;

        CanvasPanel(int rows, int columns) {
            setPreferredSize(new Dimension(1600, 900));
            this.rows = rows;
            this.columns = columns;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            int x = 20;
            int y = 20;
            for (int i = 0; i < rows; ++i) {
                x = 20;
                g.fillRect(x, y, 40, 40);
                for (int j = 0; j < columns - 1; ++j) {
                    x += 50;
                    g.fillRect(x, y, 40, 40);
                }
                y += 50;
            }
        }

        void updateGraphics() {
            repaint();
        }
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}