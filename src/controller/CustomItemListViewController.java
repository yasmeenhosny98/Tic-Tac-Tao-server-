/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * @author mina
 */
public class CustomItemListViewController {
    @FXML
    TextField itemListTextField;
    
    public void setText(String name)
    {
    itemListTextField.setText(name);
    }
    
}
