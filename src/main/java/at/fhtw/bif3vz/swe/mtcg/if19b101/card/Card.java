package at.fhtw.bif3vz.swe.mtcg.if19b101.card;

import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.interfaces.CardOperations;

import java.io.Serializable;

public abstract class Card implements CardOperations, Serializable {
    protected String name;
    protected int damage;
    protected ElementType elementType;

    //interface method
    public Card(String name, int damage, ElementType elementType) {
        this.name = name;
        this.damage = damage;
        this.elementType = elementType;
    }
    //interface method
    public int getDamage() {
        return this.damage;
    }
    //interface method
    public ElementType getElementType() {
        return this.elementType;
    }
    //interface method
    /*public String getName(){
        return this.name;
    }*/

    @Override
    public String toString() {
        return "{" + name + ", " + damage + "}";
    }
}
