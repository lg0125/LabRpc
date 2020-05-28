package com.server;

import com.io.JSONReader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyFactory {
    public static String beforeMethod = "";
    public static String afterMethod = "";
    private final static String AOP_PATH = "E:\\360MoveData\\Users\\1737783319\\Desktop\\distributing\\atmrpc6\\src\\com\\server\\aop.json";
    public static Object getProxy(final Class<?> clazz,final Object instance){
        InvocationHandler handler = (proxy, method, args) -> {
                String methodName = method.getName();
                //读取aop.json
                JSONReader.readAOP(AOP_PATH,methodName);
                //AOP编程
                Class<?> classType = MyAspect.class;
                //前
                runAOP(beforeMethod,classType,args);
                //中心方法
                Object result = method.invoke(instance,args);
                //后
                runAOP(afterMethod,classType,args);
                beforeMethod = "";
                afterMethod = "";
                return result;
        };
        System.out.println("DynamicProxy开始执行");
        return  Proxy.newProxyInstance(clazz.getClassLoader(),new Class<?>[] {clazz},handler);
    }
    private static void runAOP(String aopMethod,final Class<?> classType,Object...args)throws Throwable{
        if(aopMethod != null && aopMethod.length() > 0){
            Object o = classType.getDeclaredConstructor().newInstance();
            Method[] methods = classType.getDeclaredMethods();
            for(Method m : methods){
                if(m.getName().equalsIgnoreCase(aopMethod)) m.invoke(o,args);
            }
        }
    }
}
