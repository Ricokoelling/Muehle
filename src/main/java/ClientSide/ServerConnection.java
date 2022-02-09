package ClientSide;


import Data.*;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerConnection implements Runnable {

    private final Socket server;
    private final ObjectInputStream objReader;
    private int state = -1;
    private boolean gotData = false, gotAllowed = false;
    private int pos1, pos2;
    private boolean playerNumber;
    private boolean allowed = true;
    private boolean reset = false;
    private Color colorOne;
    private Color colorTwo;
    private ArrayList<String> userList = new ArrayList<>();
    private boolean gotList = false;
    private boolean gotAccepted = false;
    private boolean accepted = true;
    private boolean challenger = true;
    private boolean acceptMatch = false;
    private String opponent;
    private boolean disconnect = false;
    private boolean disconnected = false;
    private boolean alreadyOnline = false;
    private boolean giveup = false;

    public ServerConnection(Socket server) throws IOException {
        this.server = server;
        objReader = new ObjectInputStream(server.getInputStream());
    }

    public void setGotData(boolean gotData) {
        this.gotData = gotData;
    }

    public void setAcceptMatch() {
        this.acceptMatch = true;
    }

    public boolean isGotData() {
        return gotData;
    }

    public int getState() {
        return state;
    }

    public int getPos1() {
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

    public Boolean isAllowed() {
        return allowed;
    }

    public boolean isGotAllowed() {
        return gotAllowed;
    }

    public void setGotAllowed(boolean gotAllowed) {
        this.gotAllowed = gotAllowed;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public Color getColorOne() {
        return colorOne;
    }

    public Color getColorTwo() {
        return colorTwo;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public boolean isGotAccepted() {
        if (gotAccepted) {
            gotAccepted = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isGotList() {
        if (gotList) {
            gotList = false;
            return true;
        }
        return false;
    }


    public boolean isChallenger() {
        return challenger;
    }

    public String getOpponent() {
        return opponent;
    }

    public boolean isAcceptMatch() {
        return acceptMatch;
    }

    public boolean isDisconnect() {
        return disconnect;
    }

    public void setDisconnect(boolean disconnect) {
        this.disconnect = disconnect;
    }

    public boolean isAlreadyOnline() {
        return alreadyOnline;
    }

    public boolean isDisconnected() {
        return disconnected;
    }

    public void setDisconnected(boolean disconnected) {
        this.disconnected = disconnected;
    }

    public boolean isGiveup() {
        return giveup;
    }

    public void setGiveup(boolean giveup) {
        this.giveup = giveup;
    }

    public void print() {
        for (String s : userList) {
            System.out.println(s);
        }
    }


    public ArrayList<String> getUserList() {
        return userList;
    }

    /**
     * while testing found out we don't have to use different parse etc. maybe we should do make sure its send corretly
     * if we don't it only sends Strings
     */
    @Override
    public void run() {
        try {
            while (true) {
                while (!acceptMatch) {
                    System.out.println("[CLIENT] Wait for List....");
                    ListData ldata = (ListData) objReader.readObject();
                    System.out.println(ldata.toString());
                    acceptMatch = false;
                    gotList = true;
                    gotAccepted = true;
                    accepted = ldata.isAccept();
                    opponent = ldata.getOpponent();
                    challenger = ldata.isChallenger();
                    if (accepted) {
                        userList = ldata.getUserList();
                        if (challenger) {
                            if (ldata.isAcceptMatch()) {
                                acceptMatch = true;
                                break;
                            }
                        }
                    }else {
                        alreadyOnline = ldata.isAlreadyOnline();
                    }
                }
                while (true) {
                    do {
                        AcceptData acceptData = (AcceptData) objReader.readObject();
                        if(acceptData.isGiveup()){
                            System.out.println("breakkookoko");
                            giveup = true;
                            break;
                        }
                        if(acceptData.getState() == 23){
                            break;
                        }
                        if(!acceptData.isDisconnect()) {
                            allowed = acceptData.isAccept();
                            if (allowed) {
                                pos1 = acceptData.getPos1();
                                pos2 = acceptData.getPos2();
                                state = acceptData.getState();
                                playerNumber = acceptData.isPlayerNumb();
                                reset = acceptData.isReset();
                            }
                            gotAllowed = true;
                        }else {
                            disconnected = true;
                            break;
                        }
                    } while (!allowed);
                    if(disconnected || giveup){
                        acceptMatch = false;
                        break;
                    }
                    Data data = (Data) objReader.readObject();
                    if(data.isSetothergiveup()){
                        System.out.println("break");
                        acceptMatch = false;
                        break;
                    }
                    if(data.getState() == 23){
                        break;
                    }
                    if (!data.isDisconnect()) {
                        state = data.getState();
                        playerNumber = data.isPlayer();
                        colorOne = data.getPlayerOne();
                        colorTwo = data.getPlayerTwo();
                        if (state != -1) {
                            pos1 = data.getPos1();
                            pos2 = data.getPos2();
                        } else {
                            break;
                        }
                        gotData = true;
                    } else {
                        acceptMatch = false;
                        disconnect = true;
                        break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                objReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
