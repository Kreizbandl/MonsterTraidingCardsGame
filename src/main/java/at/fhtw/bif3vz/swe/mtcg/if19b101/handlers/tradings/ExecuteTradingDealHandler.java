package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.tradings;

import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.TradeRecord;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

public class ExecuteTradingDealHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> TRADING-EXECUTE");
        //...execute or create trading deals here

        String token = getAuthorizationToken(exchange);
        if(isLogged(token)){
            if(exchange.getRequestURI().getPath().equals("/tradings")){
                System.out.println("let's save that");
                TradeRecord trade = mapRequest(exchange.getRequestBody(),TradeRecord.class);
                DatabaseOperations.writeTradeToDatabase(token, trade);
                exchange.sendResponseHeaders(StatusCode.CREATED.getCode(), 0);
            }else{
                System.out.println("let's trade that");
                String giveCardId = mapRequest(exchange.getRequestBody(), String.class);
                String tradeId = getURIName(exchange.getRequestURI().getPath());
                int status = DatabaseOperations.tradeCards(token, tradeId, giveCardId);
                if(status == -1){
                    System.out.println("ERR: You cannot trade with yourself");
                    exchange.sendResponseHeaders(StatusCode.FORBIDDEN.getCode(), -1);
                }else if(status == -2){
                    System.out.println("not enough damage");
                    exchange.sendResponseHeaders(StatusCode.BADREQUEST.getCode(), -1);
                }/*else if(status == -3){
                    System.out.println("wrong card type");
                }*/else{
                    System.out.println("SUCCESS");
                    exchange.sendResponseHeaders(StatusCode.OK.getCode(), 0);
                }
            }
        }else{
            System.out.println("ERR: User isn't logged in");
            exchange.sendResponseHeaders(StatusCode.UNAUTHORIZED.getCode(), -1);
        }
    }
}