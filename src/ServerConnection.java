import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection implements Runnable{

    private Socket server;
    private BufferedReader reader;
    private int phase = -1;
    private boolean gotData = false;
    private int pos1;
    private boolean playerNumber;

    public ServerConnection(Socket server) throws IOException {
        this.server = server;
        reader = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    public boolean isGotData() {
        return gotData;
    }

    public int getPhase() {
        return phase;
    }
    public int getPos1(){
        gotData = false;
        return pos1;
    }
    public boolean isPlayerNumber() {
        return playerNumber;
    }

    /**
     * while testing found out we dont have to use different parse etc. maybe we should do make sure its send corretly
     * if we dont it only sends Strings
     */
    @Override
    public void run() {
        try {
            while (true) {
                playerNumber = Boolean.parseBoolean(reader.readLine());
                phase = Integer.parseInt(reader.readLine());
                if (phase != -1) {
                    gotData = true;
                    if (phase == 1) {
                        pos1 = Integer.parseInt(reader.readLine());
                    }
                }
                else{
                    break;
                }

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
