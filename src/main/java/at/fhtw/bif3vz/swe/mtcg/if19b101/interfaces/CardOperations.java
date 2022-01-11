package at.fhtw.bif3vz.swe.mtcg.if19b101.interfaces;

import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;

public interface CardOperations {

    boolean isMonster();
    //String getName();
    float getDamage();
    ElementType getElementType();
    MonsterType getMonsterType();//tut weh bei spellcard und bei enum monstertype

}
