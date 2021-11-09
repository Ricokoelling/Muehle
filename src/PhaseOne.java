import java.util.*;

public class PhaseOne extends Stones{
    private static final HashMap<HashMap<Integer,Integer>, HashMap<Integer,Boolean>> playerOne = new HashMap<>();
    private static final HashMap<HashMap<Integer,Integer>, HashMap<Integer,Boolean>> playerTwo = new HashMap<>();
    private static final playBoard Board = new playBoard();
    boolean state = false;
    HashMap<Integer,Integer> ePosition = new HashMap<>();

    public PhaseOne() {
        startPosition();
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setePosition(int key, int value) {
        this.ePosition.put(key,value);
    }

    public void startPosition(){
        for(int i = 0; i < 9; i++){
            Stones.setStatus(i,false);
            playerOne.put(position,status);
            playerTwo.put(position,status);
        }
    }

    public void putStones() {
        for (int i = 0; i < 9; i++) {
            if (state) {
                Stones.setStatus(i, true);
                playerOne.put(ePosition, status);
                System.out.println(playerOne);
            }
        }
    }
}
