package at.fhtw.bif3vz.swe.mtcg.if19b101.user;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import java.util.List;

public class User {
    private String username;
    private final Stack stack;
    private final Deck deck;

    public User(){
        this.stack = new Stack();
        this.deck = new Deck();
    }

    public User(String username){
        this.username = username;
        this.stack = new Stack();
        this.deck = new Deck();
    }

    public List<Card> getStackOfUser() {//return alle karten als list
        return this.stack.getAllCards();
    }

    /*public void addCardsToStack(List<Card> cards){
        this.stack.addCards(cards);
    }*/

    public List<Card> getDeckOfUser() {
        return this.deck.getAllCards();
    }

    public void addCardsToDeck(List<Card> cards){
        this.deck.addCards(cards);
    }

    /*public void addBestCardsToDeck(){
        this.deck.addCards(this.stack.getBestFourCardsOfStack());
    }*/

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", stack=" + getStackOfUser().toString() +
                "}";
    }
}
