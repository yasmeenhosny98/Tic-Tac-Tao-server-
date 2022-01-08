/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.MainSceneController;
import dataBase.DAO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ghaly
 */
public class ClientHandler extends Thread {

    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    Socket socket;
    GameSession gameSession;

    private DAO dao = new DAO();

    public ClientHandler(Socket s) {

        try {
            if (s.isConnected()) {
                socket = s;
                objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                objectInputStream = new ObjectInputStream(s.getInputStream());

            }
            start();
        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Client handler Thread Run");
            try {
                if (objectInputStream != null) {
                    Object recievedObject = objectInputStream.readObject();

                    if (recievedObject instanceof RequestGame) {
                        RequestGame request = (RequestGame) recievedObject;
                        System.out.println(request);
                        if (!request.isSent()) {
                            /*ClientHandler client = MainSceneController.clients.get(request.getRecieverPlayerId() - 1);
                            request.setSent(true);
                            client.objectOutputStream.writeObject(request);*/
                        } else {
                            //already sent
                            if (request.isAccepted()) {
                                gameSession = new GameSession(
                                        MainSceneController.clients.get(request.getRequesterPlayerId() - 1),
                                        MainSceneController.clients.get(request.getRecieverPlayerId() - 1)
                                );

                                MainSceneController.clients.get(request.getRecieverPlayerId() - 1).objectOutputStream.writeObject(gameSession.game);
                                MainSceneController.clients.get(request.getRequesterPlayerId() - 1).objectOutputStream.writeObject(gameSession.game);
                                MainSceneController.clients.get(request.getRequesterPlayerId() - 1).gameSession = gameSession;

                            }
                        }
                    } else if (recievedObject instanceof PlayerMove) {
                        PlayerMove move = (PlayerMove) recievedObject;
                        move.setIsX(gameSession.turn);
                        gameSession.playerOne.objectOutputStream.writeObject(move);
                        gameSession.playerTwo.objectOutputStream.writeObject(move);
                        gameSession.game.playersMoves[gameSession.game.counter] = move;
                        gameSession.game.counter++;
                        gameSession.turn = !gameSession.turn;

                    } else if (recievedObject instanceof String) {
                        String request = (String) recievedObject;
                        if (request.equals("GetPlayers")) {
                            OnlineAndCurrentlyPlayingPlayersModel players = new OnlineAndCurrentlyPlayingPlayersModel();
                            players.setAvailablePlayers(dao.selectOnLine());
                            players.setPlayingPlayers(dao.selectUavailable());
                            this.objectOutputStream.writeObject(players);
                        }
                    } else if (recievedObject instanceof PlayerOnline) {
                        PlayerOnline player = (PlayerOnline) recievedObject;
                        Player loginResult = dao.login(player.getEmail(), player.getPassword());
                        if (loginResult == null) {
                            this.objectOutputStream.writeObject("notFound");
                        } else {
                            this.objectOutputStream.writeObject(loginResult);
                        }
                    } else if (recievedObject instanceof RegisterModel) {
                        RegisterModel register = (RegisterModel) recievedObject;
                        boolean databaseResult = dao.addPlayer(register);
                        if (databaseResult) {
                            this.objectOutputStream.writeObject("PlayerRegistered");
                        } else {
                            this.objectOutputStream.writeObject("ServerError");
                        }
                    } else if (recievedObject instanceof Integer) {
                        Integer playerId = (Integer) recievedObject;
                        Player player = dao.SelectSinglePlayer(playerId.intValue());
                        player.setScore(player.getWins() * 3);
                        this.objectOutputStream.writeObject(player);
                    }
                }
            } catch (Exception ex) {

                try {
                    ex.printStackTrace();
                    //  closeConnection();
                    socket.close();

//                    if (gameSession.playerOne.socket.isClosed()) {
//                        gameSession.playerTwo.objectOutputStream.writeObject("Player disconnected");
//                    }
//
//                    if (gameSession.playerTwo.socket.isClosed()) {
//                        gameSession.playerOne.objectOutputStream.writeObject("Player disconnected");
//                    }
                    this.stop();
                    MainSceneController.clients.remove(this);

                } catch (IOException exe) {
                    ex.printStackTrace();
                }
            }

        }
    }

    public void closeConnection() {
        try {
            MainSceneController.clients.remove(this);
            this.stop();
            this.objectInputStream.close();
            this.objectOutputStream.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "ClientHandler{" + "objectInputStream=" + objectInputStream + ", objectOutputStream=" + objectOutputStream + '}';
    }

}
