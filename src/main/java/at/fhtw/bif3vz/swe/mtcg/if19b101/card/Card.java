package at.fhtw.bif3vz.swe.mtcg.if19b101.card;

import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import lombok.Data;

@Data
public abstract class Card {
    protected String name;
    protected int damage;//damage anhand von namen setzen
    protected ElementType elementType;

    public Card(String name, int damage, ElementType elementType) {
        this.name = name;
        this.damage = damage;
        this.elementType = elementType;
    }

    public String cardString(){
        return "("+name+": " + damage +")";
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", damage=" + damage +
                ", elementType=" + elementType +
                '}';
    }
}
