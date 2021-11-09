package at.fhtw.bif3vz.swe.mtcg.if19b101.command;

import java.util.Scanner;

public class LoginCommand implements Command{

    @Override
    public void execute() {
        System.out.println("execute login command...");
        //TODO some stuff...
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
    }
}
