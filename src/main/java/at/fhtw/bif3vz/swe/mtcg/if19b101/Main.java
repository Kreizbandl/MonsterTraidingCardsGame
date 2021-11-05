package at.fhtw.bif3vz.swe.mtcg.if19b101;

import at.fhtw.bif3vz.swe.mtcg.if19b101.gamelogic.Gamelogic;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;

import java.io.*;//client server
import java.net.*;//client server

public class Main {
    //lets try something
    //main as client
    /*public static void main(String[] args){
        try{
            Socket s = new Socket("localhost",6666);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("Hello Server");
            dout.flush();
            dout.close();
            s.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }*/

    public static void main(String[] args) {
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
