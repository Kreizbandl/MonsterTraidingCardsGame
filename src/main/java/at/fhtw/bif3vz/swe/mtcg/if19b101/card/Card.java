package at.fhtw.bif3vz.swe.mtcg.if19b101.card;

import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.interfaces.CardOperations;

import java.io.Serializable;

public abstract class Card implements CardOperations, Serializable {
    protected String name;
    protected float damage;
    protected ElementType elementType;

    public Card(String name, float damage, ElementType elementType) {
        this.name = name;
        this.damage = damage;
        this.elementType = elementType;
    }

    public float getDamage() {
        return this.damage;
    }

    public ElementType getElementType() {
        return this.elementType;
    }

    @Override
    public String toString() {
        return "{" + name + ", " + damage + "}";
    }
}
