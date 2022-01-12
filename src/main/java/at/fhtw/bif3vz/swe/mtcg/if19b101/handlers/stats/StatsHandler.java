package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.stats;

import at.fhtw.bif3vz.swe.mtcg.if19b101.Main;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;

public class StatsHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> STATS");
        //...return stats here

        String token = exchange.getRequestHeaders().get("Authorization").get(0);
        if(!Main.isLogged(token)){
            System.out.println("ERR: user isn't logged in");
            exchange.sendResponseHeaders(StatusCode.UNAUTHORIZED.getCode(), -1);
        }else{
            int elo = DatabaseOperations.readELOFromDatabase(token);
            System.out.println("elo: " + elo);
            byte[] response;
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(StatusCode.OK.getCode(), 0);
            response = writeResponse(elo);
            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(response);
            responseBody.close();
        }
    }
}