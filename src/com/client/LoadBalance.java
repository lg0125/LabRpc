package com.client;

import com.io.JSONReader;

import java.util.List;

public class LoadBalance {

    private static final String SERVER_PATH = "E:\\360MoveData\\Users\\1737783319\\Desktop\\distributing\\atmrpc6\\src\\com\\client\\server.json";

    private static final List<Address> servers = JSONReader.readServer(SERVER_PATH);

    private static int index = 0;

    public static Address getAddress(){
        if(index == servers.size()){
            index = 0;
        }

        return servers.get(index++);
    }

    public static int length(){
        return servers.size();
    }

}
