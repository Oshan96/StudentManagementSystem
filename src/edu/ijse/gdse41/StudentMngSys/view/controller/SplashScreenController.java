/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.view.controller;

import edu.ijse.gdse41.StudentMngSys.db.ConnectionFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author oshan
 */
public class SplashScreenController implements Initializable {
    
    @FXML
    AnchorPane ap;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection con=ConnectionFactory.getInstance().getConnection();
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/Login.fxml"));
            Parent loginRoot=(Parent)fxmlLoader.load();
            Stage logStage=new Stage();
            logStage.setScene(new Scene(loginRoot));
            logStage.initStyle(StageStyle.UNDECORATED);
            logStage.getIcons().add(new Image(getClass().getResourceAsStream("/edu/ijse/gdse41/StudentMngSys/resources/icons/LogoIcon.png")));
            
            PauseTransition delayPlay= new PauseTransition(Duration.seconds(3));
            delayPlay.setOnFinished((ActionEvent event) -> {
                logStage.show();
                
                ap.getScene().getWindow().hide();
            });
            delayPlay.play();    
        } catch (IOException ex) {
            Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}    
    

