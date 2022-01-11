package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.allcards;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCardDB;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class AllCardsHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> ALL CARDS");
        //...show all cards of user

        if(exchange.getRequestHeaders().get("Authorization") == null){
            System.out.println("not authorized");
            exchange.sendResponseHeaders(StatusCode.UNAUTHORIZED.getCode(), -1);
        }else{
            String token = exchange.getRequestHeaders().get("Authorization").get(0);
            List<TestCardDB> cards = DatabaseOperations.readCardsFromDatabaseByToken(token);
            System.out.println(cards);
            byte[] response;
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(StatusCode.OK.getCode(), 0);
            response = writeResponse(cards);

            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(response);
            responseBody.close();
        }


        //printBody(new InputStreamReader(exchange.getRequestBody(), "utf-8"));

    }
}