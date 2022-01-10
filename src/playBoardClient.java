import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Scanner;

public class playBoardClient extends JFrame implements MouseInputListener, ActionListener {
        private     static Color playerOne;
        private     static          Color       playerTwo;
        private     static  final   MyPanel     pane                = new MyPanel();
        private     static  final   JMenuBar    menubar             = new JMenuBar();
        private             final   Master      mst                 = new Master();
        private             final   Client      client = new Client();
        private             final   JMenuItem   resetItem;
        private             final   JMenuItem   plOneColor;
        private             final   JMenuItem   plTwoColor;
        private             final   JMenuItem   exitItem;
        private                     int         pos                 = 0;
        private                     int         pos2                = 0;
        private                     int         pos3                = 0;
        private                     int         count               = 0;
        private                     boolean     onlyOnce            = false;
        private                     boolean     phaseChange         = false;
        private                     int         maxstones           = 17;
        private                     boolean     boothphase3         = false;
        private                     boolean     playerJump;
        protected                   boolean     playerNumber;  //true --> player 1 ----- false --> player 2
        protected                   int         phase               = 1;
        private                     boolean     thisplayerMove      = true;


        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Are You Sure to Close Application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    //test.endConnection();
                    System.exit(0);
                }
            }
        };

        public playBoardClient(boolean playerNumber) throws IOException, InterruptedException {
            this.playerNumber = playerNumber;
            client.sendData(playerNumber);
            this.setSize(1920,1080);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);

            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.add(pane);
            playerOne = Color.BLACK;
            playerTwo = Color.GRAY;
            JMenu optionsMenu = new JMenu("Options");
            JMenu colorMenu = new JMenu("Player Colors");
            resetItem = new JMenuItem("Reset");
            plOneColor = new JMenuItem("PlayerOne Color");
            plTwoColor = new JMenuItem("PlayerTwo Color");
            exitItem = new JMenuItem("Exit");

            exitItem.addActionListener(this);
            resetItem.addActionListener(this);
            plOneColor.addActionListener(this);
            plTwoColor.addActionListener(this);

            colorMenu.add(plOneColor);
            colorMenu.add(plTwoColor);
            optionsMenu.add(resetItem);
            optionsMenu.add(exitItem);
            menubar.add(optionsMenu);
            menubar.add(colorMenu);
            this.setJMenuBar(menubar);
            this.addWindowListener(exitListener);
            this.setVisible(true);
            if(!playerNumber ){
                waitforAnswer();
            }
        }


        /**
         * after every placement the player gets asked if he wants to do his move (currently disabled, if turned back on change for phase 2 and 3 etc.)
         */
        private void takemove(){
            if(maxstones == count){
                if(phase == 1) {
                    checkPhase3();
                    phase = 2;
                    phaseChange = true;
                    if (!playerNumber) {
                        MyPanel.playerStatus.setText("Player 2 move");
                    } else {
                        MyPanel.playerStatus.setText("Player 1 move");
                    }
                }
            }
        }

        /**
         * changes PlayerNumber and depending on phase the state
         */
        private void playerChange(){
            boolean thisplayerMove = false;
            //playerNumber = !playerNumber;
            if(phase == 1) {
                changeStatus(1);
            }
            else if(phase == 2) {
                changeStatus(3);
            }
            else if(phase == 3){
                changeStatus(4);
            } else {
                changeStatus(5);
            }
        }

        public void waitforAnswer() throws IOException, InterruptedException {
            thisplayerMove = false;
            System.out.println("[Client] Waiting for Server....");
            while(true) {
                if(client.waitforData()){
                    break;
                }
                Thread.sleep(50);
            }
            System.out.println("[Client] Server Response!");
            if(client.getPhase() == 1) {
                playerNumber = client.isPlayerNumber();
                pane.repaint(client.getPos1(), playerNumber);
            }
            thisplayerMove = true;
            System.out.println("[Client] Your Move!");
        }
        /**
         * checks if a player has less then 4 stones so he can jump
         * @return true --> one player got less then 4 stones || false --> not one player got less then 4 stones
         */
        private boolean checkPhase3(){
            if(mst.getPlayerStones(!playerNumber) == 3 && mst.getPlayerStones(playerNumber) == 3){
                boothphase3 = true;
                phase = 3;
                changeStatus(4);
                playerChange();
                return true;
            }else {
                if (mst.getPlayerStones(!playerNumber) == 3 && phaseChange) {
                    changeStatus(4);
                    phase = 3;
                    playerJump = !playerNumber;
                    playerChange();
                    return true;
                }
            }
            return false;
        }

        /**
         * reset the howl game
         */
        private void reset(){
            phase = 1;
            maxstones = 17;
            count = 0;
            pos = 0;
            pos2 = 0;
            pos3 = 0;
            onlyOnce = false;
            phaseChange = false;
            boothphase3 = false;
            changeStatus(1);
        }
        @Override
        public void mouseDragged(MouseEvent e) {
            int radius = pane.getWidth() / 14;
            int circleDiameter = 30;
            int circleRadius = circleDiameter / 2;
            boolean poswasTaken = false;
            if(phase == 2){
                // biggest rect
                // pos 1 2 3
                if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [1]
                    pos2 = 1;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [2]
                    pos2 = 2;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [3]
                    pos2 = 3;
                }
                // pos 10 & 15
                else if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 60) {   //point [10]
                    pos2 = 10;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 60) {   //point [15]
                    pos2 = 15;
                }
                //pos 22 23 24
                else if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 10 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [22]
                    pos2 = 22;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [23]
                    pos2 = 23;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [24]
                    pos2 = 24;
                }
                // middle rect
                //pos 4 5 6
                else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [4]
                    pos2 = 4;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [5]
                    pos2 = 5;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [6]
                    pos2 = 6;
                }
                // pos 11 14
                else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 60) {   //point [11]
                    pos2 = 11;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 60) {   //point [14]
                    pos2 = 14;
                }
                //pos 19 20 21
                else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [19]
                    pos2 = 19;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 1 + radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [20]
                    pos2 = 20;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [21]
                    pos2 = 21;
                }
                //small rect
                //pos 7 8 9
                else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [4]
                    pos2 = 7;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius) + radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [5]
                    pos2 = 8;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [6]
                    pos2 = 9;
                }
                // pos 12 13
                else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 60) {   //point [12]
                    pos2 = 12;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 60) {   //point [13]
                    pos2 = 13;
                }
                //pos 16 17 18
                else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [16]
                    pos2 = 16;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [17]
                    pos2 = 17;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [18]
                    pos2 = 18;
                }

                if(pos2 != pos && !onlyOnce && !mst.sameplayerStone(pos,playerNumber)) { //changed sameplayer stone and solved the problem
                    onlyOnce = true;
                    if (mst.freeposNextto(pos2, pos, playerNumber)) { //check if pos2 is free and if it is only one step away
                        pane.moveStone(pos, pos2, playerNumber);
                        changeStatus(3);
                    } else
                        poswasTaken = true;

                    if (mst.checkMill(true) || mst.checkMill(false)){
                        changeStatus(2);
                        phase = 0;
                    }
                    if(mst.winConditionOne(playerNumber)){
                        phase = 4;
                        changeStatus(5);
                    }
                    if (phase == 2 && !poswasTaken) {
                        mst.stillMill();
                        if(!checkPhase3()) {
                            playerChange();
                        }
                    }
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

            //use later for 4 fade
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            int radius = pane.getWidth() / 14;
            int circleDiameter = 30;
            int circleRadius = circleDiameter / 2;
            pos = 0;
            // biggest rect
            // pos 1 2 3
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [1]
                pos = 1;
            }
            else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [2]
                pos = 2;
            }
            else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [3]
                pos = 3;
            }
            // pos 10 & 15
            else if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 60) {   //point [10]
                pos = 10;
            }
            else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 60) {   //point [15]
                pos = 15;
            }
            //pos 22 23 24
            else if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 10 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [22]
                pos = 22;
            }
            else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [23]
                pos = 23;
            }
            else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [24]
                pos = 24;
            }
            // middle rect
            //pos 4 5 6
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [4]
                pos = 4;
            }
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [5]
                pos = 5;
            }
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [6]
                pos = 6;
            }
            // pos 11 14
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 60) {   //point [11]
                pos = 11;
            }
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 60) {   //point [14]
                pos = 14;
            }
            //pos 19 20 21
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [19]
                pos = 19;
            }
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 1 + radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [20]
                pos = 20;
            }
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [21]
                pos = 21;
            }
            //small rect
            //pos 7 8 9
            else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [4]
                pos = 7;
            }
            else if (e.getX() > ((this.getWidth() / 2) - radius) + radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [5]
                pos = 8;
            }
            else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [6]
                pos = 9;
            }
            // pos 12 13
            else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 60) {   //point [12]
                pos = 12;
            }
            else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 60) {   //point [13]
                pos = 13;
            }
            //pos 16 17 18
            else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [16]
                pos = 16;
            }
            else if (e.getX() > ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [17]
                pos = 17;
            }
            else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [18]
                pos = 18;
            }

            if (phase == 1) {
                if (pos != 0) {
                    pane.repaint(pos, playerNumber);
                    System.out.println("lol");
                    client.sendPhaseOne(phase,pos);
                    try {
                        waitforAnswer();
                    } catch (IOException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            if(phase == 0) {
                if (!mst.posTaken(pos) && mst.sameplayerStone(pos, playerNumber) && mst.removeStones(pos, playerNumber)) {
                    pane.removeStone(pos);
                    maxstones--;
                    if (mst.getPlayerStones(!playerNumber) < 3 && phaseChange) {         //checks if the player whos stone got removed has still enough stones to potentially place a mill
                        changeStatus(5);
                        phase = 4;
                    }
                    else {
                        if (!phaseChange) {
                            changeStatus(1);
                            phase = 1;
                        } else {
                            if (!checkPhase3()) {
                                changeStatus(3);
                                phase = 2;
                                playerChange();
                            }
                        }
                    }
                    count--;
                }
            }
            if(count > 3 && (mst.checkMill(true) || mst.checkMill(false)) && phase == 1){
                changeStatus(2);
                phase = 0;
            }
            if((mst.getPlayerStones(true) < 3 || mst.getPlayerStones(false) < 3) && phaseChange){
                phase = 4;
                changeStatus(5);
            }
            if((mst.winConditionOne(true) || mst.winConditionOne(false)) && count == maxstones){
                phase = 4;
                playerNumber = !playerNumber; //
                changeStatus(5);
            }
            if(phase == 1){
                playerChange();
                takemove();
                count++;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int radius = pane.getWidth() / 14;
            int circleDiameter = 30;
            int circleRadius = circleDiameter / 2;
            if(phase == 2){
                onlyOnce = false;
            }
            if(phase == 3){
                // biggest rect
                // pos 1 2 3
                if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [1]
                    pos3 = 1;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [2]
                    pos3 = 2;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [3]
                    pos3 = 3;
                }
                // pos 10 & 15
                else if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 60) {   //point [10]
                    pos3 = 10;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 60) {   //point [15]
                    pos3 = 15;
                }
                //pos 22 23 24
                else if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 10 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [22]
                    pos3 = 22;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [23]
                    pos3 = 23;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [24]
                    pos3 = 24;
                }
                // middle rect
                //pos 4 5 6
                else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [4]
                    pos3 = 4;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [5]
                    pos3 = 5;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [6]
                    pos3 = 6;
                }
                // pos 11 14
                else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 60) {   //point [11]
                    pos3 = 11;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 60) {   //point [14]
                    pos3 = 14;
                }
                //pos 19 20 21
                else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [19]
                    pos3 = 19;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 1 + radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [20]
                    pos3 = 20;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [21]
                    pos3 = 21;
                }
                //small rect
                //pos 7 8 9
                else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [4]
                    pos3 = 7;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius) + radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [5]
                    pos3 = 8;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [6]
                    pos3 = 9;
                }
                // pos 12 13
                else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 60) {   //point [12]
                    pos3 = 12;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 60) {   //point [13]
                    pos3 = 13;
                }
                //pos 16 17 18
                else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [16]
                    pos3 = 16;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [17]
                    pos3 = 17;
                }
                else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius  + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [18]
                    pos3 = 18;
                }
                if(!boothphase3) {
                    if (pos != pos3 && mst.posTaken(pos3) && playerJump == playerNumber) {
                        pane.moveStone(pos, pos3, playerNumber);
                        mst.moveStone(pos, pos3, playerNumber);

                        if (mst.checkMill(playerNumber)){
                            changeStatus(2);
                            phase = 0;
                        }else {
                            phase = 2;
                            playerChange();
                            mst.stillMill();
                            onlyOnce = false;
                        }
                    }
                }
                else{
                    if (pos != pos3 && mst.posTaken(pos3)) {
                        pane.moveStone(pos, pos3, playerNumber);
                        mst.moveStone(pos, pos3, playerNumber);
                        if(mst.checkMill(playerNumber) || mst.checkMill(!playerNumber)){
                            changeStatus(2);
                            phase = 0;
                        }
                        else{
                            mst.stillMill();
                            playerChange();
                        }
                    }
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        /**
         * Changes Status on label depending on phase and which state (condition for label) it is currently in
         * @param state represents the possibilities that happen
         */
        private void changeStatus(int state){
            if(state == 1) {
                if (!playerNumber) {
                    pane.setPlayerStatus("Player 2 place", playerTwo);
                } else {
                    pane.setPlayerStatus("Player 1 place", playerOne);
                }
            }
            else if(state == 2){
                if(!playerNumber)   pane.setPlayerStatus("remove", playerTwo);
                else                pane.setPlayerStatus("remove", playerOne);
            }
            else if(state == 3){
                if (!playerNumber) {
                    pane.setPlayerStatus("Player 2 move", playerTwo);
                } else {
                    pane.setPlayerStatus("Player 1 move", playerOne);
                }
            }
            else if(state == 4){
                if (!playerNumber) {
                    pane.setPlayerStatus("Player 2 jump",playerTwo);
                } else {
                    pane.setPlayerStatus("Player 1 jump", playerOne);
                }
            }
            else if(state == 5){
                if (playerNumber) {
                    pane.setPlayerStatus("Player 1 won",playerOne);
                } else {
                    pane.setPlayerStatus("Player 2 won",playerTwo);
                }
            }
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == exitItem){
                //test.endConnection();
                System.exit(0);
            }
            else if(e.getSource() == resetItem){
                playerNumber = true;
                pane.reset();
                mst.reset();
                reset();
            }
            else if(count == 0) {
                if (e.getSource() == plOneColor) {

                    playerOne = JColorChooser.showDialog(null, "Pick Player 1 Color", Color.BLACK);
                    if(!playerOne.equals(playerTwo)) {
                        pane.setColor(true, playerOne);
                        changeStatus(1);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"This Color is already taken!");
                        playerOne = Color.BLACK;
                        if(playerOne.equals(playerTwo)){
                            playerTwo = Color.GRAY;
                        }
                    }
                } else if (e.getSource() == plTwoColor) {
                    playerTwo = JColorChooser.showDialog(null, "Pick Player 2 Color", Color.GRAY);
                    if(!playerTwo.equals(playerOne)) {
                        pane.setColor(false, playerTwo);
                        changeStatus(1);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"This Color is already taken!");
                        playerTwo = Color.GRAY;
                        if(playerTwo.equals(playerOne)){
                            playerOne = Color.BLACK;
                        }
                    }
                }
            }
        }
    }
