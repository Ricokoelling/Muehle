import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLOutput;

public class ServerConnection implements Runnable{

    private Socket server;
    private final BufferedReader reader;
    private int state = -1;
    private boolean gotData = false, gotAllowed = false;
    private int pos1,pos2,pos3;
    private boolean playerNumber;
    private boolean allowed = false;

    public ServerConnection(Socket server) throws IOException {
        this.server = server;
        reader = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    public void setGotData(boolean gotData) {
        this.gotData = gotData;
    }

    public boolean isGotData() {
        return gotData;
    }

    public int getState() {
        return state;
    }
    public int getPos1(){
        gotData = false;
        return pos1;
    }

    public int getPos2() {
        return pos2;
    }

    public int getPos3() {
        return pos3;
    }

    public boolean isPlayerNumber() {
        return playerNumber;
    }
    public Boolean isAllowed(){
        return allowed;
    }

    public boolean isGotAllowed() {
        return gotAllowed;
    }

    /**
     * while testing found out we dont have to use different parse etc. maybe we should do make sure its send corretly
     * if we dont it only sends Strings
     */
    @Override
    public void run() {
        try {
            while (true) {
                allowed = Boolean.parseBoolean(reader.readLine());
                gotAllowed = true;
                System.out.println("[CLIENT] allowed: " + allowed);
                state = Integer.parseInt(reader.readLine());
                playerNumber = Boolean.parseBoolean(reader.readLine());
                System.out.println("state: " + state + " pl: " + playerNumber);
                if (state != -1) {
                    if (state == 1) {
                        pos1 = Integer.parseInt(reader.readLine());
                        gotData = true;
                    }else if (state == 2){
                        gotData = true;
                    }else if(state == 3){
                        pos1 = Integer.parseInt(reader.readLine());
                        gotData = true;
                    }else if(state == 4){
                        pos1 = Integer.parseInt(reader.readLine());
                        gotData = true;
                    }
                }
                else{
                    break;
                }
                gotAllowed = false;
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
