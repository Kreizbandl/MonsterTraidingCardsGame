package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.createpackage;

import at.fhtw.bif3vz.swe.mtcg.if19b101.Main;
//import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCardDB;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.server.TestPackage;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class CreatePackageHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("create Package");
        //...create Package
        //check if user is logged in as admin / token
        //add package to datenbank

        String token = exchange.getRequestHeaders().get("Authorization").get(0);

        if(token.equals("Basic admin-mtcgToken")){
            System.out.println("OK - admin");

            List<TestCardDB> tc = mapCardsList(exchange.getRequestBody(), TestCardDB.class);
            TestPackage tp = new TestPackage();
            tp.addCards(tc);
            DatabaseOperations.writePackageToDatabase(tp);

            //Main.allPackages.add(tp);//here store package in database
            //System.out.println(Main.allPackages.toString());
        }else{
            System.out.println("ERROR - no admin");
        }
    }

}
