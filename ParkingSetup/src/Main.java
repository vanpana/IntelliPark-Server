import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class Main {
    private static List<ParkingBlock> blocksList;

    private static void createAndShowGUI() {
        blocksList = new ArrayList<>();

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

        CanvasPanel canvas = new CanvasPanel(blocksList);
        frame.add(canvas);
        canvas.setBounds(300, 160, 1600, 900);
        canvas.setVisible(true);

        JButton okButton = new JButton("Ok");
        okButton.addActionListener((e) -> {
            int rows = Integer.parseInt(rowField.getText());
            int columns = Integer.parseInt(columnField.getText());

            blocksList = new ArrayList<>();
            int x;
            int y = 20;
            for (int i = 0; i < rows; ++i) {
                x = 20;
                for (int j = 0; j < columns; ++j) {
                    x += 21;
                    blocksList.add(new ParkingBlock(x, y));
                }
                y += 21;
            }

            canvas.setBlocksList(blocksList);
            canvas.updateGraphics();
        });
        frame.add(okButton);
        okButton.setBounds(20, 100, 100, 20);

        frame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                for (ParkingBlock block : blocksList) {
                    if (me.getX() >= block.getX() + 310 && me.getX() <= block.getX() + 330 && me.getY() >= block.getY() + 190 && me.getY() <= block.getY() + 210) {
                        if (SwingUtilities.isLeftMouseButton(me)) {
                            block.changeToFreeParking();
                        } else if (SwingUtilities.isRightMouseButton(me)) {
                            block.changeToOccupiedParking();
                        } else {
                            block.changeToStreet();
                        }
                    }
                }
                canvas.setBlocksList(blocksList);
                canvas.updateGraphics();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    private static class CanvasPanel extends JPanel {
        private List<ParkingBlock> blocksList;

        CanvasPanel(List<ParkingBlock> blocksList) {
            this.blocksList = blocksList;
        }

        void setBlocksList(List<ParkingBlock> blocksList) {
            this.blocksList = blocksList;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (ParkingBlock block : blocksList) {
                if (block.getType() == ParkingBlock.Blocks.STREET) {
                    g.setColor(Color.BLACK);
                } else if (block.getType() == ParkingBlock.Blocks.FREEPARKING) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.RED);
                }
                g.fillRect(block.getX(), block.getY(), 20, 20);
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