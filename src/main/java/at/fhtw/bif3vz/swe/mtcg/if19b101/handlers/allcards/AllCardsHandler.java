package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.allcards;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCardDB;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class AllCardsHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("all cards");
        //...show all cards of user

        if(exchange.getRequestHeaders().get("Authorization") == null){
            System.out.println("not authorized");
        }else{
            String token = exchange.getRequestHeaders().get("Authorization").get(0);
            List<TestCardDB> cards = DatabaseOperations.readCardsFromDatabaseByToken(token);
            System.out.println(cards);
        }


        //printBody(new InputStreamReader(exchange.getRequestBody(), "utf-8"));

    }
}