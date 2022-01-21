//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class LoginScreen implements ActionListener {
    private loginPanel loginP;
    private createPanel createN;

    public LoginScreen() {
        JFrame frame = new JFrame();
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(3);
        frame.add(this.loginP = new loginPanel());
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("It works");
    }
}
