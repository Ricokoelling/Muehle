package ClientSide;

import ServerSide.Clienthandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class roomSelectionPanel extends JPanel implements ActionListener {

    private JList playerOnlineJList;
    private JButton refresh;
    private JButton duell;
    private DefaultListModel<String> model;
    ArrayList<String> userList = new ArrayList<>();
    private final Client Client;
    private boolean duel = false;


    public roomSelectionPanel(Client client, ArrayList<String> userList) {
        this.setLayout(null);
        this.Client = client;
        this.userList = userList;
        model = new DefaultListModel<>();

        //playerOnline JList
        this.playerOnlineJList = new JList(model);
        this.playerOnlineJList.setBounds(40, 10, 120, 400);
        this.add(this.playerOnlineJList);

        //refresh JButton
        this.refresh = new JButton(refresh());
        this.refresh.setBounds(180, 10, 32, 32);
        this.refresh.addActionListener(this);
        this.add(refresh);

        //testing button
        this.duell = new JButton(duell());
        this.duell.setBounds(180, 80, 80, 25);
        this.duell.addActionListener(this);
        this.add(duell);

        //looks for online Players
        //TODO needs to connect to the ServerSide.server to do that
        refreshOnlinePlayers();
    }

    private void refreshOnlinePlayers() {
        new Thread(() -> {
            while (true) {
                if (Client.waitForList()) {
                    model.removeAllElements();
                    userList = Client.getUserList();
                    for (String ul : userList) {
                        System.out.println("ul: " +ul);
                        model.addElement(ul);
                    }
                    if (duel) {
                        break;
                    }
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    //Button Control Room
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refresh) {
            //refreshOnlinePlayers();
        }
        if (e.getSource() == duell) {
            //TODO request to the ServerSide.server for a 1v1
            String enemyName = (String) playerOnlineJList.getSelectedValue();
            duel = true;
            Client.sendList(enemyName);

        }
    }

    //Srings
    public String refresh(int a) {
        return "Refresh";
    }

    public String duell() {
        return "Duell";
    }

    //Icons

    /**
     * This function shrinks down the image sice of the button
     *
     * @return the Icon for a Button
     */
    public Icon refresh() {
        return new ImageIcon("./src/Icons/2937372_reload_navigation_refresh_arrow_repeat.png");
    }

}

