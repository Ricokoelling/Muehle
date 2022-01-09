import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.sendDudes(0);
        client.close();
    }
}
