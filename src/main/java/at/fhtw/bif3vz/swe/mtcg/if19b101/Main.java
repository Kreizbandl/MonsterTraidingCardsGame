package at.fhtw.bif3vz.swe.mtcg.if19b101;

import at.fhtw.bif3vz.swe.mtcg.if19b101.database.Database;
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
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Main {

    public static List<String> loggedUsersTokenList = new ArrayList<>();//session data
    public static String adminToken = "Basic admin-mtcgToken";//admin token

    public static void main(String[] args) throws IOException{

        HttpServer server = HttpServer.create(new InetSocketAddress(10001), 0);

        server.createContext("/users",new RegHandler()::handle);
        server.createContext("/sessions",new LogHandler()::handle);
        server.createContext("/packages",new CrePackHandler()::handle);
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

        //DatabaseConnection.getInstance().close();
    }


}
