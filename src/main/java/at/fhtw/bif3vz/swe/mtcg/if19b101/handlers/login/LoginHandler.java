package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.login;

import at.fhtw.bif3vz.swe.mtcg.if19b101.Main;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
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

        User logUser = mapRequest(exchange.getRequestBody(),User.class);
        System.out.println(logUser.toString());

        Main.userList.add(logUser);

        //printBody(new InputStreamReader(exchange.getRequestBody(), "utf-8"));

    }
}
