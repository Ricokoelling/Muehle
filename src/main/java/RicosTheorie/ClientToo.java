package RicosTheorie;

import java.io.IOException;

public class ClientToo {
    public static void main(String[] args) throws IOException {
        Client2 client = new Client2();
        client.sendDudes(1);
    }
}