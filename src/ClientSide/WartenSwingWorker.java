package ClientSide;

import ClientSide.Client;
import ClientSide.MyPanel;
import ClientSide.playBoardClient;

import javax.swing.*;
import java.util.List;

public class WartenSwingWorker extends SwingWorker<Boolean, String> {

    private final Client client;
    private int state = -1, phase = 1;
    private boolean playerNumber;
    private final MyPanel pane;
    private final playBoardClient pbC;
    private boolean alllowed = true;
    private boolean phase3 = false;
    private boolean reset = false;
    private int count = 0;


    public WartenSwingWorker(Client client, boolean playerNumber, MyPanel pane, playBoardClient pbC) {
        this.client = client;
        this.playerNumber = playerNumber;
        this.pane = pane;
        this.pbC = pbC;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        System.out.println("[CLIENT] Wait for Data.Data...");
        while (true) {
            Thread.sleep(20);
            if (client.waitforData()) {
                break;
            }
            if (pbC.reset) {
                reset = true;
            }
        }
        if (count > 0) {
            done();
        }
        return false;
    }

    @Override
    protected void done() {
        System.out.println("[ClientSide.Client] Server Response!");
        state = client.getState();
        System.out.println("[CLIENT] ClientSide.WartenSwingWorker: state " + state + " pos1: " + client.getPos1() + " pos2: " + client.getPos2() + " playnumber: " + client.isPlayerNumber());
        if (state == 1) {
            pbC.changeStatus(1, !client.isPlayerNumber());
            pane.repaint(client.getPos1(), client.isPlayerNumber());
            System.out.println("[ClientSide.Client] Your Move! to place ");

        } else if (state == 2) {
            phase = 0;
            pbC.changeStatus(2, client.isPlayerNumber());
            System.out.println("[CLIENT] Remove a Stone: ");

        } else if (state == 3) {
            phase = 0;
            pbC.changeStatus(2, client.isPlayerNumber());
            pane.repaint(client.getPos1(), client.isPlayerNumber());


        } else if (state == 4) {
            pane.removeStone(client.getPos1());
            phase = 1;
            pbC.changeStatus(1, !client.isPlayerNumber());
            System.out.println("[ClientSide.Client] Your Move! to place again");

        } else if (state == 5) {
            pane.repaint(client.getPos1(), client.isPlayerNumber());
            phase = 2;
            pbC.changeStatus(3, !client.isPlayerNumber());
            System.out.println("[ClientSide.Client] Your Move! to move ");

        } else if (state == 6) {
            phase = 2;
            pbC.changeStatus(3, !client.isPlayerNumber());

        } else if (state == 7) {
            phase = 2;
            pane.moveStone(client.getPos1(), client.getPos2(), client.isPlayerNumber());
            pbC.changeStatus(3, !client.isPlayerNumber());
            System.out.println("[ClientSide.Client] Your Move! to move " + phase);

        } else if (state == 8) {
            phase = 0;
            pbC.changeStatus(2, client.isPlayerNumber());
            System.out.println("[CLIENT] Remove a Stone: ");

        } else if (state == 9) {
            phase = 0;
            pbC.changeStatus(2, client.isPlayerNumber());
            pane.moveStone(client.getPos1(), client.getPos2(), client.isPlayerNumber());

        } else if (state == 10) {
            pbC.changeStatus(3, !client.isPlayerNumber());
            pane.removeStone(client.getPos1());
            phase = 2;
            System.out.println("[ClientSide.Client] Your Move! to move again ");

        } else if (state == 11) {
            pbC.setPhase3(true);
            phase = 3;
            pbC.changeStatus(4, !client.isPlayerNumber());

        } else if (state == 12) {
            pbC.setPhase3(true);
            pbC.changeStatus(4, !client.isPlayerNumber());
            pane.removeStone(client.getPos1());
            phase = 3;
            System.out.println("[ClientSide.Client] Your Move! to jump ");

        } else if (state == 13) {
            pbC.setPhase3(true);
            pane.moveStone(client.getPos1(), client.getPos2(), client.isPlayerNumber());
            pbC.changeStatus(3, !client.isPlayerNumber());
            phase = 2;
            System.out.println("[ClientSide.Client] Your Move! after jump ");

        } else if (state == 14) {
            pane.moveStone(client.getPos1(), client.getPos2(), client.isPlayerNumber());
            pbC.changeStatus(4, !client.isPlayerNumber());
            phase = 3;
            System.out.println("[ClientSide.Client] Your Move! to jump ");
        } else if (state == 15) {
            phase = 0;
            pbC.changeStatus(2, client.isPlayerNumber());
            System.out.println("[CLIENT] Remove a Stone: ");

        } else if (state == 16) {
            phase = 0;
            pbC.changeStatus(2, !playerNumber);
            pane.moveStone(client.getPos1(), client.getPos2(), client.isPlayerNumber());

        } else if (state == 17) {
            pbC.changeStatus(4, !client.isPlayerNumber());
            pane.removeStone(client.getPos1());
            phase = 3;
            System.out.println("[ClientSide.Client] Your Move! ");
        } else if (state == 18) {
            pbC.changeStatus(4, !client.isPlayerNumber());
            phase = 3;
        } else if (state == 19) {
            pbC.setBoothphase3(true);
            pbC.changeStatus(4, !client.isPlayerNumber());
            pane.removeStone(client.getPos1());
            phase = 3;
            System.out.println("[ClientSide.Client] Your Move! ");
        } else if (state == 20) {
            pbC.setBoothphase3(true);
            pane.moveStone(client.getPos1(), client.getPos2(), client.isPlayerNumber());
            pbC.changeStatus(4, !client.isPlayerNumber());
            phase = 3;
            System.out.println("[ClientSide.Client] Your Move! boothphase3 ");

        } else if (state == 21) {
            pane.moveStone(client.getPos1(), client.getPos2(), client.isPlayerNumber());
            phase = 0;
            pbC.changeStatus(2, client.isPlayerNumber());
            System.out.println("[CLIENT] Remove a Stone: ");
        } else if (state == 22) {
            phase = 0;
            pbC.changeStatus(2, !playerNumber);
        } else if (state == 23) {
            phase = 5;
            pbC.changeStatus(5, !playerNumber);

        } else if (state == 24) {
            phase = 5;
            pane.removeStone(client.getPos1());
            pbC.changeStatus(5, !playerNumber);

        } else if (state == 25) {
            System.out.println("phase: " + phase);
            phase = 2;
            pane.removeStone(client.getPos1());
            pbC.changeStatus(3, playerNumber);
        } else if (state == 26) {
            phase = 3;
            pane.repaint(client.getPos1(), client.isPlayerNumber());
            pbC.changeStatus(4, client.isPlayerNumber());
            System.out.println("[CLIENT] Your move to jumpp");

        } else if (state == 27) {
            phase = 2;
            pane.repaint(client.getPos1(), client.isPlayerNumber());
            pbC.changeStatus(3, !client.isPlayerNumber());
            System.out.println("[CLIENT] Your move to move after place!");

        } else if (state == 1000) {
            System.out.println("reset data");
            System.out.println("[CLIENT] Your move!");
            pbC.reset();
        }
        if (state == 3 || state == 6 || state == 9 || state == 11 || state == 26 || state == 16 || state == 18) {
            try {
                count++;
                doInBackground();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        count = 0;
        pbC.phase = phase;
        pbC.setThisplayerMove();
        super.done();
    }

    @Override
    protected void process(List<String> chunks) {
        super.process(chunks);
    }

}
