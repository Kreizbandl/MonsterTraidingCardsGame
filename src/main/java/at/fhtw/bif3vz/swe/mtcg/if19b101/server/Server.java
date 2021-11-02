package at.fhtw.bif3vz.swe.mtcg.if19b101.server;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.MonsterCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.SpellCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.*;//client-server
import java.net.*;//client-server

public class Server {
    //lets try something
    //server as server
    public static void main(String[] args){
        try{
            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();//stablish connection
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String str = (String)dis.readUTF();
            System.out.println("message= "+str);
            ss.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    //functions to implement:
    //register and login user
    //aquire cards
    //battle agains other user
    //compare stats in leaderboards

    public Server() {
    }

    private Card generateRandomCard(){
        Random rand = new Random();

        int[] damageArray = {
                10,
                20,
                40
        };
            int dInt = damageArray[rand.nextInt(damageArray.length)];

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
}
