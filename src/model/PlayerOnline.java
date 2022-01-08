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
public class PlayerOnline implements Serializable {

    private static final long serialVersionUID = 6529685098267757700L;
    private int id;
    private String username;
    private String email;
    private String password;
    private boolean isactive;
    private boolean isplaying;
    private int score;
    private int wins;
    private int loses;
    private int draws;

    public PlayerOnline() {
    }

    public PlayerOnline(int id, String username, String email, String password, boolean isactive, boolean isplaying, int score, int wins, int loses, int draws) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isactive = isactive;
        this.isplaying = isplaying;
        this.wins = wins;
        this.loses = loses;
        this.draws = draws;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public boolean isActive() {
        return isactive;
    }

    public void setIsplaying(boolean isplaying) {
        this.isplaying = isplaying;
    }

    public boolean isPlaying() {
        return isplaying;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getWins() {
        return wins;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getLoses() {
        return loses;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getDraws() {
        return draws;
    }
}
