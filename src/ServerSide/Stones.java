package ServerSide;

public class Stones {

    private int position;

    public Stones(int pos) {
        this.position = pos;
    }
    protected int getPosition(){
        return position;
    }

    protected void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "ServerSide.Stones{" +
                "position=" + position +
                '}';
    }
}
