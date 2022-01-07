package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.aquirepackage;

import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;

public class AquirePackageHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("aquire package");
        //aquire package here
        //hole package aus datenbank
        //füge dieses dem stack des users hinzu
        //response


        printBody(new InputStreamReader(exchange.getRequestBody(), "utf-8"));

    }
}
