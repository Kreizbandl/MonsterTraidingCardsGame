package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.aquirepackage;

import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;

public class AquirePackageHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("aquire package");
        //aquire package here
        String token = exchange.getRequestHeaders().get("Authorization").get(0);

        int error = DatabaseOperations.aquirePackageByToken(token);
        if(error == 1){
            System.out.println("no package left, sorry");
        }else if(error == -1){
            System.out.println("not enough coins left");
        }else{
            System.out.println("package aquired");
        }


        printBody(new InputStreamReader(exchange.getRequestBody(), "utf-8"));

    }
}
