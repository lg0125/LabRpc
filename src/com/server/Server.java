package com.server;

public class Server {

    public static void start(int port){
        ServerConnector.connect(port);
        try {
            while (true) {
                try {

                    //accept
                    ServerConnector.export();

                    //开启线程启动服务
                    new Thread(new ServerThread()).start();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            ServerConnector.closeServerSocket();
        }
    }
}
