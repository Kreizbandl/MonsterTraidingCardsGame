package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.login;

import at.fhtw.bif3vz.swe.mtcg.if19b101.Main;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.TestUser;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;

public class LoginHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> LOGIN");
        //...login user

        TestUser reqUser = mapRequest(exchange.getRequestBody(),TestUser.class);
        TestUser dbUser = DatabaseOperations.readUserDataByName(reqUser.getUsername());//besser by username and password

        if(isLogged(dbUser.getToken())){
            System.out.println("ERR: user is already logged in");
            exchange.sendResponseHeaders(StatusCode.NOCONTENT.getCode(), -1);
        }else{
            if(reqUser.getUsername().equals(dbUser.getUsername())){
                byte[] response;
                if(reqUser.getPassword().equals(dbUser.getPassword())){
                    System.out.println("login ok");
                    Main.loggedUsersTokenList.add(dbUser.getToken());
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(StatusCode.OK.getCode(), 0);
                    response = writeResponse(dbUser.getToken());
                }else{
                    exchange.sendResponseHeaders(StatusCode.FORBIDDEN.getCode(), 0);
                    response = writeResponse("wrong password");
                    System.out.println("ERR: wrong password");
                }
                OutputStream responseBody = exchange.getResponseBody();
                responseBody.write(response);
                responseBody.close();
            }else{
                exchange.sendResponseHeaders(StatusCode.NOCONTENT.getCode(), -1);
                System.out.println("ERR: user doesn't exist");
            }
        }
    }
}
