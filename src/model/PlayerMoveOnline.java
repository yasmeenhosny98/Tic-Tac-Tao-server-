/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Ghaly
 */
public class PlayerMoveOnline implements Serializable {

    private static final long serialVersionUID = 6529685098267757691L;
    private int x;
    private int y;
    private boolean isX;
    private boolean isPlayerOneMove;

    public PlayerMoveOnline() {
    }

    public PlayerMoveOnline(int x, int y, boolean isX, boolean isPlayerOneMove) {
        this.x = x;
        this.y = y;
        this.isX = isX;
        this.isPlayerOneMove = isPlayerOneMove;
    }

    public boolean isIsPlayerOneMove() {
        return isPlayerOneMove;
    }

    public void setIsPlayerOneMove(boolean isPlayerOneMove) {
        this.isPlayerOneMove = isPlayerOneMove;
    }

    public PlayerMoveOnline(int x, int y, boolean isX) {
        this.x = x;
        this.y = y;
        this.isX = isX;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isIsX() {
        return isX;
    }

    public void setIsX(boolean isX) {
        this.isX = isX;
    }
    
    

    @Override
    public String toString() {
        return "PlayerMove{" + "x=" + x + ", y=" + y + ", isX=" + isX + ", isPlayerOneMove=" + isPlayerOneMove + '}';
    }

   

}
