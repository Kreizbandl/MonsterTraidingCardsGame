package at.fhtw.bif3vz.swe.mtcg.if19b101.command;

//import at.fhtw.bif3vz.swe.mtcg.if19b101.server.Client;
import at.fhtw.bif3vz.swe.mtcg.if19b101.server.Message;

import java.util.Scanner;

public class LoginCommand implements Command{

    //private Client client;

    @Override
    public void execute() { // statt void String
        Scanner scanner = new Scanner(System.in);
        String username, password;

        System.out.println("execute login command...");

        System.out.print("username: ");
        username = scanner.next();
        System.out.print("password: ");
        password = scanner.next();

        //TODO here HTTP-Requests an server

        //return client.sendMessage();

        /*try {
            Client client = new Client(new Message("login", username, password, "ok"));
            client.sendMessage(new Message("login", username, password, "ok"));
        }catch(Exception e){
            System.out.println(e);
        }*/

    }
}
