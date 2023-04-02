package lab_rab.lab2.observer;

import lab_rab.lab2.observer.components.SmileyPanel;

import javax.swing.*;

public class Gui extends JFrame {
    SmileyPanel smileyPanel;

    public Gui() {
        smileyPanel = new SmileyPanel();
        smileyPanel.setLayout(null);

        this.getContentPane().add(smileyPanel);
        this.pack();
        this.setSize(600, 600);
        smileyPanel.setSize(600, 600);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.validate();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Gui();
    }
}


