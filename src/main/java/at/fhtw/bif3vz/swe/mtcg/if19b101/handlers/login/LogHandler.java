package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.login;

import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class LogHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        if(exchange.getRequestMethod().equals("POST")){
            new LoginHandler().execute(exchange);
        }else{
            System.out.println("unsupported method in log");
        }
    }

}
