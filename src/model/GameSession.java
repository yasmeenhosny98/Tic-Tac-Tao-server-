package model;
public class GameSession {

    private int counter;
    private Player playerOne;
    private Player playerTwo;
    private PlayerMove[] playersMoves;
    private boolean isPlayerOneTurn;

    public GameSession() {
        playersMoves = new PlayerMove[9];
        counter = 0;
    }
    public void addMove(PlayerMove move){
        playersMoves[counter] = move;
        counter++;
    }
    public PlayerMove[] getPlayersMoves() {
        return playersMoves;
    }
    public void setPlayersMoves(PlayerMove[] playersMoves) {
        this.playersMoves = playersMoves;
    }
    
}
