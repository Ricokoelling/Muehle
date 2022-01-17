import java.io.Serializable;

/**
 * is for the objekt the client sends to the Server
 * Version 1.0
 */
public class Data implements Serializable {

    private int state;
    private int pos1;
    private int pos2;
    private String PlayerID;
    private boolean reset;
    private boolean Player;

    public Data(int state, int pos1, int pos2, String PlayerID, boolean reset, boolean player) {
        this.state = state;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.PlayerID = PlayerID;
        this.reset = reset;
        this.Player = player;
    }

    public int getState() {
        return state;
    }

    public int getPos1() {
        return pos1;
    }

    public int getPos2() {
        return pos2;
    }

    public String getPlayerID() {
        return PlayerID;
    }

    public boolean isReset() {
        return reset;
    }

    public boolean isPlayer() {
        return Player;
    }
}
