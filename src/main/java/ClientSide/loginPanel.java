package ClientSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.Arrays;

public class loginPanel extends JPanel implements ActionListener {
    private JLabel username;
    private JLabel passwordLabel;
    private JLabel success;

    private JTextField userText;
    private JPasswordField passwordField;

    private JButton login;
    private JButton createButton;

    private boolean gebrochen = false;

    private final Client client = new Client();

    public loginPanel() {
        this.setLayout(null);

        //username JLabel
        this.username = new JLabel(labelString());
        this.username.setBounds(10, 26, 80, 10);
        this.add(this.username);

        //userText TextField
        this.userText = new JTextField(this.userTextString());
        this.userText.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (loginPanel.this.userText.getText().equals("") || loginPanel.this.userText.getText().equals(userTextString())) {
                    loginPanel.this.userText.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (loginPanel.this.userText.getText().equals("")) {
                    loginPanel.this.userText.setText(userTextString());
                }
            }
        });

        this.userText.setBounds(100, 20, 165, 25);
        this.add(this.userText);

        //password Label
        this.passwordLabel = new JLabel(passwordLabelString());
        this.passwordLabel.setBounds(10, 50, 80, 25);
        this.add(this.passwordLabel);
        //password passwordField
        this.passwordField = new JPasswordField(passwordLabelString());

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

        //login Button
        this.login = new JButton(buttonString());
        this.login.setBounds(10, 80, 80, 25);
        this.login.addActionListener(this);
        this.add(this.login);

        //success Label
        this.success = new JLabel("");
        this.success.setBounds(10, 120, 300, 25);
        this.add(this.success);

        //create Button
        this.createButton = new JButton(createNString());
        this.createButton.setBounds(100, 80, 80, 25);
        this.createButton.addActionListener(this);
        this.add(this.createButton);
    }

    public void actionPerformed(ActionEvent e) {
        //This is to dispose of the parent so it can be closed
        Window s = SwingUtilities.getWindowAncestor(this);
        if (e.getSource() == login) {
            //control login credentials
            //TODO Server stuff
            try {
                playBoardClient pbC = new playBoardClient(client, userText.getText());
                System.out.println(userText.getText() + "    " + Arrays.toString(passwordField.getPassword()));
                client.sendsLogin(userText.getText(), Arrays.hashCode(passwordField.getPassword()), false);
                new Thread(() -> {
                    while (true) {
                        if (client.waitforAccept()) {
                            if (client.isAccepted()) {
                                s.dispose();
                                pbC.setVisible(true);
                                new roomSelectionFrame(client, client.getUserList(), pbC, userText.getText());
                            } else {
                                success.setForeground(Color.red);
                                if(client.isAlreadyOnline()){
                                    success.setText("Player is already Online!");
                                }else {
                                    success.setText("Wrong Username or Password");
                                }
                                loginPanel.this.userText.setText("");
                                loginPanel.this.passwordField.setText("");
                            }
                            gebrochen = true;
                            break;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                }).start();
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == createButton) {
            if(gebrochen) {
                new createFrame(client);
                s.dispose();
            }
        }
    }

    //Srings
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

    //Color
    private Color successGreen() {
        return new Color(64, 255, 0);
    }


}
