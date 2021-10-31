import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartScreen extends JFrame implements ActionListener{
    private static final JButton btn1 = new JButton("Start");
    private static final JButton btn2 = new JButton("Options");
    private static final JButton btn3 = new JButton("Exit");
    private static final JPanel pane = new JPanel(new GridBagLayout());
    private static final GridBagConstraints c = new GridBagConstraints();
    private static final JLabel label = new JLabel();
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    public StartScreen(){
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
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
        c.gridy = 2;
        pane.add(btn2,c);
        c.gridy = 3;
        pane.add(btn3,c);
        pane.add(label);
        this.add(pane);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn1){
            this.dispose();
            playBoard playField = new playBoard();
        }
        if(e.getSource() == btn2){
            this.dispose();
            Options op = new Options();
        }
        if(e.getSource() == btn3){
            System.exit(0);
        }
    }
}
