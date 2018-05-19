/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.view.controller;

import com.jfoenix.controls.JFXButton;
import edu.ijse.gdse41.StudentMngSys.dbControllers.ControllerFactory;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.StudentDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dto.StudentDTO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.StudentDBController;
import java.awt.Desktop;

/**
 * FXML Controller class
 *
 * @author oshan
 */
public class RegistrationCardController implements Initializable {
    
    @FXML
    ImageView btnClose;
    @FXML
    JFXButton btnSave;
    @FXML
    AnchorPane pnlScreen;
    
    
    @FXML
    ImageView imgStudent;
    @FXML
    ImageView imgBarcode;
    @FXML
    Label lblNic;
    @FXML
    Label lblName;
    @FXML
    Label lblAddress;
    @FXML
    Label lblDob;
    @FXML
    Label lblTel;
    @FXML
    Label lblRegDate;
    @FXML
    Label lblMNic;
    @FXML
    Label lblMName;
    @FXML
    Label lblMAddress;
    @FXML
    Label lblMDob;
    @FXML
    Label lblMTel;
    @FXML
    Label lblMRegDate;
    @FXML
    Label lblTitle;
    
    private String nic;
    private String name;
    private String address;
    private String dob;
    private String tel;
    private String sid;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
    
    
    
    private StudentDBController ctrlStudent;
    
    public static String number;
    
    private WritableImage image;

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public RegistrationCardController() {
        
        ctrlStudent=(StudentDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.STUDENT);
        //this.nic=RegistrationCardController.number;
        
       // setCardDetails(this.nic);
        
        setCardDetails();

        System.out.println(this.nic);
       
    }
    
    private void setCardDetails(){
        try {
            StudentDTO student=ctrlStudent.getCardDetails();
            
            if(student!=null){
                this.nic=student.getNic();
                this.name=student.getName();
                this.address=student.getAddress();
                this.tel=student.getTel1();
                this.dob=student.getDob();
                this.sid=student.getSid();
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setCardDetails(String nicNo){
        try {
            StudentDTO student=ctrlStudent.getCardDetails(nicNo);
            
            if(student!=null){
                this.name=student.getName();
                this.address=student.getAddress();
                this.tel=student.getTel1();
                this.dob=student.getDob();
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void Close(MouseEvent mouseEvent){
        try{
            ((Stage)(((ImageView)mouseEvent.getSource()).getScene().getWindow())).close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void captureScreen(AnchorPane pane){
        pane.setBackground(Background.EMPTY);
        image=pane.snapshot(new SnapshotParameters(), null);
        saveImage(pane);
    }
    
    public void saveImage(AnchorPane pane){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(pane.getScene().getWindow());
        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".png")) {
            file = new File(file.getPath() + ".png");
        }
        
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            final Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(file);
            } else {
                throw new UnsupportedOperationException("Open action not supported");
            }
            ((Stage)(pane.getScene().getWindow())).close();
        } catch (IOException ex) {
            Logger.getLogger(ExamMarksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    }
    
    @FXML
    public void btnSaveAction(ActionEvent actionEvent){
        //saveImage(pnlScreen);
        captureScreen(pnlScreen);
    }
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String file=String.format("file:src/edu/ijse/gdse41/StudentMngSys/other/%s.png", this.sid);
        System.out.println(file);
        Image img=new Image(file);
        imgBarcode.setImage(img);
        imgStudent.setImage(new Image(String.format("file:src/edu/ijse/gdse41/StudentMngSys/other/studentPic/%s.png", this.sid)));
        lblAddress.setText(this.address);
        lblDob.setText(this.dob);
        lblName.setText(this.name);
        lblNic.setText(this.nic);
        lblTel.setText(this.tel);
        
        lblRegDate.setText(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
//        captureScreen(pnlScreen);
    }    
    
}
