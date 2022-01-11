package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.scoreboard;

import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;

public class ScoreboardHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> SCOREBOARD");
        //...return scoreboard here


        System.out.println(DatabaseOperations.readScoreboardFromDatabase());

        printBody(new InputStreamReader(exchange.getRequestBody()));

    }
}
