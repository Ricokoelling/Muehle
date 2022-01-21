//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package GUI;

import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class createPanel extends JPanel {
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JLabel password2Label;
    private JTextField userText;
    private JTextField passwordText;
    private JTextField password2Text;

    public createPanel() {
        this.setLayout((LayoutManager)null);
        this.userLabel = new JLabel(this.userName());
        this.setBounds(10, 26, 80, 10);
        this.add(this.userLabel);
        this.userText = new JTextField(this.userName());
        this.setBounds(10, 26, 80, 10);
        this.add(this.userText);
    }

    private String password() {
        return "Password";
    }

    private String password2() {
        return "Confirm Password";
    }

    private String userName() {
        return "Username";
    }
}
