package lab_rab.lab2.facade;

import java.awt.*;

public class TrafficLight extends FacadeShapes {

    private static final int radius = 15;
    private int x;
    private int y;
    private int color =0;

    public TrafficLight() {
        this.x = 500;
        this.y = 200;
    }

    public TrafficLight(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void changeLight(int newLightColor){
        this.color = newLightColor;
    }
    void paintShape(Graphics2D g) {
        switch (color){
            case 0: paintGreen(g,true);paintYellow(g,false);paintRed(g,false);
                break;
            case 1: paintGreen(g,false);paintYellow(g,true);paintRed(g,false);
                break;
            case 2: paintGreen(g,false);paintYellow(g,false);paintRed(g,true);
                break;
            case 3: paintGreen(g,false);paintYellow(g,true);paintRed(g,false);
                break;
        }
    }

    private void paintRed(Graphics2D g,boolean current){
        if(current){
            g.setColor(Color.RED);
            g.fillOval(x - radius, y - 30 - radius, radius, radius * 2);
        }
        else {
            g.setColor(Color.GRAY);
            g.fillOval(x - radius, y - 30 - radius, radius, radius * 2);
        }
    }
    private void paintYellow(Graphics2D g,boolean current){
        if(current){
            g.setColor(Color.YELLOW);
            g.fillOval(x - radius, y - radius, radius, radius * 2);
        }
        else {
            g.setColor(Color.GRAY);
            g.fillOval(x - radius, y - radius, radius, radius * 2);
        }
    }
    private void paintGreen(Graphics2D g,boolean current ){
        if (current){
            g.setColor(Color.GREEN);
            g.fillOval(x - radius, y + 30 - radius, radius, radius * 2);
        }
        else {
            g.setColor(Color.GRAY);
            g.fillOval(x - radius, y + 30 - radius, radius, radius * 2);
        }
    }
}
    