import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Clienthandler implements Runnable{

    private final Socket                      client;
    private final BufferedReader              input;
    private final PrintWriter                 output;
    private final ArrayList<Clienthandler>    clients;
    private final boolean                     playerNumber;
    private Color                       playerColor;
    protected final Master                      mst                 =       new Master();
    private boolean                     allowed             =       true;
    private boolean                     onlyAnfang          =       true;
    private int                         count               =       0;
    private int                         maxstones           =       9;
    private int                         Clientphase         =        1;
    private boolean                     phaseChange         =       false;
    private boolean                     playerJump;
    private                     boolean     boothphase3         = false;



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
            while (true) {
                if (!playerNumber && onlyAnfang) {
                    outTosameClient(allowed);
                    onlyAnfang = false;
                }
                allowed = true;
                int state = Integer.parseInt(input.readLine());
                int pos1 = Integer.parseInt(input.readLine());
                int pos2 = Integer.parseInt(input.readLine());
                int pos3 = Integer.parseInt(input.readLine());
                //System.out.println("[SERVER] plNumb: " + playerNumber + " state: " + state + " pos: " + pos1);
                if(state == 1){
                        if (phaseOne(pos1,this.playerNumber) && phaseOneOtherPlayer(pos1)) {
                            System.out.println("pl: " + playerNumber + " pos: " + pos1 + " count: " + count);
                            if (!checkMill()) {
                                System.out.println("[SERVER] No mill!");
                                outToclient(1);
                            } else {
                                System.out.println("[SERVER] MILL!: " + playerNumber);
                                outTosameClient(allowed);
                                outTosameClient(2);
                                outTosameClient(playerNumber);
                                outToclient(3);
                            }
                            outToclient();
                            outToclient(pos1);
                        }
                        //System.out.println("[SERVER] Pos1: " + pos1 + " Phase: " + phase);
                    }else if(state == 2){
                    System.out.println(playerNumber);
                        if (!mst.posTaken(pos1) && mst.sameplayerStone(pos1, !playerNumber) && mst.removeStones(pos1, !playerNumber)) {
                            boolean next = true;
                            if(clients.get(0).playerNumber == playerNumber){
                                if(!clients.get(1).mst.removeStones(pos1,playerNumber)){
                                    next = false;
                                }
                            }else {
                                if(!clients.get(0).mst.removeStones(pos1,playerNumber)){
                                    next = false;
                                }
                            }
                            System.out.println(next);
                            if(next) {
                                maxstones--;
                                if (mst.getPlayerStones(!playerNumber) < 3 && phaseChange) {         //checks if the player whos stone got removed has still enough stones to potentially place a mill
                                    Clientphase = 4;
                                } else {
                                    if (!phaseChange) {
                                        Clientphase = 1;
                                        outToclient(true);
                                        outToclient(4);
                                        outToclient();
                                        outToclient(pos1);
                                    } else {
                                        if (!checkPhase3()) {
                                            Clientphase = 3;
                                        }
                                    }
                                }
                                count--;
                            }
                        }else {
                            allowed = false;
                        }
                    }else {
                    break;
                }
                if(state != 2) {
                    outTosameClient(allowed);
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

    /*for(Clienthandler aClient : clients){
            aClient.output.println(playerNumber);
        }*/
    /**
     * sends data "currently" to both clients
     * @param send  data
     */
    private void outToclient(int send) {
        if(clients.get(0).playerNumber == playerNumber){
            clients.get(1).output.println(send);
        }else {
            clients.get(0).output.println(send);
        }
    }
    private void outToclient(boolean send) {
        if(clients.get(0).playerNumber == playerNumber){
            clients.get(1).output.println(send);
        }else {
            clients.get(0).output.println(send);
        }
    }
    private void outToclient() {
        if(clients.get(0).playerNumber == playerNumber){
            clients.get(1).output.println(playerNumber);
        }else {
            clients.get(0).output.println(playerNumber);
        }
    }

    private void outTosameClient(boolean send) {
        if(clients.get(0).playerNumber == playerNumber){
            clients.get(0).output.println(send);
        }else {
            clients.get(1).output.println(send);
        }
    }
    private void outTosameClient(int send) {
        if(clients.get(0).playerNumber == playerNumber){
            clients.get(0).output.println(send);
        }else {
            clients.get(1).output.println(send);
        }
    }




    protected boolean phaseOne(int pos1, boolean plnumber){
        if(mst.posTaken(pos1)){
            mst.add(pos1,plnumber);
            count++;
            return true;
        }else {
            allowed = false;
            return false;
        }
    }
    private boolean phaseOneOtherPlayer(int pos1) {
        if (clients.get(0).playerNumber == playerNumber) {
            if (clients.get(1).mst.posTaken(pos1)) {
                if (!clients.get(1).phaseOne(pos1, playerNumber)) {
                    allowed = false;
                    return false;
                }
            } else {
                allowed = false;
                return false;
            }
        } else{
            if (clients.get(0).mst.posTaken(pos1)) {
                if (!clients.get(0).phaseOne(pos1,playerNumber)) {
                    allowed = false;
                    return false;
                }
            } else {
                allowed = false;
                return false;
            }
        }
        return true;
    }
    private boolean checkMill(){
        if(count > 3 && mst.checkMill(playerNumber)){
            Clientphase = 0;
            return true;
        }

        return false;
    }

    private boolean checkPhase3(){
        if(mst.getPlayerStones(!playerNumber) == 3 && mst.getPlayerStones(playerNumber) == 3){
            boothphase3 = true;
            Clientphase = 3;
            return true;
        }else {
            if (mst.getPlayerStones(!playerNumber) == 3 && phaseChange) {
                Clientphase = 3;
                playerJump = !playerNumber;
                return true;
            }
        }
        return false;
    }
}
