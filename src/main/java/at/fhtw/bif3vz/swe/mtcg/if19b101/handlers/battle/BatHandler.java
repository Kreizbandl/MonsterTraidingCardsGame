package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.battle;

import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.deck.EditCardDeckHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.deck.GetCardDeckHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class BatHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        if(exchange.getRequestMethod().equals("POST")){
            //handler for getting the deck
            new BattleHandler().execute(exchange);
        }else{
            System.out.println("unsupported method in battle");
        }
    }
}
