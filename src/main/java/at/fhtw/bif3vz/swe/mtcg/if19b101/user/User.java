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
        return this.stack.getAllCards();
    }

    public List<Card> getDeckOfUser() {//return alle karten als list
        return this.deck.getAllCards();
    }

    public void aquirePackage(){
        //login/register to server
        this.stack.addCards(this.server.getPackageFromServer().getAllCards());
        //this.addBestCardsToDeck();//besser extra, manage deck
    }
    public void addBestCardsToDeck(){
            this.deck.addCards(this.stack.getBestFourCardsOfStack());
        }
}
