package at.fhtw.bif3vz.swe.mtcg.if19b101.database;

import java.io.Closeable;
import java.sql.*;


public class DatabaseConnection implements Closeable {

    private static DatabaseConnection instance;
    private Connection connection;

    public DatabaseConnection(){
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            System.err.println("PostgreSQL JDBC driver not found");
            e.printStackTrace();
        }
    }

    public Connection connect(String database) throws SQLException{
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + database, "postgres", "");
    }

    public Connection connect() throws SQLException{
        return connect("monstertradingcardsgame");
    }

    public Connection getConnection(){
        if(connection == null){
            try {
                connection = DatabaseConnection.getInstance().connect();
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return connection;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException{
        return getConnection().prepareStatement(sql);
    }

    public boolean executeSql(String sql) throws SQLException{
        return executeSql(getConnection(), sql, false);
    }

    public static boolean executeSql(Connection connection, String sql, boolean ignoreIfFails) throws SQLException{
        try (Statement statement = connection.createStatement()){//use this , simpler
            statement.execute(sql);
            return true;
        }catch (SQLException e){
            if(!ignoreIfFails)
                throw e;
            return false;
        }
    }

    public static boolean executeSql(Connection connection, String sql) throws SQLException{
        return executeSql(connection, sql, false);
    }

    public void close() {
        if(connection != null){
            try {
                connection.close();
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
            connection = null;
        }
    }

    public static DatabaseConnection getInstance(){
        if(instance == null)
            instance = new DatabaseConnection();
        return instance;
    }
}
