package at.fhtw.bif3vz.swe.mtcg.if19b101.database;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCardDB;
import at.fhtw.bif3vz.swe.mtcg.if19b101.server.TestPackage;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.TestUser;

import java.lang.invoke.CallSite;
import java.sql.Connection;
import java.sql.*;
import java.util.*;

public class Database {
    public static void initDb() {
        try (Connection connection = DatabaseConnection.getInstance().connect("")) {
            DatabaseConnection.executeSql(connection, "DROP DATABASE monstertradingcardsgame", true);
            DatabaseConnection.executeSql(connection, "CREATE DATABASE monstertradingcardsgame", true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            //mehr tabellen erzeugen
            DatabaseConnection.getInstance().executeSql("""
                        CREATE TABLE IF NOT EXISTS Users (
                        Username VARCHAR(50) NOT NULL PRIMARY KEY,
                        Password VARCHAR(50) NOT NULL,
                        Token VARCHAR(50) NOT NULL,
                        Coins INT,
                        ELO INT
                    )
                    """);
            DatabaseConnection.getInstance().executeSql("""
                        CREATE TABLE IF NOT EXISTS Cards (
                        Id VARCHAR(100) NOT NULL PRIMARY KEY,
                        Name VARCHAR(50) NOT NULL,
                        Damage NUMERIC(4,2) NOT NULL,
                        User_Token VARCHAR(50),
                        inDeck BOOLEAN
                    )
                    """);
            DatabaseConnection.getInstance().executeSql("""
                        CREATE TABLE IF NOT EXISTS Packages (
                        PackageId SERIAL UNIQUE PRIMARY KEY,
                        CardId1 VARCHAR(100),
                        CardId2 VARCHAR(100),
                        CardId3 VARCHAR(100),
                        CardId4 VARCHAR(100),
                        CardId5 VARCHAR(100)
                    )
                    """);
            DatabaseConnection.getInstance().executeSql("""
                        CREATE TABLE IF NOT EXISTS Battles (
                        BattleId SERIAL UNIQUE PRIMARY KEY,
                        Token1 VARCHAR(50),
                        Token2 VARCHAR(50)
                    )
                    """);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(TestUser user) {
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                INSERT INTO users
                (username, password, token, coins, elo)
                VALUES (?, ?, ?, ?, ?);
                """ )
        ) {
            statement.setString(1, user.getUsername() );
            statement.setString(2, user.getPassword() );
            statement.setString(3, user.getToken() );
            statement.setInt(4, 20 );
            statement.setInt(5, 100 );
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int addBattle(String token){
        //get last entry
        int battleID = -1;
        String player1 = null, player2 = null;
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT * FROM battles ORDER BY battleid DESC LIMIT 1;
                """ )
        ) {
            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                battleID = resultSet.getInt(1);
                player1 = resultSet.getString(2);
                player2 = resultSet.getString(3);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //check if its the same user
        if(player1 == token){
            return -1;
        }

        //if last entry is empty (no games yet) or full (two players) [gleicher zustand]
        if(player1 == null && player2 == null || player1 != null && player2 != null){
            try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                INSERT INTO battles
                (token1)
                VALUES (?);
                """ )
            ) {
                statement.setString(1, token);
                statement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return 0;
        }else{
            try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                UPDATE battles
                SET token2 = ?
                WHERE battleid = ?;
                """ )
            ) {
                statement.setString(1, token);
                statement.setInt(2, battleID);
                statement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return 1;
        }
    }

    public int getUserStats(String token){
        int elo = -1;
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT elo FROM users
                WHERE token = ?
                """ )
        ) {
            statement.setString(1, token );
            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                elo = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return elo;
    }

    public void saveCard(TestCardDB card) {
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                INSERT INTO cards
                (id, name, damage)
                VALUES (?, ?, ?);
                """ )
        ) {
            statement.setString(1, card.getId() );
            statement.setString(2, card.getName() );
            statement.setFloat(3, card.getDamage() );
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCard(String cardID, String token) {
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                UPDATE cards
                SET user_token = ?
                WHERE id = ?;
                """ )
        ) {
            statement.setString(1, token );
            statement.setString(2, cardID );
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCardToDeck(String cardID, String token) {
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                UPDATE cards
                SET indeck = true
                WHERE id = ? AND user_token = ?;
                """ )
        ) {
            statement.setString(1, cardID );
            statement.setString(2, token );
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int updateDeck(String token, List<String> cards){//besser in dboperations
        //check if exactly 4 cards
        if(cards.size() != 4){
            System.out.println("not 4 card ids");
            return -2;
        }
        //check if new cards are from user
        List<String> allcards = getCardIds(token);
        for(String item : cards){
            if(!allcards.contains(item)){
                System.out.println("card not from this user");
                return -1;
            }
        }
        //get current deck cards
        //List<TestCardDB> currentDeckCards = getDeckCards(token);
        //reset deck cards
        excludeAllCardFromDeck(token);
        //set deck cards
        for(String item : cards){
            updateCardToDeck(item, token);
        }
        return 0;
    }

    public void excludeAllCardFromDeck(String token){
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                UPDATE cards
                SET indeck = false
                WHERE user_token = ?;
                """ )
        ) {
            statement.setString(1, token);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public HashMap<String, Integer> getScoreboard(){
        HashMap<String, Integer> scoreboard = new HashMap<>();
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT username, elo FROM users;
                """ )
        ) {
            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                scoreboard.put(
                        resultSet.getString(1),
                        resultSet.getInt(2)
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return scoreboard;
    }

    public List<String> getCardIds(String token){
        List<String> cards = new ArrayList<>();
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT id FROM cards
                WHERE user_token = ?;
                """ )
        ) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                cards.add(
                        resultSet.getString(1)
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cards;
    }

    public int getCoinsByToken(String token){
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT coins FROM users
                WHERE token = ?;
                """ )
        ) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int coins = resultSet.getInt(1);
            return coins;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public void updateCoinsByToken(String token){
        int coins = getCoinsByToken(token);
        coins -= 5;
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                UPDATE users
                SET coins = ?
                WHERE token = ?;
                """ )
        ) {
            statement.setInt(1, coins);
            statement.setString(2, token);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<TestCardDB> getCards(String token){
        List<TestCardDB> cards = new ArrayList<>();
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT id, name, damage FROM cards
                WHERE user_token = ?;
                """ )
        ) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                cards.add(new TestCardDB(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getFloat(3)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cards;
    }

    public List<TestCardDB> getDeckCards(String token){
        List<TestCardDB> cards = new ArrayList<>();
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT id, name, damage FROM cards
                WHERE user_token = ? AND indeck = true;
                """ )
        ) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                cards.add(new TestCardDB(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getFloat(3)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cards;
    }

    public void savePackage(TestPackage pack){
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                INSERT INTO packages
                (cardid1, cardid2, cardid3, cardid4, cardid5)
                VALUES (?, ?, ?, ?, ?);
                """ )
        ) {
            List<TestCardDB> cards = pack.getAllCards();
            statement.setString(1, cards.get(0).getId() );
            statement.setString(2, cards.get(1).getId() );
            statement.setString(3, cards.get(2).getId() );
            statement.setString(4, cards.get(3).getId() );
            statement.setString(5, cards.get(4).getId() );
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deletePackage(Integer packID){
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                DELETE FROM packages
                WHERE packageid = ?;
                """)
        ) {
            statement.setInt( 1, packID);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<String> getIdsOfPackage(){
        List<String> ids = new ArrayList<>();
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT * FROM packages LIMIT 1;
                """)
        ){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                ids.add(resultSet.getString(2));
                ids.add(resultSet.getString(3));
                ids.add(resultSet.getString(4));
                ids.add(resultSet.getString(5));
                ids.add(resultSet.getString(6));
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return ids;
    }

    public int getIdOfPackage(){
        int id;
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT packageid FROM packages LIMIT 1;
                """)
        ){
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            id = resultSet.getInt(1);
            return id;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return -1;
    }

    public void deleteAllUsers() {
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                DELETE FROM users;
                """)
        ) {
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAllCards() {
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                DELETE FROM cards;
                """)
        ) {
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public TestUser getUserDataByName(String name){
        TestUser user = new TestUser();
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT username, password, token 
                FROM users 
                WHERE username=?
                """)
        ){
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                user.setUsername(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                user.setToken(resultSet.getString(3));
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return user;
    }

    public List<String> getBattleNames(){
        List<String> token = getBattleTokens();
        List<String> names = new ArrayList<>();
        for(String item : token){
            names.add(readUsernameByToken(item));
        }
        return names;
    }

    public List<String> getBattleTokens(){
        List<String> tokens = new ArrayList<>();
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT token1, token2 FROM battles ORDER BY battleid DESC LIMIT 1;
                """ )
        ) {
            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                tokens.add(resultSet.getString(1));
                tokens.add(resultSet.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tokens;
    }

    public String readUsernameByToken(String token){
        String username = null;
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT username 
                FROM users
                WHERE token = ?
                """)
        ){
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                username = resultSet.getString(1);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return username;
    }
}
