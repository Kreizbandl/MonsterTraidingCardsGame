package at.fhtw.bif3vz.swe.mtcg.if19b101;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.MonsterCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Manuel","1234");
        User user2 = new User("Franz","0000");

        user1.aquirePackage();
        user1.aquirePackage();
        user2.aquirePackage();
        user2.aquirePackage();

        System.out.println(user1.getStackOfUser().toString() + "\n");
        System.out.println(user2.getStackOfUser().toString() + "\n");

        Battle battle = new Battle(user1,user2);
        battle.showBattle();

        //bier 2
        System.out.println(user1.getDeckOfUser().toString() + "\n");
        MonsterCard monTest = new MonsterCard(13, ElementType.WATER, MonsterType.DRAGON);
        user1.testAdd(monTest);
        System.out.println(user1.getDeckOfUser().toString() + "\n");
        user1.testRemove(monTest);
        System.out.println(user1.getDeckOfUser().toString() + "\n");


    }
}
