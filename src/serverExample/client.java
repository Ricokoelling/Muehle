package serverExample;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client {

    public static void main(String[] args){
        try {
            Socket s = new Socket("localhost", 4999);

            //Outgoing Package
            PrintWriter pr = new PrintWriter(s.getOutputStream());
            pr.println("Is this Working?");
            pr.flush();

            //Incoming Package
            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            //
            String str = bf.readLine();
            System.out.println("client: "+str);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

