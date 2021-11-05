package at.fhtw.bif3vz.swe.mtcg.if19b101.user;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.interfaces.PackOperations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Stack implements PackOperations {
    private List<Card> stackCards = new ArrayList<>();

    public Stack(){}//ist zu beginn leer

    //interface method
    public List<Card> getAllCards(){
        return this.stackCards;
    }
    //interface method
    public void addCards(List<Card> cards){
        this.stackCards.addAll(cards);
    }

    public List<Card> getBestFourCardsOfStack(){
        List<Card> allStackCards = this.stackCards;
        Collections.sort(allStackCards, new Comparator<Card>()
                {
                    public int compare(Card c1, Card c2)
                    {
                        return Integer.valueOf(c2.getDamage()).compareTo(c1.getDamage());
                    }
                }
        );
        return allStackCards.subList(0,4);
    }
}
