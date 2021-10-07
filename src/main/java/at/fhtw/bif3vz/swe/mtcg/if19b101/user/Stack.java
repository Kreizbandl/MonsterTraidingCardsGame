package at.fhtw.bif3vz.swe.mtcg.if19b101.user;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Stack {
    List<Card> stackCards;

    public Stack(List<Card> stackCards) {
        this.stackCards = stackCards;
    }

    public List<Card> getStackCards() {
        return stackCards;
    }

    public void setStackCards(List<Card> stackCards) {
        this.stackCards = stackCards;
    }
}
