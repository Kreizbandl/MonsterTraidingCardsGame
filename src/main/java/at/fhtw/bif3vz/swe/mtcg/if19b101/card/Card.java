package at.fhtw.bif3vz.swe.mtcg.if19b101.card;

import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;

public abstract class Card {
    protected String name;
    protected int damage;
    protected ElementType elementType;

    public Card(String name, int damage, ElementType elementType) {
        this.name = name;
        this.damage = damage;
        this.elementType = elementType;
    }

    public int getDamage() {
        return this.damage;
    }

    public ElementType getElementType() {
        return this.elementType;
    }

    /*public String getName(){
        return this.name;
    }*/

    @Override
    public String toString() {
        return "Card{" + name + ", " + damage + "}";
    }
}
