import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLOutput;

public class ServerConnection implements Runnable{

    private final Socket server;
    private final ObjectInputStream objReader;
    private int state = -1;
    private boolean gotData = false, gotAllowed = false;
    private int pos1,pos2;
    private boolean playerNumber;
    private boolean allowed = true;
    private String str;
    private boolean reset = false;

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

    public boolean isReset() {
        return reset;
    }

    public boolean isPlayerNumber() {
        return playerNumber;
    }
    public Boolean isAllowed(){
        return allowed;
    }

    public boolean isGotAllowed() {
        System.out.println("got: " + gotAllowed) ;
        return gotAllowed;
    }

    public void setGotAllowed(boolean gotAllowed) {
        this.gotAllowed = gotAllowed;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
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
                        AcceptData acceptData = (AcceptData) objReader.readObject();
                        allowed = acceptData.isAccept();
                        if (allowed) {
                            pos1 = acceptData.getPos1();
                            pos2 = acceptData.getPos2();
                            state = acceptData.getState();
                            playerNumber = acceptData.isPlayerNumb();
                            reset = acceptData.isReset();
                            System.out.println("[CLIENT] playernumber: " + playerNumber + " state: " + state + " pos1: " + pos1);
                        }
                        gotAllowed = true;
                    } while (!allowed);
                System.out.println("imout");
                    Data data = (Data) objReader.readObject();
                    state = data.getState();
                    playerNumber = data.isPlayer();
                    if (state != -1) {
                        pos1 = data.getPos1();
                        pos2 = data.getPos2();
                    } else {
                        break;
                    }
                gotData = true;
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
