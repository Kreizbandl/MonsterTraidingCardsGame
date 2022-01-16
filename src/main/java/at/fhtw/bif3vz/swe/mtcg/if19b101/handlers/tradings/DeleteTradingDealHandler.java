package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.tradings;

import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

public class DeleteTradingDealHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> TRADING-DELETE");
        //...delete trading deals here

        String token = getAuthorizationToken(exchange);
        String id = getURIName(exchange.getRequestURI().getPath());
        if(isLogged(token)){
            System.out.println("let's delete that");
            DatabaseOperations.removeTradeFromDatabase(token, id);
            exchange.sendResponseHeaders(StatusCode.OK.getCode(), 0);
        }else{
            System.out.println("User isn't logged in");
            exchange.sendResponseHeaders(StatusCode.UNAUTHORIZED.getCode(), -1);
        }
    }

}
