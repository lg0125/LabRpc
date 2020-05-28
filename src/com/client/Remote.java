package com.client;

import com.io.RemoteCaller;

public class Remote {

    public static String invoke(String classPath,String serviceName,Class<?>[] paramType,Object[] params){

        boolean conn = ClientConnector.connect();

        String result = "";

        if(conn){

            RemoteCaller call = new RemoteCaller(classPath,serviceName,paramType,params);

            //发送
            ClientConnector.send(call);

            //返回 问题?Server?
            call = (RemoteCaller) ClientConnector.receive();

            //关闭资源
            ClientConnector.close();

            result = (String) call.getResult();
        }else{
            result = "连接失败";
        }

        return result;
    }

}
