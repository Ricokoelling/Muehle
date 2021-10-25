import javax.swing.*;
import java.awt.*;

public class StartScreen extends JFrame{
    public static final Frame myFrame = new Frame("Muehle");
    private static final JButton btn1 = new JButton("Start");
    private static final JButton btn2 = new JButton("Options");
    private static final JButton btn3 = new JButton("Exit");
    private static final JPanel pane = new JPanel();

    public StartScreen() throws HeadlessException {
        myFrame.setSize(700,1000);
        myFrame.setVisible(true);
        btn1.setSize(200,50);
        btn2.setSize(200,50);
        pane.add(btn1);
        pane.add(btn2);
        pane.add(btn3);
        myFrame.add(pane);
        startpoint();


    }
    private void createFrame(){
        JFrame next = new JFrame();
        next.setSize(200,300);
        next.setVisible(true);
        myFrame.setVisible(false);
        next.addWindowListener( new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    myFrame.setVisible(true);
                }
        });
    }
    private void startpoint(){
        Options op = new Options();
        btn1.addActionListener(e -> createFrame());
        btn2.addActionListener(e-> op.start());
        btn3.addActionListener(e -> System.exit(0));
    }

}
