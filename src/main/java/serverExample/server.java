package serverExample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) {
        try {
            ServerSocket    servs   = new ServerSocket(6221);
            Socket          p1      = servs.accept();

            //If connected says so
            System.out.println("client connected");

            InputStreamReader in;
            BufferedReader bf;
            //Incoming Package Server <-- ClientSide.Client
            in = new InputStreamReader(p1.getInputStream());
            bf = new BufferedReader(in);

            String str = bf.readLine();
            System.out.println("client: "+str);

            PrintWriter pr;

            //Outgoing Package Server --> ClientSide.Client
            pr = new PrintWriter(p1.getOutputStream());
            pr.println("success");
            pr.flush();


            //Close InputStream
            in.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

