package at.fhtw.bif3vz.swe.mtcg.if19b101.database;

import java.util.*;

public class DatabaseOperations {

    private static Database db = new Database();

    //trade
    public static void writeTradeToDatabase(String token, TradeRecord trade){
        db.saveTrade(token, trade);
    }

    public static TradeRecord readTradeToDatabase(String token){
        return db.getTrades(token);
    }

    public static void removeTradeFromDatabase(String token, String id){
        //no check if trade is from user
        db.deleteTrade(token, id);
    }

    public static int tradeCards(String token, String tradeId, String giveCardId){
        //check if cards infos ok -> type, damage, token, giveCard form user
        TradeRecord trade = db.getTradeById(tradeId);
        if(Objects.equals(trade.id(), "-1")){
            System.out.println("ERR: -1");
        }
        //check if trade from current user
        if(trade.token().equals(token)){
            return -1;
        }

        //compare cards
        //CardDB card2 = db.getCardById(trade.cardToTrade());
        CardDB card1 = db.getCardById(giveCardId);
        if(card1.getDamage() < trade.minimumDamage()){
            return -2;
        }
        /*if(!card1.getName().contains(trade.type())){
            return -3;
        }*/

        //swap cards, delete trade
        db.updateCard(trade.cardToTrade(), token);
        db.updateCard(giveCardId, trade.token());
        db.deleteTrade(trade.token(), trade.id());
        return 0;
    }

    //user
    public static boolean writeUserToDatabase(UserDB user) {
        UserDB tmpUser = db.getUserDataByName(user.getUsername());
        if(user.getUsername().equals(tmpUser.getUsername())){
            return false;
        }
            db.saveUser( new UserDB(
                    user.getUsername(),
                    user.getPassword(),
                    user.getToken()
            ) );
            return true;
    }

    public static List<String> readUserProfileFromDatabase(String token){
        return db.getUserProfile(token);
    }

    public static void writeUserProfileToDatabase(String token, List<String> data){
        db.updateUserProfile(token, data);
    }

    public static String readUsernameFromDatabase(String token){
        return db.readUsernameByToken(token);
    }

    public static UserDB readUserDataByName(String name){
        return db.getUserDataByName(name);
    }

    public static int readELOFromDatabase(String token){
        return db.getUserStats(token);
    }

    public static void updateELO(String token, int result){
        int elo = db.getUserStats(token);
        if(result == 1){
            elo += 3;
        }else if(result == 2){
            elo -= 5;
        }
        db.updateUserStats(token, elo);
    }

    //cards
    public static List<CardDB> readAllCardsByToken(String token){
        return db.getCards(token);
    }

    //deck
    public static int updateDeckToDatabase(String token, List<String> cardIds){//echtes deck übergeben?
        //check if exactly 4 cards
        if(cardIds.size() != 4){
            return -2;
        }
        //check if new cards are from user
        List<String> allcards = db.getUserCardIds(token);
        for(String item : cardIds){
            if(!allcards.contains(item)){
                return -1;
            }
        }
        //reset deck cards
        db.excludeAllCardsFromDeck(token);
        //set deck cards
        for(String item : cardIds){
            db.addCardToDeck(item, token);
        }
        return 0;
    }

    public static List<CardDB> readDeckByToken(String token){//return package object
        return db.getDeckCards(token);
    }

    //package
    public static void writePackageToDatabase(List<CardDB> cards){
        db.savePackage(cards);
        for( CardDB item : cards ) {
            db.saveCard(item);
        }
    }

    public static int aquirePackageByToken(String token){
        //check if enough money left
        if(db.getCoinsByToken(token) < 5){
            return -1;
        }else{
            //get ids of cards from package
            List<String> ids = db.getIdsOfPackage();
            if(ids.isEmpty()){
                return 1;
            }else{
                //update cards with token
                for(int i = 0;i < 5;i++){
                    db.updateCard(ids.get(i), token);
                }
                //get Package ID
                int packId = db.getIdOfPackage();
                //delete package
                db.deletePackage(packId);
                //update coins of user
                db.updateCoinsByToken(token);
                return 0;
            }
        }
    }

    //battle
    public static int loginForBattle(String token){
        int battleId = db.getLastBattleId();
        List<String> tokens = db.getBattleTokens(battleId);

        if(tokens.size() == 0 || tokens.get(0) != null && tokens.get(1) != null){//neues game, wenn first game || vorheriges voll (|| im vorherigen gleicher username)
            db.createNewBattle(token);
            return 0;
        }

        if(token.equals(tokens.get(0))){
            return -1;
        }else{
            int newbattleId = db.getLastBattleId();
            db.joinBattle(token, newbattleId);
            return newbattleId;
        }
    }

    public static List<String> readBattleTokensFromDatabase(int battleId){
        return db.getBattleTokens(battleId);
    }

    //scoreboard
    public static TreeMap<String, Integer> readScoreboardFromDatabase(){//better pair?
        return db.getScoreboard();
    }

}
