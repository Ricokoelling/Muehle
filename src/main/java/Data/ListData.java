package Data;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListData implements Serializable {

    private ArrayList<String> UserList;
    private HashMap<String, Color> UserColor;
    private String opponent;
    private boolean challenger = false;
    private boolean accept = true;
    private boolean justreturnList = false;

    public ListData(ArrayList<String> userList, String opponent, Boolean challenger) {
        this.UserList = userList;
        this.opponent = opponent;
        this.challenger = challenger;
    }

    public String getOpponent() {
        return opponent;
    }

    public boolean isChallenger() {
        return challenger;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public boolean isJustreturnList() {
        return justreturnList;
    }

    public ArrayList<String> getUserList() {
        return UserList;
    }

    @Override
    public String toString() {
        return "ListData{" +
                "UserList=" + UserList +
                ", UserColor=" + UserColor +
                ", opponent='" + opponent + '\'' +
                ", challenger=" + challenger +
                ", accept=" + accept +
                ", justreturnList=" + justreturnList +
                '}';
    }
}
