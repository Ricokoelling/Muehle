import javax.swing.*;
import javax.swing.*;
import java.awt.*;

public class StartScreen extends JFrame{
    public static final JFrame myFrame = new JFrame("Muehle");
    private static final JButton btn1 = new JButton("Start");
    private static final JButton btn2 = new JButton("Options");
    private static final JButton btn3 = new JButton("Exit");
    private static final JPanel pane = new JPanel(new GridBagLayout());
    private static GridBagConstraints c = new GridBagConstraints();
    private static final JLabel label = new JLabel();
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    public StartScreen(){

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(screenSize.width, screenSize.height);
        this.setResizable(false);
        label.setText("Muehle Game Extreme");
        label.setFont(new Font("MV Boli", Font.PLAIN, 50));
        label.setForeground(new Color(102, 0, 51));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        btn1.setPreferredSize(new Dimension(this.getWidth() / 12 , this.getHeight()/ 12 ));
        btn2.setPreferredSize(new Dimension(this.getWidth() / 12 , this.getHeight()/ 12 ));
        btn3.setPreferredSize(new Dimension(this.getWidth() / 12 , this.getHeight()/ 12 ));
        c.insets = new Insets(10,10,10,10);
        c.gridx = 0;
        c.gridy = 1;
        pane.add(btn1,c);
        c.gridx = 0;
        c.gridy = 2;
        pane.add(btn2,c);
        c.gridx = 0;
        c.gridy = 3;
        pane.add(btn3,c);
        pane.add(label);
        this.add(pane);
        this.setVisible(true);
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
