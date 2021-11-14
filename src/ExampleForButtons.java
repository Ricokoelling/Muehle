import java.util.ArrayList;

public class ExampleForButtons {


    private static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) {
        /*input inp = new input();
        tes t = new tes();
        ausgabe aus = new ausgabe();*/
        list.add(5);
        list.add(10);

        System.out.println(list);


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


