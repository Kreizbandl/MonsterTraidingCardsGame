package at.fhtw.bif3vz.swe.mtcg.if19b101;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.MonsterCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.SpellCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.gamelogic.Gamelogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GamelogicTest {

    Gamelogic gamelogic;
    float damage = 50;

    @BeforeEach
    void setUp(){
        gamelogic = new Gamelogic();
    }

    //special situations
    @Test
    @DisplayName("Goblins are too afraid of Dragons")
    void test1(){
        MonsterCard card1 = new MonsterCard(damage,ElementType.REGULAR,MonsterType.DRAGON);
        MonsterCard card2 = new MonsterCard(damage,ElementType.REGULAR,MonsterType.GOBLIN);

        assertEquals(1,
                Float.compare(
                    gamelogic.getFirstCurrentDamage(card1,card2),
                    gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 1");
    }

    @Test
    @DisplayName("Wizzards can control Orks")
    void test2(){
        MonsterCard card1 = new MonsterCard(damage,ElementType.REGULAR,MonsterType.WIZZARD);
        MonsterCard card2 = new MonsterCard(damage,ElementType.REGULAR,MonsterType.ORK);

        assertEquals(1,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 1");
    }

    @Test
    @DisplayName("Knights are too heavy for WaterSpells")
    void test3(){
        SpellCard card1 = new SpellCard(damage,ElementType.WATER);
        MonsterCard card2 = new MonsterCard(damage,ElementType.REGULAR,MonsterType.KNIGHT);

        assertEquals(1,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 1");
    }

    @Test
    @DisplayName("Kraken is immune against Spells")
    void test4(){
        MonsterCard card1 = new MonsterCard(damage,ElementType.REGULAR,MonsterType.KRAKEN);
        SpellCard card2 = new SpellCard(damage,ElementType.WATER);

        assertEquals(1,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 1");
    }

    @Test
    @DisplayName("FireElf should defeat Dragon")
    void test5(){
        MonsterCard card1 = new MonsterCard(damage,ElementType.FIRE,MonsterType.ELF);
        MonsterCard card2 = new MonsterCard(damage,ElementType.REGULAR,MonsterType.DRAGON);

        assertEquals(1,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 1");
    }

    //spell against spell
    @Test
    @DisplayName("WaterSpell defeats FireSpell")
    void test6(){
        SpellCard card1 = new SpellCard(damage,ElementType.WATER);
        SpellCard card2 = new SpellCard(damage,ElementType.FIRE);

        assertEquals(1,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 1");
    }

    @Test
    @DisplayName("FireSpell defeats RegularSpell")
    void test7(){
        SpellCard card1 = new SpellCard(damage,ElementType.FIRE);
        SpellCard card2 = new SpellCard(damage,ElementType.REGULAR);

        assertEquals(1,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 1");
    }

    @Test
    @DisplayName("RegularSpell defeats WaterSpell")
    void test8(){
        SpellCard card1 = new SpellCard(damage,ElementType.REGULAR);
        SpellCard card2 = new SpellCard(damage,ElementType.WATER);

        assertEquals(1,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 1");
    }

    //monster against monster
    @Test
    @DisplayName("Goblin versus Wizzard with same strength should be a tie")
    void test9(){
        MonsterCard card1 = new MonsterCard(damage,ElementType.FIRE,MonsterType.GOBLIN);
        MonsterCard card2 = new MonsterCard(damage,ElementType.WATER,MonsterType.WIZZARD);

        assertEquals(0,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 0");
    }

    @Test
    @DisplayName("Dragon versus Ork with same strength should be a tie")
    void test10(){
        MonsterCard card1 = new MonsterCard(damage,ElementType.FIRE,MonsterType.DRAGON);
        MonsterCard card2 = new MonsterCard(damage,ElementType.WATER,MonsterType.ORK);

        assertEquals(0,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 0");
    }

    @Test
    @DisplayName("Wizzard versus Knight with same strength should be a tie")
    void test11(){
        MonsterCard card1 = new MonsterCard(damage,ElementType.FIRE,MonsterType.WIZZARD);
        MonsterCard card2 = new MonsterCard(damage,ElementType.WATER,MonsterType.KNIGHT);

        assertEquals(0,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 0");
    }

    @Test
    @DisplayName("Ork versus Knight with same strength should be a tie")
    void test12(){
        MonsterCard card1 = new MonsterCard(damage,ElementType.FIRE,MonsterType.ORK);
        MonsterCard card2 = new MonsterCard(damage,ElementType.WATER,MonsterType.KNIGHT);

        assertEquals(0,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 0");
    }

    @Test
    @DisplayName("Kraken versus Knight with same strength should be a tie")
    void test13(){
        MonsterCard card1 = new MonsterCard(damage,ElementType.FIRE,MonsterType.KRAKEN);
        MonsterCard card2 = new MonsterCard(damage,ElementType.WATER,MonsterType.KNIGHT);

        assertEquals(0,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 0");
    }

    @Test
    @DisplayName("Troll versus Dragon with same strength should be a tie")
    void test14(){
        MonsterCard card1 = new MonsterCard(damage,ElementType.FIRE,MonsterType.TROLL);
        MonsterCard card2 = new MonsterCard(damage,ElementType.WATER,MonsterType.DRAGON);

        assertEquals(0,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 0");
    }

    @Test
    @DisplayName("Troll versus Wizzard with same strength should be a tie")
    void test15(){
        MonsterCard card1 = new MonsterCard(damage,ElementType.FIRE,MonsterType.TROLL);
        MonsterCard card2 = new MonsterCard(damage,ElementType.WATER,MonsterType.WIZZARD);

        assertEquals(0,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 0");
    }

    //monster against monster different strength
    @Test
    @DisplayName("Troll defeats weaker Troll")
    void test16(){
        MonsterCard card1 = new MonsterCard(50,ElementType.FIRE,MonsterType.TROLL);
        MonsterCard card2 = new MonsterCard(25,ElementType.WATER,MonsterType.TROLL);

        assertEquals(1,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 1");
    }

    @Test
    @DisplayName("Dragon defeats weaker Dragon")
    void test17(){
        MonsterCard card1 = new MonsterCard(50,ElementType.FIRE,MonsterType.DRAGON);
        MonsterCard card2 = new MonsterCard(25,ElementType.WATER,MonsterType.DRAGON);

        assertEquals(1,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 1");
    }

    //mixed fights
    @Test
    @DisplayName("WaterSpell defeats FireDragon")
    void test18(){
        SpellCard card1 = new SpellCard(damage,ElementType.WATER);
        MonsterCard card2 = new MonsterCard(damage,ElementType.FIRE,MonsterType.DRAGON);

        assertEquals(1,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 1");
    }

    @Test
    @DisplayName("FireSpell defeats RegularOrk")
    void test19(){
        SpellCard card1 = new SpellCard(damage,ElementType.FIRE);
        MonsterCard card2 = new MonsterCard(damage,ElementType.REGULAR,MonsterType.ORK);

        assertEquals(1,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 1");
    }

    @Test
    @DisplayName("RegularSpell defeats WaterWizzard")
    void test20(){
        SpellCard card1 = new SpellCard(damage,ElementType.REGULAR);
        MonsterCard card2 = new MonsterCard(damage,ElementType.WATER,MonsterType.WIZZARD);

        assertEquals(1,
                Float.compare(
                        gamelogic.getFirstCurrentDamage(card1,card2),
                        gamelogic.getFirstCurrentDamage(card2,card1))
                , "Return value should be 1");
    }
}