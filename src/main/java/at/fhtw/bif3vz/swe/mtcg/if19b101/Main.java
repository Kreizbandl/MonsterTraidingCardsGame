package at.fhtw.bif3vz.swe.mtcg.if19b101;

import at.fhtw.bif3vz.swe.mtcg.if19b101.database.Database;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.allcards.AllCarHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.aquirepackage.AquPackHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.battle.BatHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.createpackage.CrePackHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.deck.CarDeckHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.login.LogHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.register.RegHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.scoreboard.ScoHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.stats.StaHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.tradings.TraHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.TestUser;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.*;
import java.net.Authenticator;
import java.util.*;

public class Main {

    public static List<String> loggedUsersTokenList = new ArrayList<>();//session data
    public static String adminToken = "Basic admin-mtcgToken";

    public static void main(String[] args) throws IOException{

        HttpServer server = HttpServer.create(new InetSocketAddress(10001), 0);


        server.createContext("/users",new RegHandler()::handle);
        server.createContext("/sessions",new LogHandler()::handle);


        //critical part here!!!
        //secure endpoint
        server.createContext("/packages",new CrePackHandler()::handle).setAuthenticator(new BasicAuthenticator(("packages")) {
            @Override
            public Result authenticate(HttpExchange t) {
                return super.authenticate(t);
            }

            @Override
            public boolean checkCredentials(String username, String password) {
                System.out.println(username + " : " + password);
                return username.equals("") && password.equals("");
            }
        });
        //https://tipsfordev.com/java-httpserver-basic-authentication-for-different-request-methods
        //critical part here!!!



        server.createContext("/transactions/packages",new AquPackHandler()::handle);
        server.createContext("/cards",new AllCarHandler()::handle);
        server.createContext("/deck", new CarDeckHandler()::handle);
        server.createContext("/stats", new StaHandler()::handle);
        server.createContext("/score", new ScoHandler()::handle);
        server.createContext("/battles", new BatHandler()::handle);
        server.createContext("/tradings", new TraHandler()::handle);

        server.start();

        System.out.println("Init? [Y/N]");
        Scanner scanner = new Scanner(System.in);
        var input = scanner.nextLine();
        if("y".equalsIgnoreCase(input)){
            Database.initDb();
        }

        /*public static void main (String[] args) throws Exception {
            HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
            try {
                Handler handler = new Handler();
                HttpContext ctx = server.createContext("/test", handler);

                BasicAuthenticator a = new BasicAuthenticator(REALM) {
                    public boolean checkCredentials (String username, String pw) {
                        return USERNAME.equals(username) && PASSWORD.equals(pw);
                    }
                };
                ctx.setAuthenticator(a);
                server.start();*/


        //DatabaseConnection.getInstance().close();
    }


}
