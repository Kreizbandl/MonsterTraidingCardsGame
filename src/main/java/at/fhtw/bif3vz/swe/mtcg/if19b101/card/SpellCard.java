package at.fhtw.bif3vz.swe.mtcg.if19b101.card;


import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;

import java.io.Serializable;

public class SpellCard extends Card implements Serializable {
    public SpellCard(int damage, ElementType elementType) {
        super(elementType+"-SPELL", damage, elementType);
    }

    public boolean isMonster(){
        return false;
    }

        //tut weh...
        public MonsterType getMonsterType() {
            return MonsterType.ICHMAGSNICHTSABERSPELL;
        }
}
