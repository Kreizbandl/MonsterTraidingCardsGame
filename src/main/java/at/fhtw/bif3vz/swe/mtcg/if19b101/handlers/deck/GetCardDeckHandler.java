package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.deck;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCardDB;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GetCardDeckHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> DECK-GET");
        //return the deck

        //check if format is wanted
        if(exchange.getRequestURI().getQuery().equals("format=plain")){
            System.out.println("you want plain format, ok");
        }

        String token = exchange.getRequestHeaders().get("Authorization").get(0);

        List<TestCardDB> cards = DatabaseOperations.readDeckFromDatabase(token);
        System.out.println(cards);

        printBody(new InputStreamReader(exchange.getRequestBody()));

    }
}
