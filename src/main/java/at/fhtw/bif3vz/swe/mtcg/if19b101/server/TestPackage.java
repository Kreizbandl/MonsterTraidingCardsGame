package at.fhtw.bif3vz.swe.mtcg.if19b101.server;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCard;

import java.util.List;

public class TestPackage {

    private List<TestCard> packageCards;

    public TestPackage(){}

    public TestPackage(List<TestCard> tc){
        this.packageCards = tc;
    }

    public List<TestCard> getPackageCards() {
        return packageCards;
    }

    public void setPackageCards(List<TestCard> packageCards) {
        this.packageCards = packageCards;
    }

    public List<TestCard> getAllCards(){
        return this.packageCards;
    }

    public void addCards(List<TestCard> cards){
        this.packageCards = cards;
    }

    @Override
    public String toString() {
        return "TestPackage{" +
                "packageCards=" + packageCards +
                '}' + "\n";
    }
}
