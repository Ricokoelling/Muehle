import java.util.*;

public class PhaseOne extends Stones{

    private static final HashMap<HashMap<Integer,Integer>, HashMap<Integer,Boolean>> playerOne = new HashMap<>();
    private static final HashMap<HashMap<Integer,Integer>, HashMap<Integer,Boolean>> playerTwo = new HashMap<>();
    HashMap<Integer,Integer> ePosition = new HashMap<>();
    HashMap<Integer,Boolean> state = new HashMap<>();
    int plOne = 0;
    int plTwo = 0;

    public PhaseOne() {

        startPosition();
    }

    public void setState(boolean status, int stoneNum) {

        state.put(stoneNum,status);

    }

    public void setePosition(int key, int value) {

        this.ePosition.put(key,value);
    }

    public void startPosition(){

        for(int i = 0; i < 9; i++){         //used to set defaultstone settings
            Stones.setStatus(i,false);
            playerOne.put(position,status);
            playerTwo.put(position,status);
        }
    }

    public void putStones(int player, int posX, int posY) {

            if(player == 1 && plOne < 9){       //uses playnumber and stonenumber to determine which map he needed use
                ePosition.put(posX,posY);
                state.put(plOne,true);
                playerOne.put(ePosition,state);
                plOne++;                        //used to count stonenumber
            }
            else{
                ePosition.put(posX,posY);
                state.put(plTwo,true);
                playerTwo.put(ePosition,state);
                plTwo++;
            }

    }
}
