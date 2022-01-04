package serverExample;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {
    public static void main(String[] args) {
        try {
            ServerSocket    servs   = new ServerSocket(4999);
            Socket          s       = servs.accept();
            //If connected says sp
            System.out.println("client connected");


            //Incoming Package
            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            String str = bf.readLine();
            System.out.println("client: "+str);

            //Outgoing Package
            PrintWriter pr = new PrintWriter(s.getOutputStream());
            Scanner sc = new Scanner(System.in);
            pr.println(sc);
            pr.flush();



        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

