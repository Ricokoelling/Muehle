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
        int circleDiameter      = 30;
        int circleRadius        = circleDiameter/2;
        int[][] xyOfPoints      = new int[24][2];


        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(5));

        //Rectangles
        for(int i=1;i<=numbersOfRectangles;i++){
            int x=(this.getWidth() / 2) - radius*i;
            int y=(this.getHeight() / 2) - radius*i;
            //System.out.println("oberster Punkt "+i+":"+x+"\t"+y);

            g2D.drawRect(x, y, diameter*i, diameter*i);

            //Upper Line
            g2D.fillOval(x - circleRadius, y - circleRadius, circleDiameter, circleDiameter);
            g2D.fillOval(x + 1 * radius * i - circleRadius, y - circleRadius, circleDiameter, circleDiameter);
            g2D.fillOval(x + 2 * radius * i - circleRadius, y - circleRadius, circleDiameter, circleDiameter);

            //Middle Line
            g2D.fillOval(x - circleRadius, y + radius * i- circleRadius, circleDiameter, circleDiameter);
            g2D.fillOval(x + 2 * radius * i - circleRadius, y + radius * i - circleRadius, circleDiameter, circleDiameter);

            //Last Line
            g2D.fillOval(x - circleRadius, y + 2 * radius * i - circleRadius, circleDiameter, circleDiameter);
            g2D.fillOval(x + 1 * radius * i - circleRadius, y + 2 * radius * i - circleRadius, circleDiameter, circleDiameter);
            g2D.fillOval(x + 2 * radius * i - circleRadius, y + 2 * radius * i- circleRadius, circleDiameter, circleDiameter);

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
        }

    }
