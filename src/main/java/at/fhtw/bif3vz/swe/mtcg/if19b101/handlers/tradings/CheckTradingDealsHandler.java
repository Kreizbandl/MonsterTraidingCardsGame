package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.tradings;

import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.TradeRecord;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

public class CheckTradingDealsHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> TRADING-CHECK");
        //...check for trading deals here

        String token = getAuthorizationToken(exchange);
        if(isLogged(token)){
            TradeRecord trade = DatabaseOperations.readTradeToDatabase(token);
            if(trade.id().equals("-1")){
                System.out.println("nothing here for u");
                exchange.sendResponseHeaders(StatusCode.NOCONTENT.getCode(), -1);
            }else{
                System.out.println(trade);
                sendResponseHeader(exchange, StatusCode.OK.getCode(), 0);
                byte[] response = writeResponse(trade);
                sendResponseBody(exchange, response);
            }
        }else{
            System.out.println("User isn't logged in");
            exchange.sendResponseHeaders(StatusCode.UNAUTHORIZED.getCode(), -1);
        }
    }
}
