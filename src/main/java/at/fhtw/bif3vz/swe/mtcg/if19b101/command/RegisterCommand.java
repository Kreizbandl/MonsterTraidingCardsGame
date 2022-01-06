package at.fhtw.bif3vz.swe.mtcg.if19b101.command;

import java.util.Scanner;

public class RegisterCommand implements Command{

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String username, password, passwordConfirm;

        System.out.println("execute register command...");

        System.out.print("username: ");
        username = scanner.next();
        System.out.print("password: ");
        password = scanner.next();
        System.out.print("Confirm password: ");
        passwordConfirm = scanner.next();


    }
}
