package at.fhtw.bif3vz.swe.mtcg.if19b101.user;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
//import at.fhtw.bif3vz.swe.mtcg.if19b101.server.Client;
import at.fhtw.bif3vz.swe.mtcg.if19b101.server.Server;

import java.io.IOException;
import java.util.List;
//part of server
public class User {
    private String username;
    private String password;
    private int coins;
        private Stack stack;
        private Deck deck;
        private Server server;//statt server -> client
        //private Client client;

    public User(){
        this.coins = 20;
        this.stack = new Stack();
        this.deck = new Deck();
    }

    public User(String username, String password) throws IOException {
        this.username = username;
        this.password = password;
        this.coins = 20;
            this.stack = new Stack();
            this.deck = new Deck();
            this.server = new Server();
            //this.client = new Client();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //interne Methoden
    public List<Card> getStackOfUser() {//return alle karten als list
        return this.stack.getAllCards();
    }

    public void addCardsToStack(List<Card> cards){
        this.stack.addCards(cards);
    }

    public List<Card> getDeckOfUser() {//return alle karten als list
        return this.deck.getAllCards();
    }

    public void addBestCardsToDeck(){
        this.deck.addCards(this.stack.getBestFourCardsOfStack());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", coins=" + coins +
                ", stack=" + getStackOfUser().toString() +
                "}";
    }

    //server abfragen
    public void aquirePackage(){
        //login/register to server
        this.coins -= 5;
        this.stack.addCards(this.server.getPackageFromServer().getAllCards());//statt direkt server. ... client-server verbindung
        //this.addBestCardsToDeck();//besser extra, manage deck
    }
}
