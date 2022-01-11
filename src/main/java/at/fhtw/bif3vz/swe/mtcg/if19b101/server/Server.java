package at.fhtw.bif3vz.swe.mtcg.if19b101.server;

/*import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.MonsterCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.SpellCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;
import java.util.*;

public class Server {

    private Card generateRandomCard(){
        Random rand = new Random();

        Integer[] damages = {10,20,40};
        List<Integer> damageList = Arrays.asList(damages);
        int dInt = damageList.get(rand.nextInt(damageList.size()));

        List<ElementType> eleTypeList = new ArrayList<>();
            eleTypeList.addAll(Arrays.asList(ElementType.values()));
        ElementType eType = eleTypeList.get(rand.nextInt(eleTypeList.size()));

        if(rand.nextInt(2) == 0){
            return new SpellCard(dInt, eType);
        }else{
            List<MonsterType> monTypeList = new ArrayList<>();
                for (MonsterType m:MonsterType.values()) {
                    if(m.equals(MonsterType.ICHMAGSNICHTSABERSPELL)){
                        continue;
                    }
                    monTypeList.add(m);
                }
            MonsterType mType = monTypeList.get(rand.nextInt(monTypeList.size()));
            return new MonsterCard(dInt, eType, mType);
        }
    }

    public Package getPackageFromServer(){
        List<Card> packageCards = new ArrayList<>();
        for(int i = 0;i < 5;i++){
            packageCards.add(this.generateRandomCard());
        }
        Package pack = new Package();
        pack.addCards(packageCards);
        return pack;
    }
}*/
