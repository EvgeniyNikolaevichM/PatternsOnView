package lab_rab.lab2.templateMethod;

import java.awt.*;

abstract class BouncingShape {
    int vX = 1;
    int vY = 1;
    int angle = 1;
    int x, y, boundX, boundY;
    protected int radius = 25;
    double rotationDelta = 5d;
    private Color color;

    public BouncingShape(){}

    protected BouncingShape(Rectangle boundsRect) {
        x = boundX = boundsRect.width;
        y = boundY = boundsRect.height;
        vX = vY = random(5, 10);
        color = new Color(random(255), random(255), random(255));
    }

    private static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
    }
    private static int random(int min, int max){
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    abstract BouncingShape createShape(Rectangle rectangle);
    abstract void paintShape(Graphics2D g);
    abstract void rotate(boolean bounced);

    void draw(Graphics g) {
        g.setColor(color);
        paintShape((Graphics2D) g.create());
    }

    boolean checkBounce() {
        boolean bounced = false;

        if (x - radius < 0) {
            vX = -vX;
            x = radius;
            bounced = true;
        } else if (x + radius > boundX) {
            vX = -vX;
            x = boundX - radius;
            bounced = true;
        }

        if (y - radius < 0) {
            vY = -vY;
            y = radius;
            bounced = true;
        } else if (y + radius > boundY) {
            vY = -vY;
            y = boundY - radius;
            bounced = true;
        }

        return bounced;
    }

    void move() {
        shiftByDelta();
        rotate(checkBounce());
    }

    private void shiftByDelta(){
        x += vX;
        y += vY;
    }
}
