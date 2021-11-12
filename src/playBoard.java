import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class playBoard extends JFrame implements MouseInputListener {
    private static final MyPanel pane = new MyPanel();
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final PhaseOne phaseOne = new PhaseOne();
    public int playerNumber = 1;  //takes playerNumber after playerchange
    public int phase = 1;
    private int playerOneStones = 9;
    private int playerTwoStones = 9;
    private testMuehle test = new testMuehle();


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

    private boolean playerChange(){
        int answer = JOptionPane.showConfirmDialog(null,"Did you finish your move?");

        if(answer == 0) {
            if (playerNumber == 1) {
                playerNumber = 2;
                playerOneStones--;
            } else {
                playerNumber = 1;
                playerTwoStones--;
            }
            return true;
        }
        if(playerTwoStones == 0){
            phase = 2;
        }
        return false;
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
        System.out.println(e.getX() + " " + e.getY());

        if(phase == 1) {
            // biggest rect
            // pos 1 2 3
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3)- circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50 ) {   //point [1]
               if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 1);
                }
                else
                        test.removeStone(playerNumber,1);


                }
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50 ) {   //point [2]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 2);
                }
                else
                    test.removeStone(playerNumber,2);

            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50 ) {   //point [3]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 3);
                }
                else
                    test.removeStone(playerNumber,3);
            }
            // pos 10 & 15
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 50 ) {   //point [10]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 10);
                }
                else
                    test.removeStone(playerNumber,10);;
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 50 ) {   //point [15]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 15);
                }
                else
                    test.removeStone(playerNumber,15);
            }
            //pos 22 23 24
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 ) {   //point [22]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 22);
                }
                else
                    test.removeStone(playerNumber,22);
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3  - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3  - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 ) {   //point [23]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 23);
                }
                else
                    test.removeStone(playerNumber,23);
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3  - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3  - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 ) {   //point [24]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 24);
                }
                else
                    test.removeStone(playerNumber,24);
            }

            // middle rect
            //pos 4 5 6
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2)- circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50 ) {   //point [4]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 4);
                }
                else
                    test.removeStone(playerNumber,4);
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50 ) {   //point [5]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 5);
                }
                else
                    test.removeStone(playerNumber,5);
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50 ) {   //point [6]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 6);
                }
                else
                    test.removeStone(playerNumber,6);
            }

            // pos 11 14
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 50 ) {   //point [11]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 11);
                }
                else
                    test.removeStone(playerNumber,11);
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 50 ) {   //point [14]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 14);
                }
                else
                    test.removeStone(playerNumber,14);
            }
            //pos 19 20 21
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 ) {   //point [19]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 19);
                }
                else
                    test.removeStone(playerNumber,19);
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2  - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) +  1 + radius * 2  - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 ) {   //point [20]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 20);
                }
                else
                    test.removeStone(playerNumber,20);
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2  - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2  - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 ) {   //point [21]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 21);
                }
                else
                    test.removeStone(playerNumber,21);
            }

            //smalles rect
            //pos 7 8 9
            if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius)- circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50 ) {   //point [4]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 7);
                }
                else
                    test.removeStone(playerNumber,7);
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50 ) {   //point [5]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 8);
                }
                else
                    test.removeStone(playerNumber,8);
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50 ) {   //point [6]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 9);
                }
                else
                    test.removeStone(playerNumber,9);
            }
            // pos 12 13
            if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 50 ) {   //point [12]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 12);
                }
                else
                    test.removeStone(playerNumber,12);
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 50 ) {   //point [13]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 13);
                }
                else
                    test.removeStone(playerNumber,13);
            }
            //pos 16 17 18
            if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 50 ) {   //point [16]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 16);
                }
                else
                    test.removeStone(playerNumber,16);
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 1 + radius  - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 50 ) {   //point [17]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 17);
                }
                else
                    test.removeStone(playerNumber,17);
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 50 ) {   //point [18]
                if(phase == 1){
                    if (playerChange())
                        phaseOne.putStones(playerNumber, 18);
                }
                else
                    test.removeStone(playerNumber,18);
            }

            if(test.test()){
                phase = 0;
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

}



