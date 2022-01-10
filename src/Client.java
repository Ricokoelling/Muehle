import java.io.*;
import java.net.Socket;

public class Client{

    private static Socket client;
    private int pos1 = 0,pos2 = 0,pos3 = 0,phase;
    ServerConnection serverConn;
    boolean playerNumber;



    public Client() throws IOException {
        try{
            client = new Socket("localhost", 1337);
            serverConn = new ServerConnection(client);
            System.out.println("[Client] connected");
        }catch(IOException e){
            System.out.println("Beim Erstellen des Clients ist ein Fehler aufgetreten");
            e.printStackTrace();
        }
        new Thread(serverConn).start();
    }

    public Client(String ip, int port) throws IOException {
        try{
            client = new Socket(ip, port);
        }catch(IOException e){
            System.out.println("Beim Erstellen des Clients* ist ein Fehler aufgetreten");
            e.printStackTrace();
        }

    }

    public Client( int port) throws IOException {
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
        PrintWriter output = new PrintWriter(client.getOutputStream(),true);
        output.println(playerNumber);
        this.playerNumber = playerNumber;
    }

    /**
     *
     * @return
     */
    public boolean waitforData(){
        if(serverConn.isGotData()){
            playerNumber = serverConn.isPlayerNumber();
            pos1 = serverConn.getPos1();
            phase = serverConn.getPhase();
            return true;
        }
        return false;
    }

    public void sendPhaseOne(int phasee, int pos1){
        PrintWriter output = null;
        try {
            output = new PrintWriter(client.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert output != null;
        System.out.println(phasee + " " + pos1 + " " + pos2 + " " + pos3);
        output.println(phasee);
        output.println(pos1);
        output.println(pos2);
        output.println(pos3);

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

    public int getPhase() {
        return phase;
    }

    public int getPos1() {
        return pos1;
    }

    public boolean isPlayerNumber() {
        return playerNumber;
    }
}
