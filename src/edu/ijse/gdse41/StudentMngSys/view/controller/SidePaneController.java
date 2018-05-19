/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.view.controller;

import com.jfoenix.controls.JFXButton;
import edu.ijse.gdse41.StudentMngSys.other.backup.BackUp;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author oshan
 */
public class SidePaneController implements Initializable {
    
    private AnchorPane dash;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private JFXButton btnView;
    
    @FXML
    private JFXButton btnReg;
    
    @FXML
    private JFXButton btnProgress;
    
    @FXML
    private JFXButton btnReports;
    
    @FXML
    private JFXButton btnExamMarks;
    
    @FXML
    void btnProAction(ActionEvent event){
        
        try {
            dash = FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/StudentProgress.fxml"));
            DashboardController.rootPane.getChildren().setAll(dash);
        } catch (IOException ex) {
            Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DashboardController.humburgAction(event);
    }

    @FXML
    void btnViewAction(ActionEvent event) {
        
        try {
            dash = FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/ViewRegistrations.fxml"));
            DashboardController.rootPane.getChildren().setAll(dash);
        } catch (IOException ex) {
            Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DashboardController.humburgAction(event);
    }
    
    @FXML
    void btnRegAction(ActionEvent event) {
        
        try {
            dash = FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/RegisterStudent.fxml"));
            DashboardController.rootPane.getChildren().setAll(dash);
        } catch (IOException ex) {
            Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DashboardController.humburgAction(event);
        
    }
    
    @FXML
    void btnReportsAction(ActionEvent event){
        try {
            dash = FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/Reports.fxml"));
            DashboardController.rootPane.getChildren().setAll(dash);
        } catch (IOException ex) {
            Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DashboardController.humburgAction(event);
    }
    
    @FXML
    void btnExamMarksAction(ActionEvent event){
        try {
            dash = FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/ExamMarks.fxml"));
            DashboardController.rootPane.getChildren().setAll(dash);
        } catch (IOException ex) {
            Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DashboardController.humburgAction(event);
    }
    
    @FXML
    void btnMarkAttendanceAction(ActionEvent event){
        try {
            dash = FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/MarkAttendance.fxml"));
            DashboardController.rootPane.getChildren().setAll(dash);
        } catch (IOException ex) {
            Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DashboardController.humburgAction(event);
    }
    
    @FXML
    public void btnBackupAction(ActionEvent event){
        ButtonType backup= new ButtonType("Backup");
        ButtonType restore = new ButtonType("Restore");
        
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/util/dialogs.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("dialog-pane");
                alert.getDialogPane().getButtonTypes().clear();
                alert.getDialogPane().getButtonTypes().addAll(backup,restore);
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setHeaderText("Backup/Rstore");
                alert.setContentText("Backup or Restore?");
                Optional<ButtonType> result=alert.showAndWait();
                
        if(result.get()==backup){
            try {
                DirectoryChooser dirChsr=new DirectoryChooser();
                dirChsr.setTitle("Choose Backup save directory");
                File file=dirChsr.showDialog(null);
                String path=file.getAbsolutePath();
                System.out.println(path);
                boolean bool =new BackUp().backupDB(path);
                if(bool){
                    Notifications notify=Notifications.create()
                        .title("Database Backup")
                        .text("Successfully BackedUp database")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT)
                        .darkStyle();
                notify.showInformation();
                }else{
                    Notifications notify=Notifications.create()
                        .title("Database Backup")
                        .text("Database Backup failed")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT)
                        .darkStyle();
                notify.showInformation();
                }
            } catch (IOException ex) {
                Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(result.get()==restore){
            FileChooser fileChsr=new FileChooser();
            fileChsr.setTitle("Select a backUp to restore");

            fileChsr.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQL", "*.sql"));
            File file= fileChsr.showOpenDialog(null);
            String source=file.getAbsolutePath();
            if(file!=null){
                try {
                    boolean bool=new BackUp().restoreDB(source);
                    if(bool){
                        Notifications notify=Notifications.create()
                            .title("Database Restore")
                            .text("Successfully restored database")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.BOTTOM_RIGHT)
                            .darkStyle();
                        notify.showInformation();
                    }else{
                        Notifications notify=Notifications.create()
                            .title("Database Backup")
                            .text("Database restoring failed")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.BOTTOM_RIGHT)
                            .darkStyle();
                        notify.showInformation();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}