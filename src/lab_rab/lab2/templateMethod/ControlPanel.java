package lab_rab.lab2.templateMethod;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class ControlPanel extends JPanel {
    public JButton btnStart = new JButton("Start");;
    public JButton btnClose = new JButton("Close");
    private JComboBox<BouncingShape> cbChooseShape;

    public ControlPanel(){
        cbChooseShape= new JComboBox();

        Vector<BouncingShape> items = new Vector<>();
        items.add(new Ball());
        items.add(new Square());
        items.add(new Star());

        cbChooseShape.setModel(new DefaultComboBoxModel<>(items));
        cbChooseShape.setSelectedIndex(0);

        this.add(btnStart);
        this.add(btnClose);
        this.add(cbChooseShape);

        btnClose.addActionListener(actionEvent -> {
           System.exit(0);
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600,35);
    }

    public JComboBox getChooseShapeCb(){
        return cbChooseShape;
    }

    public BouncingShape getSelectedShape(){
        return (BouncingShape)cbChooseShape.getSelectedItem();
    }
}
