package lab_rab.lab2.facade;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FacadeTraffic extends JPanel {
    private ControlPanel controlPanel = new ControlPanel();
    private DrawCanvas canvas = new DrawCanvas();
    private int width = 600;
    private int height = 600;
    public ArrayList<CarFacade> carFacadeArrayList = new ArrayList<>();
    public TrafficLight trafficLight;
    private int currentColorLight;

    public FacadeTraffic() {

        controlPanel.btnStart.addActionListener(actionEvent -> {
            try {
                start();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        trafficLight = new TrafficLight();
        carFacadeArrayList.add(new CarFacade());
        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.PAGE_END);
    }

    private void start() throws InterruptedException {
        Thread t = new Thread(() -> {
            int time =1;
            while (time< Integer.MAX_VALUE) {
                update(time);
                repaint();
                if(time % 30 == 0) currentColorLight = getNextTrafficLightColor();
                if(time % 10 == 0) carFacadeArrayList.add(new CarFacade());
                if(time % 550 == 0) {
                    carFacadeArrayList.remove(carFacadeArrayList.get(0));
                }
                time++;
                try {
                    Thread.sleep(30);
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

    private void update(int time) {
        trafficLight.changeLight(currentColorLight);
        if(currentColorLight == 0) {
            for (int i = 0; i < carFacadeArrayList.size(); i++) {
                carFacadeArrayList.get(i).nextStepX();
            }
        }
    }

    class DrawCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            trafficLight.draw(g);
            for (int i = 0; i < carFacadeArrayList.size(); i++)
                carFacadeArrayList.get(i).draw(g);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(width, height);
        }
    }

    private int getNextTrafficLightColor() {
        switch (currentColorLight) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            default:
                return 0;
        }
    }

    public static void runTrafficJam() throws InterruptedException {
        SwingUtilities.invokeLater(() -> {
            JFrame gui = new JFrame();
            gui.setResizable(false);
            gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            gui.setSize(600, 600);
            FacadeTraffic panel = new FacadeTraffic();
            gui.setContentPane(panel);
            gui.setLocationRelativeTo(null);
            gui.setVisible(true);
        });
    }
}
    