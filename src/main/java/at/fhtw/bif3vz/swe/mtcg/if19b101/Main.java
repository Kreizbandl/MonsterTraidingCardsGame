package at.fhtw.bif3vz.swe.mtcg.if19b101;

//import at.fhtw.bif3vz.swe.mtcg.if19b101.card.CardRecord;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCardDB;
import at.fhtw.bif3vz.swe.mtcg.if19b101.command.Command;
import at.fhtw.bif3vz.swe.mtcg.if19b101.command.CommandFactory;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.Database;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseConnection;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.gamelogic.Gamelogic;
//import at.fhtw.bif3vz.swe.mtcg.if19b101.server.Client;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.allcards.AllCarHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.aquirepackage.AquPackHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.createpackage.CrePackHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.deck.CarDeckHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.login.LogHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.register.RegHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.scoreboard.ScoHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.stats.StaHandler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.tradings.TraHandler;
//import at.fhtw.bif3vz.swe.mtcg.if19b101.server.Message;
import at.fhtw.bif3vz.swe.mtcg.if19b101.server.Package;
import at.fhtw.bif3vz.swe.mtcg.if19b101.server.TestPackage;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.TestUser;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;
//import at.fhtw.bif3vz.swe.mtcg.if19b101.user.UserRecord;
import com.sun.net.httpserver.HttpServer;

import java.io.*;//client server
import java.net.*;//client server
import java.util.*;

//Main as main for server
public class Main {

    public static List<TestUser> userList = new ArrayList<>();//^^session data
    public static List<TestPackage> allPackages = new ArrayList<>();//^^weg damit, packages gleich in datenbank

    public static List<TestUser> users;
    public static List<TestCardDB> cards;

    public static void main(String[] args) throws IOException{
        HttpServer server = HttpServer.create(new InetSocketAddress(10001), 0);

        //TODO multiple endpoints for different executes
        //how to cleaner?
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

        //missing 13) /deck?format=plain...

        //missing 14) /users/kienboec...

        Handler staHandler = new StaHandler();
        server.createContext("/stats", staHandler::handle);

        Handler scoHandler = new ScoHandler();
        server.createContext("/score", scoHandler::handle);

        //missing 17) /battles

        Handler traHandler = new TraHandler();
        server.createContext("/tradings", traHandler::handle);

        server.start();

        System.out.println("Init? [Y/N]");
        Scanner scanner = new Scanner(System.in);
        var input = scanner.nextLine();
        if("y".equalsIgnoreCase(input)){
            Database.initDb();
        }

        /*users = new ArrayList<>();
        users.add(new TestUser("Manuel", "1234", "Basic manuel-token"));
        users.add(new TestUser("Nina", "1212", "Basic nina-token"));
        users.add(new TestUser("Max", "3434", "Basic max-token"));

        cards = new ArrayList<>();
        cards.add(new TestCardDB("0", "FireSpell", 34.3f));
        cards.add(new TestCardDB("1", "FireMonster", 22.6f));
        cards.add(new TestCardDB("2", "EarthSpell", 12.2f));

        TestUser singleUser = new TestUser("test", "test", "Basic test-token");
        TestCardDB singleCard = new TestCardDB("42", "WaterMonster", 25.5f);



        //call these functions elsewhere
        DatabaseOperations.deleteAllUsersInDatabase();
        DatabaseOperations.deleteAllCardsInDatabase();
        DatabaseOperations.writeUsersListToDatabase(users);
        DatabaseOperations.writeUserToDatabase(singleUser);
        DatabaseOperations.writeCardsListToDatabase(cards);
        DatabaseOperations.writeCardToDatabase(singleCard);
        //deleteUserInDatabase(users.get(0));*/

        //DatabaseConnection.getInstance().close();
    }



    /*public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client();

        System.out.println("--monstertraidingcardsgame--");

        while(true){
            System.out.print("Enter: ");
            String input = scanner.next();
            CommandFactory.getCommand(input).execute();
            try {
                client.recvMessage();
            }catch(Exception e){
                System.out.println(e);
            }

            Message message = client.recvMessage();
            System.out.println(message.getError());
        }
    }*/

    /*public static void mai2(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client();//for sending and receiving data like an interface^^
        Message message = new Message();//for communication
        User user = new User();//User object is empty at beginning

        System.out.println("--monstertraidingcardsgame--");

        label:
        do {
            //CommandFactory.getCommand(scanner.next()).execute();

            message.setCommand("");//besser alles clearn
            System.out.print("Enter command: ");
                message.setCommand(scanner.next());

            switch (message.getCommand()) {
                case "quit":
                    //quit loop
                    client.sendMessage(message);//shutdown server, besser weg und server seperat shutdown
                    break label;
                case "log":
                    //login
                    System.out.print("Username: ");
                        message.setUsername(scanner.next());
                    System.out.print("Password: ");
                        message.setPassword(scanner.next());

                    client.sendMessage(message);
                    message = client.recvMessage();
                    System.out.println(message.getError());

                    if (message.getError().equals("login success")) {//no functionality yet, no logged in state
                        user.setUsername(message.getUsername());
                        user.setPassword(message.getPassword());
                    }

                    break;
                case "?":
                case "help":
                    //help
                    System.out.println(
                            "'quit' -> shutdown server and client\n" +
                                    "'log' -> login to server\n" +
                                    "'reg' -> register\n" +
                                    "'info' -> user info\n" +
                                    "'aquire' -> aquire package");
                    break;
                default:
                    //unknown command
                    System.out.println("Unknown command... '?' or 'help'");
                    break;
            }
        } while (true);

        //shutdown client
        client.closeClient();
    }*/

    /*public static void main1(String[] args) throws IOException {
        User user1 = new User("Manuel","1234");
        User user2 = new User("Franz","0000");

            user1.aquirePackage();
            user1.aquirePackage();
            user1.addBestCardsToDeck();

            user2.aquirePackage();
            user2.aquirePackage();
            user2.addBestCardsToDeck();

        Battle battle = new Battle(user1,user2);

        System.out.println("STACK1: " + user1.getStackOfUser().toString());
        System.out.println("STACK2: " + user2.getStackOfUser().toString() + "\n");

        System.out.println("-------------------FIGHT!!!-------------------");
            battle.showBattle();
        System.out.println("--------------------END-----------------------");
    }*/
}
