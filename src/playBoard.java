import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class playBoard extends JFrame implements MouseInputListener {
    private static final MyPanel pane = new MyPanel();
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final PhaseOne phaseOne = new PhaseOne();
    int diameter = screenSize.width / 7;
    int radius = screenSize.width / 14;


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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(pane);
        this.setResizable(true);
        //this.addWindowListener(exitListener);
        this.setVisible(true);
        this.addMouseMotionListener(this);
        start();
    }

    public void start(){
        phaseOne.putStones();
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println(e.getX()  + " " + e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        phaseOne.setState(true);
        if (e.getX() > (screenSize.width / 2) - radius * 3 - 15 && e.getX() < (screenSize.width / 2) - radius * 3 + 15 && e.getY() > (screenSize.height / 2) - radius * 3 - 15 && e.getY() < (screenSize.height / 2) - radius * 3 + 45) {
            phaseOne.setePosition(e.getX(),e.getY());
            System.out.println("hello");
        }
        if (e.getX() > (screenSize.width / 2) + ((diameter * 3) / 2) - radius * 3 - 15 && e.getX() < (screenSize.width / 2) + ((diameter * 3) / 2) - radius * 3 + 15 && e.getY() > (screenSize.height / 2) - radius * 3 - 15 && e.getY() < (screenSize.height / 2) - radius * 3 + 45) {
            System.out.println("your mum gay2");
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



