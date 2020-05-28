package com.server;

public class MyAspect {

    //login
    public void lock(String user,String password){

        boolean flag = CardDAO.getFlag();

        Object[] param = {user};

        String sql_1 = "";
        if(flag){
            sql_1 = "UPDATE card SET fault = 0 WHERE user = ? ";

        }else {
            sql_1 = "UPDATE card SET fault = fault + 1 WHERE user = ?";
        }
        DatabaseConnector.executeStatement(sql_1,param);

        String sql_2 = "UPDATE card SET status = 0 WHERE fault >= 3 AND user = ? ";
        DatabaseConnector.executeStatement(sql_2,param);
    }

    //withdraw
    public void log(String user,double drawMoney){
        LogDAO.log(user, drawMoney);
    }

    public void getPreCard(String user,double drawMoney){
        LogDAO.getPreCard(user);
    }

}
