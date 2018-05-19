/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.view.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import edu.ijse.gdse41.StudentMngSys.dbControllers.ControllerFactory;
import edu.ijse.gdse41.StudentMngSys.idGenerators.IDGenerator;
import edu.ijse.gdse41.StudentMngSys.dto.StudentDTO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.BatchDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.ClassesDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.StudentDBController;
import edu.ijse.gdse41.StudentMngSys.other.validation.FieldValidator;
import java.time.format.DateTimeFormatter;

/**
 * FXML Controller class
 *
 * @author oshan
 */
public class RegisterStudentController implements Initializable {

    @FXML
    TextField txtName;
    @FXML
    TextField txtTel1;
    @FXML
    TextField txtTel2;
    @FXML
    TextField txtAddress;
    @FXML
    TextField txtNic;
    @FXML
    TextField txtPic;
    @FXML
    JFXDatePicker dtPkr;
    @FXML
    JFXComboBox cmbClass;
    @FXML
    JFXComboBox cmbBatch;
    @FXML
    JFXComboBox cmbSection;
    @FXML
    JFXButton btnReg;
    @FXML
    JFXButton btnBrowse;
    @FXML
    TextField txtGuardianName;
    @FXML
    JFXRadioButton rdBtnMale;
    @FXML
    JFXRadioButton rdBtnFemale;
    
    @FXML
    Label lblAdmission;
    
    @FXML
    AnchorPane pnlMain;
    
    StudentDBController ctrlStudent;
    BatchDBController ctrlBatch;
    ClassesDBController ctrlClasses;
    
    private File filePic;

    public RegisterStudentController() {
        ctrlBatch=(BatchDBController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.BATCH);
        ctrlClasses=(ClassesDBController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.CLASSES);
        ctrlStudent=(StudentDBController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.STUDENT);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println(dtPkr.getValue().toString());
        loadSections();
        loadClasses();
        loadBatches();
    }
    
    @FXML
    public void cmbSectionState(){
        loadClasses();
    }
    @FXML
    public void cmbClassState(){
        loadBatches();
    }
    
    @FXML
    public void cmbBatchState(){
        try {
            String batchId=(String)cmbBatch.getValue();
            lblAdmission.setText(ctrlBatch.getAdmission(batchId));
           // System.out.println(ctrlBatch.getAdmission(batchId));
        } catch (SQLException ex) {
            Logger.getLogger(RegisterStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadSections(){
        List<String> section=new ArrayList<>();
        try {
            section=ctrlClasses.getSections();
            ObservableList<String> sec=FXCollections.observableArrayList(section);
            cmbSection.getItems().clear();
            for(String s : sec){
                cmbSection.getItems().add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RegisterStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadClasses(){
        List<String> classes=new ArrayList<>();
        try{ 
            String batch=(String)cmbSection.getValue();
           // System.out.println(batch);
            classes=ctrlClasses.getClassNames(batch);
            ObservableList<String> cls=FXCollections.observableArrayList(classes);
            cmbClass.getItems().clear();
            for(String c:cls){
                cmbClass.getItems().add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadBatches(){
        List<String> batches=new ArrayList<>();
        try{
            String cls=(String)cmbClass.getValue();
            batches=ctrlClasses.getClassBatches(cls);
            ObservableList<String> btch=FXCollections.observableArrayList(batches);
            cmbBatch.getItems().clear();
            for(String b:btch){
                cmbBatch.getItems().add(b);
            }
                     
        } catch (SQLException ex) {
            Logger.getLogger(RegisterStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void txtTel1Validation(KeyEvent evt){
        if(txtTel1.getText().toCharArray().length==11){
            if(!txtTel1.getText().matches("\\d{3}-\\d{7}")){
                System.out.println("A");
            }else{
                System.out.println("B");
            }
        }
    }
    
    @FXML
    public void txtTel2Validation(KeyEvent evt){
        if(txtTel2.getText().toCharArray().length==11){
            if(!txtTel2.getText().matches("\\d{3}-\\d{7}")){
                System.out.println("A");
            }else{
                System.out.println("B");
            }
        }
//        RegistrationCardController.number=txtNic.getText();
//        System.out.println(RegistrationCardController.number);
    }
    
    @FXML
    public void txtNicValidation(KeyEvent evt){
        if(!txtNic.getText().matches("\\b\\d{9}[Vv]\\b")){
            System.out.println("Wrong");
        }else{
            System.out.println("Correct");
        }
    }
    
    @FXML
    public void btnBrowseAction(ActionEvent actionEvent){
        filePic=showOpenFile();
        if(filePic!=null){
            txtPic.setText(filePic.getAbsolutePath());
        }
    }
    
    
    @FXML
    public void btnRegisterEvent(ActionEvent evt){
        
        boolean isTel2=FieldValidator.validateTelephoneNumber(txtTel2);
        boolean isNic=FieldValidator.validateNIC(txtNic);
        boolean isTel1=FieldValidator.validateTelephoneNumber(txtTel1);

        if(!isNic || !isTel1 || !isTel2){
            Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/util/dialogs.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("dialog-pane");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setHeaderText("Registration");
                alert.setContentText("Please fill the incorrect details");
                alert.showAndWait();
            
            return;
        }
        
        try {
            StudentDTO student=new StudentDTO();
            String regId=IDGenerator.getNewID("reg_detail", "rid", "R");
            String sid=IDGenerator.getNewID("student", "sid", "S");
            student.setRegId(regId);
            student.setSid(sid); 
            student.setAddress(txtAddress.getText());
            student.setDob(dtPkr.getValue().format(DateTimeFormatter.ISO_DATE));
            char[] y=dtPkr.getValue().toString().toCharArray();
            String year="";
            for(int i=0;i<4;i++){
                year+=y[i];
            }
            int age=LocalDate.now().getYear()-Integer.parseInt(year);
            student.setAge(age);
            student.setName(txtName.getText());
            student.setNic(txtNic.getText());
            student.setTel1(txtTel1.getText());
            student.setTel2(txtTel2.getText());

            if(rdBtnFemale.isSelected()){
                student.setGender("Female");
            }else{
                student.setGender("Male");
            }
            student.setGuardianName(txtGuardianName.getText());
            String batchId=(String)cmbBatch.getValue();
            
            boolean isExisting=ctrlStudent.searchNIC(student.getNic());
            if(!isExisting){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/util/dialogs.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("dialog-pane");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setHeaderText("Registration");
                alert.setContentText("Student Already Exist!");
                alert.showAndWait();
                return;
            }
            
            if(ctrlStudent.addStudent(student, batchId)){
                
                try {
                    BitMatrix bt=new Code128Writer().encode(sid, BarcodeFormat.CODE_128, 266, 60);
                    String file=String.format("src/edu/ijse/gdse41/StudentMngSys/other/%s.png", sid);
                    MatrixToImageWriter.writeToFile(bt, "png", new File(file));

                   ImageIO.write(SwingFXUtils.fromFXImage(new Image("file:"+filePic.getAbsolutePath()), null), "png", new File("src/edu/ijse/gdse41/StudentMngSys/other/studentPic/"+sid+".png"));
                   
                } catch (WriterException ex) {
                    Logger.getLogger(ExamMarksController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ExamMarksController.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println(new File("").getAbsolutePath());
                

                Notifications notify=Notifications.create()
                        .title("Student Registration")
                        .text(student.getName()+" has successfully been regsistered")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT)
                        .darkStyle();
                notify.showInformation();
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/RegistrationCard.fxml"));
                Parent root1=fxmlLoader.load();
                RegistrationCardController ct=fxmlLoader.getController();
                
                
                Stage stage=new Stage();
                stage.setScene(new Scene(root1));
                stage.initStyle(StageStyle.UNDECORATED);
                
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/edu/ijse/gdse41/StudentMngSys/resources/icons/LogoIcon.png")));
                stage.show();
                
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/util/dialogs.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("dialog-pane");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setHeaderText("Registration");
                alert.setContentText("Student Registration Failed");
                alert.showAndWait();
            }
            
            //System.out.println(dtPkr.getValue().toString());
            
        } catch (SQLException ex) {
            Logger.getLogger(RegisterStudentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegisterStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public File showOpenFile(){
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Student Photo");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG (*.png)", "*.png"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPEG (*.jpeg)", "*.jpeg"));
        return fileChooser.showOpenDialog(pnlMain.getScene().getWindow());
    }
    
    
}
