package at.fhtw.bif3vz.swe.mtcg.if19b101.database;

//import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCardDB;
import at.fhtw.bif3vz.swe.mtcg.if19b101.server.TestPackage;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.TestUser;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {

    public static void writeUsersListToDatabase(List<TestUser> data) {
        Database daoDb = new Database();
        for( TestUser item : data ) {
            System.out.println("  save item: " + item);
            daoDb.saveUser( new TestUser(
                    item.getUsername(),
                    item.getPassword(),
                    item.getToken()
            ) );
        }
    }

    public static void writeUserToDatabase(TestUser user) {
        Database daoDb = new Database();
        System.out.println("  save item: " + user);
        daoDb.saveUser( new TestUser(
                user.getUsername(),
                user.getPassword(),
                user.getToken()
        ) );
    }

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

    public static void writeCardToDatabase(TestCardDB card){
        Database db = new Database();
        System.out.println("  save item: " + card);
        db.saveCard( new TestCardDB(
                card.getId(),
                card.getName(),
                card.getDamage()
        ) );
    }

    /*private static void deleteUserInDatabase(UserRecord user){
        Database db = new Database();
        System.out.println("  delete item: " + user);
        db.deleteUser(user.name());
    }*/

    public static void deleteAllUsersInDatabase(){
        Database db = new Database();
        System.out.println("Delete all users in table users");
        db.deleteAllUsers();
    }

    public static void deleteAllCardsInDatabase(){
        Database db = new Database();
        System.out.println("Delete all cards in table cards");
        db.deleteAllCards();
    }

    public static TestUser readUserDataFromDatabase(String name){
        Database db = new Database();
        return db.getUserDataByName(name);
    }

    public static void writePackageToDatabase(TestPackage pack){
        Database db = new Database();
        List<TestCardDB> cards = pack.getAllCards();
        System.out.println("save package");
        db.savePackage(pack);
        System.out.println("save cards");
        writeCardsListToDatabase(cards);
    }

    protected static void writeCardsListToDatabase(List<TestCardDB> cards) {
        Database daoDb = new Database();
        for( TestCardDB item : cards ) {
            System.out.println("  save item: " + item);
            daoDb.saveCard( new TestCardDB(
                    item.getId(),
                    item.getName(),
                    item.getDamage()
            ) );
        }
    }

    public static void aquirePackageByToken(String token){
        Database db = new Database();
        //get ids of cards from package
        List<String> ids = db.getIdsOfPackage();
        System.out.println(ids.toString());
        //update cards with token
        for(int i = 0;i < 5;i++){
            System.out.println("  update item: " + ids.get(i));
            db.updateCard(ids.get(i), token);
        }
        //get Package ID
        int packId = db.getIdOfPackage();
        //delete package
        db.deletePackage(packId);
    }

    public static List<TestCardDB> readCardsFromDatabaseByToken(String token){
        Database db = new Database();
        List<TestCardDB> cards = db.getCards(token);
        return cards;
    }

    public static void writeDeckToDatabase(String token, List<String> cardIDs){
        Database db = new Database();
        for(String item : cardIDs){
            db.updateCardToDeck(item, token);
        }
    }

    //readUserCardsFromDatabase
    //writeDeckToDatabase
    //readDeckFromDatabase
    //readScoreboardFromDatabase
    //readStatsFromDatabase

}
