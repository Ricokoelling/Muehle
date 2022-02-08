package ClientSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startPanel extends JPanel implements ActionListener {

    private int         width;
    private int         height;

    private JButton     sp;
    private JButton     mp;
    private JButton     options;
    private JButton     exit;

    public startPanel(int w, int h){
        this.setLayout(null);
        this.width=w;
        this.height=h;

        int middle=width/2;

        //SinglePlayer JButton
            this.sp = new JButton(sp());
            this.sp.setBounds(middle-(buttonSizeX()/2)-8, 20, buttonSizeX(), buttonSizeY());
            this.sp.addActionListener(this);
            this.add(sp);

        //MultiPlayer JButton
            this.mp = new JButton(mp());
            this.mp.setBounds(middle-(buttonSizeX()/2)-8, 70, buttonSizeX(), buttonSizeY());
            this.mp.addActionListener(this);
            this.add(mp);

        //Options JButton
            this.options = new JButton(options());
            this.options.setBounds(middle-(buttonSizeX()/2)-8, 120, buttonSizeX(), buttonSizeY());
            this.options.addActionListener(this);
            this.add(options);

        //Options JButton
            this.exit = new JButton(exit());
            this.exit.setBounds(middle-(buttonSizeX()/2)-8, 170, buttonSizeX(), buttonSizeY());
            this.exit.addActionListener(this);
            this.add(exit);

        /*
        //create Button
        this.createButton = new JButton(createNString());
        this.createButton.setBounds(100, 80, 80, 25);
        this.createButton.addActionListener(this);
        this.add(this.createButton);
         */
    }
    //---------------------------------------------Sizes---------------------------------------------\\
    public int buttonSizeX(){return 160;}
    public int buttonSizeY(){return 40;}

    //---------------------------------------------Strings---------------------------------------------\\

    public String sp(){return "Singleplayer";}
    public String mp(){return "Multiplayer";}
    public String options(){return "Optionen";}
    public String exit(){return "Exit";}

    @Override
    public void actionPerformed(ActionEvent e) {
        Window s = SwingUtilities.getWindowAncestor(this);
        if(e.getSource()==sp){
            //TODO start muehle singleplayer
            s.dispose();
        }
        if(e.getSource()==mp){
            //TODO mutliplayer ADD login class
            new LoginScreen();
            s.dispose();
        }
        if(e.getSource()==options){
            //TODO Options
        }
        if(e.getSource()==exit){
            System.exit(0);
        }
    }
}
