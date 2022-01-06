package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.register;

import at.fhtw.bif3vz.swe.mtcg.if19b101.Main;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class RegistrationHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("register");
        //...registration of new user
        //neuen datenbankeintrag erstellen

        User newUser = mapRequest(exchange.getRequestBody(),User.class);
        System.out.println(newUser.toString());

        Main.userList.add(newUser);

        //printHeaders(exchange.getRequestHeaders());

        //printBody(new InputStreamReader(exchange.getRequestBody(), "utf-8"));

    }
}
