package at.fhtw.bif3vz.swe.mtcg.if19b101.command;

public class DefaulfCommand implements Command{

    @Override
    public void execute() {
        System.out.println("unknown command...");
        System.out.println("'login' -> login user");
        System.out.println("'quit' -> shutdown client");
    }
}
