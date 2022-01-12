package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.register;

import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EditUserProfileHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> USER-PROFILE-EDIT");
        //...edit users profile data

        String token = getAuthorizationToken(exchange);
        String user = getURIName(exchange.getRequestURI().getPath());

        if(!token.contains(user)){
            System.out.println("ERR: token and path for user do not match");
        }else{
            Map<String,String> list = mapRequestListMap(exchange.getRequestBody());
            List<String> data = new ArrayList<>();
            data.add(list.get("Name"));
            data.add(list.get("Bio"));
            data.add(list.get("Image"));

            DatabaseOperations.writeUserProfileToDatabase(token, data);
        }



    }
}
