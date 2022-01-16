package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.deck;

import at.fhtw.bif3vz.swe.mtcg.if19b101.database.CardDB;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.List;

public class GetCardDeckHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> DECK-GET");
        //return the deck

        String token = getAuthorizationToken(exchange);
        if(!isLogged(token)){
            System.out.println("ERR: user isn't logged in");
            exchange.sendResponseHeaders(StatusCode.UNAUTHORIZED.getCode(), -1);
        }else{
            List<CardDB> cards = DatabaseOperations.readDeckByToken(token);
            System.out.println("deck: " + cards);

            sendResponseHeader(exchange, StatusCode.OK.getCode(), 0);
            byte[] response = writeResponse(cards);
            sendResponseBody(exchange, response);
        }
    }
}
