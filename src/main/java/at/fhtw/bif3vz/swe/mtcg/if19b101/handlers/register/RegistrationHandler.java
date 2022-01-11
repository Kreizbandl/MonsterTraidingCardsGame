package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.register;

import at.fhtw.bif3vz.swe.mtcg.if19b101.Main;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.TestUser;
//import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class RegistrationHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> REGISTER");
        //...registration of new user

        //map username and password to user
        TestUser newUser = mapRequest(exchange.getRequestBody(),TestUser.class);
        //generate new token for new user
        newUser.setToken("Basic " + newUser.getUsername() + "-mtcgToken");

        //store in database
        boolean ok = DatabaseOperations.writeUserToDatabase(newUser);

        if(ok){
            //exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(StatusCode.CREATED.getCode(), 0);
            //byte[] response = writeResponse(newUser.getToken());
            System.out.println("new user registered");
            /*OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(response);
            responseBody.close();*/
        }else{
            exchange.sendResponseHeaders(StatusCode.NOCONTENT.getCode(), -1);
            //response = writeResponse("user already exists");
            System.out.println("user already exists");
        }

        //printHeaders(exchange.getRequestHeaders());
        //printBody(new InputStreamReader(exchange.getRequestBody(), "utf-8"));

    }
}
