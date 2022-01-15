import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WartenSwingWorker extends SwingWorker<Boolean,String> {

    private final Client client;
    private int state = -1, phase = 1;
    private boolean playerNumber;
    private final MyPanel pane;
    private final playBoardClient pbC;
    private     static final Color       playerOne = Color.BLACK;
    private     static final Color       playerTwo = Color.GRAY;
    private boolean alllowed = true;
    private boolean phase3 = false;


    public WartenSwingWorker(Client client, boolean playerNumber, MyPanel pane, playBoardClient pbC) {
        this.client = client;
        this.playerNumber = playerNumber;
        this.pane = pane;
        this.pbC = pbC;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
            if (client.waitForAllowed() && !pbC.isLethimwait()) {
                System.out.println("[CLIENT] Allowed Move!");
                alllowed = true;
                state = client.getState();
                System.out.println("[CLIENT] State: " + state);
                phase3 = pbC.isPhase3();
                if (state == 1) {
                    changeStatus(1, !playerNumber);
                    pane.repaint(client.getPos1(), playerNumber);

                } else if (state == 2) {
                    pane.removeStone(client.getPos1());
                    if (!phase3) {
                        changeStatus(1, !playerNumber);
                    } else {
                        changeStatus(3, !playerNumber);
                    }

                } else if (state == 3) {
                    changeStatus(2, !client.isPlayerNumber());
                    pane.repaint(client.getPos1(), playerNumber);

                } else if (state == 4) {
                    changeStatus(1, client.isPlayerNumber());
                    pane.repaint(client.getPos1(), playerNumber);

                } else if (state == 5) {
                    changeStatus(3, client.isPlayerNumber());
                    pane.repaint(client.getPos1(), playerNumber);

                } else if (state == 6) {
                    phase = 2;
                    changeStatus(3, !client.isPlayerNumber());
                    pane.repaint(client.getPos1(), playerNumber);

                } else if (state == 7) {
                    pane.moveStone(client.getPos1(), client.getPos2(), !client.isPlayerNumber());
                    if (phase3) {
                        changeStatus(4, !playerNumber);
                        phase = 3;
                    } else {
                        changeStatus(3, !playerNumber);
                        phase = 2;
                    }

                } else if (state == 8) {
                    changeStatus(3, !playerNumber);
                    pane.removeStone(client.getPos1());
                    phase = 2;

                } else if (state == 9) {
                    pane.removeStone(client.getPos1());
                    changeStatus(2, !playerNumber);

                } else if (state == 10) {
                    pane.moveStone(client.getPos1(), client.getPos2(), !client.isPlayerNumber());
                    changeStatus(2, !playerNumber);
                    phase = 2;

                } else if (state == 11) {
                    pane.moveStone(client.getPos1(), client.getPos2(), !client.isPlayerNumber());
                    changeStatus(4, !playerNumber);

                } else if (state == 12) {
                    phase = 3;
                    pane.removeStone(client.getPos1());
                    changeStatus(4, playerNumber);

                } else if (state == 13) {
                    pane.moveStone(client.getPos1(), client.getPos2(), !client.isPlayerNumber());

                    if (pbC.isBoothphase3()) {
                        phase = 3;
                        changeStatus(4, !playerNumber);
                    } else {
                        phase = 2;
                        changeStatus(3, !playerNumber);
                    }
                }else if(state == 22){
                    phase = 0;
                    changeStatus(2,!playerNumber);
                }
            }else if(!pbC.isLethimwait()){
                alllowed = false;
            }
            if (alllowed || pbC.isLethimwait()) {
                if (pbC.isLethimwait()) {
                    pbC.setLethimwait(false);
                }
                do {
                    pbC.askForReset();
                    System.out.println("[Client] Waiting for Server....");
                    if (client.waitforData()) {
                        System.out.println("[Client] Server Response! State: " + client.getState());
                        state = client.getState();
                        if (state == 1) {
                            System.out.println("[CLIENT] PlayerNumber from the other client: " + client.isPlayerNumber());
                            changeStatus(1, !client.isPlayerNumber());
                            pane.repaint(client.getPos1(), client.isPlayerNumber());
                            System.out.println("[Client] Your Move! to place ");

                        } else if (state == 2) {
                            phase = 0;
                            changeStatus(2, client.isPlayerNumber());
                            System.out.println("[CLIENT] Remove a Stone: ");

                        } else if (state == 3) {
                            changeStatus(2, client.isPlayerNumber());
                            pane.repaint(client.getPos1(), client.isPlayerNumber());

                        } else if (state == 4) {
                            pane.removeStone(client.getPos1());
                            phase = 1;
                            changeStatus(1, !client.isPlayerNumber());
                            System.out.println("[Client] Your Move! to place again");

                        } else if (state == 5) {
                            pane.repaint(client.getPos1(), client.isPlayerNumber());
                            phase = 2;
                            changeStatus(3, !client.isPlayerNumber());
                            System.out.println("[Client] Your Move! to move ");

                        } else if (state == 6) {
                            phase = 2;
                            changeStatus(3, !client.isPlayerNumber());

                        } else if (state == 7) {
                            pane.moveStone(client.getPos1(), client.getPos2(), client.isPlayerNumber());
                            changeStatus(3, !client.isPlayerNumber());
                            System.out.println("[Client] Your Move! to move " + phase);

                        } else if (state == 8) {
                            phase = 0;
                            changeStatus(2, client.isPlayerNumber());
                            System.out.println("[CLIENT] Remove a Stone: ");

                        } else if (state == 9) {
                            phase = 0;
                            changeStatus(2, client.isPlayerNumber());
                            pane.moveStone(client.getPos1(), client.getPos2(), client.isPlayerNumber());

                        } else if (state == 10) {
                            changeStatus(3, !client.isPlayerNumber());
                            pane.removeStone(client.getPos1());
                            phase = 2;
                            System.out.println("[Client] Your Move! to move again ");

                        } else if (state == 11) {
                            pbC.setPhase3(true);
                            phase = 3;
                            changeStatus(4, !client.isPlayerNumber());

                        } else if (state == 12) {
                            pbC.setPhase3(true);
                            changeStatus(4, !client.isPlayerNumber());
                            pane.removeStone(client.getPos1());
                            phase = 3;
                            System.out.println("[Client] Your Move! to jump ");

                        } else if (state == 13) {
                            pbC.setPhase3(true);
                            pane.moveStone(client.getPos1(), client.getPos2(), client.isPlayerNumber());
                            changeStatus(3, !client.isPlayerNumber());
                            phase = 2;
                            System.out.println("[Client] Your Move! after jump ");

                        } else if (state == 14) {
                            pane.moveStone(client.getPos1(), client.getPos2(), client.isPlayerNumber());
                            changeStatus(4, !client.isPlayerNumber());
                            phase = 3;
                            System.out.println("[Client] Your Move! to jump ");
                        } else if (state == 15) {
                            phase = 0;
                            changeStatus(2, client.isPlayerNumber());
                            System.out.println("[CLIENT] Remove a Stone: ");

                        } else if (state == 16) {
                            phase = 0;
                            changeStatus(2, !playerNumber);
                            pane.moveStone(client.getPos1(), client.getPos2(), client.isPlayerNumber());

                        } else if (state == 17) {
                            changeStatus(4, !client.isPlayerNumber());
                            pane.removeStone(client.getPos1());
                            phase = 3;
                            System.out.println("[Client] Your Move! ");
                        } else if (state == 18) {
                            changeStatus(4, !client.isPlayerNumber());
                            phase = 3;
                        } else if (state == 19) {
                            pbC.setBoothphase3(true);
                            changeStatus(4, !client.isPlayerNumber());
                            pane.removeStone(client.getPos1());
                            phase = 3;
                            System.out.println("[Client] Your Move! ");
                        } else if (state == 20) {
                            pbC.setBoothphase3(true);
                            pane.moveStone(client.getPos1(), client.getPos2(), client.isPlayerNumber());
                            changeStatus(4, !client.isPlayerNumber());
                            phase = 3;
                            System.out.println("[Client] Your Move! boothphase3 ");
                        }else if(state == 21){
                            pane.moveStone(client.getPos1(), client.getPos2(), client.isPlayerNumber());
                            phase = 0;
                            changeStatus(2, !playerNumber);
                        }else if(state == 22){
                            phase = 0;
                            changeStatus(2, !playerNumber);
                        }else if(state == 23){
                            phase = 5;
                            changeStatus(5,!playerNumber);
                        }else if(state == 24){
                            phase = 5;
                            pane.removeStone(client.getPos1());
                            changeStatus(5,!playerNumber);
                        }
                    }
                } while (state == 3 || state == 6 || state == 9 || state == 11 || state == 16 || state == 18);
            }
        return false;
    }
    @Override
    protected void done() {
        pbC.phase = phase;
        pbC.thisplayerMove = true;
        super.done();
    }

    @Override
    protected void process(List<String> chunks) {
        super.process(chunks);
    }

    /**
     * Changes Status on label depending on phase and which state (condition for label) it is currently in
     * @param state represents the possibilities that happen
     */
    private void changeStatus(int state, boolean plNumb){
        if(state == 1) {
            if (!plNumb) {
                pane.setPlayerStatus("Player 2 place your Stone!", playerTwo);
            } else {
                pane.setPlayerStatus("Player 1 place your Stone", playerOne);
            }
        }
        else if(state == 2){
            if(!plNumb)   pane.setPlayerStatus("Player 2 remove a Stone!", playerTwo);
            else                pane.setPlayerStatus("Player 1 remove a Stone!", playerOne);
        }
        else if(state == 3){
            if (!plNumb) {
                pane.setPlayerStatus("Player 2 move your Stone!", playerTwo);
            } else {
                pane.setPlayerStatus("Player 1 move your Stone!", playerOne);
            }
        }
        else if(state == 4){
            if (!plNumb) {
                pane.setPlayerStatus("Player 2 jump",playerTwo);
            } else {
                pane.setPlayerStatus("Player 1 jump", playerOne);
            }
        }
        else if(state == 5){
            if (plNumb) {
                pane.setPlayerStatus("!!!Player 1 Won!!!",playerOne);
            } else {
                pane.setPlayerStatus("!!!Player 2 Won!!!",playerTwo);
            }
        }
    }
}
