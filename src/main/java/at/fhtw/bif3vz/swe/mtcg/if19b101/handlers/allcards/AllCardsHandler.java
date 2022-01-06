package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.allcards;

import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;

public class AllCardsHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("all cards");
        //...show all cards of user

        printBody(new InputStreamReader(exchange.getRequestBody(), "utf-8"));

    }
}