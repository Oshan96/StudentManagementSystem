/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.view.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import edu.ijse.gdse41.StudentMngSys.view.ResizeHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author oshan
 */
public class DashboardController implements Initializable {

    @FXML
    ImageView imgSide;
    
    @FXML
    private AnchorPane anchrView;
    
    
    public static AnchorPane rootPane;
    
    @FXML
    JFXDrawer drawer;
    
    public static JFXDrawer sDrawer;
    
    @FXML
    JFXHamburger ham;
    
    public static JFXHamburger sHam;
    
    public static HamburgerNextArrowBasicTransition transition;

    @FXML
    AnchorPane anchrMain;
    Node s;
    Stage primaryStage;
    
    @FXML
    AnchorPane fade;
    
    @FXML
    ImageView imgMin;
    @FXML
    ImageView imgRe;
    
    public static AnchorPane sFade;
    
    ////////////////////////
    
    /////////////////////

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Platform.runLater(() -> {
            primaryStage = (Stage) imgMin.getScene().getWindow();
            ResizeHelper.addResizeListener(primaryStage);
        });
        
        sFade=fade;
        rootPane=anchrView;
        sHam=ham;
        sDrawer=drawer;
        fade.setVisible(false);
        try {
            s = (Node) FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/RegisterStudent.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean add = anchrView.getChildren().add(s);
        if (add) {
            System.out.println("Added"); 
        } else {
            System.out.println("Failed");
        }
        
        //////////////////
        
        rootPane = anchrView;
        transition = new HamburgerNextArrowBasicTransition(ham);
        try {
            AnchorPane box = FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/SidePane.fxml"));
            drawer.setSidePane(box);
            drawer.setDefaultDrawerSize(200);
        } catch (IOException ex) {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        transition.setRate(-0.7);
        ham.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
                /////////////
                drawer.setVisible(false);
                ///////////
                
                FadeTransition fadeOut=new FadeTransition(Duration.millis(800),fade);
                    fadeOut.setFromValue(0.4);
                    fadeOut.setToValue(0);
                    fadeOut.setCycleCount(1);
                    fadeOut.play();
                    fadeOut.setOnFinished(l -> {
                        fade.setVisible(false); 
                    });
            } else {
                try {
                    ////////////////
                    drawer.setVisible(true);
                    //////////////////
                    AnchorPane box = FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/SidePane.fxml"));
                    drawer.setSidePane(box);
                    drawer.open();
                    fade.setVisible(true);
                    FadeTransition fadeIn=new FadeTransition(Duration.millis(800),fade);
                    fadeIn.setFromValue(0);
                    fadeIn.setToValue(0.4);
                    fadeIn.setCycleCount(1);
                    fadeIn.play();
                   // fade.setVisible(true);
                } catch (IOException ex) {
                  //  Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
    @FXML
    public void Close(MouseEvent mouseEvent){
        try{
            ((Stage)(((ImageView)mouseEvent.getSource()).getScene().getWindow())).close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void sideBarMouseClicked(MouseEvent event){
        transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
                
                //////////////
                drawer.setVisible(false);
                //////////////////
                
                FadeTransition fadeOut=new FadeTransition(Duration.millis(800),fade);
                    fadeOut.setFromValue(0.4);
                    fadeOut.setToValue(0);
                    fadeOut.setCycleCount(1);
                    fadeOut.play();
                    fadeOut.setOnFinished(l -> {
                        fade.setVisible(false); 
                    });
            } else {
                try {
                    
                    ///////////
                    drawer.setVisible(true);
                    ////////////////////////
                    
                    AnchorPane box = FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/SidePane.fxml"));
                    drawer.setSidePane(box);
                    drawer.open();
                    fade.setVisible(true);
                    FadeTransition fadeIn=new FadeTransition(Duration.millis(800),fade);
                    fadeIn.setFromValue(0);
                    fadeIn.setToValue(0.4);
                    fadeIn.setCycleCount(1);
                    fadeIn.play();
                   // fade.setVisible(true);
                } catch (IOException ex) {
                  //  Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
    public static void humburgAction(ActionEvent actionEvent){
            
        transition.setRate(transition.getRate() * -1);
        transition.play();

        if (sDrawer.isShown()) {
            sDrawer.close();
            
            
            //////////
            sDrawer.setVisible(false);
            ///////////

            FadeTransition fadeOut=new FadeTransition(Duration.millis(800),sFade);
            fadeOut.setFromValue(0.4);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
            fadeOut.play();
            fadeOut.setOnFinished(l -> {
                sFade.setVisible(false); 
            });    
        }
    }
    
    
    
    @FXML
    private void sFadeAction(MouseEvent mouseEvent){
        transition.setRate(transition.getRate() * -1);
        transition.play();

        if (sDrawer.isShown()) {
            sDrawer.close();
            
            //////////
            sDrawer.setVisible(false);
            /////////////////////

            FadeTransition fadeOut=new FadeTransition(Duration.millis(800),sFade);
            fadeOut.setFromValue(0.4);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
            fadeOut.play();
            fadeOut.setOnFinished(l -> {
                sFade.setVisible(false); 
            });    
        }
    }
    
    @FXML
    public void imgMinClicked(MouseEvent event){
        ((Stage)(imgMin.getScene().getWindow())).setIconified(true);
    }
    @FXML
    public void imgReClicked(MouseEvent event){
        Stage st=((Stage)(imgMin.getScene().getWindow()));
        if(st.isFullScreen()){
            st.setFullScreen(false);
        }else{
            st.setFullScreen(true);
        }
    }
}
