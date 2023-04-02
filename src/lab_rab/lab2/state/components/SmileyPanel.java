package lab_rab.lab2.state.components;

import lab_rab.lab2.state.ControlRole;
import lab_rab.lab2.state.interfaces.IObservable;
import lab_rab.lab2.state.interfaces.IObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SmileyPanel extends JPanel implements IObservable {
    private List<IObserver> listeners;
    EyeEllipse leftEye;
    EyeEllipse rightEye;
    NoseTriangle nose;
    Mouth mouth;

    public SmileyPanel() {
        listeners = new ArrayList<>();
        leftEye = new EyeEllipse(150, 100, 75, 75, ControlRole.LEFT_EYE);
        rightEye = new EyeEllipse(350, 100, 75, 75, ControlRole.RIGHT_EYE);
        nose = new NoseTriangle(255, 250, 50, 50);
        mouth = new Mouth(150, 400, 270, 50);

        this.addAndSubscribe(leftEye);
        this.addAndSubscribe(rightEye);
        this.addAndSubscribe(nose);
        this.addAndSubscribe(mouth);
    }

    public void setState(int i){
        leftEye.setState(i);
        rightEye.setState(i);
        mouth.setState(i);
        leftEye.onUpdate();
        rightEye.onUpdate();
        mouth.onUpdate();
        this.repaint();
    }

    private void addAndSubscribe(ObserverComponent component) {
        this.add(component);
        this.addObserver(component);
    }

    @Override
    public void addObserver(IObserver observer) {
        if (observer != null) {
            this.listeners.add(observer);
            observer.subscribe(this);
        }
    }

    @Override
    public void notify(ControlRole role) {
        this.listeners.forEach(observer -> observer.update(role));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color myColor = new Color(191, 161, 135);
        g.setColor(myColor);
        g.fillOval(85, 20, 400, 500);
    }
}
