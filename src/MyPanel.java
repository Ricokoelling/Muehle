import javax.swing.*;
import java.awt.*;
public class MyPanel extends JPanel {
    public void paint(Graphics g) {
        int diameter = 200;
        int radius = diameter / 2;

        //Rectangles
        //thisirst Rectangle
        g.drawRect((this.getWidth() / 2) - radius, (this.getHeight() / 2) - radius, diameter, diameter);
        //Second Rectangle
        g.drawRect((this.getWidth() / 2) - radius * 2, (this.getHeight() / 2) - radius * 2, diameter * 2, diameter * 2);
        //Third Rectangle
        g.drawRect((this.getWidth() / 2) - radius * 3, (this.getHeight() / 2) - radius * 3, diameter * 3, diameter * 3);

        //Lines
        //Line 1
        g.drawLine((this.getWidth() / 2) - radius * 3, this.getHeight() / 2, (this.getWidth() / 2) - radius, this.getHeight() / 2);
        //Line 2
        g.drawLine((this.getWidth() / 2), (this.getHeight() / 2) - radius * 3, this.getWidth() / 2, (this.getHeight() / 2) - radius);
        //Line 3
        g.drawLine((this.getWidth() / 2) + radius, this.getHeight() / 2, (this.getWidth() / 2) + diameter * 3 / 2, this.getHeight() / 2);
        //Linie 4
        g.drawLine(this.getWidth() / 2, (this.getHeight() / 2) + radius, this.getWidth() / 2, (this.getHeight() / 2) + radius * 3);


        }
    }
