package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.aquirepackage;

import at.fhtw.bif3vz.swe.mtcg.if19b101.Main;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;

public class AquirePackageHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> AQUIRE PACKAGE");
        //aquire package here
        String token = exchange.getRequestHeaders().get("Authorization").get(0);
        if(!Main.isLogged(token)){
            System.out.println("ERR: user isn't logged in");
            exchange.sendResponseHeaders(StatusCode.UNAUTHORIZED.getCode(), -1);
        }else{
            int error = DatabaseOperations.aquirePackageByToken(token);
            if(error == 1){
                System.out.println("ERR: no package left, sorry");
                exchange.sendResponseHeaders(StatusCode.OK.getCode(), 0);
            }else if(error == -1){
                System.out.println("ERR: not enough coins left");
                exchange.sendResponseHeaders(StatusCode.OK.getCode(), 0);
            }else{
                System.out.println("package aquired");
                exchange.sendResponseHeaders(StatusCode.OK.getCode(), 0);
            }
        }

    }
}
