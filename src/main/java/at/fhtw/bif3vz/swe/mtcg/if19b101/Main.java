package at.fhtw.bif3vz.swe.mtcg.if19b101;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.MonsterCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.SpellCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;
import at.fhtw.bif3vz.swe.mtcg.if19b101.server.Package;
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


        user.aquirePackage();
        user.aquirePackage();


        for (Card card:user.getStack()) {
            System.out.println(card.toString());
        }
        for (Card card:user.getDeck()) {
            System.out.println(card.toString());
        }

        System.out.println(user.everyThingToString());
    }
}
