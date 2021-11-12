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

    private void playerChange(){
        int answer = JOptionPane.showConfirmDialog(null,"Did you finish your move?");
        if(answer == 0) {
            if (playerNumber == 1) {
                playerNumber = 2;
            } else
                playerNumber = 1;
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
        int height = pane.getHeight();
        int width = pane.getWidth();
        int diameter = pane.getWidth() / 7;
        int radius = pane.getWidth() / 14;
        int circleDiameter      = 30;
        int circleRadius        = circleDiameter/2;
        System.out.println(e.getX() + " " + e.getY());
        System.out.println((((this.getWidth() / 2) - radius * 3) + 2  * radius * 3 - circleRadius -50) + " " +(((this.getWidth() / 2) - radius * 3) + 2  * radius * 3 - circleRadius + 50));
        System.out.println((((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50) + " " + (((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50));
        if(phase == 1) {
            // biggest rect
            // pos 1 2 3
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3)- circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50 ) {   //point [1]
                //phaseOne.putStones(playerNumber, 8);
                System.out.println("pos: 1");
                playerChange();
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50 ) {   //point [2]
               //phaseOne.putStones(playerNumber, 8);
                System.out.println("pos: 2");
                playerChange();
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 50 ) {   //point [3]
                //phaseOne.putStones(playerNumber, 8);
                System.out.println("pos: 3");
                playerChange();
            }
            // pos 10 & 15
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 50 ) {   //point [10]
                //phaseOne.putStones(playerNumber, 8);
                System.out.println("pos: 10");
                playerChange();
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 50 ) {   //point [15]
                //phaseOne.putStones(playerNumber, 8);
                System.out.println("pos: 15");
                playerChange();
            }
            //pos 22 23 24
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 ) {   //point [22]
                //phaseOne.putStones(playerNumber, 7);
                System.out.println("pos: 22");
                playerChange();
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3  - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3  - circleRadius + 50 && e.getY() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 ) {   //point [23]
                //phaseOne.putStones(playerNumber, 7);
                System.out.println("pos: 23");
                playerChange();
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3  - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3  - circleRadius + 50 && e.getY() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 50 && e.getY() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 50 ) {   //point [24]
                //phaseOne.putStones(playerNumber, 7);
                System.out.println("pos: 24");
                playerChange();
            }

            // middle rect
            //pos 4 5 6
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2)- circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50 ) {   //point [4]
                //phaseOne.putStones(playerNumber, 8);
                System.out.println("pos: 4");
                playerChange();
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50 ) {   //point [5]
                //phaseOne.putStones(playerNumber, 8);
                System.out.println("pos: 5");
                playerChange();
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 50 ) {   //point [6]
                //phaseOne.putStones(playerNumber, 8);
                System.out.println("pos: 6");
                playerChange();
            }

            // pos 11 14
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 50 ) {   //point [11]
                //phaseOne.putStones(playerNumber, 8);
                System.out.println("pos: 11");
                playerChange();
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 50 ) {   //point [14]
                //phaseOne.putStones(playerNumber, 8);
                System.out.println("pos: 14");
                playerChange();
            }
            //pos 19 20 21
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 ) {   //point [19]
               // phaseOne.putStones(playerNumber, 7);
                System.out.println("pos: 19");
                playerChange();
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2  - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) +  1 + radius * 2  - circleRadius + 50 && e.getY() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 ) {   //point [20]
               // phaseOne.putStones(playerNumber, 7);
                System.out.println("pos: 20");
                playerChange();
            }
            if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2  - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2  - circleRadius + 50 && e.getY() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 50 && e.getY() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 50 ) {   //point [21]
                //phaseOne.putStones(playerNumber, 7);
                System.out.println("pos: 21");
                playerChange();
            }

            //smalles rect
            //pos 7 8 9
            if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius)- circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50 ) {   //point [4]
                //phaseOne.putStones(playerNumber, 8);
                System.out.println("pos: 7");
                playerChange();
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50 ) {   //point [5]
                //phaseOne.putStones(playerNumber, 8);
                System.out.println("pos: 8");
                playerChange();
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 50 ) {   //point [6]
               // phaseOne.putStones(playerNumber, 8);
                System.out.println("pos: 9");
                playerChange();
            }
            // pos 12 13
            if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 50 ) {   //point [12]
                //phaseOne.putStones(playerNumber, 8);
                System.out.println("pos: 12");
                playerChange();
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 50 ) {   //point [13]
                //phaseOne.putStones(playerNumber, 8);
                System.out.println("pos: 13");
                playerChange();
            }
            //pos 16 17 18
            if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 50 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 50 ) {   //point [16]
                //phaseOne.putStones(playerNumber, 7);
                System.out.println("pos: 16");
                playerChange();
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 1 + radius  - circleRadius + 50 && e.getY() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 ) {   //point [17]
               // phaseOne.putStones(playerNumber, 7);
                System.out.println("pos: 17");
                playerChange();
            }
            if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 && e.getY() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 50 && e.getY() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 50 ) {   //point [18]
               // phaseOne.putStones(playerNumber, 7);
                System.out.println("pos: 18");
                playerChange();
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

}



