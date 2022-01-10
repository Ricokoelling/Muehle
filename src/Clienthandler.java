import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

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
     * !!! sends all Data otherwise it could send wrong data
     * ⠄⠄⠄⣾⣿⠿⠿⠶⠿⢿⣿⣿⣿⣿⣦⣤⣄⢀⡅⢠⣾⣛⡉⠄⠄⠄⠸⢀⣿
     * ⠄⠄⢀⡋⣡⣴⣶⣶⡀⠄⠄⠙⢿⣿⣿⣿⣿⣿⣴⣿⣿⣿⢃⣤⣄⣀⣥⣿⣿
     * ⠄⠄⢸⣇⠻⣿⣿⣿⣧⣀⢀⣠⡌⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⠿⣿⣿⣿
     * ⠄⢀⢸⣿⣷⣤⣤⣤⣬⣙⣛⢿⣿⣿⣿⣿⣿⣿⡿⣿⣿⡍⠄⠄⢀⣤⣄⠉⠋
     * ⠄⣼⣖⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⢇⣿⣿⡷⠶⠶⢿⣿⣿⠇⢀
     * ⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣿⣿⣿⡇⣿⣿⣿⣿⣿⣿⣷⣶⣥⣴⣿
     * ⢀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟
     * ⢸⣿⣦⣌⣛⣻⣿⣿⣧⠙⠛⠛⡭⠅⠒⠦⠭⣭⡻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃
     * ⠘⣿⣿⣿⣿⣿⣿⣿⣿⡆⠄⠄⠄⠄⠄⠄⠄⠄⠹⠈⢋⣽⣿⣿⣿⣿⣵⣾⠃
     * ⠄⠘⣿⣿⣿⣿⣿⣿⣿⣿⠄⣴⣿⣶⣄⠄⣴⣶⠄⢀⣾⣿⣿⣿⣿⣿⣿⠃⠄
     * ⠄⠄⠈⠻⣿⣿⣿⣿⣿⣿⡄⢻⣿⣿⣿⠄⣿⣿⡀⣾⣿⣿⣿⣿⣛⠛⠁⠄⠄
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

    /**
     * sends data "currently" to both clients
     * @param send  data
     */
    private void outToclient(int send) {
        for(Clienthandler aClient : clients){
            aClient.output.println(send);
        }
        /*if(clients.get(0).playerNumber == playerNumber){
            clients.get(1).output.println(send);
        }else {
            clients.get(0).output.println(send);
        }*/
    }
    private void outToclient() {
        for(Clienthandler aClient : clients){
            aClient.output.println(playerNumber);
        }
        /*if(clients.get(0).playerNumber == playerNumber){
            clients.get(1).output.println(playerNumber);
        }else {
            clients.get(0).output.println(playerNumber);
        }*/
    }

        private void phaseOne(int pos1){
        if(mst.posTaken(pos1)){
            mst.add(pos1,this.playerNumber);
        }
    }

}
