package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class server {

    static ServerSocket    server;
    private static ArrayList<Clienthandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(2);

    public server(){
        try{
            server = new ServerSocket(1337);

        }catch(IOException e){
            System.err.println("Failed to create Server\nError:");
            e.printStackTrace();
        }
    }

    public server(int port){
        try{
            server = new ServerSocket(port);
        }catch(IOException e){
            System.err.println("Failed to create Server\nError:");
            e.printStackTrace();
        }
    }

    public static void start() throws IOException {
        while (true) {
            System.out.println("[SERVER] Waiting for client connection....");
            Socket client = server.accept();
            System.out.println("[SERVER] Connected to " + client.getLocalPort());
            Clienthandler clienthandler = new Clienthandler(client,clients);
            clients.add(clienthandler);


            pool.execute(clienthandler);
        }
    }
    public static void main(String[] args) throws IOException {
        server s = new server();
        s.start();
    }

}
