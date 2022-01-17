import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerConnection implements Runnable{

    private Socket server;
    private ObjectInputStream objReader;
    private int state = -1;
    private boolean gotData = false, gotAllowed = false;
    private int pos1,pos2,pos3;
    private boolean playerNumber;
    private boolean allowed = true;
    private String str;
    private boolean clear = true;

    public ServerConnection(Socket server) throws IOException {
        this.server = server;
        objReader = new ObjectInputStream(server.getInputStream());
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
                    //allowed = Boolean.parseBoolean(reader.readLine());
                    gotAllowed = true;
                }while (!allowed);
                    Data data = (Data)objReader.readObject();
                    state = data.getState();
                    playerNumber = data.isPlayer();
                    if (state != -1) {
                        pos1 = data.getPos1();
                        pos2 = data.getPos2();
                    } else {
                        break;
                    }
                gotData = true;
                gotAllowed = false;
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            try {
                objReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
