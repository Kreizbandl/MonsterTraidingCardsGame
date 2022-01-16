package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.register;

import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.UserDB;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

public class RegistrationHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> REGISTER");
        //...registration of new user

        UserDB newUser = mapRequest(exchange.getRequestBody(), UserDB.class);
        newUser.setToken("Basic " + newUser.getUsername() + "-mtcgToken");//pseudo generate new token
        if(DatabaseOperations.writeUserToDatabase(newUser)){
            exchange.sendResponseHeaders(StatusCode.CREATED.getCode(), 0);
            System.out.println("new user registered");
        }else{
            exchange.sendResponseHeaders(StatusCode.NOCONTENT.getCode(), -1);
            System.out.println("ERR: user already exists");
        }
    }
}
