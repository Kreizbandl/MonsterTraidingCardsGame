package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.tradings;

import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class TraHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        if(exchange.getRequestMethod().equals("GET")){
            //check for trading deals
            new CheckTradingDealsHandler().execute(exchange);
        }else if(exchange.getRequestMethod().equals("POST")){
            //create or execute trading deal
            new ExecuteTradingDealHandler().execute(exchange);
        }else if(exchange.getRequestMethod().equals("DELETE")){
            //delete trading deal
            new DeleteTradingDealHandler().execute(exchange);
        }else{
            System.out.println("unsupported method in tradings");
        }
    }
}
