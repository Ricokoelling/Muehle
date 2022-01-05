package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class server {
    public static void main(String[] args) {
        try {
            ServerSocket    servs   = new ServerSocket(6221);
            Socket          p1      = servs.accept();

            while(!p1.isClosed()){
                //If connected says so
                //System.out.println("client connected");

                //Incoming Package
                DataInputStream dis = new DataInputStream(p1.getInputStream());


                if(dis.available()>0)
                switch(dis.readByte()){
                    //Works with the integer
                    case 00000000 :{
                        System.out.println("Int: "+dis.readInt());
                        break;
                    }
                    //works with String
                    case 00000001 :{
                        System.out.println("String: "+dis.readUTF());
                        break;
                    }
                    case 00000010:{
                        System.out.println("Int 1: "+dis.readInt()+"\tInt 2: "+dis.readInt());
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
}
