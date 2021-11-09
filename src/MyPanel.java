import javax.swing.*;
import java.awt.*;
public class MyPanel extends JPanel {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public MyPanel() {
        this.setSize(screenSize.width, screenSize.height);
    }

    public void paint(Graphics g) {
        int diameter            = this.getWidth() / 7;
        int radius              = this.getWidth() / 14;
        int numbersOfRectangles = 3;


        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(5));

        //Rectangles
        for(int i=1;i<numbersOfRectangles+1;i++){
            g2D.drawRect((this.getWidth() / 2) - radius*i, (this.getHeight() / 2) - radius*i, diameter*i, diameter*i);
        }

        //Lines
        //Line 1
        g2D.drawLine((this.getWidth() / 2) - radius * 3, this.getHeight() / 2, (this.getWidth() / 2) - radius, this.getHeight() / 2);
        //Line 2
        g2D.drawLine((this.getWidth() / 2), (this.getHeight() / 2) - radius * 3, this.getWidth() / 2, (this.getHeight() / 2) - radius);
        //Line 3
        g2D.drawLine((this.getWidth() / 2) + radius, this.getHeight() / 2, (this.getWidth() / 2) + diameter * 3 / 2, this.getHeight() / 2);
        //Line 4
        g2D.drawLine(this.getWidth() / 2, (this.getHeight() / 2) + radius, this.getWidth() / 2, (this.getHeight() / 2) + radius * 3);

        //Circle for mouseListener
            //biggest rect
                //top
        g2D.fillOval((this.getWidth() / 2) - radius * 3 - 15, (this.getHeight() / 2) - radius * 3 - 15, 30, 30);
        g2D.fillOval((this.getWidth() / 2) - radius * 3 + ((diameter * 3) / 2) - 15, (this.getHeight() / 2) - radius * 3 - 15, 30, 30);
        g2D.fillOval((this.getWidth() / 2) - radius * 3 + (diameter * 3) - 15, (this.getHeight() / 2) - radius * 3 - 15, 30, 30);

                //side
        g2D.fillOval((this.getWidth() / 2) - radius * 3 - 15, (this.getHeight() / 2) - radius * 3 - 15 + ((diameter * 3) / 2), 30, 30);
        g2D.fillOval((this.getWidth() / 2) - radius * 3 + (diameter * 3) - 15, (this.getHeight() / 2) - radius * 3 - 15 + ((diameter * 3) / 2), 30, 30);
                //bottom
        g2D.fillOval((this.getWidth() / 2) - radius * 3 - 15, (this.getHeight() / 2) - radius * 3 - 15 + (diameter * 3), 30, 30);
        g2D.fillOval((this.getWidth() / 2) - radius * 3 + ((diameter * 3) / 2) - 15, (this.getHeight() / 2) - radius * 3 - 15 + (diameter * 3), 30, 30);
        g2D.fillOval((this.getWidth() / 2) - radius * 3 + (diameter * 3) - 15, (this.getHeight() / 2) - radius * 3 - 15 + (diameter * 3), 30, 30);

            //mid rect
                //top
        g2D.fillOval((this.getWidth() / 2) - radius * 2 - 15 , (this.getHeight() / 2) - radius * 2 - 15 , 30, 30);
        g2D.fillOval((this.getWidth() / 2) - radius * 2 + ((diameter * 2) / 2)- 15 , (this.getHeight() / 2) - radius * 2 - 15 , 30, 30);
        g2D.fillOval((this.getWidth() / 2) - radius * 2 + (diameter * 2 )- 15 , (this.getHeight() / 2) - radius * 2 - 15 , 30, 30);
                //side
        g2D.fillOval((this.getWidth() / 2) - radius * 2 - 15 , (this.getHeight() / 2) - radius * 2 + ((diameter * 2) / 2) - 15 , 30, 30);
        g2D.fillOval((this.getWidth() / 2) - radius * 2 + (diameter * 2 )- 15 , (this.getHeight() / 2) - radius * 2 + ((diameter * 2) / 2) - 15 , 30, 30);
                //bottom
        g2D.fillOval((this.getWidth() / 2) - radius * 2 - 15 , (this.getHeight() / 2) - radius * 2 + (diameter * 2 ) - 15 , 30, 30);
        g2D.fillOval((this.getWidth() / 2) - radius * 2 + ((diameter * 2) / 2)- 15 , (this.getHeight() / 2) - radius * 2 + (diameter * 2 ) - 15 , 30, 30);
        g2D.fillOval((this.getWidth() / 2) - radius * 2 + (diameter * 2 )- 15 , (this.getHeight() / 2) - radius * 2 + (diameter * 2 ) - 15 , 30, 30);

            //smalles rect
                //top
        g2D.fillOval((this.getWidth() / 2) - radius - 15 , (this.getHeight() / 2) - radius - 15 , 30, 30);
        g2D.fillOval((this.getWidth() / 2) - radius + diameter - 15 , (this.getHeight() / 2) - radius - 15 , 30, 30);
                //botttom
        g2D.fillOval((this.getWidth() / 2) - radius - 15 , (this.getHeight() / 2) - radius + diameter - 15 , 30, 30);
        g2D.fillOval((this.getWidth() / 2) - radius + diameter - 15 , (this.getHeight() / 2) - radius + diameter - 15 , 30, 30);
        }

    }
