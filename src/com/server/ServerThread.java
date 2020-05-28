package com.server;

import com.io.RemoteCaller;

public class ServerThread implements Runnable{

    private static final String CLASS_PREFIX = "com.server.";
    private static final String SERVICE = "CardService";
    private static final String CARD_SERVICE_CLASS_PATH = CLASS_PREFIX + SERVICE;

    @Override
    public void run() {
        try {
            //获得远程请求对象
            RemoteCaller call = (RemoteCaller) ServerConnector.receive();
            String className = call.getClassName();
            Object[] params = call.getParams();
            String methodName = call.getMethodName();
            //处理
            Object result = null;

            switch (className){
                case CARD_SERVICE_CLASS_PATH :
                    result = CardProxy.invoke(methodName,params);
                    break;
            }
            call.setResult(result);
            //返回
            ServerConnector.send(call);
            ServerConnector.closeSocket();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

