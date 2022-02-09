package ClientSide;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

public class roomSelectionFrame extends JFrame {

    private roomSelectionPanel roomP;
    private Client client;
    WindowListener exitListener;

    public roomSelectionFrame(Client client, ArrayList<String> userList, playBoardClient pbC, String userText) {
        this.client = client;
        exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Are You Sure to Close Application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    assert client != null;
                    client.endConnection("/");
                    System.exit(0);
                }
            }
        };
        this.addWindowListener(exitListener);
        int width=600;
        int height=500;
        this.setTitle(userText);
        this.setBounds(2560/2-width/2,1440/2-height/2,width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.roomP=new roomSelectionPanel(client,userList,pbC,this);
        roomP.setExitlistener(exitListener);
        this.add(this.roomP);
        this.setVisible(true);
    }

    public roomSelectionFrame(Client client, ArrayList<String> userList, String userText) throws IOException, InterruptedException {
        this.client = client;
        exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Are You Sure to Close Application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    assert client != null;
                    client.endConnection("/");
                    System.exit(0);
                }
            }
        };
        this.addWindowListener(exitListener);
        int width=600;
        int height=500;
        this.setTitle(userText);
        this.setBounds(2560/2-width/2,1440/2-height/2,width, height);
        this.roomP=new roomSelectionPanel(client,userList,userText,this);
        this.add(roomP);
        roomP.setExitlistener(exitListener);
        this.setVisible(true);
    }


}

