import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class playBoard extends JFrame implements MouseInputListener {
    private static final MyPanel pane = new MyPanel();
    private Master mst = new Master();
    protected int playerNumber = 1;  //takes playerNumber after playerchange
    protected int phase = 1;
    private int playerOneStones = 9;
    private int playerTwoStones = 9;
    private int pos;
    private int count = 0;
    private boolean b =false;

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
    private boolean takemove(){
        int answer =  0;    //JOptionPane.showConfirmDialog(null,"Did you finish your move?");

        if(playerTwoStones != 0) {
            return true;
        }else{
            phase = 2;
        }
        return false;
    }
    private void playerChange(){

        if (playerNumber == 1) {
            playerNumber = 2;
            playerOneStones--;
        } else {
            playerNumber = 1;
            playerTwoStones--;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(phase == 2){

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
        int circleDiameter      = 30;
        int circleRadius        = circleDiameter/2;
        //System.out.println(e.getX() + " " + e.getY());
        changePlayerStatus();

        if(phase == 1) {
            // biggest rect
            // pos 1 2 3
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50) {   //point [1]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 1;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }

            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50) {   //point [2]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 2;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }

            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50) {   //point [3]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 3;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            // pos 10 & 15
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 50) {   //point [10]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 10;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 50) {   //point [15]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 15;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            //pos 22 23 24
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50) {   //point [22]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 22;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50) {   //point [23]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 23;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50) {   //point [24]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 24;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }

            // middle rect
            //pos 4 5 6
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50) {   //point [4]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 4;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50) {   //point [5]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 5;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50) {   //point [6]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 6;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }

            // pos 11 14
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 50) {   //point [11]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 11;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 50) {   //point [14]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 14;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            //pos 19 20 21
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50) {   //point [19]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 19;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 1 + radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50) {   //point [20]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 20;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50) {   //point [21]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 21;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }

            //smalles rect
            //pos 7 8 9
            if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50) {   //point [4]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 7;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50) {   //point [5]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 8;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50) {   //point [6]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 9;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            // pos 12 13
            if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 50) {   //point [12]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 12;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 50) {   //point [13]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 13;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            //pos 16 17 18
            if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 50) {   //point [16]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 16;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 50) {   //point [17]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 17;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 50) {   //point [18]
                if (phase == 1) {
                    if (takemove()) {
                        pos = 18;
                        mst.add(pos,true,playerNumber);
                        pane.repaint(pos, playerNumber);
                        playerChange();
                        
                    }
                }
            }
        }
        count++;
            if( count > 6) {
                if (mst.test(true)) {
                }
            }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void changePlayerStatus(){
        if(!b){
            MyPanel.playerStatus.setText("Player 2");
            b=true;
        }else{
            MyPanel.playerStatus.setText("Player 1");
            b=false;
        }
    }

}



