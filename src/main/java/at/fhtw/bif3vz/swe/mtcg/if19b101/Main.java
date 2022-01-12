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
import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.*;
import java.util.*;

public class Main {

    public static List<String> loggedUsersTokenList = new ArrayList<>();//session data
    public static String adminToken = "Basic admin-mtcgToken";

    public static void main(String[] args) throws IOException{
        HttpServer server = HttpServer.create(new InetSocketAddress(10001), 0);

        Handler regHandler = new RegHandler();
        server.createContext("/users",regHandler::handle);

        Handler logHandler = new LogHandler();
        server.createContext("/sessions",logHandler::handle);

        Handler crePackHandler = new CrePackHandler();
        server.createContext("/packages",crePackHandler::handle);

        Handler aquPackHandler = new AquPackHandler();
        server.createContext("/transactions/packages",aquPackHandler::handle);

        Handler allCarHandler = new AllCarHandler();
        server.createContext("/cards",allCarHandler::handle);

        Handler carDecHandler = new CarDeckHandler();
        server.createContext("/deck", carDecHandler::handle);

        //missing 14) /users/kienboec...

        Handler staHandler = new StaHandler();
        server.createContext("/stats", staHandler::handle);

        Handler scoHandler = new ScoHandler();
        server.createContext("/score", scoHandler::handle);

        Handler batHandler = new BatHandler();
        server.createContext("/battles", batHandler::handle);

        Handler traHandler = new TraHandler();
        server.createContext("/tradings", traHandler::handle);

        server.start();

        System.out.println("Init? [Y/N]");
        Scanner scanner = new Scanner(System.in);
        var input = scanner.nextLine();
        if("y".equalsIgnoreCase(input)){
            Database.initDb();
        }

        //DatabaseConnection.getInstance().close();
    }

    public static boolean isLogged(String token){
        return loggedUsersTokenList.contains(token);
    }
}
