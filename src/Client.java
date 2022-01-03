import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException {
        playBoard Board = new playBoard();
        Clientdata data = new Clientdata();

        try {
            Socket client = new Socket("localhost", 187);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
