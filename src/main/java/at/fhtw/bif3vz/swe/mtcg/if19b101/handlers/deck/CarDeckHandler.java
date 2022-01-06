package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.deck;

import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class CarDeckHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        if(exchange.getRequestMethod().equals("GET")){
            //handler for getting the deck
            new GetCardDeckHandler().execute(exchange);
        }else if(exchange.getRequestMethod().equals("PUT")){
            //handler for editing deck
            new EditCardDeckHandler().execute(exchange);
        }else{
            System.out.println("unsupported method in deck");
        }
    }
}
