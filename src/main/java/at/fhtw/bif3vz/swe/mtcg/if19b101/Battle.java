package at.fhtw.bif3vz.swe.mtcg.if19b101;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.MonsterCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.SpellCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.User;

import java.util.Random;

public class Battle {
    private User u1;
    private User u2;

    public Battle(User u1, User u2) {
        this.u1 = u1;
        this.u2 = u2;
    }

    public void showBattle(){
        int idxOfWinnerCard, countRounds = 0;
        //select a random card (no real card-deck-gameplay, no loop)
        Random rand = new Random();
        int randCardOfDeck1, randCardOfDeck2;

        //remember size of deck to avoid errors
        int sizeDeckUser1, sizeDeckUser2;
        //winner of game 1, 0, -1
        int winner = 0;

        do{
            //show decks
            System.out.println("-------------------Round " + countRounds + "-------------------");
            System.out.println("DECK1: " + u1.getDeckOfUser().toString());
            System.out.println("DECK2: " + u2.getDeckOfUser().toString() + "\n");

            //determine size of deck
            sizeDeckUser1 = this.u1.getDeckOfUser().size();
            sizeDeckUser2 = this.u2.getDeckOfUser().size();

                //quit if one deck is empty
                if(sizeDeckUser1 == 0){
                    winner = -1;
                    break;
                }else if(sizeDeckUser2 == 0){
                    winner = 1;
                    break;
                }

            //pick rand card of deck (regarding deck size)
            randCardOfDeck1 = rand.nextInt(sizeDeckUser1);
            randCardOfDeck2 = rand.nextInt(sizeDeckUser2);

                //which cards are chosen
                System.out.println( u1.getDeckOfUser().get(randCardOfDeck1)
                        + " VS "
                        + u2.getDeckOfUser().get(randCardOfDeck2));

            //compare cards
            idxOfWinnerCard = this.compareTwoCards( u1.getDeckOfUser().get(randCardOfDeck1),
                                                    u2.getDeckOfUser().get(randCardOfDeck2));

                //which card won
                if(idxOfWinnerCard == 1){
                    System.out.println("         A           <<         V           \n");
                    u1.testAddCard(u2.getDeckOfUser().get(randCardOfDeck2));
                    u2.testRemoveCard(u2.getDeckOfUser().get(randCardOfDeck2));
                }else if(idxOfWinnerCard == -1){
                    System.out.println("         V           >>         A           \n");
                    u2.testAddCard(u1.getDeckOfUser().get(randCardOfDeck1));
                    u1.testRemoveCard(u1.getDeckOfUser().get(randCardOfDeck1));
                }else{
                    System.out.println("         .          ----        .           \n");
                }

            countRounds++;
        }while(countRounds < 20);

        //check winner
        if(winner == 1){
            System.out.println("             WINNER IS USER1");
        }else if(winner == -1){
            System.out.println("             WINNER IS USER2");
        }else{
            System.out.println("                NO WINNER");
        }
    }

    private int compareTwoCards(Card c1, Card c2){
        if(c1.getClass() == c2.getClass()){
            if(c1 instanceof MonsterCard){//beide MonsterCards
                return monsterFight(((MonsterCard) c1), ((MonsterCard) c2));
            }else{//beide SpellCards
                return spellFight(((SpellCard) c1), ((SpellCard) c2));
            }
        }else{//mixed Cards
            if(c1 instanceof SpellCard && c2 instanceof MonsterCard){
                //- the armour of Knights is so heavy that WaterSpells make them drown (WaterSpell->Knight)
                if(c1.getElementType() == ElementType.WATER && ((MonsterCard) c2).getMonsterType() == MonsterType.KNIGHT){
                    return 1;
                //- the Kraken is immune against spells (Kraken->AnySpell)
                }else if(((MonsterCard) c2).getMonsterType() == MonsterType.KRAKEN){
                    return -1;
                }
                return mixedFight(((SpellCard) c1), ((MonsterCard) c2));
            }else if(c1 instanceof MonsterCard && c2 instanceof SpellCard){
                //- the armour of Knights is so heavy that WaterSpells make them drown (WaterSpell->Knight)
                if(((MonsterCard) c1).getMonsterType() == MonsterType.KNIGHT && c2.getElementType() == ElementType.WATER ){
                    return -1;
                //- the Kraken is immune against spells (Kraken->AnySpell)
                }else if(((MonsterCard) c1).getMonsterType() == MonsterType.KRAKEN){
                    return 1;
                }
                if(mixedFight(((SpellCard) c2), ((MonsterCard) c1)) == 1){
                    return -1;
                }else if(mixedFight(((SpellCard) c2), ((MonsterCard) c1)) == -1){
                    return 1;
                }else{
                    return 0;
                }
            }
            return 69;
        }
    }

    private int mixedFight(SpellCard c1, MonsterCard c2){
        int damage1 = c1.getDamage();
        int damage2 = c2.getDamage();
        //- effective -> double damage (water->fire, fire->normal, normal->water)
        //- not effective -> half damage (---"---)
        if(c1.getElementType() == ElementType.WATER && c2.getElementType() == ElementType.FIRE){
            damage1 *= 2;
            damage2 /= 2;
        }else if(c1.getElementType() == ElementType.FIRE && c2.getElementType() == ElementType.WATER){
            damage1 /= 2;
            damage2 *= 2;
        }
        if(c1.getElementType() == ElementType.FIRE && c2.getElementType() == ElementType.NORMAL){
            damage1 *= 2;
            damage2 /= 2;
        }else if(c1.getElementType() == ElementType.NORMAL && c2.getElementType() == ElementType.FIRE){
            damage1 /= 2;
            damage2 *= 2;
        }
        if(c1.getElementType() == ElementType.NORMAL && c2.getElementType() == ElementType.WATER){
            damage1 *= 2;
            damage2 /= 2;
        }else if(c1.getElementType() == ElementType.WATER && c2.getElementType() == ElementType.NORMAL){
            damage1 /= 2;
            damage2 *= 2;
        }

        //- no effect -> same element (compare damage)
        return compareDamage(damage1, damage2);
    }

    private int monsterFight(MonsterCard c1, MonsterCard c2){
        //- Goblins are too afraid of Dragons to attack (Dragon->Goblin)
        if(c1.getMonsterType() == MonsterType.GOBLIN && c2.getMonsterType() == MonsterType.DRAGON){
            return -1;
        }else if(c1.getMonsterType() == MonsterType.DRAGON && c2.getMonsterType() == MonsterType.GOBLIN){
            return 1;
        }
        //- Wizzards are able to control Orks (Wizzard->Ork)
        if(c1.getMonsterType() == MonsterType.WIZZARD && c2.getMonsterType() == MonsterType.ORK){
            return 1;
        }else if(c1.getMonsterType() == MonsterType.ORK && c2.getMonsterType() == MonsterType.WIZZARD){
            return -1;
        }
        //- the FireElves know Dragons since they were little and can evade their attacks (FireElf->Dargon)
        if(c1.getMonsterType() == MonsterType.DRAGON && (c2.getMonsterType() == MonsterType.ELF && c2.getElementType() == ElementType.FIRE)){
            return 1;
        }else if((c1.getMonsterType() == MonsterType.ELF && c1.getElementType() == ElementType.FIRE) && c2.getMonsterType() == MonsterType.DRAGON){
            return -1;
        }

        //two monster fight: only damage compare (no element effects)
        return compareDamage(c1.getDamage(), c2.getDamage());
    }

    private int spellFight(SpellCard c1, SpellCard c2){
        int damage1 = c1.getDamage();
        int damage2 = c2.getDamage();
        //- effective -> double damage (water->fire, fire->normal, normal->water)
        //- not effective -> half damage (---"---)
        if(c1.getElementType() == ElementType.WATER && c2.getElementType() == ElementType.FIRE){
            damage1 *= 2;
            damage2 /= 2;
        }else if(c1.getElementType() == ElementType.FIRE && c2.getElementType() == ElementType.WATER){
            damage1 /= 2;
            damage2 *= 2;
        }
        if(c1.getElementType() == ElementType.FIRE && c2.getElementType() == ElementType.NORMAL){
            damage1 *= 2;
            damage2 /= 2;
        }else if(c1.getElementType() == ElementType.NORMAL && c2.getElementType() == ElementType.FIRE){
            damage1 /= 2;
            damage2 *= 2;
        }
        if(c1.getElementType() == ElementType.NORMAL && c2.getElementType() == ElementType.WATER){
            damage1 *= 2;
            damage2 /= 2;
        }else if(c1.getElementType() == ElementType.WATER && c2.getElementType() == ElementType.NORMAL){
            damage1 /= 2;
            damage2 *= 2;
        }

        //- no effect -> same element (compare damage)
        return compareDamage(damage1, damage2);
    }

    private int compareDamage(int d1, int d2){
        if(d1 > d2){
            return 1;
        }else if(d1 < d2){
            return -1;
        }else{
            return 0;
        }
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
//- the armour of Knights is so heavy that WaterSpells make them drown (WaterSpell->Knight)
//- the Kraken is immune against spells (Kraken->AnySpell)
//- the FireElves know Dragons since they were little and can evade their attacks (FireElf->Dargon)