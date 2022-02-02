import java.awt.*;
import java.io.Serializable;

/**
 * is for sending USER-Data to the Server
 * Version: 0.01
 */
public class LoginData implements Serializable {

    private String PlayerID;
    private String PlayerAlias;
    private char[] Password;
    private Boolean Player;
    private Color thisColor;
    private boolean register = false;

    public LoginData(String playerID, String playerAlias, char[] password, Boolean player) {
        this.PlayerID = playerID;
        this.PlayerAlias = playerAlias;
        this.Password = password;
        this.Player = player;
    }

    public String getPlayerID() {
        return PlayerID;
    }

    public String getPlayerAlias() {
        return PlayerAlias;
    }

    public Boolean getPlayer() {
        return Player;
    }

    public Color getThisColor() {
        return thisColor;
    }

    public void setThisColor(Color thisColor) {
        this.thisColor = thisColor;
    }
}
