package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.register;

import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class GetUserProfileHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> USER-PROFILE-GET");
        //...get users profile data

        String token = getAuthorizationToken(exchange);
        String user = getURIName(exchange.getRequestURI().getPath());
        if(!token.contains(user)){
            System.out.println("ERR: token and path for user do not match");
            exchange.sendResponseHeaders(StatusCode.UNAUTHORIZED.getCode(), 0);
        }else{
            List<String> info = DatabaseOperations.readUserProfileFromDatabase(token);
            System.out.println(info);
            sendResponseHeader(exchange, StatusCode.OK.getCode(), 0);
            byte[] response = writeResponse(info);
            sendResponseBody(exchange, response);
        }
    }
}
