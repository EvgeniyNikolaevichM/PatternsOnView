package lab_rab.lab2.observer.components;

import lab_rab.lab2.observer.ControlRole;

import java.awt.*;

public class Mouth extends ObserverComponent {
    private boolean isSmiling = false;

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

        if (!isSmiling)
            g2.drawLine(0, 0, width, 0);
        else
            g2.fillOval(0, -20, width, height);
    }

    @Override
    void onUpdate() {
        this.isSmiling = !this.isSmiling;
    }
}
