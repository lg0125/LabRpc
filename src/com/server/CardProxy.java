package com.server;

public class CardProxy {

    private static CardService cardService = (CardService) DynamicProxyFactory.getProxy(CardService.class,new CardServiceImp()) ;

    public static Object invoke(String methodName,Object[] params){

        Object result = null;

        switch (methodName){

            case "login" :
                result = cardService.login((String)params[0],(String)params[1]);
                break;

            case "save" :
                result = cardService.save((String)params[0],(double) params[1]);
                break;

            case "inquiry" :
                result = cardService.inquiry((String)params[0]);
                break;

            case "withdraw" :
                result = cardService.withdraw((String)params[0],(double) params[1]);
        }


        return result;
    }

}
