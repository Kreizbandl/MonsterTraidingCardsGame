package at.fhtw.bif3vz.swe.mtcg.if19b101;

import at.fhtw.bif3vz.swe.mtcg.if19b101.command.Command;
import at.fhtw.bif3vz.swe.mtcg.if19b101.command.CommandFactory;
import at.fhtw.bif3vz.swe.mtcg.if19b101.gamelogic.Gamelogic;
import at.fhtw.bif3vz.swe.mtcg.if19b101.server.Client;
import at.fhtw.bif3vz.swe.mtcg.if19b101.server.Message;
import at.fhtw.bif3vz.swe.mtcg.if19b101.server.Package;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;

import java.io.*;//client server
import java.net.*;//client server
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {//as Userinterface

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("Enter: ");
            String input = scanner.next();
            CommandFactory.getCommand(input).execute();
        }
    }

    public static void main2(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client();//for sending and receiving data like an interface^^
        Message message = new Message();//for communication
        User user = new User();//User object is empty at beginning
        //boolean isLogged = false;//von haus aus ausgelogged

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
                case "reg":
                    //register
                    System.out.println("We are currently waiting for a database connection...");
                    break;
                case "aquire":
                    //aquire package from server
                    System.out.println("Problem sending package class...");
                    /*client.sendMessage(message);

                    Package pack = client.recvPackage();
                    user.addCardsToStack(pack.getAllCards());*/
                    //user.setStackOfUser(message.getPack().getAllCards());
                    break;
                case "info":
                    //user info
                    System.out.println(user.toString());
                    break;
                default:
                    //unknown command
                    System.out.println("Unknown command... '?' or 'help'");
                    break;
            }
        } while (true);

        //shutdown client
        client.closeClient();
    }

    public static void main1(String[] args) throws IOException {
        //user input -> user -> client -> server
        //CLIENT-PART for each
        /*the idea:
        - a player starts and automatically connects to the server
        state: you are not logged in
        - no aquire cards
        - no battle against other player
        - no compre stats
        - can log in or register
        state: logged in
        - user can do everything
        - logout*/

        User user1 = new User("Manuel","1234");
        User user2 = new User("Franz","0000");

        //define operation over input (scanf...)
            user1.aquirePackage();
            user1.aquirePackage();
            user1.addBestCardsToDeck();

            user2.aquirePackage();
            user2.aquirePackage();
            user2.addBestCardsToDeck();
            //- register/login to server
            //- aquire package
            //- battle against other player
            //- compare stats in scoreboard
        //CLIENT-PART for each

        //SERVER-PART
        Battle battle = new Battle(user1,user2);

        System.out.println("STACK1: " + user1.getStackOfUser().toString());
        System.out.println("STACK2: " + user2.getStackOfUser().toString() + "\n");

        System.out.println("-------------------FIGHT!!!-------------------");
            battle.showBattle();
        System.out.println("--------------------END-----------------------");
        //SERVER-PART
    }
}
