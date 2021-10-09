package at.fhtw.bif3vz.swe.mtcg.if19b101.server;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.MonsterCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.SpellCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {

    public Server() {
    }

    private Card generateRandomCard(){
        Random rand = new Random();

        int[] damageArray = {
                10,
                20,
                40
        };
            int dInt = damageArray[rand.nextInt(damageArray.length)];

        ElementType[] eleTypeArray = {
                ElementType.WATER,
                ElementType.FIRE,
                ElementType.NORMAL
        };
            ElementType eType = eleTypeArray[rand.nextInt(eleTypeArray.length)];

        if(rand.nextInt(2) == 0){
            return new SpellCard(dInt, eType);
        }else{
            MonsterType[] monTypeArray = {
                    MonsterType.DRAGON,
                    MonsterType.ELF,
                    MonsterType.GOBLIN,
                    MonsterType.ORK,
                    MonsterType.KNIGHT,
                    MonsterType.KRAKEN,
                    MonsterType.WIZZARD,
                    MonsterType.TROLL
            };
                MonsterType mType = monTypeArray[rand.nextInt(monTypeArray.length)];
            return new MonsterCard(dInt, eType, mType);
        }
    }

    public Package getPackageFromServer(){
        List<Card> packageCards = new ArrayList<>();
        for(int i = 0;i < 5;i++){
            packageCards.add(this.generateRandomCard());
        }
        return new Package(packageCards);
    }
}
