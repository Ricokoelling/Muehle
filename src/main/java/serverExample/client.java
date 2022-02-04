package serverExample;
import java.io.*;
import java.net.Socket;

public class client {

    public static void main(String[] args){
        try {
            Socket s = new Socket("localhost", 6221);
            DataOutputStream ds = new DataOutputStream(s.getOutputStream());


            //Outgoing Package ClientSide.Client --> Server
            ds.writeInt(12);
            ds.flush();
            /*
            //Incoming Package ClientSide.Client <-- Server
            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            String str = bf.readLine();
            System.out.println("client: "+str);

             */


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

