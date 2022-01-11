package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.deck;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCardDB;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class GetCardDeckHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> DECK-GET");
        //return the deck

        String token = exchange.getRequestHeaders().get("Authorization").get(0);
        //System.out.println(token);

        List<TestCardDB> cards = DatabaseOperations.readDeckFromDatabase(token);
        System.out.println(cards);

        //check if format is wanted (!kann nicht als erstes aufgerufen werden?, macht probleme :()
        /*if(exchange.getRequestURI().getQuery().equals("format=plain")){
            System.out.println("you want plain format, ok");
        }*/

        byte[] response;
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(StatusCode.OK.getCode(), 0);
        response = writeResponse(cards);

        OutputStream responseBody = exchange.getResponseBody();
        responseBody.write(response);
        responseBody.close();


        //printBody(new InputStreamReader(exchange.getRequestBody()));

    }
}
