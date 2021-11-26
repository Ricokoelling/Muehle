public class Stones {

    private int position;
    private boolean state;

    public Stones(int pos, boolean state) {
        this.position = pos;
        this.state = state;
    }
    public int getPosition() {return position;}

    public void setPosition(int position) {
        this.position = position;
    }
    //Is redundant
    public boolean isState() {
        return state;
    }

    //also redundant
    public void setState(boolean state) {this.state = state;}

    @Override
    public String toString() {
        return "Stones{" +
                "position=" + position +
                ", state=" + state +
                '}';
    }
}
