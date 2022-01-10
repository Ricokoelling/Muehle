import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Clienthandler implements Runnable{
    private Socket client;
    private BufferedReader input;
    private PrintWriter output;
    private ArrayList<Clienthandler> clients;
    private Boolean playerNumber;
    private Master mst = new Master();


    public Clienthandler(Socket client, ArrayList<Clienthandler> clients) throws IOException {
        this.client = client;
        this.clients = clients;
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new PrintWriter(client.getOutputStream(), true);
        this.playerNumber = Boolean.parseBoolean(input.readLine());
    }

    /**
     * gets the output from each clint and sends it over: "outToDifferent" to the other client
     * !!! has to be updated to send only important data according to the phase
     */
    @Override
    public void run() {
        try {
            while (true){
                int phase = Integer.parseInt(input.readLine());
                int pos1 = Integer.parseInt(input.readLine());
                int pos2 = Integer.parseInt(input.readLine());
                int pos3 = Integer.parseInt(input.readLine());
                if( phase == 1) {
                    System.out.println("[SERVER] Pos1: " + pos1 + " Phase: " + phase);
                    phaseOne(pos1);
                    outToclient();
                    outToclient(phase);
                    outToclient(pos1);
                }
                else{
                    break;
                }

            }
        }catch (IOException e){
            System.err.println("Something happened that shouldn't have happened!");
            e.printStackTrace();
        } finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void outToclient(int send) {
        for(Clienthandler aClient : clients){
            aClient.output.println(send);
        }
    }
    private void outToclient() {
        for(Clienthandler aClient : clients){
            aClient.output.println(playerNumber);
        }
    }

        private void phaseOne(int pos1){
        if(mst.posTaken(pos1)){
            mst.add(pos1,this.playerNumber);
        }
    }

}
