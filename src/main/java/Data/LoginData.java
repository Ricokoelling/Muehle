package Data;

import java.awt.*;
import java.io.Serializable;

/**
 * is for sending USER-Data.Data to the Server
 * Version: 0.01
 */
public class LoginData implements Serializable {

    private String PlayerID;
    private int Password;
    private Color thisColor;
    private boolean register = false;

    public LoginData(String playerID, int password, boolean register) {
        this.PlayerID = playerID;
        this.Password = password;
        this.register = register;
    }

    public String getPlayerID() {
        return PlayerID;
    }

    public Color getThisColor() {
        return thisColor;
    }

    public void setThisColor(Color thisColor) {
        this.thisColor = thisColor;
    }

    public boolean isRegister() {
        return register;
    }

    public int getPassword() {
        return Password;
    }
}
