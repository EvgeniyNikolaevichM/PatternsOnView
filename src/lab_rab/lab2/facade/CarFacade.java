package lab_rab.lab2.facade;

import java.awt.*;
import java.util.Random;

public class CarFacade extends FacadeShapes{
    Color carColor;
    int currentX;
    CarFacade(){
        currentX = 650;
        switch (new Random().nextInt(4)){
            case 0: carColor = Color.PINK; break;
            case 1: carColor = Color.CYAN; break;
            case 2: carColor = Color.ORANGE; break;
            case 3: carColor = Color.MAGENTA; break;
        }
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }
    public void nextStepX(){
        this.currentX -= 10;
    }
    @Override
    public void paintShape(Graphics2D g){
        g.setColor(Color.lightGray);
        g.fillOval(currentX - 20, 240, 10, 20);
        g.fillOval(currentX + 20, 240, 10, 20);
        g.setColor(carColor);
        g.fillRect(currentX - 25, 230, 60, 20);
    }

}
    