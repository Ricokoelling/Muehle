import java.io.*;
import java.net.Socket;

public class Client{

    private static Socket client;
    private int pos,pos2,pos3,phase;
    private boolean playerNumber;
    private playBoardClient board = new playBoardClient();




    public Client(){
        try{
            client = new Socket("localhost", 6221);
        }catch(IOException e){
            System.out.println("Beim Erstellen des Clients ist ein Fehler aufgetreten");
            e.printStackTrace();
        }

    }

    public Client(String ip, int port){
        try{
            client = new Socket(ip, port);
        }catch(IOException e){
            System.out.println("Beim Erstellen des Clients* ist ein Fehler aufgetreten");
            e.printStackTrace();
        }

    }

    public Client( int port) {
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
    public void sendData() throws IOException {
        ServerConnection serverConn = new ServerConnection(client);
        PrintWriter output = new PrintWriter(client.getOutputStream(),true);
        new Thread(serverConn).start();
        output.println(playerNumber);
        while (true){
            if(board.getHeight() == 0){
                //send shit to server
            }
        }
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


}
