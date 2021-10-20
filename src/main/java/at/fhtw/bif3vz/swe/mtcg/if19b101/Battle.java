package at.fhtw.bif3vz.swe.mtcg.if19b101;

import at.fhtw.bif3vz.swe.mtcg.if19b101.gamelogic.Gamelogic;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;

import java.util.Random;

public class Battle {
    private final User u1;
    private final User u2;

    public Battle(User u1, User u2) {
        this.u1 = u1;
        this.u2 = u2;
    }

    public void showBattle(){
        int idxOfWinnerCard, countRounds = 0;
        //select a random card (no real card-deck-gameplay, no loop)
        Random rand = new Random();
        int randCardOfDeck1, randCardOfDeck2;
        //remember size of deck to avoid errors
        //int sizeDeckUser1, sizeDeckUser2;
        //winner of game 1, 0, -1
        //int winner = 0;

        //include GameLogic
        Gamelogic gamelogic = new Gamelogic();

        do{
            //show decks
            System.out.println("-------------------Round " + countRounds + "-------------------");
            System.out.println("DECK1: " + u1.getDeckOfUser().toString());
            System.out.println("DECK2: " + u2.getDeckOfUser().toString() + "\n");

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
                if(this.u1.getDeckOfUser().isEmpty() || this.u2.getDeckOfUser().isEmpty()){
                    break;
                }

            //pick rand card of deck (regarding deck size)
            randCardOfDeck1 = rand.nextInt(this.u1.getDeckOfUser().size());
            randCardOfDeck2 = rand.nextInt(this.u2.getDeckOfUser().size());

                //which cards are chosen
                System.out.println( u1.getDeckOfUser().get(randCardOfDeck1)
                        + " VS "
                        + u2.getDeckOfUser().get(randCardOfDeck2));

            //compare cards
            //OLD
            /*idxOfWinnerCard = this.compareTwoCards( u1.getDeckOfUser().get(randCardOfDeck1),
                                                    u2.getDeckOfUser().get(randCardOfDeck2));*/
            //OLD
            //NEW
            idxOfWinnerCard = this.compareDamage(
                    gamelogic.getFirstCurrentDamage(u1.getDeckOfUser().get(randCardOfDeck1), u2.getDeckOfUser().get(randCardOfDeck2)),
                    gamelogic.getFirstCurrentDamage(u2.getDeckOfUser().get(randCardOfDeck2), u1.getDeckOfUser().get(randCardOfDeck1)));
            //NEW

                //which card won
                if(idxOfWinnerCard == 1){
                    System.out.println("         A           <<         V           \n");
                    u1.testAddCard(u2.getDeckOfUser().get(randCardOfDeck2));
                    u2.testRemoveCard(u2.getDeckOfUser().get(randCardOfDeck2));
                }else if(idxOfWinnerCard == -1){
                    System.out.println("         V           >>         A           \n");
                    u2.testAddCard(u1.getDeckOfUser().get(randCardOfDeck1));
                    u1.testRemoveCard(u1.getDeckOfUser().get(randCardOfDeck1));
                }else{
                    System.out.println("         .          ----        .           \n");
                }

            countRounds++;
        }while(countRounds < 20);

        //print winner
        if(this.u1.getDeckOfUser().isEmpty()){
            System.out.println("             WINNER IS USER2");
        }else if(this.u2.getDeckOfUser().isEmpty()){
            System.out.println("             WINNER IS USER1");
        }else{
            System.out.println("                NO WINNER");
        }
    }

    private int compareDamage(int d1, int d2){
        if(d1 > d2){
            return 1;
        }else if(d1 < d2){
            return -1;
        }else{
            return 0;
        }
    }
}
//two monster fight: only damage compare (no element effects)

//spell attack:
//- effective -> double damage (water->fire, fire->normal, normal->water)
//- not effective -> half damage (---"---)
//- no effect -> same element (compare damage)

//special attack-situations:
//- Goblins are too afraid of Dragons to attack (Dragon->Goblin)
//- Wizzards are able to control Orks (Wizzard->Ork)
//- the FireElves know Dragons since they were little and can evade their attacks (FireElf->Dargon)
//- the armour of Knights is so heavy that WaterSpells make them drown (WaterSpell->Knight)
//- the Kraken is immune against spells (Kraken->AnySpell)
