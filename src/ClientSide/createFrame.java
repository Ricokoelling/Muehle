package ClientSide;

import javax.swing.*;

public class createFrame extends JFrame {

    private createPanel createP;

    public createFrame(){
        int width=350;
        int height=200;
        this.setBounds(2560/2-width/2,1440/2-height/2,width, height);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(this.createP =new createPanel());
        this.setVisible(true);
    }
}
