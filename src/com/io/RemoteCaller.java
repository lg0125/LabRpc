package com.io;

import java.io.Serializable;

public class RemoteCaller implements Serializable {

    private static final long serialVersionUID = 1L;

    private String className;//接口名
    private String methodName;
    private Class<?>[] paramTypes;
    private Object[] params;
    private Object result;

    public RemoteCaller(){}
    public RemoteCaller(String className,String methodName,Class<?>[] paramTypes,Object[] params){
        this.className = className;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    public String toString(){
        return "ClassName=" + className + " MethodName=" + methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
