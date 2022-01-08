package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.register;

import at.fhtw.bif3vz.swe.mtcg.if19b101.Main;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.TestUser;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class RegistrationHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("register");
        //...registration of new user
        //neuen datenbankeintrag erstellen
        //check if username and password are valid / exist
        //- check if user already exists
        //bei erfolg token zurücksenden

        //map username and password to user object
        TestUser newUser = mapRequest(exchange.getRequestBody(),TestUser.class);
        //generate new token for new user
        newUser.setToken("Basic " + newUser.getUsername() + "-mtcgToken");
        System.out.println(newUser.toString());

        //store in database
        DatabaseOperations.writeUserToDatabase(newUser);

        //Main.userList.add(newUser);

        //System.out.println(Main.userList.toString());

        //printHeaders(exchange.getRequestHeaders());

        //printBody(new InputStreamReader(exchange.getRequestBody(), "utf-8"));

    }
}
