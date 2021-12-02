import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MyPanel extends JPanel {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final HashMap<Integer,Boolean> map = new HashMap<>();
    protected static Label playerStatus = new Label("Player 1 place") ;

    public MyPanel() {
        this.setSize(screenSize.width, screenSize.height);
        //playerStatus.setOpaque(false);
        this.setLayout(new FlowLayout());

        //Define Font and Font Size with this function
        //Syntax : Label.setFont(new Font(Fontname(String), Font.PLAIN, Font Size in Pixel))
        playerStatus.setFont(new Font("Arial", Font.PLAIN, 50));
        playerStatus.setBackground(Color.WHITE);
        add(playerStatus);
    }

    public void repaint(int pos, boolean playerNumb) {
        map.put(pos,playerNumb);
        super.repaint();
    }

    public void removeStone(int pos){
        map.remove(pos);
        repaint();
    }

    public void moveStone(int pos1,int pos2, boolean playerNumb){
        map.remove(pos1);
        repaint(pos2,playerNumb);
    }
    public void setPlayerStatus(String playerStatus) {
        MyPanel.playerStatus.setText(playerStatus);
    }

    private void drawStone(Graphics2D g2D, int pos , boolean playerNumb){
        int radius          = this.getWidth() / 14;
        if(playerNumb){
            g2D.setColor(Color.BLACK);
        }
        else{
            g2D.setColor(Color.GRAY);
        }
        //System.out.println("Pos:"+pos);
        int circleDiameter = 50;
        int circleRadius = circleDiameter / 2;
        switch (pos) {
            case  1->  g2D.fillOval((this.getWidth() / 2) - radius * 3                  - circleRadius, (this.getHeight() / 2) - radius * 3                     - circleRadius, circleDiameter, circleDiameter);
            case  2->  g2D.fillOval((this.getWidth() / 2) - radius * 3 + radius * 3     - circleRadius, (this.getHeight() / 2) - radius * 3                     - circleRadius, circleDiameter, circleDiameter);
            case  3->  g2D.fillOval((this.getWidth() / 2) - radius * 3 + 2 * radius * 3 - circleRadius, (this.getHeight() / 2) - radius * 3                     - circleRadius, circleDiameter, circleDiameter);
            case  4->  g2D.fillOval((this.getWidth() / 2) - radius * 2                  - circleRadius, (this.getHeight() / 2) - radius * 2                     - circleRadius, circleDiameter, circleDiameter);
            case  5->  g2D.fillOval((this.getWidth() / 2) - radius * 2 + radius * 2     - circleRadius, (this.getHeight() / 2) - radius * 2                     - circleRadius, circleDiameter, circleDiameter);
            case  6->  g2D.fillOval((this.getWidth() / 2) - radius * 2 + 2 * radius * 2 - circleRadius, (this.getHeight() / 2) - radius * 2                     - circleRadius, circleDiameter, circleDiameter);
            case  7->  g2D.fillOval((this.getWidth() / 2) - radius                      - circleRadius, (this.getHeight() / 2) - radius                         - circleRadius, circleDiameter, circleDiameter);
            case  8->  g2D.fillOval((this.getWidth() / 2) - radius + radius             - circleRadius, (this.getHeight() / 2) - radius                         - circleRadius, circleDiameter, circleDiameter);
            case  9->  g2D.fillOval((this.getWidth() / 2) - radius + 2 * radius         - circleRadius, (this.getHeight() / 2) - radius                         - circleRadius, circleDiameter, circleDiameter);
            case 10 -> g2D.fillOval((this.getWidth() / 2) - radius * 3                  - circleRadius, (this.getHeight() / 2) - radius * 3 + radius * 3        - circleRadius, circleDiameter, circleDiameter);
            case 11 -> g2D.fillOval((this.getWidth() / 2) - radius * 2                  - circleRadius, (this.getHeight() / 2) - radius * 2 + radius * 2        - circleRadius, circleDiameter, circleDiameter);
            case 12 -> g2D.fillOval((this.getWidth() / 2) - radius                      - circleRadius, (this.getHeight() / 2) - radius + radius                - circleRadius, circleDiameter, circleDiameter);
            case 13 -> g2D.fillOval((this.getWidth() / 2) - radius + 2 * radius         - circleRadius, (this.getHeight() / 2) - radius + radius                - circleRadius, circleDiameter, circleDiameter);
            case 14 -> g2D.fillOval((this.getWidth() / 2) - radius * 2 + 2 * radius * 2 - circleRadius, (this.getHeight() / 2) - radius * 2 + radius * 2        - circleRadius, circleDiameter, circleDiameter);
            case 15 -> g2D.fillOval((this.getWidth() / 2) - radius * 3 + 2 * radius * 3 - circleRadius, (this.getHeight() / 2) - radius * 3 + radius * 3        - circleRadius, circleDiameter, circleDiameter);
            case 16 -> g2D.fillOval((this.getWidth() / 2) - radius                      - circleRadius, (this.getHeight() / 2) - radius + 2 * radius            - circleRadius, circleDiameter, circleDiameter);
            case 17 -> g2D.fillOval((this.getWidth() / 2) - radius + radius             - circleRadius, (this.getHeight() / 2) - radius + 2 * radius            - circleRadius, circleDiameter, circleDiameter);
            case 18 -> g2D.fillOval((this.getWidth() / 2) - radius + 2 * radius         - circleRadius, (this.getHeight() / 2) - radius + 2 * radius            - circleRadius, circleDiameter, circleDiameter);
            case 19 -> g2D.fillOval((this.getWidth() / 2) - radius * 2                  - circleRadius, (this.getHeight() / 2) - radius * 2 + 2 * radius * 2    - circleRadius, circleDiameter, circleDiameter);
            case 20 -> g2D.fillOval((this.getWidth() / 2) - radius * 2 + radius * 2     - circleRadius, (this.getHeight() / 2) - radius * 2 + 2 * radius * 2    - circleRadius, circleDiameter, circleDiameter);
            case 21 -> g2D.fillOval((this.getWidth() / 2) - radius * 2 + 2 * radius * 2 - circleRadius, (this.getHeight() / 2) - radius * 2 + 2 * radius * 2    - circleRadius, circleDiameter, circleDiameter);
            case 22 -> g2D.fillOval((this.getWidth() / 2) - radius * 3                  - circleRadius, (this.getHeight() / 2) - radius * 3 + 2 * radius * 3    - circleRadius, circleDiameter, circleDiameter);
            case 23 -> g2D.fillOval((this.getWidth() / 2) - radius * 3 + radius * 3     - circleRadius, (this.getHeight() / 2) - radius * 3 + 2 * radius * 3    - circleRadius, circleDiameter, circleDiameter);
            case 24 -> g2D.fillOval((this.getWidth() / 2) - radius * 3 + 2 * radius * 3 - circleRadius, (this.getHeight() / 2) - radius * 3 + 2 * radius * 3    - circleRadius, circleDiameter, circleDiameter);
        }
    }
    public void paint(Graphics g) {
        int diameter = this.getWidth() / 7;
        int radius = this.getWidth() / 14;
        int numbersOfRectangles = 3;

        Graphics2D g2D = (Graphics2D) g;
        g2D.clearRect(0, 0, this.getWidth(), this.getHeight());
        g2D.setStroke(new BasicStroke(5));
        g2D.setColor(Color.BLACK);
        //Rectangles
        for (int i = 1; i <= numbersOfRectangles; i++) {
            int x = (this.getWidth() / 2) - radius * i;
            int y = (this.getHeight() / 2) - radius * i;
            //System.out.println("oberster Punkt "+i+":"+x+"\t"+y);

            g2D.drawRect(x, y, diameter * i, diameter * i);
            //Upper Line
            /*g2D.fillOval(x - circleRadius, y - circleRadius, circleDiameter, circleDiameter);
            g2D.fillOval(x + 1 * radius * i - circleRadius, y - circleRadius, circleDiameter, circleDiameter);
            g2D.fillOval(x + 2 * radius * i - circleRadius, y - circleRadius, circleDiameter, circleDiameter);
            //Middle Line
            g2D.fillOval(x - circleRadius, y + radius * i- circleRadius, circleDiameter, circleDiameter);
            g2D.fillOval(x + 2 * radius * i - circleRadius, y + radius * i - circleRadius, circleDiameter, circleDiameter);

            //Last Line
            g2D.fillOval(x - circleRadius, y + 2 * radius * i - circleRadius, circleDiameter, circleDiameter);
            g2D.fillOval(x + 1 * radius * i - circleRadius, y + 2 * radius * i - circleRadius, circleDiameter, circleDiameter);
            g2D.fillOval(x + 2 * radius * i - circleRadius, y + 2 * radius * i- circleRadius, circleDiameter, circleDiameter);*/

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

        for(Integer key : map.keySet()){
            drawStone(g2D,key, map.get(key));
        }
    }

}

