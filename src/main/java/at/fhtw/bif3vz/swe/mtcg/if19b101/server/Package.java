package at.fhtw.bif3vz.swe.mtcg.if19b101.server;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import java.util.List;

public class Package {
    private final List<Card> packageCards;

    public Package(List<Card> packageCards) {
        this.packageCards = packageCards;
    }

    public List<Card> getPackageCards() {
        return packageCards;
    }
}
