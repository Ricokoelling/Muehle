import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection implements Runnable{

    private Socket server;
    private BufferedReader input;

    public ServerConnection(Socket s) throws IOException {
        this.server = s;
        input = new BufferedReader(new InputStreamReader(server.getInputStream()));

    }

    @Override
    public void run() {
        try {
            while (true){
                String serversays = null;
                serversays = input.readLine();
                System.out.println("Server says: " + serversays);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
