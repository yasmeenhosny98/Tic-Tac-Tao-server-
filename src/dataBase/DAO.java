package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Player;
import org.apache.derby.jdbc.ClientDriver;

public class DAO {
    
    static Connection con;
    Statement stmt;
    PreparedStatement pst;
    String queryString;
    ArrayList<Player> rows;
    ResultSet rs;
    Player player;
    
    public DAO(){}
    
    public  void open(){
        try {
            DriverManager.registerDriver(new ClientDriver());
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/TicTacToe", "root", "root");  
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList<Player> selectAll(){
        try {
            rows = new ArrayList<>();
            Statement stmt = con.createStatement() ;
            rs = stmt.executeQuery("SELECT * FROM ROOT.PLAYERS") ;
            while(rs.next()){ 
                rows.add(new Player(
                    rs.getInt(1), 
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4), 
                    rs.getBoolean(5), 
                    rs.getBoolean(6), 
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getInt(10)
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rows;
    }
    
    public ArrayList<Player> sellectOnLine(){
        try {
            rows = new ArrayList<>();
            Statement stmt = con.createStatement() ;
            rs = stmt.executeQuery("SELECT * FROM ROOT.PLAYERS WHERE ISONLINE = TRUE") ;
            while(rs.next()){ 
                rows.add(new Player(
                    rs.getInt(1), 
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4), 
                    rs.getBoolean(5), 
                    rs.getBoolean(6), 
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getInt(10)
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rows;
    }
    
    public ArrayList<Player> sellectOffLine(){
        try {
            rows = new ArrayList<>();
            Statement stmt = con.createStatement() ;
            rs = stmt.executeQuery("SELECT * FROM ROOT.PLAYERS WHERE ISONLINE = FALSE") ;
            while(rs.next()){ 
                rows.add(new Player(
                    rs.getInt(1), 
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4), 
                    rs.getBoolean(5), 
                    rs.getBoolean(6), 
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getInt(10)
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rows;
    }
    
    public ArrayList<Player> SelectAvailable(){
        try {
            rows = new ArrayList<>();
            Statement stmt = con.createStatement() ;
            rs = stmt.executeQuery("SELECT * FROM ROOT.PLAYERS WHERE ISINGAME = FALSE") ;
            while(rs.next()){ 
                rows.add(new Player(
                    rs.getInt(1), 
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4), 
                    rs.getBoolean(5), 
                    rs.getBoolean(6), 
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getInt(10)
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rows;
    }
    
    public ArrayList<Player> SelectUavailable(){
        try {
            rows = new ArrayList<>();
            Statement stmt = con.createStatement() ;
            rs = stmt.executeQuery("SELECT * FROM ROOT.PLAYERS WHERE ISINGAME = TRUE") ;
            while(rs.next()){ 
                rows.add(new Player(
                    rs.getInt(1), 
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4), 
                    rs.getBoolean(5), 
                    rs.getBoolean(6), 
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getInt(10)
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rows;
    }
    
    public void deletePlayer(int player_ID){
        try {
            pst = con.prepareStatement("DELETE FROM ROOT.PLAYERS WHERE PLAYER_ID = ?");
            pst.setInt(1, player_ID);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void addPlayer(Player player){
        try {
            pst = con.prepareStatement("INSERT INTO ROOT.PLAYERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, player.getUsername());
            pst.setString(2, player.getEmail());
            pst.setString(3, player.getPassword());
            pst.setBoolean(4, player.isActive());
            pst.setBoolean(5, player.isPlaying());
            pst.setInt(6, player.getScore());
            pst.setInt(7, player.getWins());
            pst.setInt(8, player.getLoses());
            pst.setInt(9, player.getDraws());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Player SelectSinglePlayer(int playerID){
        try {
            pst = con.prepareStatement("SELECT * FROM ROOT.PLAYERS WHERE PLAYER_ID = ?");
            pst.setInt(1, playerID);
            pst.executeQuery();
            rs = pst.executeQuery();
            while(rs.next()){
                player = new Player(
                    rs.getInt(1), 
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4), 
                    rs.getBoolean(5), 
                    rs.getBoolean(6), 
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getInt(10)
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return player;
    }
    
    public void updatePlayer(Player player){
        try {
            pst = con.prepareStatement("UPDATE ROOT.PLAYERS SET USERNAME = ?, EMAIL = ?, PASSWORD = ?, ISONLINE = ?, ISINGAME = ?, SCORE = ?, WIN = ?, LOSE = ?, DRAW = ? WHERE PLAYER_ID = ?");
            pst.setString(1, player.getUsername());
            pst.setString(2, player.getEmail());
            pst.setString(3, player.getPassword());
            pst.setBoolean(4, player.isActive());
            pst.setBoolean(5, player.isPlaying());
            pst.setInt(6, player.getScore());
            pst.setInt(7, player.getWins());
            pst.setInt(8, player.getLoses());
            pst.setInt(9, player.getDraws());
            pst.setInt(10, player.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void logOut(Player player){
        try {
            pst = con.prepareStatement("Update ROOT.PLAYERS SET ISONLINE = FALSE ,ISINGAME = FALSE WHERE PLAYER_ID = ?");
            pst.setInt(1, player.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void Online(Player player){
        try {
            pst = con.prepareStatement("Update ROOT.PLAYERS SET ISONLINE = TRUE WHERE PLAYER_ID = ?");
            pst.setInt(1, player.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void Offline(Player player){
        try {
            pst = con.prepareStatement("Update ROOT.PLAYERS SET ISONLINE = FALSE WHERE PLAYER_ID = ?");
            pst.setInt(1, player.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void inGame(Player player){
        try {
            pst = con.prepareStatement("Update ROOT.PLAYERS SET ISINGAME = TRUE WHERE PLAYER_ID = ?");
            pst.setInt(1, player.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void notINGame(Player player){
        try {
            pst = con.prepareStatement("Update ROOT.PLAYERS SET ISINGAME = FALSE WHERE PLAYER_ID = ?");
            pst.setInt(1, player.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void win(Player player){
        try {
            pst = con.prepareStatement("Update ROOT.PLAYERS SET WIN = ?, SCORE = ? WHERE PLAYER_ID = ?");
            pst.setInt(1, player.getWins()+1);
            pst.setInt(2, player.getScore()+3);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void draw(Player player){
        try {
            pst = con.prepareStatement("Update ROOT.PLAYERS SET DRAW = ?, SCORE = ? WHERE PLAYER_ID = ?");
            pst.setInt(1, player.getDraws()+1);
            pst.setInt(2, player.getScore()+1);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void lose(Player player){
        try {
            pst = con.prepareStatement("Update ROOT.PLAYERS SET LOSE = ? WHERE PLAYER_ID = ?");
            pst.setInt(1, player.getLoses()+1);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Player login(String Email , String password){
        try {
            pst = con.prepareStatement("SELECT * FROM ROOT.PLAYERS WHERE EMAIL = ? AND PASSWORD = ?");
            pst.setString(1, Email);
            pst.setString(2, password);
            pst.executeQuery();
            rs = pst.executeQuery();
            while(rs.next()){
                player = new Player(
                    rs.getInt(1), 
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4), 
                    rs.getBoolean(5), 
                    rs.getBoolean(6), 
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getInt(10)
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return player;
    }
    
    public ArrayList<Player> topTen(){
        try {
            rows = new ArrayList<>();
            Statement stmt = con.createStatement() ;
            rs = stmt.executeQuery("SELECT * FROM ROOT.PLAYERS ORDER BY SCORE DESC FETCH FIRST 10 ROWS ONLY") ;
            while(rs.next()){ 
                rows.add(new Player(
                    rs.getInt(1), 
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4), 
                    rs.getBoolean(5), 
                    rs.getBoolean(6), 
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getInt(10)
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rows;
    }
    
    public void closeConnection(){
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}