package at.fhtw.bif3vz.swe.mtcg.if19b101.user;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.server.Package;
import at.fhtw.bif3vz.swe.mtcg.if19b101.server.Server;

import java.util.ArrayList;
import java.util.List;

public class User {
    String username;
    String password;
    int coins;
    Stack stack;
    Deck deck;
    Server server;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.coins = 20;
        this.stack = new Stack();
        this.deck = new Deck();
        this.server = new Server();
    }

    public List<Card> getStack() {//return alle karten als list
        return this.stack.getStackCards();
    }

    protected void giveBestCardsToDeck(){
        this.deck.addBestCardsToDeck(this.stack.getBestFourCards());
    }

    public List<Card> getDeck() {//return alle karten als list
        return this.deck.getDeckCards();
    }

    /*public void setStack(List<Card> stackCards) {
        this.stack.setStackCards(stackCards);
    }*/


    public void aquirePackage(){//hole ein package mit 4 karten vom server und füge diese dem stack hinzu
        this.stack.addCardsToStack(this.server.getPackageFrom().getPackageCards());
        this.giveBestCardsToDeck();
    }

    public void loginServer(){

    }

    public String everyThingToString(){
        return "User: name=" + this.username +
                ", pwd=" + this.password +
                ", coins=" + this.coins +
                ", stack: " + stack.getStackCards().toString() + "\n" +
                ", deck: " + deck.getDeckCards().toString() + "\\n";
    }
}
