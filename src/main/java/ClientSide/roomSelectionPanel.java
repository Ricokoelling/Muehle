package ClientSide;

import ServerSide.Clienthandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class roomSelectionPanel extends JPanel implements ActionListener {

    private JList playerOnlineJList;
    private JButton refresh;
    private JButton duell;
    private DefaultListModel<String> model;
    ArrayList<String> userList = new ArrayList<>();
    private final Client Client;
    private boolean duel = false;
    private playBoardClient pbC;
    private roomSelectionFrame rSf;
    private WindowListener exitlistener;


    public roomSelectionPanel(Client client, ArrayList<String> userList, playBoardClient pbC, roomSelectionFrame rsf) {
        this.setLayout(null);
        this.Client = client;
        this.userList = userList;
        this.pbC = pbC;
        pbC.setVisible(true);
        this.rSf = rsf;
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

    public roomSelectionPanel(Client client, ArrayList<String> userList, String username, roomSelectionFrame rsf) throws IOException, InterruptedException {
        this.setLayout(null);
        this.Client = client;
        this.userList = userList;
        this.pbC = new playBoardClient(client,username);
        pbC.setVisible(true);
        this.rSf = rsf;
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

    public void setExitlistener(WindowListener exitlistener) {
        this.exitlistener = exitlistener;
    }

    private void refreshOnlinePlayers() {
        new Thread(() -> {
            while (true) {
                if (Client.waitForList()) {
                    model.removeAllElements();
                    userList = Client.getUserList();
                    for (String ul : userList) {
                        model.addElement(ul);
                    }
                    if (duel) {
                        if (Client.waitforAcceptMatch()) {
                            pbC.startMatch();
                            Client.sendBreak();
                            rSf.dispose();
                            break;
                        }
                    } else {
                        if (Client.isMatchFound()) {
                            int answer = JOptionPane.showConfirmDialog(null, Client.getOpponent() + " wants to play!", "Match Found", JOptionPane.YES_NO_OPTION);
                            if (answer == 0) {
                                pbC.thisplayerMove = true;
                                pbC.playerNumber = true;
                                Client.sendAccept();
                                rSf.dispose();
                                break;
                            }
                        }
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
            Client.sendList(enemyName);
            duel = true;

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

