package at.fhtw.bif3vz.swe.mtcg.if19b101.command;

public class QuitCommand implements Command{

    @Override
    public void execute() {
        System.out.println("execute quit command...");
        System.exit(0);
    }

}
