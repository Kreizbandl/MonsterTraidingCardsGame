package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.battle;

import at.fhtw.bif3vz.swe.mtcg.if19b101.gamelogic.Battle;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.MonsterCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.SpellCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.CardDB;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.DatabaseOperations;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.handlers.Handler;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BattleHandler extends Handler {

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        System.out.println("-> BATTLE");
        //request for battle

        //login for battle
        String token = getAuthorizationToken(exchange);
        if(!isLogged(token)){
            System.out.println("ERR: user isn't logged in");
            exchange.sendResponseHeaders(StatusCode.UNAUTHORIZED.getCode(), -1);
        }else{
            int battleId = DatabaseOperations.loginForBattle(token);
            if(battleId == 0){
                System.out.println("Waiting for player...");
                exchange.sendResponseHeaders(StatusCode.OK.getCode(), 0);
            }else if(battleId == -1){
                System.out.println("ERR: You cannot play against yourself!");
                exchange.sendResponseHeaders(StatusCode.FORBIDDEN.getCode(), 0);
            }else{
                System.out.println("ready for battle");
                //execute battle
                List<String> tokens = DatabaseOperations.readBattleTokensFromDatabase(battleId);
                List<String> names = new ArrayList<>();
                    names.add(DatabaseOperations.readUsernameFromDatabase(tokens.get(0)));
                    names.add(DatabaseOperations.readUsernameFromDatabase(tokens.get(1)));
                System.out.println("OPPONENTS: " + names);
                System.out.println("TOKENS: " + tokens);

                User u1 = new User(names.get(0));
                List<CardDB> deckTestCards1 = DatabaseOperations.readDeckByToken(tokens.get(0));
                List<Card> deckCards1 = new ArrayList<>();
                for(CardDB item: deckTestCards1){
                    deckCards1.add(mapCardDBToCard(item));
                }
                u1.addCardsToDeck(deckCards1);

                User u2 = new User(names.get(1));
                List<CardDB> deckTestCards2 = DatabaseOperations.readDeckByToken(tokens.get(1));
                List<Card> deckCards2 = new ArrayList<>();
                for(CardDB item: deckTestCards2){
                    deckCards2.add(mapCardDBToCard(item));
                }
                u2.addCardsToDeck(deckCards2);

                Battle battle = new Battle(u1, u2);
                int winner = battle.showBattle();
                if(winner == 1){
                    DatabaseOperations.updateELO(tokens.get(0),1);
                    DatabaseOperations.updateELO(tokens.get(1),2);
                }else if(winner == 2){
                    DatabaseOperations.updateELO(tokens.get(0),2);
                    DatabaseOperations.updateELO(tokens.get(1),1);
                }
            }
        }

    }

    private Card mapCardDBToCard(CardDB card){
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
