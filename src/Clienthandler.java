import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Clienthandler implements Runnable{
    protected       final   Master                      mst                 =       new Master();
    private                 boolean                     allowed             =       true;
    private                 boolean                     onlyAnfang          =       true;
    private                 int                         count               =       0;
    private                 int                         maxstones           =       18;
    private                 boolean                     phaseChange         =       false;
    private                 boolean                     boothphase3         =       false;
    private         final   Socket                      client;
    private         final   BufferedReader              input;
    private         final   PrintWriter                 output;
    private         final   ArrayList<Clienthandler>    clients;
    private         final   boolean                     playerNumber;
    private                 Color                       playerColor;
    private                 boolean                     playerJump;
    private                 boolean                     poswasTaken = false;
    private                 boolean                     phase3 = false;
    private                 boolean                     win = false;


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
                int state   = Integer.parseInt(input.readLine());
                int pos1    = Integer.parseInt(input.readLine());
                int pos2    = Integer.parseInt(input.readLine());
                System.out.println("pl: " + playerNumber + " pos: " + pos1 + " count: " + count + " state: " + state);
                if(state == 1){
                        if (phaseOne(pos1,this.playerNumber) && phaseOneOtherPlayer(pos1) && !phase3) {
                            if (!checkMill()) {
                                System.out.println("[SERVER] No mill!");
                                if(maxstones == count){
                                    if((mst.winConditionOne(true) || mst.winConditionOne(false)) && count == maxstones){
                                        outTosameClient(true);
                                        outTosameClient(23);
                                    }else {
                                        if(checkPhase3()){
                                            outTosameClient(11);
                                            outTosameClient(playerNumber);
                                            outTosameClient(true);
                                            outToclient(true);
                                            outToclient(12);
                                        }else {
                                            phaseChange = true;
                                            if (clients.get(0).playerNumber == playerNumber) {
                                                clients.get(1).phaseChange = true;
                                            } else {
                                                clients.get(0).phaseChange = true;
                                            }
                                        }
                                        outToclient(5);
                                        outTosameClient(true);
                                        outTosameClient(6);
                                        outTosameClient(playerNumber);
                                    }
                                }else {
                                    System.out.println("ye");
                                    outToclient(1);
                                }
                            } else {
                                System.out.println("[SERVER] MILL!: " + playerNumber);
                                outTosameClient(allowed);
                                outTosameClient(2);
                                outTosameClient(playerNumber);
                                outToclient(3);
                            }
                            outToclient();
                            outToclient(pos1);
                        }else {
                            allowed = false;
                    }
                        //System.out.println("[SERVER] Pos1: " + pos1 + " Phase: " + phase);
                    }else if(state == 2){
                        if(win){
                            outTosameClient(true);
                            outTosameClient(23);
                            outToclient(true);
                            outToclient(24);
                            outToclient();
                            outToclient(pos1);
                        }
                        if (!mst.posTaken(pos1) && mst.sameplayerStone(pos1, playerNumber) && mst.removeStones(pos1, playerNumber)) {
                            boolean next = true;
                            if(clients.get(0).playerNumber == playerNumber){
                                if(!clients.get(1).mst.removeStones(pos1,playerNumber)){
                                    allowed = false;
                                    next = false;
                                }
                            }else {
                                if(!clients.get(0).mst.removeStones(pos1,playerNumber)){
                                    allowed = false;
                                    next = false;
                                }
                            }
                            if(next) {
                                maxstones--;
                                if(clients.get(0).playerNumber == playerNumber){
                                    clients.get(1).maxstones--;
                                }else {
                                    clients.get(0).maxstones--;
                                }
                                if (mst.getPlayerStones(!playerNumber) < 3 && phaseChange) {         //checks if the player whos stone got removed has still enough stones to potentially place a mill
                                    outTosameClient(true);
                                    outTosameClient(23);
                                    outToclient(true);
                                    outToclient(24);
                                    outToclient();
                                    outToclient(pos1);
                                } else {
                                        if (!phaseChange) {
                                            outToclient(true);
                                            outToclient(4);
                                            outToclient();
                                            outToclient(pos1);
                                        } else {
                                            if (!checkPhase3()) {
                                                outToclient(true);
                                                outToclient(10);
                                            } else {
                                                if(!boothphase3) {
                                                    outTosameClient(11);
                                                    outTosameClient(playerNumber);
                                                    outTosameClient(true);
                                                    outToclient(true);
                                                    outToclient(12);
                                                }else {
                                                    outTosameClient(18);
                                                    outTosameClient(playerNumber);
                                                    outTosameClient(true);
                                                    outToclient(true);
                                                    outToclient(19);
                                                }
                                            }
                                            outToclient();
                                            outToclient(pos1);
                                        }
                                    }
                                count--;
                                if(clients.get(0).playerNumber == playerNumber){
                                    clients.get(1).count--;
                                }else {
                                    clients.get(0).count--;
                                }
                            }
                        }else {
                            allowed = false;
                        }
                    }else if(state == 7){
                        if (!mst.freeposNextto(pos2, pos1, playerNumber) && mst.sameplayerStone(pos1,playerNumber)){
                            allowed = false;
                            poswasTaken = true;
                        }
                        if(clients.get(0).playerNumber == playerNumber){
                            if(!clients.get(1).mst.freeposNextto(pos2,pos1,playerNumber)){
                                allowed = false;
                                poswasTaken = true;
                            }
                        }else {
                            if(!clients.get(0).mst.freeposNextto(pos2,pos1,playerNumber)){
                                allowed = false;
                                poswasTaken = true;
                            }
                        }
                    if((mst.winConditionOne(true) || mst.winConditionOne(false)) && count == maxstones){
                        outTosameClient(true);
                        outTosameClient(23);
                    }else {
                        if (!poswasTaken) {
                            if (playerNumber == !playerJump && phase3) {
                                if(!checkMill()){
                                    outToclient(14);
                                }else {
                                    win(pos1,pos2);
                                    win = true;
                                }
                            } else if (!win){
                                if (!checkMill()) {
                                    System.out.println("[SERVER] No mill in phase 2!");
                                    outToclient(7);

                                } else {
                                    System.out.println("[SERVER] Mill in phase 2! " + allowed);
                                    outTosameClient(allowed);
                                    outTosameClient(8);
                                    outTosameClient(playerNumber);
                                    outToclient(9);
                                }
                            }
                            if(!win) {
                                outToclient();
                                outToclient(pos1);
                                outToclient(pos2);
                                stillMill();
                            }
                        } else {
                            allowed = false;
                        }
                    }
                }else if(state == 13){
                    if(!boothphase3) {
                        if (mst.posTaken(pos2) && playerJump == playerNumber) {
                            moveStone(pos1,pos2);
                            if (!checkMill()){
                                outToclient(13);
                            }else {
                                outTosameClient(allowed);
                                outTosameClient(15);
                                outTosameClient(playerNumber);
                                outToclient(16);
                            }
                            outToclient();
                            outToclient(pos1);
                            outToclient(pos2);
                            stillMill();
                        }else {
                            allowed = false;
                        }
                    }
                    else{
                        if (mst.posTaken(pos2)) {
                            moveStone(pos1,pos2);
                            if (!checkMill()){
                                outToclient(20);
                                outToclient();
                                outToclient(pos1);
                                outToclient(pos2);
                                stillMill();
                            }else {
                                win(pos1,pos2);
                            }
                        }else {
                            allowed = false;
                        }
                    }

                }else if(state == 1000){
                    maxstones = 17;
                    count = 0;
                    pos2 = 0;
                    phaseChange = false;
                    boothphase3 = false;
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
        return count > 3 && (mst.checkMill(true) || mst.checkMill(false));
    }

    private boolean checkPhase3(){
        System.out.println("checkto: " + mst.getPlayerStones(!playerNumber) + " " + mst.getPlayerStones(playerNumber));
        if(mst.getPlayerStones(!playerNumber) == 3 && mst.getPlayerStones(playerNumber) == 3){
            boothphase3 = true;
            if(clients.get(0).playerNumber == playerNumber){
                clients.get(1).boothphase3 = true;
            }else {
                clients.get(0).boothphase3 = true;
            }
            return true;
        }else {
            if ((mst.getPlayerStones(!playerNumber) == 3 || mst.getPlayerStones(playerNumber) == 3 )&& phaseChange) {
                if(mst.getPlayerStones(!playerNumber) == 3) {
                    playerJump = !playerNumber;
                    phase3 = true;
                }else if(mst.getPlayerStones(playerNumber) == 3){
                    phase3 = true;
                    playerJump = playerNumber;
                }
                if (clients.get(0).playerNumber == playerNumber) {
                    clients.get(1).phase3 = true;
                } else {
                    clients.get(0).phase3 = true;
                }
                return true;
            }
        }
        return false;
    }

    private void stillMill(){
        mst.stillMill();
        if(clients.get(0).playerNumber == playerNumber){
            clients.get(1).mst.stillMill();
        }else {
            clients.get(0).mst.stillMill();
        }
    }
    private void moveStone(int pos1, int pos2){
        mst.moveStone(pos1, pos2, playerNumber);
        if(clients.get(0).playerNumber == playerNumber){
            clients.get(1).mst.moveStone(pos1,pos2,playerNumber);
        }else {
            clients.get(0).mst.moveStone(pos1,pos2,playerNumber);
        }
    }
    private void win(int pos1, int pos2){
        outToclient(21);
        outToclient();
        outToclient(pos1);
        outToclient(pos2);
        outTosameClient(true);
        outTosameClient(22);
    }
}
