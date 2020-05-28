package com.server;

import java.util.Date;

public class LogDAO {

    private static Card preCard;

    public static void getPreCard(String user){

        preCard = CardDAO.inquiry(user);

    }

    public static boolean log(String user,double drawMoney){

        String sql = "INSERT INTO log (user,drawMoney,drawBefore,drawAfter,time) VALUES (?,?,?,?,?) ";

        double drawBefore = preCard.getMoney();
        double drawAfter = CardDAO.inquiry(user).getMoney();
        Date time = new Date();

        Object[] param = {user,drawMoney,drawBefore,drawAfter,time};

        return DatabaseConnector.executeStatement(sql,param);

    }

}
