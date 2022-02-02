import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class roomSelectionPanel extends JPanel implements ActionListener {

    private JList                       playerOnlineJList;
    private JButton                     refresh;
    private JButton                     duell;
    private DefaultListModel<String>    model;


    public roomSelectionPanel() {
        this.setLayout(null);

        //model DefaultListModel<String>
            model = new DefaultListModel<>();

        //playerOnline JList
            this.playerOnlineJList = new JList(model);
            this.playerOnlineJList.setBounds(40,10,120, 400);
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
            //TODO needs to connect to the server to do that
            //refreshOnlinePlayers();
    }

    private void refreshOnlinePlayers(String[] arr){
        model.removeAllElements();
        for(int i=0;i!=arr.length;i++) model.addElement(arr[i]);
    }

    //Button Control Room
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==refresh){
            //refreshOnlinePlayers();
        }if(e.getSource()==duell){
            //TODO request to the server for a 1v1
            String enemyName = (String) playerOnlineJList.getSelectedValue();
        }
    }

    //Srings
    public String refresh(int a){ return "Refresh"; }

    public String duell(){ return "Duell"; }

    //Icons
    /**        This function shrinks down the image sice of the button
     *
     * @return the Icon for a Button
     */
    public Icon refresh(){ return new ImageIcon("./src/Icons/2937372_reload_navigation_refresh_arrow_repeat.png"); }

}

