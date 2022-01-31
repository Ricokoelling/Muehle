import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class playBoardClient extends JFrame implements MouseInputListener, ActionListener {
    private Color thisColor;
    private Color otherColor;
    private static final MyPanel pane = new MyPanel();
    private static final JMenuBar menubar = new JMenuBar();
    private final Client client = new Client();
    private final JMenuItem resetItem;
    private final JMenuItem exitItem;
    private int pos = 0;
    private int pos2 = 0;
    private int pos3 = 0;
    private int count = 0;
    private boolean onlyOnce = false;
    private boolean phaseChange = false;
    private int maxstones = 17;
    private boolean boothphase3 = false;
    protected boolean playerNumber;
    protected int phase = 1;
    private int state = -1;
    protected boolean thisplayerMove = true;
    protected boolean lethimwait = true;
    protected boolean phase3 = false;
    protected boolean reset = false;


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

    public void Login(String username, char[] password){
        client.sendData(username,password);
        thisplayerMove = false;
    }
    public void Register(String username, char[] password, String alias){

    }
    public void setThisplayerMove() {
        this.thisplayerMove = true;
        new Thread() {
            public void run() {
                while (true) {
                    if (client.isReset()) {
                        reset();
                        break;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void setPhase3(boolean phase3) {
        this.phase3 = phase3;
    }

    public void setBoothphase3(boolean boothphase3) {
        this.boothphase3 = boothphase3;
    }

    public boolean isBoothphase3() {
        return boothphase3;
    }

    public void setOtherColor(Color otherColor) {
        this.otherColor = otherColor;
    }

    public playBoardClient() throws IOException, InterruptedException {
        this.setSize(1080, 720);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(pane);
        thisColor = Color.BLACK;
        otherColor = Color.GRAY;
        JMenu optionsMenu = new JMenu("Options");
        resetItem = new JMenuItem("Reset");
        exitItem = new JMenuItem("Exit");

        exitItem.addActionListener(this);
        resetItem.addActionListener(this);

        optionsMenu.add(resetItem);
        optionsMenu.add(exitItem);
        menubar.add(optionsMenu);
        this.setJMenuBar(menubar);
        this.addWindowListener(exitListener);
        this.setVisible(true);

    }

    /**
     * reset the howl game
     */
    protected void reset() {
        phase = 1;
        maxstones = 17;
        count = 0;
        pos = 0;
        pos2 = 0;
        pos3 = 0;
        onlyOnce = false;
        changeStatus(1, playerNumber);
        pane.reset();
        if (!playerNumber) {
            new WartenSwingWorker(this.client, false, pane, this).execute();
        } else {
            lethimwait = false;
            thisplayerMove = true;
        }
        client.setReset(false);

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int radius = pane.getWidth() / 14;
        int circleDiameter = 30;
        int circleRadius = circleDiameter / 2;
        if (phase == 2 && thisplayerMove) {
            // biggest rect
            // pos 1 2 3
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [1]
                pos2 = 1;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [2]
                pos2 = 2;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [3]
                pos2 = 3;
            }
            // pos 10 & 15
            else if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 60) {   //point [10]
                pos2 = 10;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 60) {   //point [15]
                pos2 = 15;
            }
            //pos 22 23 24
            else if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 10 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [22]
                pos2 = 22;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [23]
                pos2 = 23;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [24]
                pos2 = 24;
            }
            // middle rect
            //pos 4 5 6
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [4]
                pos2 = 4;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [5]
                pos2 = 5;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [6]
                pos2 = 6;
            }
            // pos 11 14
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 60) {   //point [11]
                pos2 = 11;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 60) {   //point [14]
                pos2 = 14;
            }
            //pos 19 20 21
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [19]
                pos2 = 19;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 1 + radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [20]
                pos2 = 20;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [21]
                pos2 = 21;
            }
            //small rect
            //pos 7 8 9
            else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [4]
                pos2 = 7;
            } else if (e.getX() > ((this.getWidth() / 2) - radius) + radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [5]
                pos2 = 8;
            } else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [6]
                pos2 = 9;
            }
            // pos 12 13
            else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 60) {   //point [12]
                pos2 = 12;
            } else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 60) {   //point [13]
                pos2 = 13;
            }
            //pos 16 17 18
            else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [16]
                pos2 = 16;
            } else if (e.getX() > ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [17]
                pos2 = 17;
            } else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [18]
                pos2 = 18;
            }

            if (pos2 != pos && !onlyOnce && pos != 0) {
                onlyOnce = true;
                client.sendData(7, pos, pos2);
                new GUISwingWorker(this.client, this.playerNumber, pane, this).execute();
                thisplayerMove = false;
                count++;
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
        if (thisplayerMove) {
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [1]
                pos = 1;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [2]
                pos = 2;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [3]
                pos = 3;
            }
            // pos 10 & 15
            else if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 60) {   //point [10]
                pos = 10;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 60) {   //point [15]
                pos = 15;
            }
            //pos 22 23 24
            else if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 10 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [22]
                pos = 22;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [23]
                pos = 23;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [24]
                pos = 24;
            }
            // middle rect
            //pos 4 5 6
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [4]
                pos = 4;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [5]
                pos = 5;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [6]
                pos = 6;
            }
            // pos 11 14
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 60) {   //point [11]
                pos = 11;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 60) {   //point [14]
                pos = 14;
            }
            //pos 19 20 21
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [19]
                pos = 19;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 1 + radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [20]
                pos = 20;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [21]
                pos = 21;
            }
            //small rect
            //pos 7 8 9
            else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [4]
                pos = 7;
            } else if (e.getX() > ((this.getWidth() / 2) - radius) + radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [5]
                pos = 8;
            } else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [6]
                pos = 9;
            }
            // pos 12 13
            else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 60) {   //point [12]
                pos = 12;
            } else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 60) {   //point [13]
                pos = 13;
            }
            //pos 16 17 18
            else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [16]
                pos = 16;
            } else if (e.getX() > ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [17]
                pos = 17;
            } else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [18]
                pos = 18;
            }
            if (phase == 1) {
                if (pos != 0) {
                    client.sendData(1, pos);
                    new GUISwingWorker(this.client, playerNumber, pane, this).execute();
                    thisplayerMove = false;
                    count++;
                }
            } else if (phase == 0) {
                if (pos != 0) {
                    client.sendData(2, pos);
                    new GUISwingWorker(this.client, playerNumber, pane, this).execute();
                    thisplayerMove = false;
                    count--;
                    maxstones--;
                }
            }
               /* if ((mst.getPlayerStones(true) < 3 || mst.getPlayerStones(false) < 3) && phaseChange) {
                    phase = 4;
                    changeStatus(5, playerNumber);
                }
                if ((mst.winConditionOne(true) || mst.winConditionOne(false)) && count == maxstones) {
                    phase = 4;
                    changeStatus(5, playerNumber);
                }*/
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        int radius = pane.getWidth() / 14;
        int circleDiameter = 30;
        int circleRadius = circleDiameter / 2;
        if (phase == 2) {
            onlyOnce = false;
        }
        if (phase == 3 && thisplayerMove) {
            // biggest rect
            // pos 1 2 3
            if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [1]
                pos3 = 1;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [2]
                pos3 = 2;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) - circleRadius + 60) {   //point [3]
                pos3 = 3;
            }
            // pos 10 & 15
            else if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 60) {   //point [10]
                pos3 = 10;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + radius * 3 - circleRadius + 60) {   //point [15]
                pos3 = 15;
            }
            //pos 22 23 24
            else if (e.getX() > ((this.getWidth() / 2) - radius * 3) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) - circleRadius + 10 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [22]
                pos3 = 22;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 1 + radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [23]
                pos3 = 23;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 3) + 2 * radius * 3 - circleRadius + 60) {   //point [24]
                pos3 = 24;
            }
            // middle rect
            //pos 4 5 6
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [4]
                pos3 = 4;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [5]
                pos3 = 5;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) - circleRadius + 60) {   //point [6]
                pos3 = 6;
            }
            // pos 11 14
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 60) {   //point [11]
                pos3 = 11;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + radius * 2 - circleRadius + 60) {   //point [14]
                pos3 = 14;
            }
            //pos 19 20 21
            else if (e.getX() > ((this.getWidth() / 2) - radius * 2) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [19]
                pos3 = 19;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 1 + radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [20]
                pos3 = 20;
            } else if (e.getX() > ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius * 2) + 2 * radius * 2 - circleRadius + 60) {   //point [21]
                pos3 = 21;
            }
            //small rect
            //pos 7 8 9
            else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [4]
                pos3 = 7;
            } else if (e.getX() > ((this.getWidth() / 2) - radius) + radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [5]
                pos3 = 8;
            } else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) - circleRadius + 60) {   //point [6]
                pos3 = 9;
            }
            // pos 12 13
            else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 60) {   //point [12]
                pos3 = 12;
            } else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + radius - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) + radius - circleRadius + 60) {   //point [13]
                pos3 = 13;
            }
            //pos 16 17 18
            else if (e.getX() > ((this.getWidth() / 2) - radius) - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [16]
                pos3 = 16;
            } else if (e.getX() > ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 1 + radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [17]
                pos3 = 17;
            } else if (e.getX() > ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius - 10 && e.getX() < ((this.getWidth() / 2) - radius) + 2 * radius - circleRadius + 40 && e.getY() > ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 10 && e.getY() < ((this.getHeight() / 2) - radius) + 2 * radius - circleRadius + 60) {   //point [18]
                pos3 = 18;
            }
            if (pos != pos3 && pos != 0) { //if (pos != pos3 && mst.posTaken(pos3) && playerJump == playerNumber)
                client.sendData(13, pos, pos3);
                onlyOnce = false;
                new GUISwingWorker(this.client, playerNumber, pane, this).execute();
                thisplayerMove = false;
                count++;
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
     *
     * @param state represents the possibilities that happen
     */
    void changeStatus(int state, boolean plNumb) {
        if (state == 1) {
            if (!plNumb) {
                pane.setPlayerStatus("Player 2 place your Stone!", otherColor);
            } else {
                pane.setPlayerStatus("Player 1 place your Stone", thisColor);
            }
        } else if (state == 2) {
            if (!plNumb) pane.setPlayerStatus("Player 2 remove a Stone!", otherColor);
            else pane.setPlayerStatus("Player 1 remove a Stone!", thisColor);
        } else if (state == 3) {
            if (!plNumb) {
                pane.setPlayerStatus("Player 2 move your Stone!", otherColor);
            } else {
                pane.setPlayerStatus("Player 1 move your Stone!", thisColor);
            }
        } else if (state == 4) {
            if (!plNumb) {
                pane.setPlayerStatus("Player 2 jump", otherColor);
            } else {
                pane.setPlayerStatus("Player 1 jump", thisColor);
            }
        } else if (state == 5) {
            if (plNumb) {
                pane.setPlayerStatus("!!!Player 1 Won!!!", otherColor);
            } else {
                pane.setPlayerStatus("!!!Player 2 Won!!!", thisColor);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitItem) {
            //test.endConnection();
            System.exit(0);
        } else if (e.getSource() == resetItem) {
            playerNumber = client.isPlayerNumberOr();
            pane.reset();
            client.sendData(1000, 0);
            reset();
        }
    }
}
