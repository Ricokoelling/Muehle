package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class LoginScreen extends JFrame {

    private loginPanel loginP;

    public LoginScreen() {
        int width=350;
        int height=200;
        this.setBounds(2560/2-width/2,1440/2-height/2,width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(this.loginP = new loginPanel());
        this.setVisible(true);
    }
}
