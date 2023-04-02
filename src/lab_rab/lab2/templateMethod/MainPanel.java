package lab_rab.lab2.templateMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Vector;

public class MainPanel extends JPanel {
    private ControlPanel controlPanel = new ControlPanel();
    private DrawCanvas canvas = new DrawCanvas();
    private int width = 600;
    private int height = 600;
    private Vector<BouncingShape> shapeList = new Vector<>();

    public MainPanel() {
        controlPanel.setBackground(Color.GRAY);

        controlPanel.btnStart.addActionListener(actionEvent -> {
            BouncingShape shape = controlPanel.getSelectedShape().createShape(canvas.getBounds());
            shapeList.add(shape);
        });

        controlPanel.getChooseShapeCb().addItemListener(itemEvent -> {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                shapeList.clear();
            }
        });

        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.PAGE_END);
        start();
    }

    private void start() {
        Thread t = new Thread(() -> {
            while (true) {
                update();
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    private void update() {
        for (BouncingShape shape : shapeList)
            shape.move();
    }

    class DrawCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (BouncingShape shape : shapeList)
                shape.draw(g);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(width, height);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame gui = new JFrame();
            gui.setResizable(false);
            gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            gui.setSize(600, 600);
            MainPanel panel = new MainPanel();
            gui.setContentPane(panel);
            gui.setLocationRelativeTo(null);
            gui.setVisible(true);
        });
    }
}


