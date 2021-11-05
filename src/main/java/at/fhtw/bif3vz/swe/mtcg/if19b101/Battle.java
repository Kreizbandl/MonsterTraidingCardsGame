package at.fhtw.bif3vz.swe.mtcg.if19b101;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.gamelogic.Gamelogic;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.Deck;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;

import java.util.List;
import java.util.Random;

public class Battle {
    //OLD
    /*private final User u1;
    private final User u2;*/
    //OLD

    //NEW
    private List<Card> deckUser1;
    private List<Card> deckUser2;
    //NEW

    //OLD
    /*public Battle(User u1, User u2) {//es würde reichen nur die decks zu übergeben, aber probleme da getDeckofuser List<Card>
        this.u1 = u1;
        this.u2 = u2;
    }*/
    //OLD

    //NEW
    public Battle(User u1, User u2){
        this.deckUser1 = u1.getDeckOfUser();
        this.deckUser2 = u2.getDeckOfUser();
    }
    //NEW

    public void showBattle(){
        //roundWinner 1, 0, -1
        int roundWinner, countRounds = 0;
        //select a random card (no real card-deck-gameplay, no loop)
        Random rand = new Random();
        int randCardOfDeck1, randCardOfDeck2;
        //remember size of deck to avoid errors

        //include GameLogic
        Gamelogic gamelogic = new Gamelogic();

        do{
            //show decks
            System.out.println("-------------------Round " + countRounds + "-------------------");

            //NEW
            System.out.println("DECK1: " + deckUser1.toString());
            System.out.println("DECK1: " + deckUser2.toString());
            //NEW

            //OLD
            //System.out.println("DECK1: " + u1.getDeckOfUser().toString());
            //System.out.println("DECK2: " + u2.getDeckOfUser().toString() + "\n");
            //OLD

            //determine size of deck
            //sizeDeckUser1 = this.u1.getDeckOfUser().size();
            //sizeDeckUser2 = this.u2.getDeckOfUser().size();

                //quit if one deck is empty
                /*if(this.u1.getDeckOfUser().size() == 0){
                    winner = -1;
                    break;
                }else if(this.u2.getDeckOfUser().size() == 0){
                    winner = 1;
                    break;
                }*/
                if(this.deckUser1.isEmpty() || this.deckUser2.isEmpty()){
                    break;
                }


            //pick rand card of deck (regarding deck size)
            randCardOfDeck1 = rand.nextInt(this.deckUser1.size());
            randCardOfDeck2 = rand.nextInt(this.deckUser2.size());

                //which cards are chosen
                System.out.println( deckUser1.get(randCardOfDeck1)
                        + " VS "
                        + deckUser2.get(randCardOfDeck2));

            //compare cards
            //OLD
            /*idxOfWinnerCard = this.compareTwoCards( u1.getDeckOfUser().get(randCardOfDeck1),
                                                    u2.getDeckOfUser().get(randCardOfDeck2));*/
            //OLD
            //NEW
            /*idxOfWinnerCard = this.compareDamage(
                    gamelogic.getFirstCurrentDamage(deckUser1.get(randCardOfDeck1), deckUser2.get(randCardOfDeck2)),
                    gamelogic.getFirstCurrentDamage(deckUser2.get(randCardOfDeck2), deckUser1.get(randCardOfDeck1)));*/
            //NEW
            //NEWER
            roundWinner = Integer.compare(
                    gamelogic.getFirstCurrentDamage(deckUser1.get(randCardOfDeck1), deckUser2.get(randCardOfDeck2)),
                    gamelogic.getFirstCurrentDamage(deckUser2.get(randCardOfDeck2), deckUser1.get(randCardOfDeck1)));
            //NEWER

                //which card won, swap simulation
                if(roundWinner == 1){
                    System.out.println("         A           <<         V           \n");

                    //NEW
                    deckUser1.add(deckUser2.get(randCardOfDeck2));
                    deckUser2.remove(deckUser2.get(randCardOfDeck2));
                    //NEW

                    //OLD
                    /*u1.testAddCard(u2.getDeckOfUser().get(randCardOfDeck2));
                    u2.testRemoveCard(u2.getDeckOfUser().get(randCardOfDeck2));*/
                    //OLD

                }else if(roundWinner == -1){
                    System.out.println("         V           >>         A           \n");

                    //NEW
                    deckUser2.add(deckUser1.get(randCardOfDeck1));
                    deckUser1.remove(deckUser1.get(randCardOfDeck1));
                    //NEW

                    //OLD
                    /*u2.testAddCard(u1.getDeckOfUser().get(randCardOfDeck1));
                    u1.testRemoveCard(u1.getDeckOfUser().get(randCardOfDeck1));*/
                    //OLD

                }else{
                    System.out.println("         .          ----        .           \n");
                }

            countRounds++;
        }while(countRounds < 20);

        //print winner
        if(this.deckUser1.isEmpty()){
            System.out.println("             WINNER IS USER2");
        }else if(this.deckUser2.isEmpty()){
            System.out.println("             WINNER IS USER1");
        }else{
            System.out.println("                NO WINNER");
        }
    }

    //OLD
    /*private int compareDamage(int d1, int d2){
        if(d1 > d2){
            return 1;
        }else if(d1 < d2){
            return -1;
        }else{
            return 0;
        }
    }*/
    //OLD
}