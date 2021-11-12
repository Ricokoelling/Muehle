public class Stones {

    private int position;
    private int stNumb;
    private boolean state;
    private int playerNumb;
    private Stones[] player = new Stones[18];
    private int count = 0;

    public Stones() {
    }

    public Stones(int pos, int stNumb, boolean state, int plNumb) {
        this.position = pos;
        this.stNumb = stNumb;
        this.state = state;
        this.playerNumb = plNumb;
    }

    public void setPlayer(Stones st) {
        player[count] = st;
        count+= 1;
    }

    public Stones[] getPlayer() {
        return player;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getStNumb() {
        return stNumb;
    }

    public void setStNumb(int stNumb) {
        this.stNumb = stNumb;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getPlayerNumb() {
        return playerNumb;
    }

    @Override
    public String toString() {
        return "Stones{" +
                "position=" + position +
                ", stNumb=" + stNumb +
                ", state=" + state +
                ", playerNumb=" + playerNumb +
                ", count=" + count +
                '}';
    }
}
