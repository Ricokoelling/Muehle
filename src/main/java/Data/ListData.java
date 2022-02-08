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
    private boolean acceptMatch = false;
    private boolean justBreakwhile = false;
    private boolean alreadyOnline = false;

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

    public void setAcceptMatch(boolean acceptMatch) {
        this.acceptMatch = acceptMatch;
    }

    public boolean isAcceptMatch() {
        return acceptMatch;
    }

    public void setJustBreakwhile(boolean justBreakwhile) {
        this.justBreakwhile = justBreakwhile;
    }

    public boolean isJustBreakwhile() {
        return justBreakwhile;
    }

    public boolean isAlreadyOnline() {
        return alreadyOnline;
    }

    public void setAlreadyOnline(boolean alreadyOnline) {
        this.alreadyOnline = alreadyOnline;
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
