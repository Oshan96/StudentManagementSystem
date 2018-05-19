/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import edu.ijse.gdse41.StudentMngSys.dbControllers.ControllerFactory;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.BatchDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.ClassesDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.RegisterDBController;
import edu.ijse.gdse41.StudentMngSys.dto.StudentDTO;
import edu.ijse.gdse41.StudentMngSys.tableModel.RegisterStudentTableModel;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.StudentDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.BatchDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.ClassesDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.RegisterDBControllerImpl;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author oshan
 */
public class ViewRegistrationsController implements Initializable {

    @FXML 
    TableView tblRegStudent;
    
    @FXML
    TableColumn tblColRegId;
    @FXML
    TableColumn tblColName;
    @FXML
    TableColumn tblColDob;
    @FXML
    TableColumn tblColAge;
    @FXML
    TableColumn tblColNic;
    @FXML
    TableColumn tblColTel1;
    @FXML
    TableColumn tblColTel2;
    @FXML
    TableColumn tblColAddress;
    @FXML
    JFXComboBox cmbSection;
    @FXML
    JFXComboBox cmbClass;
    @FXML
    JFXComboBox cmbBatch;
    @FXML
    Label lblAdmission;
    
    StudentDBController ctrlStudent;
    ClassesDBController ctrlClasses;
    
    @FXML
    JFXButton btnRegister;
    

    public ViewRegistrationsController() {
        ctrlStudent=(StudentDBController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.STUDENT);
        ctrlClasses=(ClassesDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.CLASSES);
    }

    private final ObservableList<RegisterStudentTableModel> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            tblColRegId.setCellValueFactory(new PropertyValueFactory("sid"));
            tblColName.setCellValueFactory(new PropertyValueFactory("name"));
            tblColDob.setCellValueFactory(new PropertyValueFactory("dob"));
            tblColAge.setCellValueFactory(new PropertyValueFactory("age"));
            tblColNic.setCellValueFactory(new PropertyValueFactory("nic"));
            tblColTel1.setCellValueFactory(new PropertyValueFactory("tel1"));
            tblColTel2.setCellValueFactory(new PropertyValueFactory("tel2"));
            tblColAddress.setCellValueFactory(new PropertyValueFactory("address"));

            tblRegStudent.setItems(data);
            ArrayList<StudentDTO> students=null;
            students=ctrlStudent.getTableDetails();
       
            for(StudentDTO stud:students){
                RegisterStudentTableModel rstm=new RegisterStudentTableModel();
                rstm.setSid(stud.getSid());
                rstm.setName(stud.getName());
                rstm.setDob(stud.getDob());
                rstm.setAge(stud.getAge());
                rstm.setNic(stud.getNic());
                rstm.setTel1(stud.getTel1());
                rstm.setTel2(stud.getTel2());
                rstm.setAddress(stud.getAddress());
                data.add(rstm);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(RegisterStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        
        BatchDBController ctrlBatch=(BatchDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.BATCH);
        
        try {
            String batchId=(String)cmbBatch.getValue();
            lblAdmission.setText(ctrlBatch.getAdmission(batchId));
            System.out.println(ctrlBatch.getAdmission(batchId));
        } catch (SQLException ex) {
            Logger.getLogger(RegisterStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    @FXML
    public void btnRegisterAction(ActionEvent evt){
        try {
            RegisterDBController ctrlReg=(RegisterDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.REGISTER);
            
            RegisterStudentTableModel tbl=(RegisterStudentTableModel) tblRegStudent.getSelectionModel().getSelectedItem();
            String sid=tbl.getSid();

            String batchId=(String)cmbBatch.getValue();
                 
            if(ctrlReg.RegisterForClass(batchId, sid)){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/util/dialogs.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("dialog-pane");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setHeaderText("Registration");
                alert.setContentText("Student Successfully Registered");
                alert.showAndWait();

            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/util/dialogs.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("dialog-pane");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setHeaderText("Registration");
                alert.setContentText("Student Registration Failed");
                alert.showAndWait();
            }
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
    
}
