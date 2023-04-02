package lab_rab.lab2.state.components;

import lab_rab.lab2.state.ControlRole;

import java.awt.*;

public class NoseTriangle extends ObserverComponent {
    private Color curColor = Color.GRAY;
    private int[] xpoints, ypoints;

    public NoseTriangle(int x, int y, int width, int height) {
        super(x, y, width, height, ControlRole.NOSE);
        xpoints = new int[]{width / 2, 0, width};
        ypoints = new int[]{0, height, height};
        stroke = new BasicStroke(2.5f);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(curColor);
        g2.setStroke(stroke);
        g2.fillPolygon(xpoints, ypoints, 3);
    }

    @Override
    void onUpdate() {
        this.curColor = getRandomColor();
    }

    private Color getRandomColor() {
        int r = getRandomColorPart();
        int g = getRandomColorPart();
        int b = getRandomColorPart();
        return new Color(r, g, b);
    }

    private int getRandomColorPart() {
        return (int) (Math.random() * 256);
    }
}
