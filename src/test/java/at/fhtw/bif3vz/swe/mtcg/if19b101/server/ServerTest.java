package at.fhtw.bif3vz.swe.mtcg.if19b101.server;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ServerTest extends TestCase {

    @Test
    public void testFiveCardsInPackage(){
        //test if a new package consists of five cards
        Server server = new Server();
        List<Card> cardArray = server.getPackageFromServer().getPackageCards();

        if(cardArray.size() != 5){
            fail("A Package from Server should consist of five Cards");
        }
    }

    /*@Test//nicht möglich da getrandomcard private
    public void testRandomCardAllAttributes(){
        //test if a new random card has all attributes
        //Server server = new Server();

    }*/


}