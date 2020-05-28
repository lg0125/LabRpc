package com.server;

import java.util.HashMap;
import java.util.Map;

public class Registry {

    private static Map<String,Object> remoteObjects = new HashMap<>();

    private static final String CLASS_PREFIX = "com.server.";

    static {

        remoteObjects.put(CLASS_PREFIX + "CardService",new CardServiceImp());

    }

   /* public static void registry(String className,Object object){

        remoteObjects.put(className,object);

    }*/

    public static Object getObject(String className){
        return remoteObjects.get(className);
    }



}
