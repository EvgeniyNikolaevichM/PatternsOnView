package lab_rab.lab2.templateMethod;

import java.awt.*;

public class Ball extends BouncingShape {

    public Ball() { }

    public Ball(Rectangle rectangle) {
        super(rectangle);
    }

    @Override
    BouncingShape createShape(Rectangle rectangle) {
        return new Ball(rectangle);
    }

    @Override
    public String toString() {
        return "Ball";
    }

    @Override
    void paintShape(Graphics2D g) {
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    void rotate(boolean bounced) {

    }
}
