package at.fhtw.bif3vz.swe.mtcg.if19b101.server;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.interfaces.PackOperations;

import java.io.Serializable;
import java.util.List;

public class Package implements PackOperations,Serializable {
    private List<Card> packageCards;

    public Package(){}

    //interface method
    public List<Card> getAllCards(){
        return this.packageCards;
    }
    //interface method
    public void addCards(List<Card> cards){
        this.packageCards = cards;
    }
}
