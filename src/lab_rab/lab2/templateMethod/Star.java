package lab_rab.lab2.templateMethod;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class Star extends BouncingShape {
    Path2D path;

    Star() {}

    Star(Rectangle rectangle) {
        super(rectangle);
        path = new Path2D.Double();

        int height, width;
        height = width = radius * 2;
        double heightPart = height / 3d;
        double widthPart = width / 3d;

        path.moveTo(width / 2, 0);
        path.lineTo(widthPart * 2, heightPart);
        path.lineTo(width, heightPart);
        path.lineTo(widthPart * 2, height / 2);
        path.lineTo(width, height);

        path.lineTo(width / 2, heightPart * 2);
        path.lineTo(0, height);
        path.lineTo(widthPart, height / 2);
        path.lineTo(0, heightPart);
        path.lineTo(widthPart, heightPart);

        path.closePath();
    }

    @Override
    BouncingShape createShape(Rectangle rectangle) {
        return new Star(rectangle);
    }

    @Override
    public String toString() {
        return "Star";
    }

    public Shape getTransformedInstance() {
        AffineTransform at = new AffineTransform();
        Rectangle bounds = path.getBounds();
        at.rotate(Math.toRadians(angle), x + (bounds.width / 2), y + (bounds.height / 2));
        at.translate(x, y);
        return path.createTransformedShape(at);
    }

    @Override
    void rotate(boolean bounced) {
        angle += rotationDelta;
    }

    @Override
    boolean checkBounce() {
        Rectangle bounds = path.getBounds();

        boolean bounced = false;
        if (x < 0) {
            x = 0;
            vX *= -1;
            bounced = true;
        } else if (x + bounds.width > boundX) {
            x = boundX - bounds.width;
            vX *= -1;
            bounced = true;
        }
        if (y < 0) {
            y = 0;
            vY *= -1;
            bounced = true;
        } else if (y + bounds.height > boundY) {
            y = boundY - bounds.height;
            vY *= -1;
            bounced = true;
        }
        if (bounced) {
            rotationDelta *= -1;
        }

        return bounced;
    }

    @Override
    void paintShape(Graphics2D g) {
        g.setStroke(new BasicStroke(4.f));
        g.fill(getTransformedInstance());
    }
}
