import java.util.Iterator;
import java.util.LinkedList;

public class testMuehle extends Stones{

    private Stones[] player= new Stones[16];
    private Stones[] stonesMuehle = new Stones[2];
    private LinkedList<Stones[]> muehlen = new LinkedList<>();

    public testMuehle() {

    }
    public boolean fNumb(int pos){

        if(pos == 4 || pos == 7 || pos == 10 || pos == 13 || pos == 16 || pos == 19 || pos == 22) {
            return false;
        }
        return true;
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

    public boolean test() {
        player = getPlayer();

        for(int i= 0; i < 24 ; i++){
            int pos1 = player[i].getPosition();
            int pos2 = player[i + 1].getPosition();
            if(pos1 + 1 == pos2 && fNumb(pos2)){
                if(player[i].getPlayer() == player[i+1].getPlayer()){
                    int pos3 = player[i+2].getPosition();
                    int pos4 = player[i-1].getPosition();
                    if(pos1 + 2 == pos3 && pos2 + 1 == pos3 && fNumb(pos3)  && player[i+2].getPlayer() == player[i].getPlayer()){
                        return true;
                    }
                    else if(pos1 - 1 == pos4 && pos2 - 2 == pos4 && player[i-1].getPlayer() == player[i].getPlayer()){
                            if(pos4 == 3 || pos4 == 6 || pos4 == 9 || pos4 == 12 || pos4 == 15 || pos4 == 18 || pos4 == 21){
                                return true;
                            }
                    }
                }
            }else if(sNumb(pos1) == 1 || sNumb(pos1) == 2 ){
                for(int j = i+1 ; j < 24 ;j++){
                    if(sNumb(player[j].getPosition()) == sNumb(pos1) && player[i].getPlayer() == player[j].getPlayer()){
                        for(int k = j+1 ; k < 24 ; k++) {
                            if(sNumb(player[k].getPosition()) == sNumb(player[j].getPosition()) && player[i].getPlayer() == player[k].getPlayer()){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    public void removeStone(int playerNumb, int pos){
        Iterator it = muehlen.iterator();
        Stones[] playNumb = getPlayer();
        for(int i= 0; i < player.length ; i++){
            if(player[i].getPosition() == pos && player[i].getPlayerNumb() == playerNumb){
                player[i].setState(false);
            }
        }

    }
}
