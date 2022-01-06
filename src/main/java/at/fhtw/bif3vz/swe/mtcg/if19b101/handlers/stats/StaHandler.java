package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.stats;

import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class StaHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        if(exchange.getRequestMethod().equals("GET")){
            new StatsHandler().execute(exchange);
        }else{
            System.out.println("unsupported method in stats");
        }
    }
}
