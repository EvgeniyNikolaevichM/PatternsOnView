package lab_rab.lab2.facade;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public JButton btnStart = new JButton("Start");;
    public JButton btnClose = new JButton("Close");

    public ControlPanel(){
        this.add(btnStart);
        this.add(btnClose);
        btnClose.addActionListener(actionEvent -> {
            System.exit(0);
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600,35);
    }
}
    