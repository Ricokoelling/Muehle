import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class playBoard extends JFrame implements MouseInputListener {
    private static final MyPanel pane = new MyPanel();
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final PhaseOne phaseOne = new PhaseOne();
    private int diameter = screenSize.width / 7;
    private int radius = screenSize.width / 14;
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
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
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
        if(phase == 1) {
            if (true) {   //position on screen point [0,0]
                phaseOne.putStones(playerNumber, 0, 0);
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



