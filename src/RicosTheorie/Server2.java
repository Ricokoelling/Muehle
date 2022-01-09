package RicosTheorie;

import javax.swing.text.StyledEditorKit;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server2 {
    static ServerSocket server;

    private static ArrayList<Clienthandler2> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(2);
    private static int count = 0;

    public Server2(){
        try {
            server = new ServerSocket(1337);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void start() throws IOException {
        while (true) {
            System.out.println("[SERVER] Waiting for client connection....");
            Socket client = server.accept();
            System.out.println("[SERVER] Connected to " + client.getLocalPort());
            Clienthandler2 clienthandler = new Clienthandler2(client,clients);
                clients.add(clienthandler);


            pool.execute(clienthandler);
        }

    }
    public static void main(String[] args) throws IOException {
        Server2 s = new Server2();
        start();

    }
}
