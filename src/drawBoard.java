import java.awt.*;

public class drawBoard {

    public static void main(String args[]) {

        MyFrame f = new MyFrame();
        f.setTitle("Drawing Graphics in a Frame Directly");
        f.setBounds(100,50,1280,720);
        f.setVisible(true);
    }

    static class MyFrame extends Frame {
        public void paint(Graphics g) {
            int diameter=200;
            int radius=diameter/2;

            //Rectangles
            //First Rectangle
            g.drawRect((this.getWidth()/2)-radius,(this.getHeight()/2)-radius,diameter,diameter);
            //Second Rectangle
            g.drawRect((this.getWidth()/2)-radius*2,(this.getHeight()/2)-radius*2,diameter*2,diameter*2);
            //Third Rectangle
            g.drawRect((this.getWidth()/2)-radius*3,(this.getHeight()/2)-radius*3,diameter*3,diameter*3);

            //Lines
            //Line 1
            g.drawLine((this.getWidth()/2)-radius*3,this.getHeight()/2, (this.getWidth()/2)-radius,this.getHeight()/2);
            //Line 2
            g.drawLine((this.getWidth()/2), (this.getHeight()/2)-radius*3, this.getWidth()/2, (this.getHeight()/2)-radius);
            //Line 3
            g.drawLine((this.getWidth()/2)+radius, this.getHeight()/2, (this.getWidth()/2)+diameter*3/2,this.getHeight()/2);
            //Linie 4
            g.drawLine(this.getWidth()/2, (this.getHeight()/2)+radius, this.getWidth()/2, (this.getHeight()/2)+radius*3);
        }
    }

}



