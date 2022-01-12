package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.createpackage;

import at.fhtw.bif3vz.swe.mtcg.if19b101.Main;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.CardDB;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.List;

public class CreatePackageHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> CREATE PACKAGE");
        //...create Package

        //String token = exchange.getRequestHeaders().get("Authorization").get(0);
        String token = getAuthorizationToken(exchange);
        if(!isLogged(token)){
            System.out.println("ERR: user isn't logged in");
            exchange.sendResponseHeaders(StatusCode.UNAUTHORIZED.getCode(), -1);
        }else{
            if(token.equals(Main.adminToken)){
                System.out.println("package created");
                List<CardDB> tc = mapCardsList(exchange.getRequestBody(), CardDB.class);
                DatabaseOperations.writePackageToDatabase(tc);
            }else{
                System.out.println("ERROR - no admin");
            }
        }
    }

}
