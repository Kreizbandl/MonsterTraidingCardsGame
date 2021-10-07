package at.fhtw.bif3vz.swe.mtcg.if19b101.server;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.MonsterCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.SpellCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Server {// store all cards here, give 4 to package
    List<Card> allExistingCards = new ArrayList<>();

    public Server() {//wenn ein server object erstellt -> erzeuge alle möglichen karten
        this.allExistingCards.add(new SpellCard(35,ElementType.FIRE));
        this.allExistingCards.add(new SpellCard(77,ElementType.WATER));
        this.allExistingCards.add(new MonsterCard(65,ElementType.NORMAL,MonsterType.GOBLIN));
        this.allExistingCards.add(new MonsterCard(98,ElementType.FIRE,MonsterType.DRAGON));
    }

    public Package getPackageFrom(){//erzeugte ein package mit 4 zufällig gezogenen karten
        List<Card> fourCards = new ArrayList<>();
        for(int i = 0;i < 4;i++){
            fourCards.add(this.allExistingCards.get(i));//random fehlt
        }
        Package pack = new Package(fourCards);
        return pack;
    }
}
