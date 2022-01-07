/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Eslam Esmael
 */
public class GameOnline implements Serializable{
    
    
    private static final long serialVersionUID = 6529685098267757610L;
    

    PlayerMove[] playersMoves;
    boolean isPlayerOneTurn;
    boolean isGameEnd;

    boolean firstPlayerWinner = false;
    boolean secondPlayerWinner = false;
    PlayerMove[][] pmfc;

    //TODO add players id to use it in database 
    int counter;

    public GameOnline() {
        counter = 0;
        playersMoves = new PlayerMove[9];
        isGameEnd = true;
        isPlayerOneTurn = true;
        System.out.println(this.toString());

        pmfc = new PlayerMove[3][3];
    }

}
