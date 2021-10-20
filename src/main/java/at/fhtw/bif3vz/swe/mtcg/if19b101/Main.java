package at.fhtw.bif3vz.swe.mtcg.if19b101;

import at.fhtw.bif3vz.swe.mtcg.if19b101.gamelogic.Gamelogic;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Manuel","1234");
        User user2 = new User("Franz","0000");
            Battle battle = new Battle(user1,user2);

            user1.aquirePackage();
            user1.aquirePackage();
            user2.aquirePackage();
            user2.aquirePackage();

        System.out.println("STACK1: " + user1.getStackOfUser().toString());
        System.out.println("STACK2: " + user2.getStackOfUser().toString() + "\n");

        System.out.println("-------------------FIGHT!!!-------------------");
            battle.showBattle();
        System.out.println("--------------------END-----------------------");

        //TEST new gameLogic
        /*Gamelogic gLogic = new Gamelogic();
            int d1, d2;
            for(int i = 0;i < 10;i++){
                d1 = gLogic.getFirstCurrentDamage(user1.getStackOfUser().get(i),user2.getStackOfUser().get(i));
                System.out.println("new damage card 1: " + d1);
                d2 = gLogic.getFirstCurrentDamage(user2.getStackOfUser().get(i),user1.getStackOfUser().get(i));
                System.out.println("new damage card 2: " + d2);
            }*/
    }
}
