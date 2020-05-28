package com.server;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseConnector {

    private static Connection conn;

    private static final String PATH = "E:\\360MoveData\\Users\\1737783319\\Desktop\\distributing\\atmrpc6\\src\\com\\server\\mysql.ini";

    private static void connect(){
        Properties properties = new Properties();

        try{
            properties.load(new FileInputStream(PATH));
        }catch (Exception e){
            System.out.println("加载mysql.ini失败");
        }

        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        try{
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            System.out.println("Class.forName()失败,driver有问题");
        }

        try{
            conn = DriverManager.getConnection(url,user,password);
            conn.setAutoCommit(true);
        }catch (Exception e){
            System.out.println("Connection对象获取失败");
        }
    }

    private static void invoke(Object object,String name,Object...param){
        Class clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        try{
            for(Method method : methods){
                if(method.getName().equalsIgnoreCase(name)){
                    method.invoke(object,param);
                    return;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static <T> List<T> executeQuery(String sql,Class<T> clazz,Object...param){
        connect();
        List<T> list = new ArrayList<>();
        try(
                PreparedStatement pstmt = conn.prepareStatement(sql))
        {
                int length = param.length;
                if(length > 0){
                    for(int i = 0;i < length;i ++){
                        pstmt.setObject(i + 1,param[i]);
                    }
                }
                try(
                        ResultSet rs = pstmt.executeQuery())
                {
                        ResultSetMetaData rsmd = rs.getMetaData();

                        int column = rsmd.getColumnCount();
                        while (rs.next()){
                            T t = clazz.getDeclaredConstructor().newInstance();

                            for(int i = 0;i < column;i++){
                                invoke(t,"set" + rsmd.getColumnName(i + 1),rs.getObject(i + 1));
                            }
                            list.add(t);
                        }
                        return list;
                }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean executeStatement(String sql,Object...param){
        connect();

        try(
                PreparedStatement pstmt = conn.prepareStatement(sql))
        {
                for(int i = 0;i < param.length;i++){
                    pstmt.setObject(i + 1,param[i]);
                }

                return pstmt.executeUpdate() > 0;
        }catch (SQLException e){
                System.out.println(e.getMessage());
                return false;
        }

    }

}
