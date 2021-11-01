import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


public class ExampleForButtons extends JFrame{

    private JButton button1;
    private JPanel panel1;

    public static void main(String[] args) {
        ExampleForButtons test = new ExampleForButtons();
    }




    ExampleForButtons(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920 , 1080);
        this.add(panel1);
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
                System.out.println("Entered");
            }
        });
    }

    public void paint(Graphics g){

        Graphics2D g2D =  (Graphics2D) g;
        g2D.drawLine(0,0,500,500);

        while(true) {
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            int x = (int) b.getX();
            int y = (int) b.getY();         //gets info from
            System.out.println(y + " " + x);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}

