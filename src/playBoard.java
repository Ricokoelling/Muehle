import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class playBoard extends JFrame implements MouseInputListener {
    private static final MyPanel pane = new MyPanel();
    private Master mst = new Master();
    protected boolean playerNumber = true;  //true --> player 1 ----- false --> player 2
    protected int phase = 1;
    private int pos;
    private int count = 0;

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

        if(count != 18) {
            return true;
        }else{
            phase = 2;
        }
        return false;
    }
    private void playerChange(){
        if (playerNumber) {
            playerNumber = false;
        } else {
            playerNumber = true;
        }
        changeStatus(1);
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
        int circleDiameter = 30;
        int circleRadius = circleDiameter / 2;
        boolean poswasTaken = false;
        //System.out.println(e.getX() + " " + e.getY());
        //System.out.println(phase);
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
            if (takemove() && mst.posTaken(pos)) {
                mst.add(pos,true,playerNumber);
                pane.repaint(pos, playerNumber);
            }
            else{
                poswasTaken = true;
            }
        }
        if(phase == 0) {
            if (!mst.posTaken(pos) && mst.sameplayerStone(pos, playerNumber) && mst.removeStones(pos, playerNumber)) {
                pane.removeStone(pos);
                changeStatus(1);
                phase = 1;
                count--;
            }
        }
        if(count > 3 && (mst.test(true) || mst.test(false))){
            changeStatus(2);
            phase = 0;
        }
        if(phase == 1 && !poswasTaken){
            playerChange();
            poswasTaken = false;
        }
        count++;
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

    private void changeStatus(int state){
        if(state == 1) {
            if (!playerNumber) {
                MyPanel.playerStatus.setText("Player 2");
            } else {
                MyPanel.playerStatus.setText("Player 1");
            }
        }
        else if(state == 2){
            MyPanel.playerStatus.setText("Remove");
        }
    }

}



