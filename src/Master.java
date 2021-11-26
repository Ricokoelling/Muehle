import java.util.ArrayList;
import java.util.LinkedList;

public class Master {

    private static ArrayList<Stones>    playerOne   = new ArrayList<>();
    private static ArrayList<Stones>    playerTwo   = new ArrayList<>();
    private LinkedList<Stones[]>        muehlen     = new LinkedList<>();

    public Master() {
    }
    /*      dont mind this just for testing :D
    for(Stones stone: playerOne){
            System.out.println(stone.toString());
        }
        for(Stones value: playerTwo){
            System.out.println(value.toString());
        }
     */
    public void add(int position, boolean state, boolean playerNumb) {
        Stones stone = new Stones(position, state);
        if (playerNumb) {
            playerOne.add(stone);
        } else {
            playerTwo.add(stone);
        }
    }
    
    //name change from fNumb to falseNumb
    private boolean falseNumb(int pos) {
        return pos != 4 && pos != 7 && pos != 10 && pos != 13 && pos != 16 && pos != 19 && pos != 22;
    }
    
    //name change from vNumb to verticalNumb
    private int verticalNumb(int pos) {
        if (pos == 2 || pos == 5 || pos == 8) {
            return 1;
        } else if (pos == 17 || pos == 20 || pos == 23) {
            return 2;
        } else
            return 0;
    }

    private boolean validMove(int pos1, int pos2){

        if(pos1 == 1 && pos2 == 10 || pos2 == 1 && pos1 == 10)
            return true;
        if(pos1 == 10 && pos2 == 22 || pos2 == 10 && pos1 == 22)
            return true;
        if(pos1 == 4 && pos2 == 11 || pos2 == 4 && pos1 == 11)
            return true;
        if(pos1 == 11 && pos2 == 19 || pos2 == 11 && pos1 == 19)
            return true;
        if(pos1 == 7 && pos2 == 12 || pos2 == 7 && pos1 == 12)
            return true;
        if(pos1 == 12 && pos2 == 16 || pos2 == 12 && pos1 == 16)
            return true;
        if(pos1 == 9 && pos2 == 13 || pos2 == 9 && pos1 == 13)
            return true;
        if(pos1 == 13 && pos2 == 18 || pos2 == 13 && pos1 == 18)
            return true;
        if(pos1 == 6 && pos2 == 14 || pos2 == 6 && pos1 == 14)
            return true;
        if(pos1 == 14 && pos2 == 21 || pos2 == 14 && pos1 == 21)
            return true;
        if(pos1 == 3 && pos2 == 15 || pos2 == 3 && pos1 == 15)
            return true;
        if(pos1 == 15 && pos2 == 24 || pos2 == 15 && pos1 == 24)
            return true;

        return false;
    }

    public boolean test(boolean player) {
        ArrayList<Stones> testList;
        if (player) {
            testList = playerOne;
        } else {
            testList = playerTwo;
        }
        for (int i = 0; i < testList.size(); i++) {
            int pos1 = testList.get(i).getPosition();
            for (int j = 0; j < testList.size(); j++) {
                if ((pos1 + 1) == testList.get(j).getPosition() && falseNumb(testList.get(j).getPosition()) && inMuehle(pos1, testList.get(j).getPosition())) {
                    for (Stones stones : testList) {
                        if ((pos1 + 2) == stones.getPosition() && falseNumb(stones.getPosition())) {
                            muehle(testList.get(i), testList.get(j), stones);
                            return true;
                        }
                    }
                }
            }
            if (verticalNumb(pos1) == 1) {
                for (int ii = 0; ii < testList.size(); ii++) {
                    if (verticalNumb(testList.get(ii).getPosition()) == 1 && testList.get(ii).getPosition() != pos1 && inMuehle(pos1, testList.get(ii).getPosition())) {
                        for (Stones stones : testList) {
                            if (verticalNumb(stones.getPosition()) == 1 && stones.getPosition() != pos1 && testList.get(ii).getPosition() != stones.getPosition()) {
                                muehle(testList.get(i), testList.get(ii), stones);
                                return true;
                            }
                        }
                    }
                }
            } else if (verticalNumb(pos1) == 2) {
                for (int jj = 0; jj < testList.size(); jj++) {
                    if (verticalNumb(testList.get(jj).getPosition()) == 2 && testList.get(jj).getPosition() != pos1 && inMuehle(pos1, testList.get(jj).getPosition())) {
                        for (Stones stones : testList) {
                            if (verticalNumb(stones.getPosition()) == 2 && stones.getPosition() != pos1 && testList.get(jj).getPosition() != stones.getPosition()) {
                                muehle(testList.get(i), testList.get(jj), stones);
                                return true;
                            }
                        }
                    }
                }
            }
            if (verticalNumb(pos1) == 0) {
                switch (pos1) {
                    case 1 -> {
                        for (Stones stones : testList) {
                            if ((stones.getPosition() == 10 || stones.getPosition() == 22) && stones.getPosition() != pos1 && inMuehle(pos1, stones.getPosition())) {
                                for (Stones value : testList) {
                                    if ((value.getPosition() == 10 || value.getPosition() == 22) && value.getPosition() != pos1 && value.getPosition() != stones.getPosition()) {
                                        muehle(testList.get(i), stones, value);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    case 3 -> {
                        for (Stones stones : testList) {
                            if ((stones.getPosition() == 15 || stones.getPosition() == 24) && stones.getPosition() != pos1 && inMuehle(pos1, stones.getPosition())) {
                                for (Stones value : testList) {
                                    if ((value.getPosition() == 15 || value.getPosition() == 24) && value.getPosition() != pos1 && value.getPosition() != stones.getPosition()) {
                                        muehle(testList.get(i), stones, value);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    case 4 -> {
                        for (Stones stones : testList) {
                            if ((stones.getPosition() == 11 || stones.getPosition() == 19) && stones.getPosition() != pos1 && inMuehle(pos1, stones.getPosition())) {
                                for (Stones value : testList) {
                                    if ((value.getPosition() == 11 || value.getPosition() == 19) && value.getPosition() != pos1 && value.getPosition() != stones.getPosition()) {
                                        muehle(testList.get(i), stones, value);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    case 6 -> {
                        for (Stones stones : testList) {
                            if ((stones.getPosition() == 14 || stones.getPosition() == 21) && stones.getPosition() != pos1 && inMuehle(pos1, stones.getPosition())) {
                                for (Stones value : testList) {
                                    if ((value.getPosition() == 14 || value.getPosition() == 21) && value.getPosition() != pos1 && value.getPosition() != stones.getPosition()) {
                                        muehle(testList.get(i), stones, value);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    case 7 -> {
                        for (Stones stones : testList) {
                            if ((stones.getPosition() == 12 || stones.getPosition() == 16) && stones.getPosition() != pos1 && inMuehle(pos1, stones.getPosition())) {
                                for (Stones value : testList) {
                                    if ((value.getPosition() == 12 || value.getPosition() == 16) && value.getPosition() != pos1 && value.getPosition() != stones.getPosition()) {
                                        muehle(testList.get(i), stones, value);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    case 9 -> {
                        for (Stones stones : testList) {
                            if ((stones.getPosition() == 13 || stones.getPosition() == 18) && stones.getPosition() != pos1 && inMuehle(pos1, stones.getPosition())) {
                                for (Stones value : testList) {
                                    if ((value.getPosition() == 13 || value.getPosition() == 18) && value.getPosition() != pos1 && value.getPosition() != stones.getPosition()) {
                                        muehle(testList.get(i), stones, value);
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

    private void muehle(Stones stone1, Stones stone2, Stones stone3) {
        Stones[] temp = {stone1, stone2, stone3};
        muehlen.add(temp);
    }

    private boolean inMuehle(int pos1, int pos2) {
        for (Stones[] stones : muehlen) {
            for (Stones value : stones) {
                if (value.getPosition() == pos1) {
                    for (Stones stone : stones) {
                        if (stone.getPosition() == pos2) {
                            return false;                       //returns false for is in mill
                        }
                    }
                }
            }
        }
        return true;                                            //returns true for is not in mill
    }

    private boolean inMuehle(int pos1) {
        for (Stones[] stones : muehlen) {
            for (Stones value : stones) {
                if (value.getPosition() == pos1) {
                    return false;                               //returns false for is in mill
                }
            }
        }
        return true;                                            //returns true for is not in mill
    }

    public boolean removeStones(int pos, boolean playerNumb) {
        //deleted the second if statement cause if playerNumb == true it goes in the first if its not true than it has to be false so it goes into else
        //checks if there are only mills and if it is so the player is able to take stones from the mill
        if(onlyMills(playerNumb)){
            if(playerNumb){
                for (int i = 0; i < playerTwo.size(); i++) {
                    if (playerTwo.get(i).getPosition() == pos) {
                        playerTwo.remove(i);
                    }
                }
            }else {
                for (int i = 0; i < playerOne.size(); i++) {
                    if (playerOne.get(i).getPosition() == pos) {
                        playerOne.remove(i);
                    }
                }
            }
            return true;
        } else if (inMuehle(pos)) {
            if(playerNumb){
                for (int i = 0; i < playerTwo.size(); i++) {
                    if (playerTwo.get(i).getPosition() == pos) {
                        playerTwo.remove(i);
                    }
                }
            }else{
                for (int i = 0; i < playerOne.size(); i++) {
                    if (playerOne.get(i).getPosition() == pos) {
                        playerOne.remove(i);
                    }
                }
            }
            return true;
        }
        else return false;
    }


    public boolean sameplayerStone(int pos, boolean playerNumb) {
        //changed the second if statement into else cause if playNumb != true it is false so else is faster and does not have to compare anymore
        if(playerNumb) {
            for (int i = 0; i < playerOne.size(); i++) {
                if(playerOne.get(i).getPosition() == pos){
                    return false;
                }
            }
        }else {
            for (int i = 0; i < playerTwo.size(); i++) {
                if(playerTwo.get(i).getPosition() == pos){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean posTaken(int pos){
            for (int i = 0; i < playerOne.size(); i++) {
                if(playerOne.get(i).getPosition() == pos){
                    return false;
                }
            }
            for (int i = 0; i < playerTwo.size(); i++) {
                if(playerTwo.get(i).getPosition() == pos){
                    return false;
                }
            }
        return true;
    }
    public boolean freeposNextto(int pos1, int pos2, boolean playNumb){   //this.pos1 == playBoard.pos2 || pos2 ist ursprungspos
        int stone = 0;  //zum überschreiben der pos des stones
        if (playNumb) {
            for (int i = 0; i < playerOne.size(); i++) {
                if (playerOne.get(i).getPosition() == pos2)
                    stone = i;
            }
        } else {
            for (int i = 0; i < playerTwo.size(); i++) {
                if (playerTwo.get(i).getPosition() == pos2)
                    stone = i;
            }
        }
        System.out.println("posGO: " + pos1 + "posUr: " + pos2);
        if (posTaken(pos1)) {
            if (((pos1 - 1 == pos2) || pos1 + 1 == pos2) || validMove(pos1, pos2) || (verticalNumb(pos1) == 1 && verticalNumb(pos2) == 1 || verticalNumb(pos1) == 2 && verticalNumb(pos2) == 2)) {
                if (playNumb) {
                    playerOne.get(stone).setPosition(pos1);
                } else {
                    playerTwo.get(stone).setPosition(pos1);
                }
                return true;
            }
        }
        return false;
    }

    /**Function the returns boolean depending on if there are only mills or not
     *
     * @param playerNumb transfers
     * @return
     */
    protected boolean onlyMills(boolean playerNumb){
        if(playerNumb){
            for(Stones stones: playerTwo){
                if(inMuehle(stones.getPosition())) return false;
            }
        }else{
            for(Stones stones: playerOne){
                if(inMuehle(stones.getPosition())) return false;
            }
        }
        return true;
    }
}

