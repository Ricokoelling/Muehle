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


    public void sendInt(int n){
        try {
            DataOutputStream ds = new DataOutputStream(client.getOutputStream());

            //Outgoing Package Client --> Server
            ds.writeByte(00000000);
            ds.writeInt(n);
            ds.flush();

        }catch(IOException e){
            System.out.println("Beim senden der Daten ist ein Fehler aufgetreten");
            e.printStackTrace();
        }
    }

    public void sendString(String n){
        try {
            DataOutputStream ds = new DataOutputStream(client.getOutputStream());

            //Outgoing Package Client --> Server
            ds.writeByte(00000001);
            ds.writeUTF(n);
            ds.flush();
        }catch(IOException e){
            System.out.println("Beim senden der Daten ist ein Fehler aufgetreten");
            e.printStackTrace();
        }
    }

    public void sendTwoInts(int n, int n2){//hehe variable names go brrrr
        try {
            DataOutputStream ds = new DataOutputStream(client.getOutputStream());

            //Outgoing Package Client --> Server
            ds.writeByte(00000010);
            ds.writeInt(n);
            ds.writeInt(n2);
            ds.flush();
        }catch(IOException e){
            System.out.println("Beim senden der Daten ist ein Fehler aufgetreten");
            e.printStackTrace();
        }
    }


    public void endConnection(){
        try{
            client.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /*

    public static void main(String[] args) throws IOException {




        playBoard Board = new playBoard();
        Clientdata data = new Clientdata();

        try {
            Socket client = new Socket("localhost", 187);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

     */
}
