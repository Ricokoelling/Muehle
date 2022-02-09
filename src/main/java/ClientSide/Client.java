package ClientSide;


import Data.*;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
public class Client{

    private static Socket client;
    private int pos1 = 0,pos2 = 0;
    private int state = 1;
    ServerConnection serverConn;
    private boolean playerNumber;
    private boolean playerNumberOr;
    private boolean allowed = true;
    private boolean accepted = true;
    private boolean reset;
    private ObjectOutputStream objWriter;
    private Color thisColor;
    private Color otherColor;
    private ArrayList<String> userList;
    private String opponent;
    private boolean disconnect;
    private String userID;
    private boolean dontReset = true;
    private boolean alreadyOnline = false;



    public Client(){
        try{
            client = new Socket("localhost", 1337);
            serverConn = new ServerConnection(client);
            objWriter = new ObjectOutputStream(client.getOutputStream());
            search();
            System.out.println("[ClientSide.Client] connected");
        }catch(IOException e){
            System.out.println("Beim Erstellen des Clients ist ein Fehler aufgetreten");
            e.printStackTrace();
        }
        new Thread(serverConn).start();

    }
/*
    public Client(String ip, int port){
        try{
            client = new Socket(ip, port);
        }catch(IOException e){
            System.out.println("Beim Erstellen des Clients* ist ein Fehler aufgetreten");
            e.printStackTrace();
        }

    }

    public Client( int port){
        try{
            client = new Socket("localhost", port);
        }catch(IOException e){
            System.out.println("Beim Erstellen des Clients* ist ein Fehler aufgetreten");
            e.printStackTrace();
        }
    }
*/
    public void sendBreak(){
        ListData ldata = new ListData(userList,userID,false);
        ldata.setJustBreakwhile(true);
        serverConn.setAcceptMatch();
        try {
            objWriter.writeObject(ldata);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Sends data when needed to Server and starts recive funktion
     * @throws IOException yeee
     */
    public void sendsLogin(String username , int password, boolean register) {
        LoginData logData = new LoginData(username,  password,register);
        userID = username;
        System.out.println("reg: " + register);
        try {
            objWriter.writeObject(logData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendList(String username){
        ListData ldata = new ListData(userList,username,true);
        this.playerNumberOr = false;
        try {
            objWriter.writeObject(ldata);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAccept(){
        ListData ldata = new ListData(userList,serverConn.getOpponent(),false);
        ldata.setAcceptMatch(true);
        serverConn.setAcceptMatch();
        this.playerNumberOr = true;
        try {
            objWriter.writeObject(ldata);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean waitforAcceptMatch(){
        if(serverConn.isAcceptMatch()){
            opponent = serverConn.getOpponent();
            return true;
        }
        return false;
    }
    public boolean waitforAccept(){
        if(serverConn.isGotAccepted()){
            accepted = serverConn.isAccepted();
            alreadyOnline = serverConn.isAlreadyOnline();
            return true;
        }
        return false;
    }

    public boolean waitForList(){
        if(serverConn.isGotList()){
            userList = serverConn.getUserList();
            return true;
        }
        return false;
    }

    public boolean waitForAllowed(){
            if(serverConn.isGotAllowed()) {
                if (serverConn.isAllowed()) {
                    allowed = true;
                    state = serverConn.getState();
                    pos1 = serverConn.getPos1();
                    pos2 = serverConn.getPos2();
                    playerNumber = serverConn.isPlayerNumber();
                }else {
                    allowed = false;
                }
                return true;
            }
            return false;
    }
    /**
     *
     * @return
     */
    public boolean waitforData() throws InterruptedException {
            if (serverConn.isGotData()) {
                playerNumber = serverConn.isPlayerNumber();
                state = serverConn.getState();
                if(state == 1) {
                    pos1 = serverConn.getPos1();
                }else if(state == 2 || state == 6 || state == 8 || state == 11  ||state == 18  || state == 22){
                }else if(state == 3){
                    pos1 = serverConn.getPos1();
                }else if(state == 4 || state == 5 || state == 10 || state == 12 || state == 17 || state == 19 || state == 24 || state == 27){
                    pos1 = serverConn.getPos1();
                }else if(state == 7 || state == 9 || state == 13 || state == 14  || state == 15|| state == 16 || state == 20 || state == 21 || state == 25 || state == 23){
                    pos1 = serverConn.getPos1();
                    pos2 = serverConn.getPos2();
                }
                serverConn.setGotData(false);
                serverConn.setGotAllowed(false);
                return true;
            }
        return false;
    }

    public void search(){
        new Thread(() -> {
            while (dontReset) {
                if (serverConn.isReset()) {
                    serverConn.setReset(false);
                    reset = true;
                }
                if(serverConn.isDisconnect()){
                    serverConn.setDisconnect(false);
                    endConnection(false);
                    disconnect = true;
                }
                if(serverConn.isDisconnected()){
                    serverConn.setDisconnected(false);
                    endConnection(false);
                    disconnect = true;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void sendData(int state, int pos1){
        System.out.println(playerNumberOr);
        Data data = new Data(state, pos1, 0, this.userID, reset, playerNumberOr);
        try {
            objWriter.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendData(int state, int pos1, int pos2){
        Data data = new Data(state, pos1, 0, this.userID, reset, playerNumberOr);
        try {
            objWriter.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /***
     *      This function safely disconnects from the ServerSide.server
     */
    public void endConnection(){
        try{
            Data data = new Data(state, pos1, 0, this.userID,true);
            try {
                objWriter.writeObject(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
            client.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void endConnection(boolean k){
        Data data = new Data(state, pos1, 0, this.userID, reset,true);
        data.setOtherDisconnect(true);
        try {
            objWriter.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void endConnection(byte b){
        Data data = new Data(state, pos1, 0, this.userID,true);
        data.setNotmymove(true);
        try {
            objWriter.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void giveup(){
        Data data = new Data(state, pos1, 0, this.userID,true);
        data.setGiveup(true);
        try {
            objWriter.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public boolean isPlayerNumber() {
        return playerNumber;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public boolean isReset() {
        return reset;
    }

    public boolean isPlayerNumberOr() {
        return playerNumberOr;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public void setPlayerOne(Color thisColor) {
        this.thisColor = thisColor;
    }

    public ArrayList<String> getUserList() {
        return userList;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getOpponent() {
        return serverConn.getOpponent();
    }

    public boolean isMatchFound() {
        return serverConn.isChallenger();
    }

    public boolean isDisconnect() {
        if(disconnect){
            disconnect = false;
            return true;
        }
        return false;
    }

    public boolean isAlreadyOnline() {
        return alreadyOnline;
    }

    public String getUserID() {
        return userID;
    }
}
