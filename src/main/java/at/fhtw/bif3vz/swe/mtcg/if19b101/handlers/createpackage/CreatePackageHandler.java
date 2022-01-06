package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.createpackage;

import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;

public class CreatePackageHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("create Package");
        //...create Package

        printHeaders(exchange.getRequestHeaders());
        printBody(new InputStreamReader(exchange.getRequestBody(), "utf-8"));
    }

}
