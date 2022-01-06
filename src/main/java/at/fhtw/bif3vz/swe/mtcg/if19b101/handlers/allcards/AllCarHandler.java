package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.allcards;

import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class AllCarHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        if(exchange.getRequestMethod().equals("GET")){
            new AllCardsHandler().execute(exchange);
        }else{
            System.out.println("unsupported method in all cards");
        }
    }
}
