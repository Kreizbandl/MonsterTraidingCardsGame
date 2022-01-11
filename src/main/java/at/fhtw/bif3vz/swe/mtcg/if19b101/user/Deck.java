package at.fhtw.bif3vz.swe.mtcg.if19b101.user;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.interfaces.PackOperations;
import java.util.ArrayList;
import java.util.List;

public class Deck implements PackOperations {
    private List<Card> deckCards = new ArrayList<>();

    public Deck(){}//deck ist zu beginn leer

    //interface method
    public List<Card> getAllCards(){
        return this.deckCards;
    }
    //interface method
    public void addCards(List<Card> bestCards){
        this.deckCards.clear();
        this.deckCards.addAll(bestCards);
    }
}
