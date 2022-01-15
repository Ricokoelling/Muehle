import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLOutput;

public class ServerConnection implements Runnable{

    private Socket server;
    private BufferedReader reader;
    private int state = -1;
    private boolean gotData = false, gotAllowed = false;
    private int pos1,pos2,pos3;
    private boolean playerNumber;
    private boolean allowed = true;
    private String str;
    private boolean clear = true;

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
     * while testing found out we don't have to use different parse etc. maybe we should do make sure its send corretly
     * if we don't it only sends Strings
     */
    @Override
    public void run() {
        try {
            while (true) {
                do {
                    allowed = Boolean.parseBoolean(reader.readLine());
                    gotAllowed = true;
                }while (!allowed);
                System.out.println("[CLIENT] allowed: " + allowed);
                    state = Integer.parseInt(reader.readLine());
                    playerNumber = Boolean.parseBoolean(reader.readLine());
                    System.out.println("state: " + state + " pl: " + playerNumber);
                    if (state != -1) {
                        //versteh das if hier nicht ganz macht ja immer das gleiche :D (außer in state 2)
                        if (state == 1) {
                            pos1 = Integer.parseInt(reader.readLine());
                        } else if (state == 2 || state == 6 || state == 8 || state == 11 || state == 15 || state == 18 || state == 22 || state == 23) {
                        } else if (state == 3) {
                            pos1 = Integer.parseInt(reader.readLine());
                        } else if (state == 4) {
                            pos1 = Integer.parseInt(reader.readLine());
                        } else if (state == 5 || state == 10 || state == 12 || state == 17 || state == 19 || state == 24) {
                            pos1 = Integer.parseInt(reader.readLine());
                        } else if (state == 7 || state == 9 || state == 13 || state == 14 || state == 16 || state == 20 || state == 21) {
                            pos1 = Integer.parseInt(reader.readLine());
                            pos2 = Integer.parseInt(reader.readLine());
                            System.out.println(pos1 + " " + pos2);
                        }
                    } else {
                        break;
                    }
                gotData = true;
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
