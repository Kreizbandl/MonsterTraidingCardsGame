package at.fhtw.bif3vz.swe.mtcg.if19b101.command;

public class CommandFactory {

    public static Command getCommand(String input) {
        switch(input){
            case "login": return getLogin();
            case "register": return getRegister();
            case "quit": return getQuit();
            case "info": return getInfo();
            case "aquire": return getAquire();
            case "deck": return getDeck();
            case "compare": return getCompare();
        }
        return getDefault();
    }

    public static Command getDefault(){
        return new DefaulfCommand();
    }

    public static Command getLogin(){
        return new LoginCommand();
    }

    public static Command getRegister(){
        return new RegisterCommand();
    }

    public static Command getQuit(){
        return new QuitCommand();
    }

    public static Command getInfo(){
        return new InfoCommand();
    }

    public static Command getAquire(){
        return new AquireCommand();
    }

    public static Command getDeck(){
        return new DeckCommand();
    }

    public static Command getCompare(){
        return new CompareCommand();
    }

}
