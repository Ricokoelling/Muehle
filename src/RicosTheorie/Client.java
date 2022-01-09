import java.io.*;
import java.net.*;

public class Client {
    private static Socket client;

    public Client() {
        try{
            client = new Socket("localhost",1337);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendDudes(int clientName) throws IOException {
        ServerConnection serverConn = new ServerConnection(client);
        PrintWriter output = new PrintWriter(client.getOutputStream(),true);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        new Thread(serverConn).start();
        output.println(clientName);
        while (true){
            System.out.println("< ");
            String command = keyboard.readLine();
            if(command.equals("quit")) break;
            output.println(command);
        }

    }

    public void close(){
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
