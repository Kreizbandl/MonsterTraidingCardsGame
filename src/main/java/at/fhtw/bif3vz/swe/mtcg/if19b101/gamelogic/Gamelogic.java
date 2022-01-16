package at.fhtw.bif3vz.swe.mtcg.if19b101.gamelogic;

import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.interfaces.CardOperations;

public class Gamelogic {

    public Gamelogic() { }

    //this function has to be called twice -> then compare returned float damage of first argument card
    public float getFirstCurrentDamage(CardOperations card1, CardOperations card2){//Card card1, Card card2

        //both are monster cards -> just return damage
        if(card1.isMonster() && card2.isMonster()){//card1 instanceof MonsterCard && card2 instanceof MonsterCard

                //special: FIREELF -> DRAGON
                    //fix gewonnen
                    if(card1.getMonsterType() == MonsterType.ELF &&
                            card1.getElementType() == ElementType.FIRE &&
                            card2.getMonsterType() == MonsterType.DRAGON){
                        return 1000;
                    //fix verloren
                    }else if(card2.getMonsterType() == MonsterType.ELF &&
                            card2.getElementType() == ElementType.FIRE &&
                            card1.getMonsterType() == MonsterType.DRAGON){
                        return 0;
                    }

            //check if: DRGAON -> GOBLIN, WIZZARD -> ORK
            return card1.getDamage() * moreOrLessPowerFullSituation(card1.getMonsterType(), card2.getMonsterType());
        }

        //if at least one spell card -> element type makes a difference - get etype of second card only to check if spellcard1 wins

                //special: WATERSPELL -> (any)KNIGHT
                    //fix gewonnen
                    if(card2.isMonster()){//prevent error if card2 isn't a monstercard
                        if(!card1.isMonster() &&
                           card1.getElementType() == ElementType.WATER &&
                           card2.getMonsterType() == MonsterType.KNIGHT){
                            return 1000;
                        }
                    //fix verloren
                    }else if(card1.isMonster()){//prevent error if card1 isn't a monstercard
                        if(card1.getMonsterType() == MonsterType.KNIGHT &&
                           !card2.isMonster() &&
                           card2.getElementType() == ElementType.WATER){
                            return 0;
                        }
                    }

                //special: KRAKEN -> (any)SPELL
                    //fix gewonnen
                    if(card1.isMonster()){//prevent error if card1 isn't a monstercard
                        if(card1.getMonsterType() == MonsterType.KRAKEN &&
                          !card2.isMonster()){
                            return 1000;
                        }
                    //fix verloren
                    }else if(card2.isMonster()){//prevent error if card2 isn't a monstercard
                        if(!card1.isMonster() &&
                          card2.getMonsterType() == MonsterType.KRAKEN){
                            return 0;
                        }
                    }

            //check if: WATER -> FIRE, FIRE -> NORMAL, NORMAL -> WATER
            double damage = card1.getDamage() * moreOrLessSpellEffect(card1.getElementType(), card2.getElementType());
            return (int)damage;
    }

    //check if first argument/Element has more or less power then
    private double moreOrLessSpellEffect(ElementType eType1, ElementType eType2){
        //check if: WATER -> FIRE, FIRE -> NORMAL, NORMAL -> WATER
        //effective
        if(eType1 == ElementType.WATER && eType2 == ElementType.FIRE ||
           eType1 == ElementType.FIRE && eType2 == ElementType.REGULAR ||
           eType1 == ElementType.REGULAR && eType2 == ElementType.WATER){
            return 2;
        //no effect
        }else if(eType1 == eType2){
            return 1;
        }
        //not effective
        return 0.5;
    }

    private int moreOrLessPowerFullSituation(MonsterType mType1, MonsterType mType2){
        //check if: DRAGON -> GOBLIN, WIZZARD -> ORK
        //fix gewonnen
        if(mType1 == MonsterType.DRAGON && mType2 == MonsterType.GOBLIN ||
           mType1 == MonsterType.WIZZARD && mType2 == MonsterType.ORK){
            return 1000;
        //fix verloren
        }else if(mType1 == MonsterType.GOBLIN && mType2 == MonsterType.DRAGON ||
                mType1 == MonsterType.ORK && mType2 == MonsterType.WIZZARD){
            return 0;
        }
        //nix passiert
        return 1;
    }
}

//two monster fight: only damage compare (no element effects)

//spell attack:
//- effective -> double damage (water->fire, fire->normal, normal->water)
//- not effective -> half damage (---"---)
//- no effect -> same element (compare damage)

//special attack-situations:
//- Goblins are too afraid of Dragons to attack (Dragon->Goblin)
//- Wizzards are able to control Orks (Wizzard->Ork)
//- the FireElves know Dragons since they were little and can evade their attacks (FireElf->Dargon)
//- the armour of Knights is so heavy that WaterSpells make them drown (WaterSpell->Knight)
//- the Kraken is immune against spells (Kraken->AnySpell)