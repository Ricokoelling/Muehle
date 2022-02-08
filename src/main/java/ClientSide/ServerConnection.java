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
    private String str;
    private boolean reset = false;
    private Color colorOne;
    private Color colorTwo;
    private ArrayList<String> userList = new ArrayList<>();
    private boolean gotList = false;
    private boolean gotAccepted = false;
    private boolean accepted = true;
    private boolean challenger = true;

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
            do{
                System.out.println("[CLIENT] Wait for List....");
                ListData ldata = (ListData) objReader.readObject();
                System.out.println(ldata.toString());
                gotList = true;
                gotAccepted = true;
                accepted = ldata.isAccept();
                challenger = ldata.isChallenger();
                if (accepted) {
                    userList = ldata.getUserList();
                }
            }while (!challenger);
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
                    }
                    gotAllowed = true;
                } while (!allowed);
                Data data = (Data) objReader.readObject();
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
