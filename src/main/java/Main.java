import ClientSide.LoginScreen;
import ClientSide.startFrame;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        LoginScreen login = new LoginScreen();
    }

}


/*
TODO:   Server disconnect doesnt work fully, only wenn players turn not when he waits for data (other client data to accept data)
        after win back to lobby and send bzw. recive list
        reset ask
        idk prob more
*/

