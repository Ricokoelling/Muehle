import java.io.Serializable;

/**
 * is for sending USER-Data to the Server
 * Version: 0.01
 */
public class LoginData implements Serializable {

    private String PlayerID;
    private String PlayerAlias;
    private String Password;
    private Boolean Player;

    public LoginData(String playerID, String playerAlias, String password, Boolean player) {
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

    public String getPassword() {
        return Password;
    }

    public Boolean getPlayer() {
        return Player;
    }
}
