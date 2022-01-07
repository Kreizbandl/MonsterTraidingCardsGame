package at.fhtw.bif3vz.swe.mtcg.if19b101.database;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCardDB;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.TestUser;

import java.util.List;

public class DatabaseOperations {

    public static void writeUsersListToDatabase(List<TestUser> data) {
        Database daoDb = new Database();
        for( TestUser item : data ) {
            System.out.println("  save item: " + item);
            daoDb.saveUser( new TestUser(
                    item.getName(),
                    item.getPassword(),
                    item.getToken()
            ) );
        }
    }

    public static void writeUserToDatabase(TestUser user) {
        Database daoDb = new Database();
        System.out.println("  save item: " + user);
        daoDb.saveUser( new TestUser(
                user.getName(),
                user.getPassword(),
                user.getToken()
        ) );
    }

    public static void writeCardsListToDatabase(List<TestCardDB> data) {
        Database daoDb = new Database();
        for( TestCardDB item : data ) {
            System.out.println("  save item: " + item);
            daoDb.saveCard( new TestCardDB(
                    item.getCardid(),
                    item.getName(),
                    item.getDamage()
            ) );
        }
    }

    public static void writeCardToDatabase(TestCardDB card){
        Database db = new Database();
        System.out.println("  save item: " + card);
        db.saveCard( new TestCardDB(
                card.getCardid(),
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

}
