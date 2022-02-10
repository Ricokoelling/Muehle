package ClientSide;

import javax.swing.*;
import java.io.IOException;

public class GUISwingWorker extends SwingWorker<Boolean, String> {

    private final Client client;
    private int phase = 1;
    private boolean playerNumber;
    private final MyPanel pane;
    private final playBoardClient pbC;
    private boolean reset = false;
    private boolean allowed = true;


    public GUISwingWorker(Client client, boolean playerNumber, MyPanel pane, playBoardClient pbC) {
        this.client = client;
        this.playerNumber = playerNumber;
        this.pane = pane;
        this.pbC = pbC;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        System.out.println("[CLIENT] Wait for allowed...");
        while (true) {
            Thread.sleep(20);
            if (pbC.reset) {
                reset = true;
            }
            if(client.isDisconnect()){
                pbC.disconnect();
                reset = true;
                break;
            }
            if(client.isGiveup()){
                reset = true;
                break;
            }
            if (client.waitForAllowed()) {
                System.out.println(client.getState());
                allowed = client.isAllowed();
                break;
            }
        }
        return null;
    }

    @Override
    protected void done() {
        System.out.println("allowed: " + allowed);
        if (!reset && allowed) {
            System.out.println("[CLIENT] GUISwingWorker: state " + client.getState() + " pos1: " + client.getPos1() + " pos2: " + client.getPos2() + " playnumber: " + client.isPlayerNumber());
            playerNumber = client.isPlayerNumber();
            if (client.getState() == 1) {
                pbC.changeStatus(1, !playerNumber);
                pane.repaint(client.getPos1(), playerNumber);

            } else if (client.getState() == 2) {
                phase = 0;
                pane.repaint(client.getPos1(), playerNumber);
                pbC.changeStatus(2, playerNumber);

            } else if (client.getState() == 3) {
                pbC.changeStatus(2, !client.isPlayerNumber());
                pane.repaint(client.getPos1(), playerNumber);

            } else if (client.getState() == 4) {
                pbC.changeStatus(1, !playerNumber);
                pane.removeStone(client.getPos1());

            } else if (client.getState() == 5) {
                pbC.changeStatus(3, playerNumber);
                pane.repaint(client.getPos1(), playerNumber);

            } else if (client.getState() == 6) {
                phase = 2;
                pbC.changeStatus(3, !client.isPlayerNumber());
                pane.repaint(client.getPos1(), playerNumber);

            } else if (client.getState() == 7) {
                pane.moveStone(client.getPos1(), client.getPos2(), playerNumber);
                if (pbC.phase3) {
                    pbC.changeStatus(4, !playerNumber);
                    phase = 3;
                } else {
                    pbC.changeStatus(3, !playerNumber);
                    phase = 2;
                }

            } else if (client.getState() == 8) {
                pane.moveStone(client.getPos1(), client.getPos2(), playerNumber);

            } else if (client.getState() == 9) {
                pane.removeStone(client.getPos1());
                pbC.changeStatus(3, !playerNumber);
                phase = 2;

            } else if (client.getState() == 10) {
                pane.removeStone(client.getPos1());
                pbC.changeStatus(3, !playerNumber);

            } else if (client.getState() == 11) {
                pane.removeStone(client.getPos1());
                pbC.changeStatus(3, !playerNumber);

            } else if (client.getState() == 12) {
                phase = 3;
                pane.removeStone(client.getPos1());
                pbC.changeStatus(4, playerNumber);

            } else if (client.getState() == 13) {
                pane.moveStone(client.getPos1(), client.getPos2(), playerNumber);

                if (pbC.isBoothphase3()) {
                    phase = 3;
                    pbC.changeStatus(4, !playerNumber);
                } else {
                    phase = 2;
                    pbC.changeStatus(3, !playerNumber);
                }
            } else if (client.getState() == 14) {
                pane.moveStone(client.getPos1(), client.getPos2(), playerNumber);
                phase = 3;
                pbC.changeStatus(4, !playerNumber);

            }else if(client.getState() == 15) {
                pane.removeStone(client.getPos1());
                phase = 3;
                pbC.changeStatus(3, !playerNumber);
            }else if(client.getState() == 18){

                pane.removeStone(client.getPos1());
                phase = 3;
                pbC.setBoothphase3(true);
                pbC.changeStatus(4,!playerNumber);

            }else if(client.getState() == 20){

                pane.moveStone(client.getPos1(), client.getPos2(), playerNumber);
                pbC.changeStatus(4,!playerNumber);

            }else if(client.getState() == 21){

                pane.moveStone(client.getPos1(), client.getPos2(), playerNumber);
                phase = 0;
                pbC.changeStatus(2, playerNumber);

            } else if (client.getState() == 22) {

                phase = 0;
                pbC.changeStatus(2, !playerNumber);

            }else if(client.getState() == 23) {

                pane.moveStone(client.getPos1(), client.getPos2(), playerNumber);
                phase = 4;
                pbC.changeStatus(5, playerNumber);
                pbC.reset();
                try {
                    pbC.disconnect();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            }else if(client.getState() == 26){

                pane.repaint(client.getPos1(),playerNumber);
                pbC.changeStatus(3,!playerNumber);
                phase = 3;

            } else if (client.getState() == 1000) {
                System.out.println("reset accept");
                pbC.reset();
            }
        }
        if (allowed) {
            pbC.phase = phase;
            new WartenSwingWorker(this.client, this.playerNumber, pane, this.pbC).execute();
        } else {
            System.out.println("[CLIENT] Do your move again!");
            pbC.setThisplayerMove();
        }
        super.done();
    }

}
