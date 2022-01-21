//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package GUI;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class loginPanel extends JPanel implements ActionListener {
    private JLabel label;
    private JLabel passwordLabel;
    private JLabel success;
    private JTextField userText;
    private JPasswordField passwordField;
    private JButton button;
    private JButton createN;

    public loginPanel() {
        this.setLayout((LayoutManager)null);
        this.label = new JLabel(this.labelString());
        this.label.setBounds(10, 26, 80, 10);
        this.add(this.label);
        this.userText = new JTextField(this.userTextString());
        this.userText.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (loginPanel.this.userText.getText().equals("") || loginPanel.this.userText.getText().equals(loginPanel.this.userTextString())) {
                    loginPanel.this.userText.setText("");
                }

            }

            public void focusLost(FocusEvent e) {
                if (loginPanel.this.userText.getText().equals("")) {
                    loginPanel.this.userText.setText(loginPanel.this.userTextString());
                }

            }
        });
        this.userText.setBounds(100, 20, 165, 25);
        this.add(this.userText);
        this.passwordLabel = new JLabel(this.passwordLabelString());
        this.passwordLabel.setBounds(10, 50, 80, 25);
        this.add(this.passwordLabel);
        this.passwordField = new JPasswordField(this.passwordLabelString());
        this.passwordField.addFocusListener(new FocusListener() {
            boolean alreadyAccessed = false;

            public void focusGained(FocusEvent e) {
                if (!this.alreadyAccessed) {
                    this.alreadyAccessed = true;
                    loginPanel.this.passwordField.setText("");
                }

            }

            public void focusLost(FocusEvent e) {
            }
        });
        this.passwordField.setBounds(100, 50, 165, 25);
        this.add(this.passwordField);
        this.button = new JButton(this.buttonString());
        this.button.setBounds(10, 80, 80, 25);
        this.button.addActionListener((ActionListener)this.getParent());
        this.add(this.button);
        this.success = new JLabel("");
        this.success.setBounds(10, 110, 300, 25);
        this.add(this.success);
        this.createN = new JButton(this.createNString());
        this.createN.setBounds(100, 80, 80, 25);
        this.createN.addActionListener((ActionListener)this.getParent());
        this.add(this.createN);
    }

    private String labelString() {
        return "User";
    }

    private String userTextString() {
        return "Username";
    }

    private String passwordLabelString() {
        return "Password";
    }

    private String buttonString() {
        return "login";
    }

    private String createNString() {
        return "Sign Up";
    }

    public void actionPerformed(ActionEvent e) {
    }
}
