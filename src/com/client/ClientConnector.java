package com.client;

import com.server.Server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientConnector {

    private static Socket socket;
    private static InputStream is;
    private static ObjectInputStream ois;
    private static OutputStream os;
    private static ObjectOutputStream oos;

    private static boolean connectSocket(Address address){
        try {
            System.out.println(address.getHost() + " " + address.getPort());
            socket = new Socket(address.getHost(), address.getPort());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return !socket.isClosed() && socket.isConnected();
    }

    private static boolean connectServer(Address address){
        if(connectSocket(address)){
            try {
                os = socket.getOutputStream();
                oos = new ObjectOutputStream(os);
                is = socket.getInputStream();
                ois = new ObjectInputStream(is);
                return true;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return false;
            }
        }

        return false;
    }

    public static boolean connect(){

        int length = LoadBalance.length();

        for(int i = 0;i < length;i++){
            Address address = LoadBalance.getAddress();

            if(connectServer(address)) { return true; }
        }

        return false;

    }

    public static void send(Object obj) {
        try {

            oos.writeObject(obj);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static Object receive(){
        Object result = null;
        try{
            result = ois.readObject();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
       return result;
    }

    public static void close(){
        try {
            ois.close();
            oos.close();
            socket.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
