import java.util.ArrayList;
import java.util.LinkedList;

public class Master {

    private static ArrayList<Stones> playerOne = new ArrayList<>();
    private static ArrayList<Stones> playerTwo = new ArrayList<>();
    private LinkedList<Stones[]> muehlen = new LinkedList<>();

    public Master() {
    }

    public void add(int position, boolean state, int playerNumb) {
        Stones stone = new Stones(position, state);
        if (playerNumb == 1) {
            playerOne.add(stone);
        } else {
            playerTwo.add(stone);
        }
    }

    public boolean fNumb(int pos) {

        return pos != 4 && pos != 7 && pos != 10 && pos != 13 && pos != 16 && pos != 19 && pos != 22;
    }

    public int sNumb(int pos) {
        if (pos == 2 || pos == 5 || pos == 8) {
            return 1;
        } else if (pos == 17 || pos == 20 || pos == 23) {
            return 2;
        } else
            return 0;
    }

    public boolean test(boolean player) {
        ArrayList<Stones> testList;
        if (player) {
            testList = playerOne;
        } else {
            testList = playerTwo;
        }

        /*for(int i= 0; i < testList.size() ; i++) {
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
        }*/
        for (int i = 0; i < testList.size(); i++) {
            int pos1 = testList.get(i).getPosition();
            for (int j = 0; j < testList.size(); j++) {
                if ((pos1 + 1) == testList.get(j).getPosition() && fNumb(testList.get(j).getPosition()) && inMuehle(pos1,testList.get(j).getPosition())) {
                    for (Stones stones : testList) {
                        if ((pos1 + 2) == stones.getPosition() && fNumb(stones.getPosition())) {
                            muehle(testList.get(i), testList.get(j), stones);
                            return true;
                        }
                    }
                }
            }
            if (sNumb(pos1) == 1) {
                for (int ii = 0; ii < testList.size(); ii++) {
                    if (sNumb(testList.get(ii).getPosition()) == 1 && testList.get(ii).getPosition() != pos1 && inMuehle(pos1,testList.get(ii).getPosition())) {
                        for (Stones stones : testList) {
                            if (sNumb(stones.getPosition()) == 1 && stones.getPosition() != pos1 && testList.get(ii).getPosition() != stones.getPosition()) {
                                muehle(testList.get(i), testList.get(ii), stones);
                                return true;
                            }
                        }
                    }
                }
            } else if (sNumb(pos1) == 2) {
                for (int jj = 0; jj < testList.size(); jj++) {
                    if (sNumb(testList.get(jj).getPosition()) == 2 && testList.get(jj).getPosition() != pos1 && inMuehle(pos1,testList.get(jj).getPosition())) {
                        for (Stones stones : testList) {
                            if (sNumb(stones.getPosition()) == 2 && stones.getPosition() != pos1 && testList.get(jj).getPosition() != stones.getPosition()) {
                                muehle(testList.get(i), testList.get(jj), stones);
                                return true;
                            }
                        }
                    }
                }
            }
            if(sNumb(pos1) == 0){
                switch (pos1){
                    case 1:{ for(Stones stones : testList) {
                               if((stones.getPosition() == 10 || stones.getPosition() == 22) && stones.getPosition() !=  pos1 && inMuehle(pos1,stones.getPosition())){
                                   for(Stones value : testList){
                                       if((value.getPosition() == 10 || value.getPosition() == 22) && value.getPosition() !=  pos1 && value.getPosition() != stones.getPosition()){
                                           muehle(testList.get(i), stones, value);
                                           return true;
                                       }
                                   }
                               }
                            }
                    }
                    break;
                    case 3: {
                        for (Stones stones : testList) {
                            if ((stones.getPosition() == 15 || stones.getPosition() == 24) && stones.getPosition() != pos1 && inMuehle(pos1,stones.getPosition())) {
                                for (Stones value : testList) {
                                    if ((value.getPosition() == 15 || value.getPosition() == 24) && value.getPosition() != pos1 && value.getPosition() != stones.getPosition()) {
                                        muehle(testList.get(i), stones, value);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    break;
                    case 4: {
                        for(Stones stones : testList) {
                            if((stones.getPosition() == 11 || stones.getPosition() == 19) && stones.getPosition() !=  pos1 && inMuehle(pos1,stones.getPosition())){
                                for(Stones value : testList){
                                    if((value.getPosition() == 11 || value.getPosition() == 19) && value.getPosition() !=  pos1 && value.getPosition() != stones.getPosition()){
                                        muehle(testList.get(i), stones, value);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    break;
                    case 6:{
                        for(Stones stones : testList) {
                            if((stones.getPosition() == 14 || stones.getPosition() == 21) && stones.getPosition() !=  pos1 && inMuehle(pos1,stones.getPosition())){
                                for(Stones value : testList){
                                    if((value.getPosition() == 14 || value.getPosition() == 21) && value.getPosition() !=  pos1 && value.getPosition() != stones.getPosition()){
                                        muehle(testList.get(i), stones, value);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    break;
                    case 7:{
                        for(Stones stones : testList) {
                            if((stones.getPosition() == 12 || stones.getPosition() == 16) && stones.getPosition() !=  pos1 && inMuehle(pos1,stones.getPosition())){
                                for(Stones value : testList){
                                    if((value.getPosition() == 12 || value.getPosition() == 16) && value.getPosition() !=  pos1 && value.getPosition() != stones.getPosition()){
                                        muehle(testList.get(i), stones, value);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    break;
                    case 9:{
                        for(Stones stones : testList) {
                            if((stones.getPosition() == 13 || stones.getPosition() == 18) && stones.getPosition() !=  pos1 && inMuehle(pos1,stones.getPosition())){
                                for(Stones value : testList){
                                    if((value.getPosition() == 13 || value.getPosition() == 18) && value.getPosition() !=  pos1 && value.getPosition() != stones.getPosition()){
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
        Stones[] temp = {stone1,stone2,stone3};
        muehlen.add(temp);
        removeStones();
    }

    private boolean inMuehle(int pos1, int pos2) {

        for (Stones[] stones : muehlen) {
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

    private void removeStones(){

    }
}
