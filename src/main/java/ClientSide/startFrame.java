package ClientSide;

import javax.swing.*;

public class startFrame extends JFrame {

    private startPanel startP;

    public startFrame(){
        int width=202;
        int height=260;
        int swidth=2560;
        int sheight=1440;
        this.setBounds(swidth/2-width/2,sheight/2-height/2,width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(startP = new startPanel(width, height));
        this.setVisible(true);
    }


}
