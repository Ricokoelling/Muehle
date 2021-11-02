import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class ExampleForButtons extends JFrame implements MouseInputListener {

    private JButton button1;
    private JPanel panel1;

    public static void main(String[] args) {
        ExampleForButtons test = new ExampleForButtons();
    }




    ExampleForButtons(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920 , 1080);
        this.add(panel1);
        this.addMouseMotionListener(this);
        this.setResizable(true);
        //this.addWindowListener(exitListener);
        this.setVisible(true);

        //Makes the button invisible but clickable
        button1.setOpaque(false);
        button1.setContentAreaFilled(false);
        button1.setBorderPainted(false);

        //Checks if the Mouse is on the button
        button1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //System.out.println("Entered");
            }
        });
    }

    public void paint(Graphics g){

        Graphics2D g2D =  (Graphics2D) g;
        g2D.drawLine(0,0,500,500);



    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("lol");
        //phase 2
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println(e.getX()  + " " + e.getY());
        //always has been
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //phase 1
    }

    @Override
    public void mousePressed(MouseEvent e) {

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


