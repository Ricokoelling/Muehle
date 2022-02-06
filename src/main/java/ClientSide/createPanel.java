package ClientSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.Arrays;

public class createPanel extends JPanel implements ActionListener, SwingConstants {
    private JLabel username;
    private JTextField userText;
    private JButton cancel;
    private JButton create;
    private JPasswordField passwordText;
    private JPasswordField passwordText2;

    public createPanel() {
        this.setLayout(null);

        //username JLabel
        this.username = new JLabel(username());
        this.username.setBounds(10, 26, 80, 10);
        this.add(this.username);
        //password JLabel
        this.username = new JLabel(password());
        this.username.setBounds(10, 56, 80, 10);
        this.add(this.username);

        //password2 JLabel
        this.username = new JLabel(password2());
        this.username.setBounds(10, 86, 120, 10);
        this.add(this.username);

        //userText TextField
        this.userText = new JTextField(username());
        this.userText.setBounds(140, 20, 165, 25);

        this.userText.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (userText.getText().equals("") || userText.getText().equals(username())) {
                    userText.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (userText.getText().equals("")) {
                    userText.setText(username());
                }

            }
        });

        this.add(this.userText);

        //password TextField
        this.passwordText = new JPasswordField(password());
        this.passwordText.setBounds(140, 50, 165, 25);

        passwordText.addFocusListener(new FocusListener() {
            boolean alreadyAccessed = false;

            public void focusGained(FocusEvent e) {
                if (!this.alreadyAccessed) {
                    this.alreadyAccessed = true;
                    passwordText.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
            }
        });

        this.add(this.passwordText);

        //password2 TextField
        this.passwordText2 = new JPasswordField(password());
        this.passwordText2.setBounds(140, 80, 165, 25);

        passwordText2.addFocusListener(new FocusListener() {
            boolean alreadyAccessed = false;

            public void focusGained(FocusEvent e) {
                if (!this.alreadyAccessed) {
                    this.alreadyAccessed = true;
                    passwordText2.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
            }
        });

        this.add(this.passwordText2);

        //cancel JButton
        this.cancel = new JButton(cancel());
        this.cancel.setBounds(10, 110, 80, 25);
        this.cancel.addActionListener(this);
        this.add(cancel);

        //create JButton
        this.create = new JButton(create());
        this.create.setBounds(100, 110, 80, 25);
        this.create.addActionListener(this);
        this.add(create);

    }

    private boolean CheckPW() {
        if(Arrays.equals(passwordText.getPassword(), passwordText2.getPassword())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //This uses the SwingConstants to callback on Window to dispose of it
        Window s = SwingUtilities.getWindowAncestor(this);
        //TODO Server stuff
        if (e.getSource() == cancel) {
            new LoginScreen();
            s.dispose();
        }
        if (e.getSource() == create) {
            if(CheckPW()){
                playBoardClient pbC = null;
                try {
                    pbC = new playBoardClient();
                    pbC.Register(userText.getText(),passwordText.getPassword().hashCode());
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            s.dispose();
        }
    }

    //Srings
    private String create() {
        return "Create";
    }

    private String cancel() {
        return "Cancel";
    }

    private String password() {
        return "Password";
    }

    private String password2() {
        return "Confirm Password";
    }

    private String username() {
        return "Username";
    }

}
