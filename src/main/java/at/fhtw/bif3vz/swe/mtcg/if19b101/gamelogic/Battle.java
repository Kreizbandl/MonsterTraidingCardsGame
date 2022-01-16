package at.fhtw.bif3vz.swe.mtcg.if19b101.gamelogic;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.gamelogic.Gamelogic;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;
import java.util.Collections;
import java.util.List;

public class Battle {

    private final List<Card> deckUser1;
    private final List<Card> deckUser2;
    private final Gamelogic gamelogic = new Gamelogic();

    public Battle(User u1, User u2){
        //only use decks for battle
        deckUser1 = u1.getDeckOfUser();
        deckUser2 = u2.getDeckOfUser();
    }

    public int showBattle(){
        //roundWinner 1, 0, -1
        int roundWinner, roundCount = 1;

            do{
                //shuffle decks
                Collections.shuffle(deckUser1);
                Collections.shuffle(deckUser2);

                //show decks
                System.out.println("-------------------Round " + roundCount + "-------------------");
                    System.out.println("DECK1: " + deckUser1.toString());
                    System.out.println("DECK2: " + deckUser2.toString());

                //check/print winner
                if(deckUser1.isEmpty()){
                    System.out.println("             WINNER IS PLAYER 2");
                }else if(deckUser2.isEmpty()){
                    System.out.println("             WINNER IS PLAYER 1");
                }/*else{
                    System.out.println("                NO WINNER");
                }*/

                if(deckUser1.isEmpty() || deckUser2.isEmpty()){
                    break;
                }

                //little round overview
                System.out.println(
                        "Player 1 -> " +
                        deckUser1.get(0) +
                        " VS " +
                        deckUser2.get(0) +
                        " <- Player 2"
                );

                //which card won (1,0,-1)
                roundWinner = Float.compare(
                        gamelogic.getFirstCurrentDamage(deckUser1.get(0), deckUser2.get(0)),
                        gamelogic.getFirstCurrentDamage(deckUser2.get(0), deckUser1.get(0)));

                    //which card won, swap simulation
                    if(roundWinner == 1){
                        System.out.println("         A           <<         V           \n");
                            deckUser1.add(deckUser2.get(0));
                            deckUser2.remove(0);
                    }else if(roundWinner == -1){
                        System.out.println("         V           >>         A           \n");
                            deckUser2.add(deckUser1.get(0));
                            deckUser1.remove(0);
                    }else{
                        System.out.println("         .          ----        .           \n");
                    }

                roundCount++;
            }while(roundCount <= 100);

            if(deckUser1.isEmpty()){
                return 1;
            }else if(deckUser2.isEmpty()){
                return 2;
            }else{
                return 0;
            }
    }
}