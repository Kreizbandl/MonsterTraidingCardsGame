package at.fhtw.bif3vz.swe.mtcg.if19b101.user;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Stack {
    private List<Card> stackCards = new ArrayList<>();

    public Stack(){}//ist zu beginn leer

    public List<Card> getStackCards() {
        return this.stackCards;
    }

    public void addCardsToStack(List<Card> stackCards){
        this.stackCards.addAll(stackCards);
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

    //public Card tradeCard(){return Card;}
}
