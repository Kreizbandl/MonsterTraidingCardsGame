package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.login;

import at.fhtw.bif3vz.swe.mtcg.if19b101.Main;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.TestUser;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class LoginHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> LOGIN");
        //...login user

        //map request to user
        TestUser logUser = mapRequest(exchange.getRequestBody(),TestUser.class);
        //get user data from database (null if no user)
        TestUser dbUser = DatabaseOperations.readUserDataFromDatabase(logUser.getUsername());


        //check if username exists
        if(logUser.getUsername().equals(dbUser.getUsername())){
            byte[] response;
            //check if password correct
            if(logUser.getPassword().equals(dbUser.getPassword())){
                System.out.println("login ok");
                Main.userList.add(dbUser);//session starten
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(StatusCode.OK.getCode(), 0);
                response = writeResponse(dbUser.getToken());
            }else{
                exchange.sendResponseHeaders(StatusCode.FORBIDDEN.getCode(), 0);
                response = writeResponse("wrong password");
                System.out.println("wrong password");
            }
            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(response);
            responseBody.close();
        }else{
            exchange.sendResponseHeaders(StatusCode.NOCONTENT.getCode(), -1);
            //response = writeResponse("user doesn't exist");
            System.out.println("user doesn't exist");
        }

        //printBody(new InputStreamReader(exchange.getRequestBody(), "utf-8"));
    }
}
