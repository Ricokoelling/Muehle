package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Clienthandler implements Runnable{
    private Socket client;
    private BufferedReader input;
    private PrintWriter output;
    private ArrayList<Clienthandler> clients;
    private Boolean playerNumber;

    public Clienthandler(Socket client, ArrayList<Clienthandler> clients) throws IOException {
        this.client = client;
        this.clients = clients;
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new PrintWriter(client.getOutputStream(), true);
        this.playerNumber = Boolean.parseBoolean(input.readLine());
    }

    @Override
    public void run() {
        try {
            while (true) {
                String inputt = input.readLine();
                outToDifferent(inputt);
            }
        }catch (IOException e){
            System.err.println("Something Happend that shouldnt have happend!");
            System.err.println(e.getStackTrace());
        } finally{
            output.close();
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void outToDifferent(String inputt) {
        if(clients.get(0).playerNumber == this.playerNumber){
            clients.get(1).output.println(inputt);
        }
        else{
            clients.get(0).output.println(inputt);
        }
    }

}
