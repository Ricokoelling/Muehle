


public class Clientdata {
    private int OriginPos,FinalPos, phase;
    private boolean playerNumber;


    public Clientdata() {
        this.OriginPos = 0;
        this.FinalPos = 0;
        this.phase = 1;
        this.playerNumber = true;

    }

    public void setOriginPos(int OriginPos) {
        this.OriginPos = OriginPos;
    }

    public void setFinalPos(int FinalPos) {
        this.FinalPos = FinalPos;
    }
    public void setPhase(int phase) {
        this.phase = phase;
    }

    public void setPlayerNumber(boolean playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPos1() {
        return OriginPos;
    }

    public int getPos2() {
        return FinalPos;
    }

    public int getPhase() {
        return phase;
    }

    public boolean isPlayerNumber() {
        return playerNumber;
    }
}
