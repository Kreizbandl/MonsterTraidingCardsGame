package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.stats;

import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;

public class StatsHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> STATS");
        //...return stats here

        //DatabaseOperations.readStatsFromDatabase();

        printBody(new InputStreamReader(exchange.getRequestBody()));

    }
}