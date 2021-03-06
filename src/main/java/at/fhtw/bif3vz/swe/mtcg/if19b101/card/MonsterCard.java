package at.fhtw.bif3vz.swe.mtcg.if19b101.card;

import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;
import java.io.Serializable;

public class MonsterCard extends Card implements Serializable {
    private final MonsterType monsterType;

    public MonsterCard(float damage, ElementType elementType, MonsterType monsterType) {
        super(elementType+"-"+monsterType, damage, elementType);
        this.monsterType = monsterType;
    }

    public boolean isMonster(){
        return true;
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }
}
