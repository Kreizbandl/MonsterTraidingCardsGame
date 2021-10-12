package at.fhtw.bif3vz.swe.mtcg.if19b101;

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
    }
}
