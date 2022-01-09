package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    static ServerSocket    servs;
    Socket          p1, p2;
    static DataInputStream p1in, p2in;
    DataOutputStream p1out, p2out;

    public server(){
        try{
            servs       = new ServerSocket(6221);
            p1          = servs.accept(); System.out.println("Player 1 connected");
            p2          = servs.accept(); System.out.println("Player 2 connected ");

            p1in        = new DataInputStream(p1.getInputStream());
            p1out       = new DataOutputStream(p1.getOutputStream()); System.out.println("Player 1 Streams created");

            p2in        = new DataInputStream(p1.getInputStream());
            p2out       = new DataOutputStream(p2.getOutputStream()); System.out.println("Player 2 Streams created");


        }catch(IOException e){
            System.err.println("Failed to create Server\nError:");
            e.printStackTrace();
        }
    }

    public server(int port){
        try{
            servs       = new ServerSocket(port);
            /*p1          = servs.accept(); System.out.println("Player 1 connected");
            p2          = servs.accept(); System.out.println("Player 2 connected ");

            p1in        = new DataInputStream(p1.getInputStream());
            p1out       = new DataOutputStream(p1.getOutputStream()); System.out.println("Player 1 Streams created");

            p2in        = new DataInputStream(p1.getInputStream());
            p2out       = new DataOutputStream(p2.getOutputStream()); System.out.println("Player 2 Streams created");*/


        }catch(IOException e){
            System.err.println("Failed to create Server\nError:");
            e.printStackTrace();
        }
    }

    public static void phaseOne(int pos){
        System.out.println(pos);
    }
    public static Integer Continue(int k){
        if(k == 1){
            return 1;
        }
        else if(k == 2){
            return 2;
        }
        return -1;
    }
    public static void start() throws IOException {
        System.out.println("waiting for player 1 place: " + servs.getLocalPort());
        Socket client = servs.accept();
        while(!client.isClosed()){
           /*System.out.println("p1:"+p1.isClosed());
           System.out.println("p2:"+p2.isClosed());*/
            try{
                DataInputStream streamPhase = new DataInputStream(client.getInputStream());
                int phase = Continue((streamPhase.readInt()));




                if(phase == 1){
                    DataInputStream streamPlayerNumber = new DataInputStream(client.getInputStream());
                    System.out.println(streamPlayerNumber.readBoolean());
                    DataInputStream streamPos = new DataInputStream(client.getInputStream());
                    System.out.println(streamPos.readInt());
                }


            }catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        server s = new server(6221);
        s.start();
    }
/*
    public static void main(String[] args) {
        try {
            ServerSocket    servs   = new ServerSocket(6221);
            Socket          p1      = servs.accept();
            System.out.println("Player 1 connected");
            Socket          p2      = servs.accept();
            System.out.println("Player 2 connected");

            DataInputStream p1in = new DataInputStream(p1.getInputStream());
            DataOutputStream p1out = new DataOutputStream(p1.getOutputStream());

            DataInputStream p2in = new DataInputStream(p1.getInputStream());
            DataOutputStream p2out = new DataOutputStream(p2.getOutputStream());

            while(!p1.isClosed()){
                //If connected says so
                //System.out.println("client connected");

                //Incoming Package

                if(p1in.available()>0)
                    switch(p1in.readByte()){
                        //Works with the integer
                        case 00000000 :{
                            int tempInt = p1in.readInt();
                            System.out.println("Int: "+tempInt);
                            p2out.writeByte(00000000);
                            p2out.writeInt(tempInt);
                            p2out.flush();
                            break;
                        }
                        //works with String
                        case 00000001 :{
                            System.out.println("String: "+p1in.readUTF());
                            break;
                        }
                        case 00000010:{
                            System.out.println("Int 1: "+p1in.readInt()+"\tInt 2: "+p1in.readInt());
                            break;
                        }
                        //You should never be here
                        default:{
                            System.out.println("Something unfortunate happened");
                        }
                    }
            }
        }
        catch (IOException e) {
            switch(e.getMessage()){
                case "Connection reset":    System.out.println("Socket Disconnected");
                                            break;

                default:                    System.out.println("Something unfortunate happenned");
            }
            System.err.println("Error:");
            e.printStackTrace();
        }
    }

 */

}
