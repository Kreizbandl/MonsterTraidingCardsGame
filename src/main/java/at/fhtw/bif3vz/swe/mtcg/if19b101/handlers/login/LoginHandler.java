package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.login;

import at.fhtw.bif3vz.swe.mtcg.if19b101.Main;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.TestUser;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;

public class LoginHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("login");
        //...login user
        //mit daten aus batenbank vergleichen
        //bei erfolg token zurücksenden

        TestUser logUser = mapRequest(exchange.getRequestBody(),TestUser.class);
        System.out.println(logUser.toString());

        Main.userList.add(logUser);

        //send token for user back to client

        //System.out.println(Main.userList.toString());

        //printBody(new InputStreamReader(exchange.getRequestBody(), "utf-8"));

    }
}
