package at.fhtw.bif3vz.swe.mtcg.if19b101.database;

import at.fhtw.bif3vz.swe.mtcg.if19b101.server.TestPackage;
import at.fhtw.bif3vz.swe.mtcg.if19b101.user.TestUser;
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
                    """);//remember winner?
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //user
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

    public TestUser getUserDataByName(String name){//besser mit username and password
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

    public void updateUserStats(String token, int elo){
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                UPDATE users
                SET elo = ?
                WHERE token = ?;
                """ )
        ) {
            statement.setInt(1, elo );
            statement.setString(2, token );
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
            return resultSet.getInt(1);
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

    //card
    public void saveCard(CardDB card) {
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

    public List<CardDB> getCards(String token){
        List<CardDB> cards = new ArrayList<>();
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT id, name, damage FROM cards
                WHERE user_token = ?;
                """ )
        ) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                cards.add(new CardDB(
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

    public List<String> getUserCardIds(String token){
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

    //deck
    public void addCardToDeck(String cardID, String token) {
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

    public void excludeAllCardsFromDeck(String token){
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

    public List<CardDB> getDeckCards(String token){
        List<CardDB> cards = new ArrayList<>();
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT id, name, damage FROM cards
                WHERE user_token = ? AND indeck = true;
                """ )
        ) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                cards.add(new CardDB(
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

    //package
    public void savePackage(List<CardDB> cards){
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                INSERT INTO packages
                (cardid1, cardid2, cardid3, cardid4, cardid5)
                VALUES (?, ?, ?, ?, ?);
                """ )
        ) {
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
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT packageid FROM packages LIMIT 1;
                """)
        ){
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return -1;
    }

    //battle
    public int getLastBattleId(){
        int battleId = -1;
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT battleid FROM battles ORDER BY battleid DESC LIMIT 1;
                """ )
        ) {
            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                battleId = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return battleId;
    }

    public List<String> getBattleTokens(int battleId){//pair besser?
        List<String> tokens = new ArrayList<>();
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                SELECT token1, token2 FROM battles
                WHERE battleid = ?;
                """ )
        ) {
            statement.setInt(1, battleId);
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

    public void createNewBattle(String token){
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
    }

    public void joinBattle(String token, int battleId){
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                UPDATE battles
                SET token2 = ?
                WHERE battleid = ?;
                """ )
        ) {
            statement.setString(1, token);
            statement.setInt(2, battleId);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //scoreboard
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

    /////////////////////////////////////////////////////////////////////////////////

    /*public List<String> getBattleNames(){
        List<String> token = getBattleTokens();
        List<String> names = new ArrayList<>();
        for(String item : token){
            names.add(readUsernameByToken(item));
        }
        return names;
    }*/

    /*public int addUserToBattle(String token){
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
        if(player1.equals(token)){
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
    }*/

    /*public void deleteAllUsers() {
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                DELETE FROM users;
                """)
        ) {
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/

    /*public void deleteAllCards() {
        try ( PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement("""
                DELETE FROM cards;
                """)
        ) {
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/
}
