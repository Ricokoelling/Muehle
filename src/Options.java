import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Options extends JFrame{
    String[] insertBox = { "1920x1080", "1280x720" };
    //JComboBox comboBox = new JComboBox(insertBox);
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    WindowListener exitListener = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            int confirm = JOptionPane.showOptionDialog(
                    null, "Are You Sure to Close Application?",
                    "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == 0) {
                System.exit(0);
            }
        }
    };

    public Options() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(screenSize.width, screenSize.height);
        this.setVisible(true);
        this.setResizable(false);
       // comboBox.setSize(200,200);
        //this.add(comboBox);
        this.addWindowListener(exitListener);
    }

    private void btnOne(){

    }
}
