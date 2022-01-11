package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.battle;

import at.fhtw.bif3vz.swe.mtcg.if19b101.Battle;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.MonsterCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.SpellCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCardDB;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.Deck;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BattleHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> BATTLE");
        //request for battle

        //login for battle
        int status = DatabaseOperations.addUserToBattle(exchange.getRequestHeaders().get("Authorization").get(0));
        System.out.println(status);
        //execute battle
        if(status == 0){
            System.out.println("Waiting for player...");
        }else if(status == -1){
            System.out.println("You cannot play against yourself!");
        }else{
            List<String> names = DatabaseOperations.readBattleUsernamesFromDatabase();
            List<String> token = DatabaseOperations.readBattleTokensFromDatabase();
            System.out.println("OPPONENTS: " + names);
            System.out.println("TOKENS: " + token);

            //here
            User u1 = new User(names.get(0));
            List<TestCardDB> deckTestCards1 = DatabaseOperations.readDeckFromDatabase(token.get(0));
            List<Card> deckCards1 = new ArrayList<>();
            for(TestCardDB item: deckTestCards1){
                deckCards1.add(mapTestCardDBToCard(item));
            }
            u1.addCardsToDeck(deckCards1);

            User u2 = new User(names.get(1));
            List<TestCardDB> deckTestCards2 = DatabaseOperations.readDeckFromDatabase(token.get(1));
            List<Card> deckCards2 = new ArrayList<>();
            for(TestCardDB item: deckTestCards2){
                deckCards2.add(mapTestCardDBToCard(item));
            }
            u2.addCardsToDeck(deckCards2);
            //here

            Battle battle = new Battle(u1, u2);
            battle.showBattle();
        }

    }

    private Card mapTestCardDBToCard(TestCardDB card){
        String name = card.getName();
        float damage = card.getDamage();

        ElementType etype;
        if(name.contains("Water")){
            etype = ElementType.WATER;
        }else if(name.contains("Fire")){
            etype = ElementType.FIRE;
        }else{
            etype = ElementType.REGULAR;
        }
        if(name.contains("Spell")){
            return new SpellCard(damage, etype);
        }else{
            MonsterType mtype;
            if(name.contains("Goblin")){
                mtype = MonsterType.GOBLIN;
            }else if(name.contains("Dragon")){
                mtype = MonsterType.DRAGON;
            }else if(name.contains("Wizzard")){
                mtype = MonsterType.WIZZARD;
            }else if(name.contains("Ork")){
                mtype = MonsterType.ORK;
            }else if(name.contains("Knight")){
                mtype = MonsterType.KNIGHT;
            }else if(name.contains("Kraken")){
                mtype = MonsterType.KRAKEN;
            }else if(name.contains("Elf")){
                mtype = MonsterType.ELF;
            }else{
                mtype = MonsterType.TROLL;
            }
            return new MonsterCard(damage, etype, mtype);
        }
    }

}
