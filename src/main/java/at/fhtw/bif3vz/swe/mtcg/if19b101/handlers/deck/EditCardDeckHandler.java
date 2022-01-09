package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.deck;

import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class EditCardDeckHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> DECK-EDIT");
        //... edit deck here

        String token = exchange.getRequestHeaders().get("Authorization").get(0);
        List<String> cardIDs = mapStringList(exchange.getRequestBody());
        System.out.println(cardIDs);

        System.out.println("user: " + token);
        DatabaseOperations.writeDeckToDatabase(token, cardIDs);

        //printBody(new InputStreamReader(exchange.getRequestBody()));

    }
}
