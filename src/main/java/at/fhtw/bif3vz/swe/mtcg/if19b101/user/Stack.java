package at.fhtw.bif3vz.swe.mtcg.if19b101.user;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Stack {
    List<Card> stackCards = new ArrayList<>();

    public Stack(){}//ist zu beginn leer

    public List<Card> getStackCards() {//return all cards in stack
        return this.stackCards;
    }

    public List<Card> getBestFourCards(){//return 4 besten karten aus stack
        List<Card> bestFourCards = new ArrayList<>();

        for(int i = this.stackCards.size();i > this.stackCards.size()-4;i--){//ersten vier -> eigentlich besten vier (vergleich zwischen den karten fehlt)
            bestFourCards.add(this.stackCards.get(i));
        }

        return bestFourCards;
    }

    public void addCardsToStack(List<Card> stackCards){//füge karten zum stack hinzu
            this.stackCards.addAll(stackCards);
    }

    //public Card tradeCard(){return Card;}
}
