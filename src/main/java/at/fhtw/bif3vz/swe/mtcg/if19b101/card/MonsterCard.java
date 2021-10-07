package at.fhtw.bif3vz.swe.mtcg.if19b101.card;

import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;

public class MonsterCard extends Card{
    MonsterType monsterType;

    public MonsterCard(int damage, ElementType elementType, MonsterType monsterType) {
        super(elementType+"-"+monsterType, damage, elementType);
        this.monsterType = monsterType;
    }

    @Override
    public String toString() {
        return "MonsterCard{" +
                "name='" + name + '\'' +
                ", damage=" + damage +
                ", elementType=" + elementType +
                ", monsterType=" + monsterType +
                '}';
    }
}
