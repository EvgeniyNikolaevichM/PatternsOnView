package lab_rab.lab2.state;

import lab_rab.lab2.state.components.SmileyPanel;

import javax.swing.*;

public class Gui extends JFrame {
    SmileyPanel smileyPanel;
    public JButton btnWeekends = new JButton("Weekends");
    public JButton btnExams = new JButton("Exams");
    public JButton btnSemester = new JButton("Semester");

    public Gui() {
        smileyPanel = new SmileyPanel();
        smileyPanel.setLayout(null);

        this.add(btnWeekends);
        this.add(btnExams);
        this.add(btnSemester);
        btnWeekends.setBounds(50,600,100,50);
        btnExams.setBounds(200,600,100,50);
        btnSemester.setBounds(350,600,100,50);

        btnWeekends.addActionListener(actionEvent -> {
            smileyPanel.setState(1);
        });
        btnExams.addActionListener(actionEvent -> {
            smileyPanel.setState(2);
        });
        btnSemester.addActionListener(actionEvent -> {
            smileyPanel.setState(0);
        });

        this.getContentPane().add(smileyPanel);
        this.pack();
        this.setSize(600, 800);
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


