package at.fhtw.bif3vz.swe.mtcg.if19b101.card;


import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;

public class SpellCard extends Card {
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
