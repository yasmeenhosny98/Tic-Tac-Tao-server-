/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dataBase.DAO;
import java.io.IOException;
import java.net.URL;
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
     
    FXMLLoader fxmlLoader;
    
    boolean isServerRunning=false; 
    int i=0;
    ArrayList<FXMLLoader> onLinePlayerViewItem,offLinePlayerViewItem,currentGameViewItem;
    ArrayList<String> onLinePlayers,offLinePlayers,currentGames;
    ObservableList observableOnlineList,observableOfflineList,observableCurrentGameList ;
   
    private DAO data =new DAO();
    int k=0;
    
    @FXML
    public void startServer()
    {          
       showOnlinePlayer();
       showOfflinePlayersIn();
       showCurrentGame();
       showOnlineUserGraph();
    
    }
    
    @FXML
    public void stopServer()
    {
   
    }
    int c=0;
    String name;
    public void showOnlinePlayer()
    {     
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/CustomItemListView.fxml"));
            observableOnlineList.add(fxmlLoader.load());
            ((CustomItemListViewController)fxmlLoader.getController()).setText("Online"+k++);
            onlineListView.refresh();
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void showOfflinePlayersIn()
    { 
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/CustomItemListView.fxml"));
            observableOfflineList.add(fxmlLoader.load());
            ((CustomItemListViewController)fxmlLoader.getController()).setText("Offline"+k);
            onlineListView.refresh();
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void showCurrentGame()
    {
      try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/CustomItemListView.fxml"));
            observableCurrentGameList.add(fxmlLoader.load());
            ((CustomItemListViewController)fxmlLoader.getController()).setText("current Game"+k);
            onlineListView.refresh();
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showOnlineUserGraph()
    {
    onlineUserGraph.setTitle("Graph For Online Users");
    XYChart.Series series = new XYChart.Series();
        series.setName("2004");
        series.getData().add(new XYChart.Data("Available", 57401.85));
    XYChart.Series series2 = new XYChart.Series();
        series.setName("2001");
        series.getData().add(new XYChart.Data("In Game", 40000.85));
        onlineUserGraph.getData().addAll(series,series2);
    }
  

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onLinePlayerViewItem=new ArrayList<>();
        offLinePlayerViewItem=new ArrayList<>();
        currentGameViewItem=new ArrayList<>();
        observableOnlineList = FXCollections.observableArrayList();
        observableOfflineList = FXCollections.observableArrayList();
        observableCurrentGameList = FXCollections.observableArrayList();
        onlineListView.setItems(observableOnlineList);
        offlineListView.setItems(observableOfflineList);
        currentGameListView.setItems(observableCurrentGameList);
    }
    
}
