import java.util.HashMap;

public class Stones {

    private int position;
    private int stNumb;
    private boolean state;
    private Stones[] playerOne = new Stones[8];
    private Stones[] playerTwo = new Stones[8];


    public Stones() {
    }

    public Stones(int pos, int stNumb, boolean state) {
        this.position = pos;
        this.stNumb = stNumb;
        this.state = state;
    }

    public void setPlayerOne(Stones[] playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(Stones[] playerTwo) {
        this.playerTwo = playerTwo;
    }

}
