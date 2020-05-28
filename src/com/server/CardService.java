package com.server;

public interface CardService {
    String login(String user,String password);

    String inquiry(String user);

    String save(String user,double money);

    String withdraw(String user,double money);
}
