import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class playBoard extends JFrame implements MouseInputListener {
    private     static      final MyPanel pane  = new MyPanel();
    private     final       Master mst          = new Master();
    private     int         pos                 = 0;
    private     int         pos2                = 0;
    private     int         pos3                = 0;
    private     int         count               = 0;
    private     int         playerOnestones     = 9;
    private     int         playerTwostones     = 9;
    private     boolean     onlyOnce            = false;
    private     boolean     phaseChange         = false;
    protected   boolean     playerNumber        = true;  //true --> player 1 ----- false --> player 2
    protected   int         phase               = 1;
    private     int         maxstones           = 17;
    private     boolean     playerJump;

    /*WindowListener exitListener = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            int confirm = JOptionPane.showOptionDialog(
                    null, "Are You Sure to Close Application?",
                    "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == 0) {
                System.exit(0);
            }
        }
    };*/

    public playBoard() {
        //For testing purposes
        //this.setSize(1920,1080);

        //This makes the Frame go Fullscreen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Adds the MouseListener and the MouseMotionListener to the Frame
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //This deactivates the function for the user to resize the window. This is to prevent bugs
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(pane);
        //this.addWindowListener(exitListener);
        this.setVisible(true);
    }

    /**
     * after every placement the player gets asked if he wants to do his move (currently disabled, if turned back on change for phase 2 and 3 etc.)
     */
    private void takemove(){
        //int answer = JOptionPane.showConfirmDialog(null,"Did you finish your move?");
        if(maxstones == count){
            if(phase == 1){
                System.out.println("lolz je baited");
                phase = 2;
                phaseChange = true;
            }
            if (!playerNumber) {
                MyPanel.playerStatus.setText("Player 2 move");
            } else {
                MyPanel.playerStatus.setText("Player 1 move");
            }
        }
    }
    private void playerChange(){
        playerNumber = !playerNumber;
        if(phase == 1) {
            changeStatus(1);
        }
        else if(phase == 2) {
            changeStatus(3);
        }
        else if(phase == 3){
            changeStatus(4);
        } else {
            changeStatus(5);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int radius = pane.getWidth() / 14;
        int circleDiameter = 30;
        int circleRadius = circleDiameter / 2;
        boolean poswasTaken = false;
        if(phase == 2){
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50) {   //point [1]
                pos2 = 1;
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50) {   //point [2]
                pos2 = 2;
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50) {   //point [3]
                pos2 = 3;
            }
            // pos 10 & 15
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 50) {   //point [10]
                pos2 = 10;
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 50) {   //point [15]
                pos2 = 15;
            }
            //pos 22 23 24
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50) {   //point [22]
                pos2 = 22;
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50) {   //point [23]
                pos2 = 23;
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50) {   //point [24]
                pos2 = 24;
            }
            // middle rect
            //pos 4 5 6
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50) {   //point [4]
                pos2 = 4;
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50) {   //point [5]
                pos2 = 5;
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50) {   //point [6]
                pos2 = 6;
            }
            // pos 11 14
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 50) {   //point [11]
                pos2 = 11;
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 50) {   //point [14]
                pos2 = 14;
            }
            //pos 19 20 21
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50) {   //point [19]
                pos2 = 19;
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 1 + radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50) {   //point [20]
                pos2 = 20;
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50) {   //point [21]
                pos2 = 21;
            }
            //small rect
            //pos 7 8 9
            if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50) {   //point [4]
                pos2 = 7;
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50) {   //point [5]
                pos2 = 8;
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50) {   //point [6]
                pos2 = 9;
            }
            // pos 12 13
            if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 50) {   //point [12]
                pos2 = 12;
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 50) {   //point [13]
                pos2 = 13;
            }
            //pos 16 17 18
            if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 50) {   //point [16]
                pos2 = 16;
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 50) {   //point [17]
                pos2 = 17;
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 50) {   //point [18]
                pos2 = 18;
            }

            //System.out.println("pos " + pos + "pos2: " + pos2);
            if(pos2 != pos && !onlyOnce && !mst.sameplayerStone(pos,playerNumber)) { //changed sameplayer stone and solved the problem
                onlyOnce = true;
                if (mst.freeposNextto(pos2, pos, playerNumber)) { //check if pos2 is free and if it is only one step away
                    pane.moveStone(pos, pos2, playerNumber);
                    changeStatus(3);
                } else
                    poswasTaken = true;

                if (mst.checkMill(true) || mst.checkMill(false)){
                    changeStatus(2);
                    phase = 0;
                }
                if(mst.winConditionOne(playerNumber)){
                    phase = 4;
                    changeStatus(5);
                    System.out.println("player " + playerNumber + "won the Game!");
                }
                //System.out.println("postaken " + poswasTaken );
                if (phase == 2 && !poswasTaken) {
                    mst.stillMill(playerNumber);
                    playerChange();
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        //use later for 4 fade
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int radius = pane.getWidth() / 14;
        int circleDiameter = 30;
        int circleRadius = circleDiameter / 2;
        boolean poswasTaken = false;
        //System.out.println(e.getX() + " " + e.getY());
        //System.out.println(phase);
        //System.out.println(playerNumber);
        if(true){
        // biggest rect
        // pos 1 2 3
        if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50) {   //point [1]
            pos = 1;
        }
        if (e.getX() > ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50) {   //point [2]
            pos = 2;
        }
        if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50) {   //point [3]
            pos = 3;
        }
        // pos 10 & 15
        if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 50) {   //point [10]
            pos = 10;
        }
        if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 50) {   //point [15]
            pos = 15;
        }
        //pos 22 23 24
        if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50) {   //point [22]
            pos = 22;
        }
        if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50) {   //point [23]
            pos = 23;
        }
        if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50) {   //point [24]
            pos = 24;
        }
        // middle rect
        //pos 4 5 6
        if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50) {   //point [4]
            pos = 4;
        }
        if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50) {   //point [5]
            pos = 5;
        }
        if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50) {   //point [6]
            pos = 6;
        }
        // pos 11 14
        if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 50) {   //point [11]
            pos = 11;
        }
        if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 50) {   //point [14]
            pos = 14;
        }
        //pos 19 20 21
        if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50) {   //point [19]
            pos = 19;
        }
        if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 1 + radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50) {   //point [20]
            pos = 20;
        }
        if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50) {   //point [21]
            pos = 21;
        }
        //small rect
        //pos 7 8 9
        if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50) {   //point [4]
            pos = 7;
        }
        if (e.getX() > ((this.getWidth() / 2) - radius) + radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50) {   //point [5]
            pos = 8;
        }
        if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50) {   //point [6]
            pos = 9;
        }
        // pos 12 13
        if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 50) {   //point [12]
            pos = 12;
        }
        if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 50) {   //point [13]
            pos = 13;
        }
        //pos 16 17 18
        if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 50) {   //point [16]
            pos = 16;
        }
        if (e.getX() > ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 50) {   //point [17]
            pos = 17;
        }
        if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 50) {   //point [18]
            pos = 18;
        }
    }
        if (phase == 1) {
            if (pos != 0 && mst.posTaken(pos)) {
                mst.add(pos,true,playerNumber);
                pane.repaint(pos, playerNumber);
                //System.out.println("max: " + maxstones + " count: " + count);
            }
            else{
                poswasTaken = true;
            }
        }
        if(phase == 0) {
            if (!mst.posTaken(pos) && mst.sameplayerStone(pos, playerNumber) && mst.removeStones(pos, playerNumber)) {
                pane.removeStone(pos);
                maxstones--;
                //System.out.println("max: " + maxstones + " count: " + count);
                if(!phaseChange){
                    changeStatus(1);
                    phase = 1;
                }else {
                    if(mst.getPlayerStones(!playerNumber) <= 3){
                        phase = 3;
                        changeStatus(4);
                        playerJump = playerNumber;
                        playerChange();
                    }else {
                        changeStatus(3);
                        phase = 2;
                        playerChange();
                    }
                }
                count--;
            }
        }
        if(count > 3 && (mst.checkMill(true) || mst.checkMill(false)) && phase == 1){
            changeStatus(2);
            phase = 0;
        }

        if((mst.winConditionOne(true) || mst.winConditionOne(false)) && count == maxstones){
            phase = 4;
            playerNumber = !playerNumber;
            changeStatus(5);
        }
        if(phase == 1 && !poswasTaken){
            playerChange();
            takemove();
            count++;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int radius = pane.getWidth() / 14;
        int circleDiameter = 30;
        int circleRadius = circleDiameter / 2;
            if(phase == 2){
                onlyOnce = false;
            }
            if(phase == 3){
                if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50) {   //point [1]
                    pos3 = 1;
                }
                if (e.getX() > ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50) {   //point [2]
                    pos3 = 2;
                }
                if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50) {   //point [3]
                    pos3 = 3;
                }
                // pos 10 & 15
                if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 50) {   //point [10]
                    pos3 = 10;
                }
                if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 50) {   //point [15]
                    pos3 = 15;
                }
                //pos 22 23 24
                if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50) {   //point [22]
                    pos3 = 22;
                }
                if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50) {   //point [23]
                    pos3 = 23;
                }
                if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50) {   //point [24]
                    pos3 = 24;
                }
                // middle rect
                //pos 4 5 6
                if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50) {   //point [4]
                    pos3 = 4;
                }
                if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50) {   //point [5]
                    pos3 = 5;
                }
                if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50) {   //point [6]
                    pos3 = 6;
                }
                // pos 11 14
                if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 50) {   //point [11]
                    pos3 = 11;
                }
                if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 50) {   //point [14]
                    pos3 = 14;
                }
                //pos 19 20 21
                if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50) {   //point [19]
                    pos3 = 19;
                }
                if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 1 + radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50) {   //point [20]
                    pos3 = 20;
                }
                if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50) {   //point [21]
                    pos3 = 21;
                }
                //small rect
                //pos 7 8 9
                if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50) {   //point [4]
                    pos3 = 7;
                }
                if (e.getX() > ((this.getWidth() / 2) - radius) + radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50) {   //point [5]
                    pos3 = 8;
                }
                if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50) {   //point [6]
                    pos3 = 9;
                }
                // pos 12 13
                if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 50) {   //point [12]
                    pos3 = 12;
                }
                if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 50) {   //point [13]
                    pos3 = 13;
                }
                //pos 16 17 18
                if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 50) {   //point [16]
                    pos3 = 16;
                }
                if (e.getX() > ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 50) {   //point [17]
                    pos3 = 17;
                }
                if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 50) {   //point [18]
                    pos3 = 18;
                }

                if(pos != pos3 && mst.posTaken(pos3) && playerJump == playerNumber){
                    pane.moveStone(pos,pos3,playerNumber);
                    mst.moveStone(pos,pos3,playerNumber);

                    if(mst.checkMill(true) || mst.checkMill(false)){
                        phase = 4;
                        playerNumber = !playerNumber;
                        changeStatus(5);
                    }
                    if(phase == 3){
                        playerChange();
                    }
                }
                else{
                    phase = 2;
                }
            }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Changes Status on label depending on phase and which state (condition for label) it is currently in
     * @param state represents the possibilities that happen
     */
    private void changeStatus(int state){
        if(state == 1) {
            if (!playerNumber) {
                pane.setPlayerStatus("Player 2 place");
            } else {
                pane.setPlayerStatus("Player 1 place");
            }
        }
        else if(state == 2){
                pane.setPlayerStatus("Remove");
            }
        else if(state == 3){
            if (!playerNumber) {
                pane.setPlayerStatus("Player 2 move");
            } else {
                pane.setPlayerStatus("Player 1 move");
            }
        }
        else if(state == 4){
            if (!playerNumber) {
                pane.setPlayerStatus("Player 2 jump");
            } else {
                pane.setPlayerStatus("Player 1 jump");
            }
        }
        else if(state == 5){
            if (playerNumber) {
                pane.setPlayerStatus("Player 1 won");
            } else {
                pane.setPlayerStatus("Player 2 won");
            }
        }
    }


}



