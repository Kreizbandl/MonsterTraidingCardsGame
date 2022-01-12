package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.allcards;

import at.fhtw.bif3vz.swe.mtcg.if19b101.Main;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.CardDB;
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

        //ifs umkehren, zuerst ob token == null, dann erst ob isLogged()
        String token = getAuthorizationToken(exchange);
        if(token == null){
            System.out.println("ERR: not authorized");
            exchange.sendResponseHeaders(StatusCode.UNAUTHORIZED.getCode(), -1);
        }else{
            if(!isLogged(token)){
                System.out.println("ERR: user isn't logged in");
                exchange.sendResponseHeaders(StatusCode.UNAUTHORIZED.getCode(), -1);
            }else{
                List<CardDB> cards = DatabaseOperations.readAllCardsByToken(token);
                System.out.println(cards);
                byte[] response;
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(StatusCode.OK.getCode(), 0);
                response = writeResponse(cards);

                OutputStream responseBody = exchange.getResponseBody();
                responseBody.write(response);
                responseBody.close();
            }
        }

    }
}