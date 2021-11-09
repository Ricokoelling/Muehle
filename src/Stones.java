import java.util.HashMap;

public class Stones {
        protected static HashMap<Integer,Integer> position= new HashMap<>();
        protected static HashMap<Integer,Boolean> status= new HashMap<>();


    public Stones() {

    }

    public static HashMap<Integer, Boolean> getStatus() {
        return status;
    }

    public static HashMap<Integer, Integer> getPosition() {
        return position;
    }

    public static void setStatus(int key, boolean value) {
        Stones.status.put(key, value);
    }

    public static void setPosition(HashMap<Integer, Integer> position) {
        Stones.position = position;
    }


}
