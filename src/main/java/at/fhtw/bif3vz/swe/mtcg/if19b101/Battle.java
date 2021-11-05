package at.fhtw.bif3vz.swe.mtcg.if19b101;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.gamelogic.Gamelogic;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.Deck;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Battle {

    private List<Card> deckUser1;
    private List<Card> deckUser2;
    private Gamelogic gamelogic = new Gamelogic();

    public Battle(User u1, User u2){
        deckUser1 = u1.getDeckOfUser();
        deckUser2 = u2.getDeckOfUser();
    }

    public void showBattle(){
        //roundWinner 1, 0, -1
        int roundWinner, roundCount = 1;
        //select a random card (no real card-deck-gameplay, no loop)

            do{
                //shuffle decks
                Collections.shuffle(deckUser1);
                Collections.shuffle(deckUser2);

                //show decks
                System.out.println("-------------------Round " + roundCount + "-------------------");
                    System.out.println("DECK1: " + deckUser1.toString());
                    System.out.println("DECK2: " + deckUser2.toString());

                    if(deckUser1.isEmpty() || deckUser2.isEmpty()){
                        break;
                    }

                System.out.println(
                        "Player 1 -> " +
                        deckUser1.get(0) +
                        " VS " +
                        deckUser2.get(0) +
                        " <- Player 2"
                );

                //which card won (1,0,-1)
                roundWinner = Integer.compare(
                        gamelogic.getFirstCurrentDamage(deckUser1.get(0), deckUser2.get(0)),
                        gamelogic.getFirstCurrentDamage(deckUser2.get(0), deckUser1.get(0)));

                    //which card won, swap simulation
                    if(roundWinner == 1){
                        System.out.println("         A           <<         V           \n");
                            deckUser1.add(deckUser2.get(0));
                            //deckUser2.remove(deckUser2.get(0));
                        deckUser2.remove(0);
                    }else if(roundWinner == -1){
                        System.out.println("         V           >>         A           \n");
                            deckUser2.add(deckUser1.get(0));
                            //deckUser1.remove(deckUser1.get(0));
                        deckUser1.remove(0);
                    }else{
                        System.out.println("         .          ----        .           \n");
                    }

                roundCount++;
            }while(roundCount <= 50);

        //print winner
        if(deckUser1.isEmpty()){
            System.out.println("             WINNER IS PLAYER 2");
        }else if(deckUser2.isEmpty()){
            System.out.println("             WINNER IS PLAYER 1");
        }else{
            System.out.println("                NO WINNER");
        }
    }
}