package Data;

import java.io.Serializable;

public class AcceptData implements Serializable {
    private boolean Accept;
    private int State;
    private int Pos1;
    private int Pos2;
    private String PlayerID;
    private boolean PlayerNumb;
    private boolean Reset;
    private boolean disconnect;


    public AcceptData(boolean accept, int state, int pos1, int pos2, String playerID, boolean playerNumb, boolean reset) {
        this.Accept = accept;
        this.State = state;
        this.Pos1 = pos1;
        this.Pos2 = pos2;
        this.PlayerID = playerID;
        this.PlayerNumb = playerNumb;
        this.Reset = reset;
    }
    public AcceptData(boolean accept, int state, int pos1, int pos2, String playerID, boolean playerNumb, boolean reset,boolean disconnect) {
        this.Accept = accept;
        this.State = state;
        this.Pos1 = pos1;
        this.Pos2 = pos2;
        this.PlayerID = playerID;
        this.PlayerNumb = playerNumb;
        this.Reset = reset;
        this.disconnect = disconnect;
    }

    public boolean isAccept() {
        return Accept;
    }

    public int getState() {
        return State;
    }

    public int getPos1() {
        return Pos1;
    }

    public int getPos2() {
        return Pos2;
    }

    public String getPlayerID() {
        return PlayerID;
    }

    public boolean isPlayerNumb() {
        return PlayerNumb;
    }

    public boolean isReset() {
        return Reset;
    }

    public boolean isDisconnect() {
        return disconnect;
    }
}
