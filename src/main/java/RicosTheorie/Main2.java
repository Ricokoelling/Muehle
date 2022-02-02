package RicosTheorie;

import java.io.IOException;

public class Main2{
    public static void main(String[] args) throws IOException {
        Client2 client = new Client2();
        client.sendDudes(0);
        client.close();
    }
}
