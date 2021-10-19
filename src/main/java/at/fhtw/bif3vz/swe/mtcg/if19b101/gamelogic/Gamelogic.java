package at.fhtw.bif3vz.swe.mtcg.if19b101.gamelogic;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.MonsterCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.SpellCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;

public class Gamelogic {
    //private Card card1;
    //private Card card2;


    public Gamelogic() {
    }

    //this function has to be called twice -> then compare returned int damage of first argument card
    public int getFirstCurrentDamage(Card card1, Card card2){
        //both are monster cards -> just return damage
        if(card1 instanceof MonsterCard && card2 instanceof MonsterCard){

                //special: FIREELF -> DRAGON
                    //fix gewonnen
                    if(((MonsterCard) card1).getMonsterType() == MonsterType.ELF &&
                            card1.getElementType() == ElementType.FIRE &&
                            ((MonsterCard) card2).getMonsterType() == MonsterType.DRAGON){
                        return 1000;
                    //fix verloren
                    }else if(((MonsterCard) card2).getMonsterType() == MonsterType.ELF &&
                            card2.getElementType() == ElementType.FIRE &&
                            ((MonsterCard) card1).getMonsterType() == MonsterType.DRAGON){
                        return 0;
                    }

            //check if: DRGAON -> GOBLIN, WIZZARD -> ORK
            return card1.getDamage() * moreOrLessPowerFullSituation(((MonsterCard) card1).getMonsterType(), ((MonsterCard) card2).getMonsterType());
        }

        //if at least one spell card -> element type makes a difference - get etype of second card only to check if spellcard1 wins
        else/*if(card1 instanceof SpellCard)*/{

                //special: WATERSPELL -> (any)KNIGHT
                    //fix gewonnen
                    if(card2 instanceof MonsterCard){//prevent error if card2 isn't a monstercard
                        if(card1 instanceof SpellCard &&
                           card1.getElementType() == ElementType.WATER &&
                           ((MonsterCard) card2).getMonsterType() == MonsterType.KNIGHT){
                            return 1000;
                        }
                    //fix verloren
                    }else if(card1 instanceof MonsterCard){//prevent error if card1 isn't a monstercard
                        if(((MonsterCard) card1).getMonsterType() == MonsterType.KNIGHT &&
                                card2 instanceof SpellCard &&
                                card2.getElementType() == ElementType.WATER){
                            return 0;
                        }
                    }



                //special: KRAKEN -> (any)SPELL
                    //fix gewonnen
                    if(card1 instanceof MonsterCard){//prevent error if card1 isn't a monstercard
                        if(((MonsterCard)card1).getMonsterType() == MonsterType.KRAKEN &&
                          card2 instanceof SpellCard){
                            return 1000;
                        }
                    //fix verloren
                    }else if(card2 instanceof MonsterCard){//prevent error if card2 isn't a monstercard
                        if(card1 instanceof SpellCard &&
                          ((MonsterCard)card2).getMonsterType() == MonsterType.KRAKEN){
                            return 0;
                        }
                    }

            //check if: WATER -> FIRE, FIRE -> NORMAL, NORMAL -> WATER
            double damage = card1.getDamage() * moreOrLessSpellEffect(card1.getElementType(), card2.getElementType());
            return (int)damage;
        }

        //return 69;
    }

    //check if first argument/Element has more or less power then
    private double moreOrLessSpellEffect(ElementType eType1, ElementType eType2){
        //check if: WATER -> FIRE, FIRE -> NORMAL, NORMAL -> WATER
        //effective
        if(eType1 == ElementType.WATER && eType2 == ElementType.FIRE ||
           eType1 == ElementType.FIRE && eType2 == ElementType.NORMAL ||
           eType1 == ElementType.NORMAL && eType2 == ElementType.WATER){
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
