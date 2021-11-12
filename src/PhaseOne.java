public class PhaseOne extends Stones{

    private int plOne = 0;
    private int plTwo = 0;

    public PhaseOne() {

    }

    public void putStones(int player, int pos) {
        System.out.println(player + " " + plOne);
            if(player == 1 && plOne < 9){       //uses playnumber and stonenumber to determine which map he needed use
                Stones stone = new Stones(pos, plOne, true,1);
                setPlayer(stone);
                plOne++;
            }
            if(player == 2 && plTwo < 9){
                Stones stone = new Stones(pos,plTwo,true,2);
                setPlayer(stone);
                plTwo++;
            }


    }
}
