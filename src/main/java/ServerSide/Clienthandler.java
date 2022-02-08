package ServerSide;

import Data.AcceptData;
import Data.Data;
import Data.ListData;
import Data.LoginData;
import SQL.SQLite;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Clienthandler implements Runnable {
    protected final Master mst = new Master();
    private boolean onlyAnfang = true;
    private int count = 0;
    private int maxstones = 18;
    private boolean phaseChange = false;
    private boolean boothphase3 = false;
    private final Socket client;
    private final ObjectInputStream objReader;
    private final ObjectOutputStream objWriter;
    private final ArrayList<Clienthandler> ALLclients;
    private final ArrayList<Clienthandler> clients = new ArrayList<>();
    private ArrayList<String> userList = new ArrayList<>();
    private boolean playerNumber = true;
    private String playerID;
    private String playerName;
    private Color playerColor;
    private boolean playerJump;
    private boolean poswasTaken = false;
    private boolean phase3 = false;
    private boolean win = false;
    public boolean ingame = false;
    private SQLite sql;


    public Clienthandler(Socket client, ArrayList<Clienthandler> clients, SQLite sql) throws IOException {
        this.client = client;
        this.ALLclients = clients;
        this.sql = sql;
        objWriter = new ObjectOutputStream(client.getOutputStream());
        objReader = new ObjectInputStream(client.getInputStream());
    }

    /**
     * gets the output from each clint and sends it over: "outToDifferent" to the other client
     * !!! sends all Data.Data otherwise it could send wrong data
     */
    /*
    TODO: write function that sends everyone a ArrayList with all players except their self
     */
    @Override
    public void run() {
        try {
            while (true) {
                LoginData loginData = (LoginData) objReader.readObject();
                if (loginData.isRegister()) {
                    if (sql.create(loginData.getPlayerID(), loginData.getPassword())) {
                        this.playerID = loginData.getPlayerID();
                        sendList();
                        break;
                    } else {
                        ListData wrongData = new ListData(userList,null,false);
                        wrongData.setAccept(false);
                        this.objWriter.writeObject(wrongData);

                    }
                } else {
                    if (sql.login(loginData.getPlayerID(), loginData.getPassword())) {
                        this.playerID = loginData.getPlayerID();
                        sendList();
                        break;
                    } else {
                        ListData wrongData = new ListData(userList,null,false);
                        wrongData.setAccept(false);
                        this.objWriter.writeObject(wrongData);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                while (true) {
                    ListData listData = (ListData) objReader.readObject();          // guy asks for match
                    if (listData.isJustreturnList()) {
                        //sql ask for list
                        this.objWriter.writeObject(new ListData(userList, playerID, false));
                    } else {
                        if (listData.isChallenger()) {
                            for (Clienthandler cl : ALLclients) {
                                if (cl.playerID.equals(listData.getOpponent())) {             //lookin out if opponent exists
                                    cl.objWriter.writeObject(new ListData(userList, this.playerID, true));
                                }
                            }
                        } else {
                            if (listData.isAccept()) {
                                for (Clienthandler cl : ALLclients) {
                                    if (listData.getOpponent().equals(cl.playerID)) {
                                        cl.objWriter.writeObject(new ListData(userList, this.playerID, true));
                                        clients.add(this);
                                        clients.add(cl);
                                        cl.clients.add(this);
                                        cl.clients.add(cl);
                                    }
                                }
                                ingame = true;
                                break;
                            }
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                while (true) {
                    if (!playerNumber && onlyAnfang) {
                        outTosameClient(0, true, false, 0);
                        onlyAnfang = false;
                    }
                    Data data = (Data) objReader.readObject();
                    int state = data.getState();
                    int pos1 = data.getPos1();
                    int pos2 = data.getPos2();
                    if (playerNumber) {
                        playerColor = data.getPlayerOne();
                    } else {
                        playerColor = data.getPlayerTwo();
                    }
                    System.out.println("[SERVER] state: " + state + " pos1: " + pos1 + " pos2: " + pos2 + " PlayerID " + data.getPlayerID() + "plNumber: " + playerNumber + " reset: " + data.isReset() + " count: " + count + " != " + maxstones);

                    if (state == 1) {
                        if (phaseOne(pos1, this.playerNumber) && phaseOneOtherPlayer(pos1) && !phase3) {
                            if (!checkMill()) {
                                System.out.println("[SERVER] No mill! in state 1");
                                if (maxstones == count) {
                                    phaseChange = true;
                                    if ((mst.winConditionOne(true) || mst.winConditionOne(false)) && count == maxstones) {
                                        outTosameClient(23, true, playerNumber, 0);
                                        break;
                                    } else {
                                        if (checkPhase3()) {
                                            outTosameClient(26, true, playerNumber, pos1);
                                            outToclient(27, playerNumber, pos1);
                                        } else {
                                            if (clients.get(0).playerNumber == playerNumber) {
                                                clients.get(1).phaseChange = true;
                                            } else {
                                                clients.get(0).phaseChange = true;
                                            }
                                            outToclient(5, playerNumber, pos1);
                                            outTosameClient(6, true, playerNumber, pos1);
                                        }
                                    }
                                } else {
                                    outTosameClient(1, true, playerNumber, pos1);
                                    outToclient(1, playerNumber, pos1);
                                }
                            } else {
                                System.out.println("[SERVER] MILL! in state 1: " + playerNumber);
                                outTosameClient(2, true, playerNumber, pos1);
                                outTosameClientData(2, playerNumber, pos1);
                                outToclient(3, playerNumber, pos1);
                            }
                        } else {
                            outTosameClient(0, false, playerNumber, 0);
                        }
                    } else if (state == 2) {
                        if (!mst.posTaken(pos1) && mst.sameplayerStone(pos1, playerNumber) && mst.removeStones(pos1, playerNumber)) {
                            boolean next = true;
                            if (clients.get(0).playerNumber == playerNumber) {
                                if (!clients.get(1).mst.removeStones(pos1, playerNumber)) {
                                    outTosameClient(0, false, playerNumber, 0);
                                    next = false;
                                }
                            } else {
                                if (!clients.get(0).mst.removeStones(pos1, playerNumber)) {
                                    outTosameClient(0, false, playerNumber, 0);
                                    next = false;
                                }
                            }
                            if (maxstones != count) {
                                if (next) {
                                    maxstones--;
                                    if (clients.get(0).playerNumber == playerNumber) {
                                        clients.get(1).maxstones--;
                                    } else {
                                        clients.get(0).maxstones--;
                                    }
                                    if (mst.getPlayerStones(!playerNumber) < 3 && phaseChange) {         //checks if the player whos stone got removed has still enough stones to potentially place a mill
                                        outTosameClient(23, true, playerNumber, pos1);
                                        outToclient(24, playerNumber, pos1);
                                    } else {
                                        if (!phaseChange) {
                                            dummy();
                                            outTosameClient(4, true, playerNumber, pos1);
                                            outToclient(4, playerNumber, pos1);
                                        } else {
                                            if (!checkPhase3()) {
                                                dummy();
                                                outTosameClient(10, true, playerNumber, pos1);
                                                outToclient(10, playerNumber, pos1);
                                            } else {
                                                if (!boothphase3) {
                                                    if (playerNumber != playerJump) {
                                                        dummy();
                                                        outTosameClient(26, true, playerNumber, pos1);
                                                        outToclient(27, playerNumber, pos1);
                                                    } else {
                                                        dummy();
                                                        outTosameClient(15, true, playerNumber, pos1);
                                                        outToclient(25, playerNumber, pos1);
                                                    }
                                                } else {
                                                    dummy();
                                                    outTosameClient(18, true, playerNumber, pos1);
                                                    outToclient(19, playerNumber, pos1);
                                                }
                                            }
                                        }
                                    }
                                    count--;
                                    if (clients.get(0).playerNumber == playerNumber) {
                                        clients.get(1).count--;
                                    } else {
                                        clients.get(0).count--;
                                    }
                                }
                            } else {
                                phaseChange = true;
                                if (mst.getPlayerStones(!playerNumber) < 3 && phaseChange) {         //checks if the player whos stone got removed has still enough stones to potentially place a mill
                                    outTosameClient(23, true, playerNumber, pos1);
                                    outToclient(24, playerNumber, pos1);
                                } else {
                                    if (!checkPhase3()) {
                                        dummy();
                                        outTosameClient(10, true, playerNumber, pos1);
                                        outToclient(10, playerNumber, pos1);
                                    } else {
                                        if (!boothphase3) {
                                            if (playerNumber != playerJump) {
                                                dummy();
                                                outTosameClient(26, true, playerNumber, pos1);
                                                outToclient(27, playerNumber, pos1);
                                            } else {
                                                dummy();
                                                outTosameClient(15, true, playerNumber, pos1);
                                                outToclient(25, playerNumber, pos1);
                                            }
                                        } else {
                                            dummy();
                                            outTosameClient(18, true, playerNumber, pos1);
                                            outToclient(19, playerNumber, pos1);
                                        }
                                    }
                                }
                            }
                        } else {
                            outTosameClient(0, false, playerNumber, 0);
                        }
                    } else if (state == 7) {
                        if (!mst.sameplayerStone(pos1, playerNumber)) {
                            if (!mst.freeposNextto(pos2, pos1, playerNumber)) {
                                outTosameClient(0, false, playerNumber, 0);
                                poswasTaken = true;
                            }
                            if (!poswasTaken) {
                                if (clients.get(0).playerNumber == playerNumber) {
                                    if (!clients.get(1).mst.freeposNextto(pos2, pos1, playerNumber)) {
                                        outTosameClient(0, false, playerNumber, 0);
                                    }
                                } else {
                                    if (!clients.get(0).mst.freeposNextto(pos2, pos1, playerNumber)) {
                                        outTosameClient(0, false, playerNumber, 0);
                                    }
                                }
                                if ((mst.winConditionOne(true) || mst.winConditionOne(false)) && count == maxstones) {
                                    outTosameClient(23, true, playerNumber, pos1, pos2);
                                    break;
                                } else {
                                    if (playerNumber == !playerJump && phase3) {
                                        if (!checkMill()) {
                                            outTosameClient(14, true, playerNumber, pos1, pos2);
                                            outToclient(14, playerNumber, pos1, pos2);
                                        } else {
                                            win(pos1, pos2);
                                            win = true;
                                            break;
                                        }
                                    } else if (!win) {
                                        if (!checkMill()) {
                                            System.out.println("[SERVER] No mill in state 7!");
                                            outTosameClient(7, true, playerNumber, pos1, pos2);
                                            outToclient(7, playerNumber, pos1, pos2);


                                        } else {
                                            System.out.println("[SERVER] Mill in state 7! ");
                                            outTosameClient(8, true, playerNumber, pos1, pos2);
                                            outTosameClientData(8, playerNumber, pos1, pos2);
                                            outToclient(9, playerNumber, pos1, pos2);
                                        }
                                    }
                                    if (!win) {
                                        stillMill();
                                    }
                                }
                            }
                        } else {
                            poswasTaken = false;
                            outTosameClient(0, false, playerNumber, 0);
                        }
                    } else if (state == 13) {
                        if (!boothphase3) {
                            if (mst.posTaken(pos2) && playerJump == playerNumber) {
                                moveStone(pos1, pos2);
                                if (!checkMill()) {
                                    outTosameClient(13, true, playerNumber, pos1, pos2);
                                    outToclient(13, playerNumber, pos1, pos2);
                                } else {
                                    outTosameClient(15, true, playerNumber, pos1, pos2);
                                    outTosameClientData(21, playerNumber, pos1, pos2);
                                    outToclient(16, playerNumber, pos1, pos2);
                                }
                                stillMill();
                            } else {
                                outTosameClient(0, false, playerNumber, 0);
                            }
                        } else {
                            if (mst.posTaken(pos2)) {
                                moveStone(pos1, pos2);
                                if (!checkMill()) {
                                    outTosameClient(20, true, playerNumber, pos1, pos2);
                                    outToclient(20, playerNumber, pos1, pos2);
                                    stillMill();
                                } else {
                                    win(pos1, pos2);
                                    win = true;
                                    break;
                                }
                            } else {
                                outTosameClient(0, false, playerNumber, 0);
                            }
                        }

                    } else if (state == 1000) {
                        reset();
                    } else {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Something happened that shouldn't have happened!");
                e.printStackTrace();
            }
        }
        /*try {
            objReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /*for(ServerSide.Clienthandler aClient : clients){
            aClient.output.println(playerNumber);
        }*/
    private void outToclient(int state, boolean playerNumber, int pos1) throws IOException {
        Data sendData = new Data(state, pos1, 0, null, false, playerNumber);
        if (playerNumber) {
            sendData.setPlayerOne(playerColor);
        } else {
            sendData.setPlayerTwo(playerColor);
        }
        if (clients.get(0).playerNumber == playerNumber) {
            clients.get(1).objWriter.writeObject(sendData);
        } else {
            clients.get(0).objWriter.writeObject(sendData);
        }
    }

    private void outToclient(int state, boolean playerNumber, int pos1, int pos2) throws IOException {
        Data sendData = new Data(state, pos1, pos2, null, false, playerNumber);
        if (playerNumber) {
            sendData.setPlayerOne(playerColor);
        } else {
            sendData.setPlayerTwo(playerColor);
        }
        if (clients.get(0).playerNumber == playerNumber) {
            clients.get(1).objWriter.writeObject(sendData);
        } else {
            clients.get(0).objWriter.writeObject(sendData);
        }
    }

    private void outTosameClient(int state, boolean allowed, boolean playerNumber, int pos1) throws IOException {
        AcceptData acceptData = new AcceptData(allowed, state, pos1, 0, null, playerNumber, false);
        if (clients.get(0).playerNumber == playerNumber) {
            clients.get(0).objWriter.writeObject(acceptData);
        } else {
            clients.get(1).objWriter.writeObject(acceptData);
        }
    }

    private void outTosameClient(int state, boolean allowed, boolean playerNumber, int pos1, int pos2) throws IOException {
        AcceptData acceptData = new AcceptData(allowed, state, pos1, pos2, null, playerNumber, false);
        if (clients.get(0).playerNumber == playerNumber) {
            clients.get(0).objWriter.writeObject(acceptData);
        } else {
            clients.get(1).objWriter.writeObject(acceptData);
        }
    }

    private void outTosameClientData(int state, boolean playerNumber, int pos1) throws IOException {
        Data sendData = new Data(state, pos1, 0, null, false, playerNumber);
        if (playerNumber) {
            sendData.setPlayerOne(playerColor);
        } else {
            sendData.setPlayerTwo(playerColor);
        }
        if (clients.get(0).playerNumber == playerNumber) {
            clients.get(0).objWriter.writeObject(sendData);
        } else {
            clients.get(1).objWriter.writeObject(sendData);
        }
    }

    private void outTosameClientData(int state, boolean playerNumber, int pos1, int pos2) throws IOException {
        Data sendData = new Data(state, pos1, pos2, null, false, playerNumber);
        if (clients.get(0).playerNumber == playerNumber) {
            clients.get(0).objWriter.writeObject(sendData);
        } else {
            clients.get(1).objWriter.writeObject(sendData);
        }
    }

    private void dummy() throws IOException {
        AcceptData acceptData = new AcceptData(true, 0, 0, 0, null, playerNumber, false);
        if (clients.get(0).playerNumber == playerNumber) {
            clients.get(1).objWriter.writeObject(acceptData);
        } else {
            clients.get(0).objWriter.writeObject(acceptData);
        }
    }


    protected boolean phaseOne(int pos1, boolean plnumber) {
        if (mst.posTaken(pos1)) {
            mst.add(pos1, plnumber);
            count++;
            return true;
        } else {
            return false;
        }
    }

    private boolean phaseOneOtherPlayer(int pos1) {
        if (clients.get(0).playerNumber == playerNumber) {
            if (clients.get(1).mst.posTaken(pos1)) {
                return clients.get(1).phaseOne(pos1, playerNumber);
            } else {
                return false;
            }
        } else {
            if (clients.get(0).mst.posTaken(pos1)) {
                return clients.get(0).phaseOne(pos1, playerNumber);
            } else {
                return false;
            }
        }
    }

    private boolean checkMill() {
        return count > 3 && (mst.checkMill(true) || mst.checkMill(false));
    }

    private boolean checkPhase3() {
        if (mst.getPlayerStones(!playerNumber) == 3 && mst.getPlayerStones(playerNumber) == 3) {
            boothphase3 = true;
            if (clients.get(0).playerNumber == playerNumber) {
                clients.get(1).boothphase3 = true;
            } else {
                clients.get(0).boothphase3 = true;
            }
            return true;
        } else {
            if ((mst.getPlayerStones(!playerNumber) == 3 || mst.getPlayerStones(playerNumber) == 3) && phaseChange) {
                if (mst.getPlayerStones(!playerNumber) == 3) {
                    playerJump = !playerNumber;
                    phase3 = true;
                } else if (mst.getPlayerStones(playerNumber) == 3) {
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

    private void stillMill() {
        mst.stillMill();
        if (clients.get(0).playerNumber == playerNumber) {
            clients.get(1).mst.stillMill();
        } else {
            clients.get(0).mst.stillMill();
        }
    }

    private void moveStone(int pos1, int pos2) {
        mst.moveStone(pos1, pos2, playerNumber);
        if (clients.get(0).playerNumber == playerNumber) {
            clients.get(1).mst.moveStone(pos1, pos2, playerNumber);
        } else {
            clients.get(0).mst.moveStone(pos1, pos2, playerNumber);
        }
    }

    private void win(int pos1, int pos2) throws IOException {
        outTosameClient(23, true, playerNumber, pos1, pos2);
        outToclient(23, playerNumber, pos1, pos2);
        System.out.println("[SERVER] ClientSide.Client: " + playerNumber + " Won the Game!");
    }

    private void reset() throws IOException {           //only in one direction
        maxstones = 17;
        count = 0;
        phaseChange = false;
        boothphase3 = false;
        mst.reset();
        if (clients.get(0).playerNumber == playerNumber) {
            clients.get(1).resetOther();
        } else {
            clients.get(0).resetOther();
        }
        AcceptData acceptData = new AcceptData(true, 1000, 0, 0, null, playerNumber, true);
        if (clients.get(0).playerNumber == playerNumber) {
            clients.get(1).objWriter.writeObject(acceptData);
        } else {
            clients.get(0).objWriter.writeObject(acceptData);
        }
        Data data = new Data(1000, 0, 0, null, true, playerNumber);
        if (clients.get(0).playerNumber == playerNumber) {
            clients.get(0).objWriter.writeObject(data);
        } else {
            clients.get(1).objWriter.writeObject(data);
        }
    }

    protected void resetOther() {
        maxstones = 17;
        count = 0;
        phaseChange = false;
        boothphase3 = false;
        mst.reset();
    }

/*
geht bis zum ersten client, sieht okay
 */
    private void sendList() throws IOException {
        for (Clienthandler ch : ALLclients){
            if(!ch.ingame) {
                for (Clienthandler chs : ALLclients) {
                    if (chs.playerID != null && !ch.playerID.equals(chs.playerID)) {

                        userList.add(chs.playerID);
                    }
                }
                ch.objWriter.writeObject(new ListData(new ArrayList<String>(userList),null,false));
                userList.clear();
            }
        }
    }
    public void print() {
        for(String ul: userList){
            System.out.println("ul: " + ul);
        }
    }
}
