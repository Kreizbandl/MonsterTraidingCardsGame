package at.fhtw.bif3vz.swe.mtcg.if19b101.interfaces;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;

import java.util.List;

public interface PackOperations {

    List<Card> getAllCards();
    void addCards(List<Card> cards);

}
