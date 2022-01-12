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
        System.out.println("chosen cards" + cardIDs);

        int error = DatabaseOperations.updateDeckToDatabase(token, cardIDs);
        if(error == -1){
            System.out.println("ERR: card not from this user");
            exchange.sendResponseHeaders(StatusCode.UNAUTHORIZED.getCode(), 0);
        }else if(error == -2){
            System.out.println("ERR: not 4 card ids");
            exchange.sendResponseHeaders(StatusCode.FORBIDDEN.getCode(), 0);
        }else{
            System.out.println("cards added to deck");
            exchange.sendResponseHeaders(StatusCode.OK.getCode(), 0);
        }

        //printBody(new InputStreamReader(exchange.getRequestBody()));

    }
}
