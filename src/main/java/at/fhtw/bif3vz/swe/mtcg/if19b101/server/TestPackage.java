package at.fhtw.bif3vz.swe.mtcg.if19b101.server;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
//import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCardDB;

import java.util.List;

public class TestPackage {

    private List<TestCardDB> packageCards;

    public TestPackage(){}

    public TestPackage(List<TestCardDB> tc){
        this.packageCards = tc;
    }

    public List<TestCardDB> getPackageCards() {
        return packageCards;
    }

    public void setPackageCards(List<TestCardDB> packageCards) {
        this.packageCards = packageCards;
    }

    public List<TestCardDB> getAllCards(){
        return this.packageCards;
    }

    public void addCards(List<TestCardDB> cards){
        this.packageCards = cards;
    }

    @Override
    public String toString() {
        return "TestPackage{" +
                "packageCards=" + packageCards +
                '}' + "\n";
    }
}
