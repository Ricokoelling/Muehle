import java.io.*;
import java.net.*;
import java.net.Socket;
import java.io.*;

public class Client {

    Socket client;

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

    public Client( int port){
        try{
            client = new Socket("localhost", port);
        }catch(IOException e){
            System.out.println("Beim Erstellen des Clients* ist ein Fehler aufgetreten");
            e.printStackTrace();
        }

    }

    /**
     * sends Phase to Server to handle the other outputs
     * @param phase phase
     */
    public void sendPhase(int phase) {
        try {
            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            output.writeInt(phase);
            output.close();
        } catch (IOException e) {
            System.out.println("Beim senden der Daten ist ein Fehler aufgetreten p");
            e.printStackTrace();
        }
    }
    public void sendPlayerNumber(boolean playerNumber){
        try{
            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            output.writeBoolean(playerNumber);
            output.close();

        }
        catch (IOException e){
            System.out.println("Beim senden der Daten ist ein Fehler aufgetreten pn");
            e.printStackTrace();
        }
    }
    public void sendInt(int n){
        try {
            DataOutputStream ds = new DataOutputStream(client.getOutputStream());

            System.out.println(n);
            //Outgoing Package Client --> Server
            //writes the byte that has the information what the server has to do with the package
            //ds.writeByte(00000000);
            //writes the actual information that was send to the outputstream
            ds.writeInt(n);
            //sends the information to the server
            //ds.flush();
            //closes the DataOutputStream NOT! the connection
            ds.close();

        }catch(IOException e){
            System.out.println("Beim senden der Daten ist ein Fehler aufgetreten");
            e.printStackTrace();
        }
    }

    public void sendString(String n){
        try {
            DataOutputStream ds = new DataOutputStream(client.getOutputStream());

            //Outgoing Package Client --> Server
            //writes the byte that has the information what the server has to do with the package
            ds.writeByte(00000001);
            //writes string to the outputstream
            ds.writeUTF(n);
            //sends the information to the server
            ds.flush();
            //closes the DataOutputStream NOT! the connection
            ds.close();
        }catch(IOException e){
            System.out.println("Beim senden der Daten ist ein Fehler aufgetreten");
            e.printStackTrace();
        }
    }


    public void sendTwoInts(int n, int n2){//hehe variable names go brrrr
        try {
            DataOutputStream ds = new DataOutputStream(client.getOutputStream());

            //Outgoing Package Client --> Server
            //writes the byte that has the information what the server has to do with the package
            ds.writeByte(00000010);
            //writes the actual information that was send to the outputstream
            ds.writeInt(n);
            //writes the second int that was send to the outputstream
            ds.writeInt(n2);
            //sends the information to the server
            ds.flush();
            //closes the DataOutputStream NOT! the connection
            ds.close();
        }catch(IOException e){
            System.out.println("Beim senden der Daten ist ein Fehler aufgetreten");
            e.printStackTrace();
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
