package at.fhtw.bif3vz.swe.mtcg.if19b101;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.MonsterCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.SpellCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;
import lombok.Builder;
import lombok.ToString;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Builder
@ToString
public class Main {
    public static void main(String[] args) {
        User user = new User("Manuel","1234");

        //create list of all cards
        List<Card> allCards = new ArrayList<>();
        //allCards.add(MonsterCard.builder().monsterType(MonsterType.DRAGON).build());//wieso kann ich nicht auf abrstract methoden zugreifen?????!!!!!
        allCards.add(new MonsterCard(3000, ElementType.NORMAL, MonsterType.ORK));
        allCards.add(new SpellCard(2000,ElementType.WATER));


        user.setStack(allCards);
        System.out.println(user.getStack());

        //System.out.println(monster.getName());
        for (Card card:allCards) {
            System.out.println(card.toString());
            System.out.println(card.cardString());
        }
        //System.out.println(user.getDeck());
    }
}
