import java.util.ArrayList;

public class Master {

    private final ArrayList<Stones>     playerOne           = new ArrayList<>();
    private final ArrayList<Stones>     playerTwo           = new ArrayList<>();
    private final ArrayList<Stones[]>   muehlenPlayerOne    = new ArrayList<>();
    private final ArrayList<Stones[]>   muehlenPlayerTwo    = new ArrayList<>();

    public Master() {
    }

    /*
    for(Stones stones : playerOne){
            System.out.println("player 1: " + stones.toString());
        }
        for(Stones value : playerTwo){
            System.out.println("player 2: " + value.toString());
        }
     */
    /**
     * addes stones to player Arraylist for further use
     * @param position      pos where the stone is
     * @param playerNumb    Player
     */
    protected void add(int position, boolean playerNumb) {
        Stones stone = new Stones(position);
        if (playerNumb) {
            playerOne.add(stone);
        } else {
            playerTwo.add(stone);
        }
    }

    /**
     * checks if a positon is on a left side edge which would mean (z.b. 3,4,5 are not a mill)
     * @param pos position which is checked
     * @return  true --> pos isn`t one of these || false --> pos is a falseNumber
     */
    private boolean falseNumb(int pos) {
        return pos != 4 && pos != 7 && pos != 10 && pos != 13 && pos != 16 && pos != 19 && pos != 22;
    }

    /**
     * checks if the pos is a in a vertical position
     * @param pos   position
     * @return  1 --> first vertical numbers || 2 --> second vertical numbers || 0 --> isn´t a vertical number
     */
    private int verticalNumb(int pos) {
        if (pos == 2 || pos == 5 || pos == 8) {
            return 1;
        } else if (pos == 17 || pos == 20 || pos == 23) {
            return 2;
        } else
            return 0;
    }

    /**
     * checks if in phase 2 the move is available
     * @param pos1 position the stone gets moved to
     * @param pos2 origin position
     * @return true -> if the move is possible || false -> if the move isn´t listed her or not possible
     */
    private boolean validMove(int pos1, int pos2) {
        if (pos1 == 1 && pos2 == 10 || pos2 == 1 && pos1 == 10)
            return true;
        if (pos1 == 10 && pos2 == 22 || pos2 == 10 && pos1 == 22)
            return true;
        if (pos1 == 4 && pos2 == 11 || pos2 == 4 && pos1 == 11)
            return true;
        if (pos1 == 11 && pos2 == 19 || pos2 == 11 && pos1 == 19)
            return true;
        if (pos1 == 7 && pos2 == 12 || pos2 == 7 && pos1 == 12)
            return true;
        if (pos1 == 12 && pos2 == 16 || pos2 == 12 && pos1 == 16)
            return true;
        if (pos1 == 9 && pos2 == 13 || pos2 == 9 && pos1 == 13)
            return true;
        if (pos1 == 13 && pos2 == 18 || pos2 == 13 && pos1 == 18)
            return true;
        if (pos1 == 6 && pos2 == 14 || pos2 == 6 && pos1 == 14)
            return true;
        if (pos1 == 14 && pos2 == 21 || pos2 == 14 && pos1 == 21)
            return true;
        if (pos1 == 3 && pos2 == 15 || pos2 == 3 && pos1 == 15)
            return true;
        return pos1 == 15 && pos2 == 24 || pos2 == 15 && pos1 == 24;
    }

    /**
     * checks if a certian player did place a mill
     *
     * @param playerNumb player who placed the last stone
     * @return true -> if the player placed a mill || false -> player didnt place a mill
     */
    protected boolean checkMill(boolean playerNumb) {
        boolean checkreturn = false;
        ArrayList<Stones> testList;
        if (playerNumb) {
            testList = playerOne;
        } else {
            testList = playerTwo;
        }

        for (int i = 0; i < testList.size(); i++) {
            int pos1 = testList.get(i).getPosition();
            for (int j = 0; j < testList.size(); j++) {
                if ((pos1 + 1) == testList.get(j).getPosition() && falseNumb(testList.get(j).getPosition()) && inMill(pos1, testList.get(j).getPosition())) {
                    for (Stones stones : testList) {
                        if ((pos1 + 2) == stones.getPosition() && falseNumb(stones.getPosition())) {
                            muehle(testList.get(i), testList.get(j), stones, playerNumb);
                            checkreturn = true;
                        }
                    }
                }
            }
            if (verticalNumb(pos1) == 1) {
                for (int ii = 0; ii < testList.size(); ii++) {
                    if (verticalNumb(testList.get(ii).getPosition()) == 1 && testList.get(ii).getPosition() != pos1 && inMill(pos1, testList.get(ii).getPosition())) {
                        for (Stones stones : testList) {
                            if (verticalNumb(stones.getPosition()) == 1 && stones.getPosition() != pos1 && testList.get(ii).getPosition() != stones.getPosition()) {
                                muehle(testList.get(i), testList.get(ii), stones, playerNumb);
                                checkreturn = true;
                            }
                        }
                    }
                }
            } else if (verticalNumb(pos1) == 2) {
                for (int jj = 0; jj < testList.size(); jj++) {
                    if (verticalNumb(testList.get(jj).getPosition()) == 2 && testList.get(jj).getPosition() != pos1 && inMill(pos1, testList.get(jj).getPosition())) {
                        for (Stones stones : testList) {
                            if (verticalNumb(stones.getPosition()) == 2 && stones.getPosition() != pos1 && testList.get(jj).getPosition() != stones.getPosition()) {
                                muehle(testList.get(i), testList.get(jj), stones, playerNumb);
                                checkreturn = true;
                            }
                        }
                    }
                }
            }
            if (verticalNumb(pos1) == 0) {
                switch (pos1) {
                    case 1 -> {
                        for (Stones stones : testList) {
                            if ((stones.getPosition() == 10 || stones.getPosition() == 22) && stones.getPosition() != pos1 && inMill(pos1, stones.getPosition())) {
                                for (Stones value : testList) {
                                    if ((value.getPosition() == 10 || value.getPosition() == 22) && value.getPosition() != pos1 && value.getPosition() != stones.getPosition()) {
                                        muehle(testList.get(i), stones, value, playerNumb);
                                        checkreturn = true;
                                    }
                                }
                            }
                        }
                    }
                    case 3 -> {
                        for (Stones stones : testList) {
                            if ((stones.getPosition() == 15 || stones.getPosition() == 24) && stones.getPosition() != pos1 && inMill(pos1, stones.getPosition())) {
                                for (Stones value : testList) {
                                    if ((value.getPosition() == 15 || value.getPosition() == 24) && value.getPosition() != pos1 && value.getPosition() != stones.getPosition()) {
                                        muehle(testList.get(i), stones, value, playerNumb);
                                        checkreturn = true;
                                    }
                                }
                            }
                        }
                    }
                    case 4 -> {
                        for (Stones stones : testList) {
                            if ((stones.getPosition() == 11 || stones.getPosition() == 19) && stones.getPosition() != pos1 && inMill(pos1, stones.getPosition())) {
                                for (Stones value : testList) {
                                    if ((value.getPosition() == 11 || value.getPosition() == 19) && value.getPosition() != pos1 && value.getPosition() != stones.getPosition()) {
                                        muehle(testList.get(i), stones, value, playerNumb);
                                        checkreturn = true;
                                    }
                                }
                            }
                        }
                    }
                    case 6 -> {
                        for (Stones stones : testList) {
                            if ((stones.getPosition() == 14 || stones.getPosition() == 21) && stones.getPosition() != pos1 && inMill(pos1, stones.getPosition())) {
                                for (Stones value : testList) {
                                    if ((value.getPosition() == 14 || value.getPosition() == 21) && value.getPosition() != pos1 && value.getPosition() != stones.getPosition()) {
                                        muehle(testList.get(i), stones, value, playerNumb);
                                        checkreturn = true;
                                    }
                                }
                            }
                        }
                    }
                    case 7 -> {
                        for (Stones stones : testList) {
                            if ((stones.getPosition() == 12 || stones.getPosition() == 16) && stones.getPosition() != pos1 && inMill(pos1, stones.getPosition())) {
                                for (Stones value : testList) {
                                    if ((value.getPosition() == 12 || value.getPosition() == 16) && value.getPosition() != pos1 && value.getPosition() != stones.getPosition()) {
                                        muehle(testList.get(i), stones, value, playerNumb);
                                        checkreturn = true;
                                    }
                                }
                            }
                        }
                    }
                    case 9 -> {
                        for (Stones stones : testList) {
                            if ((stones.getPosition() == 13 || stones.getPosition() == 18) && stones.getPosition() != pos1 && inMill(pos1, stones.getPosition())) {
                                for (Stones value : testList) {
                                    if ((value.getPosition() == 13 || value.getPosition() == 18) && value.getPosition() != pos1 && value.getPosition() != stones.getPosition()) {
                                        muehle(testList.get(i), stones, value, playerNumb);
                                        checkreturn = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return checkreturn;
    }

    /**
     * checks if the mills still exits
     */
    protected void stillMill() {
            muehlenPlayerTwo.clear();
            muehlenPlayerOne.clear();
            checkMill(true);
            checkMill(false);

        //print();
        }

    /**
     * addes a mill to the mill List
     * @param stone1 -
     * @param stone2 -
     * @param stone3 -
     */
    private void muehle(Stones stone1, Stones stone2, Stones stone3, boolean playerNumb) {
        Stones[] temp = {stone1, stone2, stone3};
        //System.out.println(stone1.toString() + " 2: " + stone2.toString() + " 3: " + stone3.toString());
        if(playerNumb){
            muehlenPlayerOne.add(temp);
        }
        else {
            muehlenPlayerTwo.add(temp);
        }
    }
    /**
     * checks if two Stones already build a mill
     * @param pos1 position from stone one
     * @param pos2 position from stone two
     * @return true -> isn´t in a mill || false -> is in a mill
     */
    private boolean inMill(int pos1, int pos2) {
        for (Stones[] stones : muehlenPlayerOne) {
            for (Stones value : stones) {
                if (value.getPosition() == pos1) {
                    for (Stones stone : stones) {
                        if (stone.getPosition() == pos2) {
                            return false;
                        }
                    }
                }
            }
        }
        for (Stones[] stones : muehlenPlayerTwo) {
            for (Stones value : stones) {
                if (value.getPosition() == pos1) {
                    for (Stones stone : stones) {
                        if (stone.getPosition() == pos2) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * checks if the Stone is in a mill
     * @param pos stone position which is checked, doesnt matter from which player
     * @return true -> is not in mill || false -> isn't in a mill
     */
    private boolean inMill(int pos) {
        for (Stones[] stones : muehlenPlayerTwo) {
            for (Stones value : stones) {
                if (value.getPosition() == pos) {
                    return false;
                }
            }
        }
        for (Stones[] stones : muehlenPlayerOne) {
            for (Stones value : stones) {
                if (value.getPosition() == pos) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * removes Stone from player List and checks if their are only mills to remove from
     * @param pos stone which is going to be removed
     * @param playerNumb player who removes the stone
     * @return true -> if stone is found, will be removed || false -> stone couldnt be found so he doesnt get removed
     */
    protected boolean removeStones(int pos, boolean playerNumb) {
        //checks if there are only mills and if it is so the player is able to take stones from the mill
        //only mills should check in playBoard otherwise cant remove stone here
        if (onlyMills(playerNumb) || inMill(pos)) {
            if (playerNumb) {
                for (int i = 0; i < playerTwo.size(); i++) {
                    if (playerTwo.get(i).getPosition() == pos) {
                        playerTwo.remove(i);
                        stillMill();
                        return true;
                    }
                }
            } else {
                for (int i = 0; i < playerOne.size(); i++) {
                    if (playerOne.get(i).getPosition() == pos) {
                        playerOne.remove(i);
                        stillMill();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if the stone which should get removed / moved is not from the same player
     * @param pos position from the stone which should get removed
     * @param playerNumb player who removes the stone
     * @return true -> stone can be removed ||false -> stone can't be removed
     */
    protected boolean sameplayerStone(int pos, boolean playerNumb) {
        if(playerNumb) {
            for (Stones stones : playerOne) {
                if (stones.getPosition() == pos) {
                    return false;
                }
            }
        }else {
            for (Stones stones : playerTwo) {
                if (stones.getPosition() == pos) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * funktion tests if the position the stone should go to is avalible 
     * @param pos phase1 stoneplace
     * @return true -> pos isnt taken || false -> pos is taken
     */
    protected boolean posTaken(int pos){
        for (Stones stones : playerOne) {
            if (stones.getPosition() == pos) {
                return false;
            }
        }
        for (Stones stones : playerTwo) {
            if (stones.getPosition() == pos) {
                return false;
            }
        }
        return true;
    }

    /**
     * Funktion checks if the new position is avaliable and if the pos is only 1 step away
     * @param pos1 new position
     * @param pos2 old position
     * @param playerNumb playerNumber
     * @return true ->stone will be moved to new pos || false ->stone cant move
     */
    protected boolean freeposNextto(int pos1, int pos2, boolean playerNumb){   //this.pos1 == playBoard.pos2 || pos2 ist ursprungspos

        print();


        int stone = 0;  //zum überschreiben der pos des stones
        if (playerNumb) {
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
        if (posTaken(pos1)) {
            if (((pos1 - 1 == pos2) || pos1 + 1 == pos2) || validMove(pos1, pos2) || (verticalNumb(pos1) == 1 && verticalNumb(pos2) == 1 || verticalNumb(pos1) == 2 && verticalNumb(pos2) == 2)) {
                if (playerNumb) {
                    playerOne.get(stone).setPosition(pos1);
                } else {
                    playerTwo.get(stone).setPosition(pos1);
                }
                System.out.println("[Why]");
                return true;
            }
        }
        return false;
    }

    /**Function that returns boolean depending on if there are only mills or not()
     *
     * @param playerNumb transfers
     * @return true -> their are only mills || false -> at least one stone isn´t in a mill
     */
    protected boolean onlyMills(boolean playerNumb){
        if(playerNumb){
            for(Stones stones: playerTwo){
                if(inMill(stones.getPosition())) return false;
            }
        }else{
            for(Stones stones: playerOne){
                if(inMill(stones.getPosition())) return false;
            }
        }
        return true;
    }

    /**
     * checks if one stone is able to move in any direktion
     * @param pos position of stone
     * @return true -> stone got available moves || false -> stone cant move
     */
    private boolean availableMoves(int pos){
        if(posTaken(pos + 1) && falseNumb(pos + 1)){
            return true;
        }
        if(pos != 1 && posTaken(pos - 1) && falseNumb(pos)){
            return true;
        }
        if(verticalNumb(pos) == 1){
            switch (pos){
                case 2: if(posTaken(5)){
                    return true;
                }
                    break;
                case 5: if(posTaken(2) || posTaken(8)){
                    return true;
                }
                case 8: if(posTaken(5)){
                    return true;
                }
                    break;
            }
        }
        else if(verticalNumb(pos) == 2){
            switch (pos){
                case 17: if(posTaken(20)){
                    return true;
                }
                    break;
                case 20: if(posTaken(17) || posTaken(23)){
                    return true;
                }
                case 23: if(posTaken(20)){
                    return true;
                }
                    break;
            }
        }
        for(int i = 1; i < 25; i++){
            if(posTaken(i)){
                if(validMove(pos,i)){
                    return true;
                }
            }
        }
    return false;
    }
    /**
     * checks if one player won by blocking all the enemies stones
     * @param playerNumb player who last moved
     * @return true -> playerWon || false -> player didn't win so match continues
     */
    protected boolean winConditionOne(boolean playerNumb){

        if(playerNumb) {
            for (Stones stone : playerTwo) {
                if(availableMoves(stone.getPosition())){
                    return false;
                }
            }
        }
        else{
            for (Stones stone : playerOne) {
                if(availableMoves(stone.getPosition())){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @param playerNumb from which player
     * @return  player stones left
     */
    protected int getPlayerStones(boolean playerNumb){
        if(playerNumb){
            return playerOne.size();
        }
        else {
            return playerTwo.size();
        }
    }

    /**
     * moves stone to a new position, doesnt check here (check in playBoard)
     * @param pos1  origin pos
     * @param pos2  new pos
     * @param playerNumb  player
     */
    protected void moveStone(int pos1, int pos2, boolean playerNumb){
        if(playerNumb) {
            for (Stones stone : playerOne) {
                if(stone.getPosition() == pos1){
                    stone.setPosition(pos2);
                }
            }
        }
        else{
            for (Stones stone : playerTwo) {
                if(stone.getPosition() == pos1){
                    stone.setPosition(pos2);
                }
            }
        }
    }
    protected void reset(){
        playerOne.clear();
        playerTwo.clear();
        muehlenPlayerOne.clear();
        muehlenPlayerTwo.clear();
    }
    protected void print(){
       /* for(Stones[] yee: muehlenPlayerOne){
            for(Stones ye : yee){
                System.out.println("mill 1: " +  ye.toString());
            }
        }
        for(Stones[] yee: muehlenPlayerTwo){
            for(Stones ye : yee){
                System.out.println("mill 2: " +  ye.toString());
            }
        }
        System.out.println(" ");*/
        for(Stones stones : playerOne){
            System.out.println("player 1: " + stones.toString());
        }
        System.out.println(" ");
        for(Stones value : playerTwo){
            System.out.println("player 2: " + value.toString());
        }
    }
}

