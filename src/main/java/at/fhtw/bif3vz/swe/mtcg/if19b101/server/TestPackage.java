package at.fhtw.bif3vz.swe.mtcg.if19b101.server;

//import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.CardDB;

import java.util.List;

public class TestPackage {

    private List<CardDB> packageCards;

    public TestPackage(){}

    public TestPackage(List<CardDB> tc){
        this.packageCards = tc;
    }

    public List<CardDB> getPackageCards() {
        return packageCards;
    }

    public void setPackageCards(List<CardDB> packageCards) {
        this.packageCards = packageCards;
    }

    public List<CardDB> getAllCards(){
        return this.packageCards;
    }

    public void addCards(List<CardDB> cards){
        this.packageCards = cards;
    }

    @Override
    public String toString() {
        return "TestPackage{" +
                "packageCards=" + packageCards +
                '}' + "\n";
    }
}
