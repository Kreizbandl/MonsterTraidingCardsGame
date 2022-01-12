package at.fhtw.bif3vz.swe.mtcg.if19b101.database;

import at.fhtw.bif3vz.swe.mtcg.if19b101.server.TestPackage;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.TestUser;
import java.util.HashMap;
import java.util.List;

public class DatabaseOperations {

    //would be nice: function to enable other functions only if user is alloed to (auth-token)

    //user
    public static boolean writeUserToDatabase(TestUser user) {
        Database db = new Database();
        TestUser tmpUser = db.getUserDataByName(user.getUsername());
        if(user.getUsername().equals(tmpUser.getUsername())){
            return false;
        }
            db.saveUser( new TestUser(
                    user.getUsername(),
                    user.getPassword(),
                    user.getToken()
            ) );
            return true;
    }

    public static String readUsernameFromDatabase(String token){
        Database db = new Database();
        return db.readUsernameByToken(token);
    }

    public static TestUser readUserDataByName(String name){
        Database db = new Database();
        return db.getUserDataByName(name);
    }

    public static int readELOFromDatabase(String token){
        Database db = new Database();
        return db.getUserStats(token);
    }

    public static void updateELO(String token, int result){
        Database db = new Database();
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
        Database db = new Database();
        return db.getCards(token);
    }

    //deck
    public static int updateDeckToDatabase(String token, List<String> cardIds){//echtes deck übergeben?
        Database db = new Database();
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
        Database db = new Database();
        return db.getDeckCards(token);
    }

    //package
    public static void writePackageToDatabase(List<CardDB> cards){
        Database db = new Database();
        db.savePackage(cards);
        for( CardDB item : cards ) {
            db.saveCard(item);
        }
    }

    public static int aquirePackageByToken(String token){
        Database db = new Database();
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
        Database db = new Database();
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
        Database db = new Database();
        return db.getBattleTokens(battleId);
    }

    //scoreboard

    public static HashMap<String, Integer> readScoreboardFromDatabase(){//better pair?
        Database db = new Database();
        return db.getScoreboard();
    }



    /////////////////////////////////////////////////////////

    /*public static List<String> readBattleUsernamesFromDatabase(){//überflüssig?
        Database db = new Database();
        return db.getBattleNames();
    }*/

    /*public static void writeCardToDatabase(CardDB card){
        Database db = new Database();
        System.out.println("  save item: " + card);
        db.saveCard( new CardDB(
                card.getId(),
                card.getName(),
                card.getDamage()
        ) );
    }*/

    /*public static void writeDeckToDatabase(String token, List<String> cardIDs){
        Database db = new Database();
        for(String item : cardIDs){
            db.addCardToDeck(item, token);
        }
    }*/

    /*public static void writeUsersListToDatabase(List<TestUser> data) {
        Database daoDb = new Database();
        for( TestUser item : data ) {
            System.out.println("  save item: " + item);
            daoDb.saveUser( new TestUser(
                    item.getUsername(),
                    item.getPassword(),
                    item.getToken()
            ) );
        }
    }*/

    /*protected static void writeCardsListToDatabase(List<CardDB> cards) {
        Database db = new Database();
        for( CardDB item : cards ) {
            System.out.println("  save item: " + item);
            db.saveCard( new CardDB(
                    item.getId(),
                    item.getName(),
                    item.getDamage()
            ) );
        }
    }*/

    /*public static void writeCardsListToDatabase(List<TestCardDB> data) {
        Database daoDb = new Database();
        for( TestCardDB item : data ) {
            System.out.println("  save item: " + item);
            daoDb.saveCard( new TestCardDB(
                    item.getCardid(),
                    item.getName(),
                    item.getDamage()
            ) );
        }
    }*/

    /*public static void deleteAllUsersInDatabase(){
        Database db = new Database();
        System.out.println("Delete all users in table users");
        db.deleteAllUsers();
    }*/

    /*public static void deleteAllCardsInDatabase(){
        Database db = new Database();
        System.out.println("Delete all cards in table cards");
        db.deleteAllCards();
    }*/

    /*private static void deleteUserInDatabase(UserRecord user){
        Database db = new Database();
        System.out.println("  delete item: " + user);
        db.deleteUser(user.name());
    }*/

}
