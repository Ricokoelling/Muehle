import java.util.ArrayList;

public class ExampleForButtons {


    private static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) {
         ArrayList<Integer[]> muehlenPlayerOne = new ArrayList<>();
         Integer[]  stone1 = new Integer[2];
        Integer[]  stone2 = new Integer[2];
        Integer[]  stone3 = new Integer[2];
        stone1[0] = 1;
        stone2[0] = 2;
        stone3[0] = 3;


        muehlenPlayerOne.add(stone1);
        muehlenPlayerOne.add(stone2);
        muehlenPlayerOne.add(stone3);

        muehlenPlayerOne.remove(1);
        for(int i = 0; i < muehlenPlayerOne.size(); i++) {
            for(int j = 0; j < 2; j++) {
                System.out.println(muehlenPlayerOne.get(i)[j]);
            }
        }


    }

}

class Stone {
    private int position;
    private int stNumb;
    private boolean state;
    private int playerNumb;

    public Stone(int position, int stNumb, boolean state, int playerNumb) {
        this.position = position;
        this.playerNumb = playerNumb;
        this.state = state;
        this.stNumb = stNumb;
    }
    @Override
    public String toString() {
        return "Stone{" +
                "position=" + position +
                ", stNumb=" + stNumb +
                ", state=" + state +
                ", playerNumb=" + playerNumb +
                '}';
    }
}
class Mister{
    protected static Stone[] stein = new Stone[5];
    public Mister() {
    }
}

class input{
    Mister mst = new Mister();
    public input() {
    }
    public void put(int k){
        for(int i = 0; i < 4; i++){
            Stone kiesel = new Stone(i,i+1,true, k);
            Mister.stein[i] = kiesel;
        }
    }
}

class ausgabe extends Mister{
    public ausgabe() {
        for (int i = 0; i < Mister.stein.length + 1; i++) {
            System.out.println(Mister.stein[i].toString());
        }
    }
}

class tes extends input{
    public tes() {
        put(1);
    }
}


