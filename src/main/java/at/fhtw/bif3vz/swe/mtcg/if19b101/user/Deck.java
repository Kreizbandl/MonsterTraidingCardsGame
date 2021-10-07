package at.fhtw.bif3vz.swe.mtcg.if19b101.user;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    List<Card> fourBestCards = new ArrayList<>();

    public Deck(){}//deck ist zu beginn leer

    public void addBestCardsToDeck(List<Card> bestCards){//füge besten karten hinzu
        this.fourBestCards.addAll(bestCards);
    }

    public List<Card> getDeckCards(){
        return this.fourBestCards;
    }
}
