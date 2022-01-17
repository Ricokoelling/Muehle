import javax.swing.*;
import java.awt.*;

public class GUISwingWorker extends SwingWorker<Boolean,String> {

    private final Client client;
    private int phase = 1;
    private boolean playerNumber;
    private final MyPanel pane;
    private final playBoardClient pbC;
    private boolean reset = false;


    public GUISwingWorker(Client client, boolean playerNumber, MyPanel pane, playBoardClient pbC) {
        this.client = client;
        this.playerNumber = playerNumber;
        this.pane = pane;
        this.pbC = pbC;
    }
    @Override
    protected Boolean doInBackground() throws Exception {
        System.out.println("[CLIENT] Wait for allowed...");
        while (true){
            if(client.waitForAllowed()){
                break;
            }
            if(pbC.reset){
                reset = true;
            }
            Thread.sleep(50);
        }
        return null;
    }
    @Override
    protected void done() {
        System.out.println("[CLIENT] Allowed move!");
        if(!reset) {
            if (client.getState() == 1) {
                pbC.changeStatus(1, !playerNumber);
                pane.repaint(client.getPos1(), playerNumber);

            } else if (client.getState() == 2) {
                pane.removeStone(client.getPos1());
                if (!pbC.phase3) {
                    pbC.changeStatus(1, !playerNumber);
                } else {
                    pbC.changeStatus(3, !playerNumber);
                }

            } else if (client.getState() == 3) {
                pbC.changeStatus(2, !client.isPlayerNumber());
                pane.repaint(client.getPos1(), playerNumber);

            } else if (client.getState() == 4) {
                pbC.changeStatus(1, client.isPlayerNumber());
                pane.repaint(client.getPos1(), playerNumber);

            } else if (client.getState() == 5) {
                pbC.changeStatus(3, client.isPlayerNumber());
                pane.repaint(client.getPos1(), playerNumber);

            } else if (client.getState() == 6) {
                phase = 2;
                pbC.changeStatus(3, !client.isPlayerNumber());
                pane.repaint(client.getPos1(), playerNumber);

            } else if (client.getState() == 7) {
                pane.moveStone(client.getPos1(), client.getPos2(), !client.isPlayerNumber());
                if (pbC.phase3) {
                    pbC.changeStatus(4, !playerNumber);
                    phase = 3;
                } else {
                    pbC.changeStatus(3, !playerNumber);
                    phase = 2;
                }

            } else if (client.getState() == 8) {
                pbC.changeStatus(3, !playerNumber);
                pane.removeStone(client.getPos1());
                phase = 2;

            } else if (client.getState() == 9) {
                pane.removeStone(client.getPos1());
                pbC.changeStatus(2, !playerNumber);

            } else if (client.getState() == 10) {
                pane.moveStone(client.getPos1(), client.getPos2(), !client.isPlayerNumber());
                pbC.changeStatus(2, !playerNumber);
                phase = 2;

            } else if (client.getState() == 11) {
                pane.moveStone(client.getPos1(), client.getPos2(), !client.isPlayerNumber());
                pbC.changeStatus(4, !playerNumber);

            } else if (client.getState() == 12) {
                phase = 3;
                pane.removeStone(client.getPos1());
                pbC.changeStatus(4, playerNumber);

            } else if (client.getState() == 13) {
                pane.moveStone(client.getPos1(), client.getPos2(), !client.isPlayerNumber());

                if (pbC.isBoothphase3()) {
                    phase = 3;
                    pbC.changeStatus(4, !playerNumber);
                } else {
                    phase = 2;
                    pbC.changeStatus(3, !playerNumber);
                }
            } else if (client.getState() == 22) {
                phase = 0;
                pbC.changeStatus(2, !playerNumber);
            }
        }
        pbC.phase = phase;
        new WartenSwingWorker(this.client, this.playerNumber, pane,this.pbC).execute();
        super.done();
    }

}
