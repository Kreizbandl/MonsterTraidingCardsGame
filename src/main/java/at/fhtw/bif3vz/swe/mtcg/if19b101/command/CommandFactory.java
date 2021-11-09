package at.fhtw.bif3vz.swe.mtcg.if19b101.command;

public class CommandFactory {

    public static Command getCommand(String input) {
        switch(input){
            case "login": return getLogin();
            case "quit": return getQuit();
        }
        return getDefault();
    }

    public static Command getLogin(){
        return new LoginCommand();
    }

    public static Command getQuit(){
        return new QuitCommand();
    }

    public static Command getDefault(){
        return new DefaulfCommand();
    }
}
