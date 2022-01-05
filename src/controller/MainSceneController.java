/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dataBase.DAO;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Player;

/**
 *
 * @author mina
 */
public class MainSceneController implements Initializable{
    
    @FXML
    Button btnStart;
    
    @FXML
    Button btnStop;
    
    @FXML
    ListView onlineListView;
    
    @FXML
    ListView offlineListView;
    
    @FXML
    ListView currentGameListView;
    
    @FXML
    BarChart onlineUserGraph;
    
    @FXML
    BarChart usersGraph;
    
    @FXML
    TextField ServerIPLabel;
     
    FXMLLoader fxmlLoader;
    
    boolean isServerRunning=false; 
   
   
    ObservableList observableOnlineList,observableOfflineList,observablePlayersInGameList,observableBarChart ;
    ArrayList<Player>onlinePlayersList,offlinePlayersList,playersInGameList;
    private DAO data =new DAO();
 
    
    
    @FXML
    public void startServer()
    {        
        if(!isServerRunning){
        data.open();
        onlinePlayersList=data.sellectOnLine();
        offlinePlayersList=data.sellectOffLine();
        playersInGameList=data.SelectUavailable();
        
        
       showOnlinePlayer();
       showOfflinePlayersIn();
       showCurrentGame();
       showOnlineUserGraph();
       isServerRunning=true;
        }
        
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            String serverIP = localhost.getHostAddress().trim();
            ServerIPLabel.setText("Server IP:  " + serverIP);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void stopServer()
    {
        if(isServerRunning){
        data.closeConnection();
        clearAllFields();
        isServerRunning=false;
        }
        else
        playersInGameList.add(new Player(5, "mia", "asdasd", "sdsa", isServerRunning, isServerRunning, 0, 0, 0, 0));
       
        
        //ServerIPLabel.setText("");
    }
    private void clearAllFields()
    {
        ServerIPLabel.clear();
        observableOnlineList.clear();
        observableOfflineList.clear();
        observablePlayersInGameList.clear();
        playersInGameList.clear();
        onlineListView.refresh();
        offlineListView.refresh();
        currentGameListView.refresh();
        onlineUserGraph.getData().clear();
    
    }

    public void showOnlinePlayer()
    {     
        for(int i=0;i<onlinePlayersList.size();i++){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/CustomItemListView.fxml"));
            observableOnlineList.add(fxmlLoader.load());
            ((CustomItemListViewController)fxmlLoader.getController()).setText(onlinePlayersList.get(i).getUsername());
            onlineListView.refresh();
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    public void showOfflinePlayersIn()
    { 
        for(int i=0;i<offlinePlayersList.size();i++)
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/CustomItemListView.fxml"));
            observableOfflineList.add(fxmlLoader.load());
            ((CustomItemListViewController)fxmlLoader.getController()).setText(offlinePlayersList.get(i).getUsername());
            onlineListView.refresh();
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void showCurrentGame()
    {
        for(int i=0;i<playersInGameList.size();i++){
      try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/CustomItemListView.fxml"));
            observablePlayersInGameList.add(fxmlLoader.load());
            ((CustomItemListViewController)fxmlLoader.getController()).setText(playersInGameList.get(i).getUsername());
            onlineListView.refresh();
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    public void showOnlineUserGraph()
    {
    onlineUserGraph.setTitle("Users In Server");
    XYChart.Series series = new XYChart.Series();
        series.setName("Online");
        series.getData().add(new XYChart.Data("Online",onlinePlayersList.size()));
    XYChart.Series series2 = new XYChart.Series();
        series2.setName("Offline");
        series2.getData().add(new XYChart.Data("Offline", offlinePlayersList.size()));
    XYChart.Series series3 = new XYChart.Series();
        series3.setName("In Game");
        series3.getData().add(new XYChart.Data("In Game", playersInGameList.size()));
        observableBarChart.addAll(series,series2,series3);
        
    }
  

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onlinePlayersList=new ArrayList<>();
        offlinePlayersList=new ArrayList<>();
        playersInGameList=new ArrayList<>();
        observableOnlineList = FXCollections.observableArrayList();
        observableOfflineList = FXCollections.observableArrayList();
        observablePlayersInGameList = FXCollections.observableArrayList();
        observableBarChart= FXCollections.observableArrayList();
        onlineListView.setItems(observableOnlineList);
        offlineListView.setItems(observableOfflineList);
        currentGameListView.setItems(observablePlayersInGameList);
        
        onlineUserGraph.setData(observableBarChart);
    }
    
}
