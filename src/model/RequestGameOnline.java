/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.MainSceneController;
import java.io.Serializable;


/**
 *
 * @author Ghaly
 */
public class RequestGameOnline implements Serializable {

    ;

    private int requesterPlayerId;
    private int recieverPlayerId;

    private boolean accepted;
    private boolean sent;

    private GameSessionOnline gameSession;
    private static final long serialVersionUID = 6529685098267757690L;

    public RequestGameOnline(int requesterPlayerId, int recieverPlayerId) {
        this.requesterPlayerId = requesterPlayerId;
        this.recieverPlayerId = recieverPlayerId;
        this.accepted = false;
        if (accepted) {
            gameSession = new GameSessionOnline(MainSceneController.clients.get(requesterPlayerId - 1), MainSceneController.clients.get(recieverPlayerId - 1));
            System.out.println("GameSession Created");
        }
    }

    public RequestGameOnline(int requesterPlayerId, int recieverPlayerId, boolean accetpance) {
        this.requesterPlayerId = requesterPlayerId;
        this.recieverPlayerId = recieverPlayerId;
        this.accepted = accetpance;

    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public int getRequesterPlayerId() {
        return requesterPlayerId;
    }

    public void setRequesterPlayerId(int requesterPlayerId) {
        this.requesterPlayerId = requesterPlayerId;
    }

    public int getRecieverPlayerId() {
        return recieverPlayerId;
    }

    public void setRecieverPlayerId(int recieverPlayerId) {
        this.recieverPlayerId = recieverPlayerId;
    }

    @Override
    public String toString() {
        return "RequestGame{" + "requesterPlayerId=" + requesterPlayerId + ", recieverPlayerId=" + recieverPlayerId + ", accepted=" + accepted + ", gameSession=" + gameSession + '}';
    }

    public boolean isAccepted() {
        return accepted;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

}
