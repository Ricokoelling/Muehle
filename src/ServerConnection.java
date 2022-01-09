import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection implements Runnable{

    private Socket server;
    private BufferedReader phaseReader;

    public ServerConnection(Socket server) throws IOException {
        this.server = server;
        phaseReader = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    /**
     * while testing found out we dont have to use different parse etc. maybe we should do make sure its send corretly
     * if we dont it only sends Strings
     */
    @Override
    public void run() {
        try {
            int phase = -1;
            phase = Integer.parseInt(phaseReader.readLine());

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
