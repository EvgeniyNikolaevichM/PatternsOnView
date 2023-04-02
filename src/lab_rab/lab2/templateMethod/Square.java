package lab_rab.lab2.templateMethod;

import java.awt.*;
import java.awt.geom.Path2D;

public class Square extends BouncingShape {

    public Square() {
    }

    public Square(Rectangle rectangle) { super(rectangle); }

    @Override
    BouncingShape createShape(Rectangle rectangle) {
        return new Square(rectangle);
    }

    @Override
    public String toString() {
        return "Square";
    }

    @Override
    void paintShape(Graphics2D g) {
        Rectangle rect = new Rectangle(x, y, radius * 2, radius * 2);

        Path2D.Double path = new Path2D.Double();
        path.append(rect, false);

        g.translate(x, y);
        g.rotate(-angle);
        g.translate(-rect.getCenterX(), -rect.getCenterY());
        g.fill(rect);
    }

    @Override
    void rotate(boolean bounced) {
        if (bounced) {
            angle += rotationDelta;
            if (angle > 360)
                angle = 0;
        }
    }
}
