
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Clienthandler implements Runnable{

    private Socket client;
    private BufferedReader input;
    private PrintWriter output;
    private ArrayList<Clienthandler> clients;
    private Integer clientName;


    public Clienthandler(Socket client, ArrayList<Clienthandler> clients) throws IOException {
        this.client = client;
        this.clients = clients;
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new PrintWriter(client.getOutputStream(), true);
        this.clientName = Integer.parseInt(input.readLine());
    }

    @Override
    public void run() {
        try {
            while (true) {
                int input1 = Integer.parseInt(input.readLine());
                boolean input2 = Boolean.parseBoolean(input.readLine());
                int input3 = Integer.parseInt(input.readLine());
                if(input1 == 0){
                    System.out.println("lol");
                }
                else if(input1 == 1){
                    outToDifferent(input1);
                    outToDifferent(input2);
                    outToDifferent(input3);
                }
                else {
                    outToDifferent("you shit");
                    outToDifferent("you shit");
                    outToDifferent("you shit");
                    outToDifferent("hello");
                    outToDifferent("gello");
                }

            }
        }catch (IOException e){
            System.err.println("Something Happend that shouldnt have happend!");
            System.err.println(e.getStackTrace());
        } finally{
            output.close();
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void outToDifferent(String inputt) {
        if(clients.get(0).clientName == this.clientName){
            clients.get(1).output.println(inputt);
        }
        else{
            clients.get(0).output.println(inputt);
        }
    }
    private void outToDifferent(int inputt) {

        if (clients.get(0).clientName == this.clientName) {
            clients.get(1).output.println(inputt);
        } else {
            clients.get(0).output.println(inputt);
        }
    }
    private void outToDifferent(boolean inputt){
        if (clients.get(0).clientName == this.clientName) {
            clients.get(1).output.println(inputt);
        } else {
            clients.get(0).output.println(inputt);
        }
    }


}
