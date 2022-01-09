package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.tradings;

import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteTradingDealHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> TRADING-EXECUTE");
        //...execute or create trading deals here

        printBody(new InputStreamReader(exchange.getRequestBody()));
    }
}