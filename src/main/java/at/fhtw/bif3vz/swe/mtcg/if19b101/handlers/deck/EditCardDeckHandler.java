package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.deck;

import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;

public class EditCardDeckHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("deck: edit...");
        //... edit deck here

        printBody(new InputStreamReader(exchange.getRequestBody()));

    }
}
