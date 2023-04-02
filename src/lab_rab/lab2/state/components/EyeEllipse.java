package lab_rab.lab2.state.components;

import lab_rab.lab2.state.ControlRole;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class EyeEllipse extends ObserverComponent {
    private boolean isOpen;
    private final int offset = 15;
    private int state = 0;

    EyeEllipse(int x, int y, int width, int height, ControlRole role) {
        super(x, y, width, height, role);
        this.isOpen = true;
    }

    @Override
    void onUpdate() {
        this.isOpen = !this.isOpen;
    }

    private int halfWidth() {
        return width / 2;
    }

    private int halfHeight() {
        return height / 2;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(Color.BLACK);
        g2.setStroke(new BasicStroke(2.5f));
        g2.draw(new Ellipse2D.Double(0, 1, width, height));

        Color myColor = new Color(99,69,51);
        g2.setPaint(myColor);
        int x = halfWidth() - offset;
        int y = halfHeight() - offset;

        if (state == 0){
            g2.setStroke(new BasicStroke(3f));
            g2.drawLine(x, halfHeight(), halfWidth() + offset, halfHeight());
            g2.setColor(Color.BLACK);
            g2.drawString("Z Z Z Z Z Z Z Z", x, y);
        } else if (state == 1) {
            g2.setStroke(new BasicStroke(1f));
            g2.fill(new Ellipse2D.Double(x, y, 30, 30));
            g2.setColor((Color.blue));
        } else if (state == 2) {
            int s = 100;
            g2.setStroke(new BasicStroke(1f));
            g2.fill(new Ellipse2D.Double(x, y, 30, 30));
            g2.fillOval(x+5,y+15, 20, 40);
        }
    }

    public void setState(int state){
       this.state = state;
    }
}
