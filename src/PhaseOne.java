import java.util.*;

public class PhaseOne extends Stones{

    private int plOne = 0;
    private int plTwo = 0;
    private Stones[] playerOne = new Stones[8];
    private Stones[] playerTwo = new Stones[8];

    public PhaseOne() {

    }

    public void putStones(int player, int pos) {

            if(player == 1 && plOne < 9){       //uses playnumber and stonenumber to determine which map he needed use
                Stones stone = new Stones(pos, plOne, true);
                playerOne[plOne] = stone;
                setPlayerOne(playerOne);
                plOne++;
            }
            else{
                Stones stone = new Stones(pos,plTwo,true);
                playerTwo[plTwo] = stone;
                setPlayerTwo(playerTwo);
                plTwo++;
            }


    }
}
