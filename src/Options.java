import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options extends JFrame implements ActionListener {
    JButton btn1 = new JButton("Resolution");
    JFrame next = new JFrame("Options");
    String[] insertBox = { "1920x1080", "1280x720" };
    JComboBox comboBox = new JComboBox(insertBox);
    public void start(){
        this.setSize(200,300);
        this.setVisible(true);
        btn1.addActionListener(e -> btnOne());
        this.add(comboBox);
        this.pack();
    }

    private void btnOne(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
