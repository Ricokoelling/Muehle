public class Stones {

    private int position;
    private int stNumb;
    private boolean state;
    private int playerNumb;
    private final Stones[] player = new Stones[16];
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
        count++;
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
}
