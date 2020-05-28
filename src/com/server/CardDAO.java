package com.server;

import java.util.List;

public class CardDAO {

    private static boolean flag = false;

    private static String preUser = "";

    public static boolean getFlag(){
        return flag;
    }

    public static boolean login(String user,String password){
        //if语句 要测试
        if(!preUser.equals(user)) {
            preUser = user;
            flag = false;
        }

        Class<Card> clazz = Card.class;

        String sql = "SELECT * FROM card WHERE user=? AND password=? ";

        Object[] param = {user,password};

        List<Card> result = DatabaseConnector.executeQuery(sql,clazz,param);

        if(result != null && result.size() > 0){
            Card card = result.get(0);
            if(card.getStatus() != 0) {
                flag = user.equals(card.getUser()) && password.equals(card.getPassword());
            }else{
                flag = false;
            }
        }

        return flag;

    }

    public static Card inquiry(String user){

        Class<Card> clazz = Card.class;

        String sql = "SELECT * FROM card WHERE user=? ";

        Object[] param = {user};

        Card result = null;

        List<Card> list = DatabaseConnector.executeQuery(sql,clazz,param);

        if(list != null){
            result = list.get(0);
        }

        return result;

    }

    public static boolean save(String user,double money){

        String sql = "UPDATE card SET money=money+? WHERE user=? ";

        Object[] param = {money,user};

        return DatabaseConnector.executeStatement(sql,param);

    }

    public static boolean withdraw(String user,double money){

        Card card = CardDAO.inquiry(user);

        assert card != null;

        if(card.getMoney() > money) {

            String sql = "UPDATE card SET money=money-? WHERE user=? ";

            Object[] param = {money, user};

            return DatabaseConnector.executeStatement(sql, param);
        }else{
            return false;
        }

    }

}
