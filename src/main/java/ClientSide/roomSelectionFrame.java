package ClientSide;

import javax.swing.*;
import java.util.ArrayList;

public class roomSelectionFrame extends JFrame {

    private roomSelectionPanel roomP;


    public roomSelectionFrame(Client client, ArrayList<String> userList, playBoardClient pbC) {
        int width=600;
        int height=500;
        this.setBounds(2560/2-width/2,1440/2-height/2,width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(this.roomP=new roomSelectionPanel(client,userList,pbC,this));
        this.setVisible(true);
    }


}

