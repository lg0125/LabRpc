package com.server;

public class CardServiceImp implements CardService{
    @Override
    public String login(String user, String password) {

        boolean result = CardDAO.login(user,password);

        String ans = "";

        if(result) ans = "登录成功";
        else ans = "登录失败";

        return ans;
    }

    @Override
    public String inquiry(String user) {
        String ans = "";

        Card card = CardDAO.inquiry(user);

        if(card != null) {
            ans = "user:" + card.getUser() + " " + "password:" + card.getPassword() + " " + "money" + card.getMoney();
        }
        return ans;
    }

    @Override
    public String save(String user, double money) {
        String ans = "";

        boolean result = CardDAO.save(user,money);
        if(result){
            Card card = CardDAO.inquiry(user);
            ans = "save success!!! " + card.getUser() + ":" + card.getMoney();
        }

        return ans;
    }

    @Override
    public String withdraw(String user, double money) {
        String ans = "";

        boolean result = CardDAO.withdraw(user, money);

        if(result){
            Card card = CardDAO.inquiry(user);
            ans = "withdraw success!!! " + card.getUser() + ":" + card.getMoney();
        }else{
            ans = "withdraw error";
        }

        return ans;
    }
}
