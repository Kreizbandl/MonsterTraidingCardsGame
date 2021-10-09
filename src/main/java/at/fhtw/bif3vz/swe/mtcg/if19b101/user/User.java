package at.fhtw.bif3vz.swe.mtcg.if19b101.user;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.server.Server;

import java.util.List;

public class User {
    private final String username;
    private final String password;
    private int coins;
        private Stack stack;
        private Deck deck;
        private Server server;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.coins = 20;
            this.stack = new Stack();
            this.deck = new Deck();
            this.server = new Server();
    }

    public List<Card> getStackOfUser() {//return alle karten als list
        return this.stack.getStackCards();
    }

    public List<Card> getDeckOfUser() {//return alle karten als list
        return this.deck.getDeckCards();
    }

    public void aquirePackage(){
        this.stack.addCardsToStack(this.server.getPackageFromServer().getPackageCards());
        this.addBestCardsToDeck();
    }
        private void addBestCardsToDeck(){
            this.deck.addCardsToDeck(this.stack.getBestFourCardsOfStack());
        }

    public void testAdd(Card card){
        this.deck.addSingleCard(card);
    }//bier 2

    public void testRemove(Card card){
        this.deck.removeSingleCard(card);
    }//bier 2

    //public void loginServer(){}

    /*public String everyThingToString(){
        return "User: name=" + this.username +
                ", pwd=" + this.password +
                ", coins=" + this.coins +
                ", stack: " + stack.getStackCards().toString() + "\n" +
                ", deck: " + deck.getDeckCards().toString() + "\\n";
    }*/
}
