package com.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnector {

    private static ServerSocket serverSocket;
    private static Socket socket;
    private static InputStream is;
    private static ObjectInputStream ois;
    private static OutputStream os;
    private static ObjectOutputStream oos;

    public static void connect(int port){
        try {
            serverSocket = new ServerSocket(port);
            System.out.println(" 服务器启动......");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void export(){
        try{
            socket = serverSocket.accept();

            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void send(Object o){
        try {
            oos.writeObject(o);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Object receive(){
        Object result = null;
        try {
            result = ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static void closeSocket(){
        try{
            ois.close();
            oos.close();
            socket.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void closeServerSocket(){
        try {
            serverSocket.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
