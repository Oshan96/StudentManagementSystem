/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.view.controller;

import com.jfoenix.controls.JFXComboBox;
import edu.ijse.gdse41.StudentMngSys.dbControllers.ControllerFactory;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.AttendanceDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.RegisterDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dto.AttendanceDTO;
import edu.ijse.gdse41.StudentMngSys.tableModel.AttendanceTablelModel;
import java.net.URL;
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
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.AttendanceDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.BatchDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.ClassesDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.PaymentDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.RegisterDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.StudentDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.BatchDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.ClassesDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.PaymentDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.StudentDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dto.PaymentDTO;
import edu.ijse.gdse41.StudentMngSys.idGenerators.IDGenerator;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author oshan
 */
public class MarkAttendanceController implements Initializable {

    @FXML
    TableView tblAttendance;
    @FXML
    TableColumn tblColStudentName;
    @FXML
    TableColumn tblColNic;
    @FXML
    TableColumn tblColTel;
    @FXML
    TableColumn tblColSubject;
    @FXML
    TableColumn tblColHall;
    @FXML
    JFXComboBox cmbSubject;
    @FXML
    TextField txtSid;
    @FXML
    TextField txtStudentName;

    @FXML
    TextField txtRegId;
    @FXML
    Label lblPDate;
    @FXML
    Label lblPaymentStatus;
    @FXML
    Label lblClassFee;

    
    @FXML
    JFXComboBox cmbBatchId;
    
    private ObservableList<AttendanceTablelModel> data= FXCollections.observableArrayList();
    
    private final RegisterDBController ctrlReg;
    private final AttendanceDBController ctrlAttendance;
    private final BatchDBController ctrlBatch;
    private final ClassesDBController ctrlClasses;
    private final StudentDBController ctrlStudent;
    
    private String fee;

    public MarkAttendanceController() {
        ctrlReg=(RegisterDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.REGISTER);
        ctrlAttendance=(AttendanceDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.ATTENDANCE);
        ctrlBatch=(BatchDBControllerImpl) ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.BATCH);
        ctrlClasses=(ClassesDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.CLASSES);
        ctrlStudent=(StudentDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.STUDENT);
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblColStudentName.setCellValueFactory(new PropertyValueFactory("studentName"));
        tblColNic.setCellValueFactory(new PropertyValueFactory("nic"));
        tblColTel.setCellValueFactory(new PropertyValueFactory("tel"));
        tblColSubject.setCellValueFactory(new PropertyValueFactory("subject"));
        tblColHall.setCellValueFactory(new PropertyValueFactory("hall"));
        
        tblAttendance.setItems(data);
        
        try {
            loadSubjects();
            loadTableDetails();
        } catch (Exception ex) {
            Logger.getLogger(MarkAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void txtSidAction(ActionEvent event){
        try {
            txtStudentName.setText(ctrlStudent.getName(txtSid.getText()));
            String rid=ctrlReg.getId(txtSid.getText(), (String)cmbBatchId.getValue());
            txtRegId.setText(rid);
            updateData();
        } catch (SQLException ex) {
            Logger.getLogger(MarkAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void cmbSubjectState(){
        try {
            String cid=ctrlClasses.getCid((String)cmbSubject.getValue());
            ArrayList<String> bidList=ctrlBatch.getBatchIds(cid);
            cmbBatchId.getItems().clear();
            for(String bid:bidList){
                cmbBatchId.getItems().add(bid);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadSubjects() throws SQLException{
        ArrayList<String> subjects=ctrlClasses.getSubjects();
        ObservableList<String> subs=FXCollections.observableArrayList(subjects);
        cmbSubject.getItems().clear();
        for(String sub:subs){
            cmbSubject.getItems().add(sub);
        }
    }
    
    
    private void updateData() throws SQLException{
        PaymentDBController ctrlPayment=(PaymentDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.PAYMENT);

        lblPDate.setText(ctrlPayment.getLastPayment(txtRegId.getText()));
        
        fee=ctrlBatch.getFee((String)cmbBatchId.getValue());
        if(fee==null){
            lblClassFee.setText("");
        }else{
            lblClassFee.setText("Rs. "+fee);
        }
        try{
            LocalDate date=LocalDate.parse(lblPDate.getText());
            if(LocalDate.now().getMonthValue()-date.getMonthValue()>0){
            lblPaymentStatus.setText("Not Paid");
            
            }else{
                lblPaymentStatus.setText("Paid");
                
            }
        }catch(DateTimeParseException ex){
            lblPaymentStatus.setText("Not Paid");
            
        }
        btnSubmitAction();
    }
    
    

    private void loadTableDetails() throws Exception{
        ArrayList<AttendanceDTO> attendance=ctrlAttendance.getTableDetails();
        if(attendance!=null){
            data.clear();
            for(AttendanceDTO att:attendance){
                AttendanceTablelModel tbl=new AttendanceTablelModel();
                
                tbl.setStudentName(att.getStudentName());
                tbl.setNic(att.getNic());
                tbl.setTel(att.getTel());
                tbl.setSubject(att.getSubject());
                tbl.setHall(att.getHall());
                
                data.add(tbl);
            }
        }
    }
    
    public void btnPayAction(){
        try {
            
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/util/dialogs.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("dialog-pane");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setTitle("Payment");
                alert.setHeaderText("Payment Confirmation");
                alert.setContentText("Proceed Payment?");
                Optional<ButtonType> result= alert.showAndWait();
            
            if(result.get()==ButtonType.OK){
                String pid=IDGenerator.getNewID("payment", "pid", "P");
                String pDate=LocalDate.now().format(DateTimeFormatter.ISO_DATE);
                String rid=txtRegId.getText();
                double amount=Double.parseDouble(fee);

                PaymentDTO pay=new PaymentDTO(pid, pDate, rid, amount);
                PaymentDBController ctrlPay=(PaymentDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.PAYMENT);

                if(ctrlPay.add(pay)){
                    Notifications notify=Notifications.create()
                            .title("Payment")
                            .text("Successfully Paid")
                            .hideAfter(Duration.seconds(1.5))
                            .position(Pos.BOTTOM_RIGHT)
                            .darkStyle();
                    notify.showInformation();
                    
                    lblPaymentStatus.setText("Paid");
                    lblPDate.setText(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
                    
                    fee="";
                }else{
                    Notifications notify=Notifications.create()
                            .title("Payment")
                            .text("Payment Failed!")
                            .hideAfter(Duration.seconds(2))
                            .position(Pos.BOTTOM_RIGHT)
                            .darkStyle();
                    notify.showInformation();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public void btnSubmitAction(){
        
        if(lblPaymentStatus.getText().equals("Not Paid")){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/util/dialogs.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("dialog-pane");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setTitle("Payment");
                alert.setHeaderText("Monthly Payment");
                alert.setContentText("Do you want to pay now?");
                Optional<ButtonType> result= alert.showAndWait();
            if(result.get()==ButtonType.OK){
                btnPayAction();
            }
        }
        
        try {
            String aid=IDGenerator.getNewID("attendance", "aid", "A");
            AttendanceDTO attendance=new AttendanceDTO(aid, txtRegId.getText(), (String)cmbBatchId.getValue(), LocalDate.now().format(DateTimeFormatter.ISO_DATE));
            if(ctrlAttendance.add(attendance)){
                Notifications notify=Notifications.create()
                            .title("Attendance")
                            .text("Attendance Marked")
                            .hideAfter(Duration.seconds(1.5))
                            .position(Pos.BOTTOM_RIGHT)
                            .darkStyle();
                    notify.showInformation();
                    
                clear();
                loadTableDetails();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MarkAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clear(){
        txtSid.clear();
        txtStudentName.clear();
        txtRegId.clear();
        lblPaymentStatus.setText("");
        lblPDate.setText("");
        lblClassFee.setText("");
        fee="";
    }
    
}
