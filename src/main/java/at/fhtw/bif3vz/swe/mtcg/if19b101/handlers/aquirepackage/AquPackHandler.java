package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.aquirepackage;

import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class AquPackHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        if(exchange.getRequestMethod().equals("POST")){
            new AquirePackageHandler().execute(exchange);
        }else{
            System.out.println("unsupported method in aquire Package");
        }
    }
}
