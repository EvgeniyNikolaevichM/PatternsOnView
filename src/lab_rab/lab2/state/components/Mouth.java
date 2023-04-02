package lab_rab.lab2.state.components;

import lab_rab.lab2.state.ControlRole;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Mouth extends ObserverComponent {
    private boolean isSmiling = false;
    private int state = 0;

    public Mouth(int x, int y, int width, int height) {
        super(x, y, width, height, ControlRole.MOUTH);
        this.stroke = new BasicStroke(10.0f);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(new Color(205, 55, 55));
        g2.setStroke(stroke);

        if (state == 0){
            g2.drawLine(0, 0, width, 0);
        } else if (state == 1) {
            g2.fillOval(100, -30, 100, 50);
        } else if (state == 2) {
            g2.fillOval(100, 0, 100, 50);
        }
    }

    @Override
    void onUpdate() {
        this.isSmiling = !this.isSmiling;
    }

    public void setState(int state){
        this.state = state;
    }
}
