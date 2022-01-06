package at.fhtw.bif3vz.swe.mtcg.if19b101.command;

public class DefaulfCommand implements Command{

    @Override
    public void execute() {
        System.out.println("execute default command...");

        System.out.println("unknown command...");
        System.out.println("'login' -> login user");
        System.out.println("'register' -> register user");
        System.out.println("'quit' -> shutdown client");
        System.out.println("'info' -> user information");
        System.out.println("'aquire' -> aquire package from server");
        System.out.println("'deck' -> define deck");
        System.out.println("'compare' -> compare stats in score-board");
    }
}
