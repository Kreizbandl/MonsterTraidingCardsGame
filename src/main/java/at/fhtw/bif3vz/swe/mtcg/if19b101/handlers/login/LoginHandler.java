package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.login;

import at.fhtw.bif3vz.swe.mtcg.if19b101.Main;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
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
        //System.out.println(logUser.toString());

        TestUser dbUser = DatabaseOperations.readUserDataFromDatabase(logUser.getUsername());

        //besser gleich als SQL check machen statt hier vergleichen
        if(logUser.getPassword().equals(dbUser.getPassword())){
            System.out.println("LOGIN: OK");
            Main.userList.add(dbUser);//session starten
            //response token to client
        }else{
            System.out.println("LOGIN: ERR");
        }

        //System.out.println(Main.userList.toString());

        //printBody(new InputStreamReader(exchange.getRequestBody(), "utf-8"));

    }
}
