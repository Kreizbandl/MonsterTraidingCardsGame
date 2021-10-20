package at.fhtw.bif3vz.swe.mtcg.if19b101.gamelogic;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.MonsterCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class GamelogicTest {

    //fuck testing!!!!!!!!!!!!!!!!!
    /*Gamelogic gl;

    @BeforeEach
    void setup(){
        gl = new Gamelogic();
    }

    //Test for different card situations
    //'FIREELF' -> DRAGON
    @Test
    @DisplayName("FireElf sould defeat Dragon")
    public void test_fireElfVsDragon(){
        MonsterCard fireElf = new MonsterCard(20,ElementType.FIRE, MonsterType.ELF);
        MonsterCard dragon = new MonsterCard(20,ElementType.NORMAL, MonsterType.DRAGON);

        //assertFalse("damage sould be different", fireElf.getDamage() == dragon.getDamage());
        assertTrue("FireElf defeats Dragon", gl.getFirstCurrentDamage(fireElf,dragon) > gl.getFirstCurrentDamage(dragon,fireElf));
    }*/
}