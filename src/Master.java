import java.util.ArrayList;

public class Master {

    private static ArrayList<Stones> playerOne = new ArrayList<>();
    private static ArrayList<Stones> playerTwo = new ArrayList<>();

    public Master() {
    }


    public void add(int position, boolean state, int playerNumb){
        Stones stone = new Stones(position,state);
        if(playerNumb == 1) {
            playerOne.add(stone);
        }
        else{
            playerTwo.add(stone);
        }
    }
    public boolean fNumb(int pos){

        return pos != 4 && pos != 7 && pos != 10 && pos != 13 && pos != 16 && pos != 19 && pos != 22;
    }
    public int sNumb(int pos){
        if(pos == 2 || pos == 5 || pos == 8){
            return 1;
        }
        else
        if(pos == 17 || pos == 20 ||pos == 23) {
            return 2;
        }
        else
            return 0;
    }

    public boolean test(boolean player) {
        ArrayList<Stones> testList;
        if(player){
            testList = playerOne;
        }
        else{
            testList = playerTwo;
        }
        for(int i= 0; i < testList.size() ; i++) {
            int pos1 = testList.get(i).getPosition();
            if (i+1 < testList.size()) {
                int pos2 = testList.get(i+1).getPosition();
                if (pos1 + 1 == pos2 && fNumb(pos2)) {
                    if ( i+2 < testList.size()) {
                        int pos3 = testList.get(i+2).getPosition();
                            if (pos1 + 2 == pos3 && pos2 + 1 == pos3 && fNumb(pos3)) {
                                return true;
                            }
                            if (i > 0 ) {
                                int pos4 = testList.get(i-1).getPosition();
                                if (pos1 - 1 == pos4 && pos2 - 2 == pos4 ) {
                                    if (pos4 == 3 || pos4 == 6 || pos4 == 9 || pos4 == 12 || pos4 == 15 || pos4 == 18 || pos4 == 21) {
                                        return true;
                                    }
                                }
                            }
                    } else if (sNumb(pos1) == 1 || sNumb(pos1) == 2) {
                        for (int j = i + 1; j < testList.size(); j++) {
                            if (sNumb(testList.get(j).getPosition()) == sNumb(pos1)) {
                                for (int k = j + 1; k < testList.size(); k++) {
                                    if (sNumb(testList.get(k).getPosition()) == sNumb(testList.get(j).getPosition())) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
