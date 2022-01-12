package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.register;

import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class GetUserProfileHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> USER-PROFILE-GET");
        //...get users profile data

        //String token = exchange.getRequestHeaders().get("Authorization").get(0);
        String token = getAuthorizationToken(exchange);
        String user = getURIName(exchange.getRequestURI().getPath());

        if(!token.contains(user)){
            System.out.println("ERR: token and path for user do not match");
        }else{
            System.out.println(DatabaseOperations.readUserProfileFromDatabase(token));
        }

        //response!

    }
}
