import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;

public class Client{

    private static Socket client;
    private int pos1 = 0,pos2 = 0;
    private int state = 1;
    ServerConnection serverConn;
    private boolean playerNumber;
    private boolean playerNumberOr;
    private boolean allowed = true;
    private ObjectOutputStream objWriter;



    public Client(){
        try{
            client = new Socket("localhost", 1337);
            serverConn = new ServerConnection(client);
            objWriter = new ObjectOutputStream(client.getOutputStream());
            System.out.println("[Client] connected");
        }catch(IOException e){
            System.out.println("Beim Erstellen des Clients ist ein Fehler aufgetreten");
            e.printStackTrace();
        }
        new Thread(serverConn).start();

    }

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

    /**
     * Sends data when needed to Server and starts recive funktion
     * @throws IOException yeee
     */
    public void sendData(boolean playerNumber) throws IOException {
        LoginData loginData;
        playerNumberOr = playerNumber;
        if(playerNumber) {
            loginData = new LoginData("0001", "Kirito", "Schule123", true);
        }else {
            loginData = new LoginData("0002", "Asuna", "Schule123", false);
        }
        objWriter.writeObject(loginData);
    }

    public boolean waitForAllowed(){
            if(serverConn.isGotAllowed()) {
                if (serverConn.isAllowed()) {
                    state = serverConn.getState();
                    pos1 = serverConn.getPos1();
                    pos2 = serverConn.getPos2();
                    playerNumber = serverConn.isPlayerNumber();
                    return true;
                }
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
                System.out.println("[CLIENT] playernumber: " + playerNumber + " state: " + state);
                if(state == 1) {
                    pos1 = serverConn.getPos1();
                }else if(state == 2 || state == 6 || state == 8 || state == 11  || state == 15 ||state == 18  || state == 22 || state == 23){
                }else if(state == 3){
                    pos1 = serverConn.getPos1();
                }else if(state == 4 || state == 5 || state == 10 || state == 12 || state == 17 || state == 19 || state == 24){
                    pos1 = serverConn.getPos1();
                }else if(state == 7 || state == 9 || state == 13 || state == 14  || state == 16 || state == 20 || state == 21){
                    pos1 = serverConn.getPos1();
                    pos2 = serverConn.getPos2();
                }
                serverConn.setGotData(false);
                serverConn.setGotAllowed(false);
                System.out.println("hure");
                return true;
            }
        return false;
    }

    public void reset(){

    }
    public void sendData(int state, int pos1){
        Data data;
        if(playerNumberOr) {
            data = new Data(state, pos1, 0, "0001", false, true);
            System.out.println("hier");
        }else {
            data = new Data(state, pos1, 0, "0002", false, false);
            System.out.println("hier2");
        }
        try {
            objWriter.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[CLIENT] state: " + state + " pos1: " + pos1 );
    }

    public void sendData(int state, int pos1, int pos2){
        PrintWriter output = null;
        try {
            output = new PrintWriter(client.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert output != null;
        System.out.println("state: " + state + " pos1: " + pos1 + " pos2: " + pos2);
        output.println(state);
        output.println(pos1);
        output.println(pos2);
    }

    /***
     *      This function safely disconnects from the server
     */
    public void endConnection(){
        try{
            client.close();
        }catch(IOException e){
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
}
