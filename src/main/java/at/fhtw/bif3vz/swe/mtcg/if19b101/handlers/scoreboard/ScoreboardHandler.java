package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.scoreboard;

import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.TreeMap;

public class ScoreboardHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> SCOREBOARD");
        //...return scoreboard here

        String token = getAuthorizationToken(exchange);
        if(!isLogged(token)){
            System.out.println("ERR: user isn't logged in");
            exchange.sendResponseHeaders(StatusCode.UNAUTHORIZED.getCode(), -1);
        }else{
            TreeMap<String, Integer> scoreboard = DatabaseOperations.readScoreboardFromDatabase();
            System.out.println(scoreboard);
            sendResponseHeader(exchange, StatusCode.OK.getCode(), 0);
            byte[] response = writeResponse(scoreboard);
            sendResponseBody(exchange, response);
        }
    }
}
