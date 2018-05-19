/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.ijse.gdse41.StudentMngSys.dbControllers.ControllerFactory;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.LoginDBControllerImpl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author oshan
 */
public class LoginController implements Initializable {

    @FXML
    JFXTextField txtUsername;
    
    @FXML
    JFXPasswordField pwdUsername;
    
    @FXML
    JFXButton btnLogin;
    
    LoginDBControllerImpl ctrlLogin;

    public LoginController() {
        ctrlLogin=(LoginDBControllerImpl) ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.LOGIN);
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
    public void btnLoginAction(ActionEvent actionEvent){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/Dashboard.fxml"));
            Parent root1=(Parent) fxmlLoader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.UNDECORATED);
            String username=txtUsername.getText();
            String password=pwdUsername.getText();
            boolean bool=ctrlLogin.Login(username, password);
            
           if(bool){ 
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/edu/ijse/gdse41/StudentMngSys/resources/icons/LogoIcon.png")));
                stage.show();
                Notifications notify=Notifications.create()
                        .title("User Logged In")
                        .text(username+" has successfully logged in")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT)
                        .darkStyle();
                notify.showInformation();
                
           }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/util/dialogs.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("dialog-pane");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setTitle("Login Error!");
                alert.setHeaderText("Login Failed!");
                alert.setContentText("Username/Password does not match");
                txtUsername.setText("");
                pwdUsername.setText("");
                txtUsername.requestFocus();
                alert.showAndWait();
           }  
        }catch(Exception e){
             e.printStackTrace();
        }
    }

    
}
