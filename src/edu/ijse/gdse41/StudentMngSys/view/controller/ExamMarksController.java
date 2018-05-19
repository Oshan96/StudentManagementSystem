/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import edu.ijse.gdse41.StudentMngSys.dbControllers.ControllerFactory;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.BatchDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.ClassesDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.ExamDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.RegisterDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.StudentDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.BatchDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.ClassesDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.ExamDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.RegisterDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.StudentDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dto.ExamDTO;
import edu.ijse.gdse41.StudentMngSys.dto.ExamDetailDTO;
import edu.ijse.gdse41.StudentMngSys.dto.StudentDTO;
import edu.ijse.gdse41.StudentMngSys.idGenerators.IDGenerator;
import edu.ijse.gdse41.StudentMngSys.tableModel.ExamMarksTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author oshan
 */
public class ExamMarksController implements Initializable {

    @FXML
    AnchorPane paneCover;
    @FXML
    JFXButton btnSubmit;
    @FXML
    JFXButton btnImport;
    
    @FXML
    TextField txtExamId;

    @FXML
    JFXComboBox cmbSubject;
    @FXML
    JFXComboBox cmbYear;
    @FXML
    JFXComboBox cmbBatch;
    @FXML
    Label lblName;
    @FXML
    Label lblDob;
    @FXML
    Label lblTel;
    @FXML
    ImageView imgStudent;
    @FXML
    JFXDatePicker dtPckr;
            
    @FXML
    TableView tblExamMarks;
    @FXML
    TableColumn tblColEid;
    @FXML
    TableColumn tblColRid;
    @FXML
    TableColumn tblColStudentName;
    @FXML
    TableColumn tblColMarks;
    
    private ObservableList<ExamMarksTableModel> data= FXCollections.observableArrayList();
    
    private ArrayList<ExamDetailDTO> examList;
    
    private final RegisterDBController ctrlReg;
    private final BatchDBController ctrlBatch;
    private final ClassesDBController ctrlClasses;
    private final StudentDBController ctrlStudent;
    private final ExamDBController ctrlExam;
    
    private String sid;

    public ExamMarksController() {
        ctrlReg=(RegisterDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.REGISTER);
        ctrlBatch=(BatchDBControllerImpl) ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.BATCH);
        ctrlClasses=(ClassesDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.CLASSES);
        ctrlStudent=(StudentDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.STUDENT);
        ctrlExam=(ExamDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.EXAM);
        
        
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            txtExamId.setText(IDGenerator.getNewID("exam", "exId", "EX"));
        } catch (SQLException ex) {
            Logger.getLogger(ExamMarksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tblColEid.setCellValueFactory(new PropertyValueFactory("eid"));
        tblColRid.setCellValueFactory(new PropertyValueFactory("rid"));
        tblColStudentName.setCellValueFactory(new PropertyValueFactory("studentName"));
        tblColMarks.setCellValueFactory(new PropertyValueFactory("marks"));
        
        tblExamMarks.setItems(data);
        
        try {
            loadSubjects();
        } catch (SQLException ex) {
            Logger.getLogger(ExamMarksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    private void loadTableData(ArrayList<ExamDetailDTO> dtoList){
        data.clear();
        for(ExamDetailDTO exam:dtoList){
                ExamMarksTableModel tbl=new ExamMarksTableModel();
                tbl.setEid(exam.getEid());
                tbl.setRid(exam.getRid());
               // tbl.setBid(exam.getBid());
                tbl.setStudentName(exam.getStudentName());
//                tbl.setNic(exam.getNic());
                tbl.setMarks(exam.getMarks());
                
                data.add(tbl);
            }
    }
   
    
    private void loadSubjects() throws SQLException{

        ArrayList<String> subjects=ctrlClasses.getSubjects();
        ObservableList<String> subs=FXCollections.observableArrayList(subjects);
        //cmbSubject.getItems().clear();
        cmbSubject.setItems(subs);
        loadYears();
    }
    
    private void loadYears() throws SQLException{
        ArrayList<String> yearList=ctrlBatch.getYear((String)cmbSubject.getValue());
        ObservableList<String> years=FXCollections.observableArrayList(yearList);
        cmbYear.getItems().clear();
        for(String year:years){
            cmbYear.getItems().add(year);
        }
        loadBatchIds();
    }
    
    private void loadBatchIds() throws SQLException{
        ArrayList<String> bidList=ctrlBatch.getBatchIds((String)cmbSubject.getValue(),(String) cmbYear.getValue());
        ObservableList<String> bids=FXCollections.observableArrayList(bidList);
        cmbBatch.getItems().clear();
        for(String bid:bids){
            cmbBatch.getItems().add(bid);
        }
    }
    
    private boolean readFromExcel(File file) throws SQLException{
        String lastEdId=IDGenerator.getNewID("exam_detail", "eid", "ED");
        try {
            examList=new ArrayList<>();
            
            FileInputStream input=new FileInputStream(file);
            XSSFWorkbook workbook=new XSSFWorkbook(input);
            XSSFSheet sheet=workbook.getSheetAt(0);
            
            Row row;
            Cell cell;
            for(int i=3;i<sheet.getLastRowNum();i++){
                row=sheet.getRow(i);
                ExamDetailDTO examDetail=new ExamDetailDTO();
                examDetail.setEid(lastEdId);
                examDetail.setExId(IDGenerator.getNewID("exam", "exId", "EX"));
                for(int j=0;j<3;j++){
                    cell=row.getCell(j);
                    //examDetail.setBid((String)cmbBatch.getValue());
                    switch(j){
                        case 0:
                            examDetail.setSid(cell.getStringCellValue());
//                            cell.getStringCellValue();
                            break;
                        case 1:
                            examDetail.setStudentName(cell.getStringCellValue());
//                            cell.getStringCellValue();
                            break;
                        case 2:
                            examDetail.setMarks((int)cell.getNumericCellValue());
//                            cell.getNumericCellValue();
                            break;
                    }
                    
                }
                examDetail.setRid(ctrlReg.getId(examDetail.getSid(),(String) cmbBatch.getValue()));
                examList.add(examDetail);
                lastEdId=IDGenerator.getNewID(lastEdId, "ED");
            }

            input.close();
            loadTableData(examList);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExamMarksController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExamMarksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return true;
    }
    @FXML
    private void cmbSubjectState(){
        try {
            loadYears();
        } catch (SQLException ex) {
            Logger.getLogger(ExamMarksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void cmbYearState(){
        try {
            loadBatchIds();
        } catch (SQLException ex) {
            Logger.getLogger(ExamMarksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void btnUpdateAction(ActionEvent actionEvent){
        ExamDTO exam=new ExamDTO(txtExamId.getText(), dtPckr.getValue().format(DateTimeFormatter.ISO_DATE), (String)cmbBatch.getValue(), examList);
        try {
            if(ctrlExam.add(exam)){
                Notifications notify=Notifications.create()
                        .title("Exam Marks")
                        .text("Exam Details has been successfully added")
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.BOTTOM_RIGHT)
                        .darkStyle();
                notify.showInformation();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamMarksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void btnImportAction(ActionEvent event){
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Student Marks Sheet");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("ExcelWorkbook", "*.xlsx"));
        File file= fileChooser.showOpenDialog(paneCover.getScene().getWindow());
        if(file!=null){
            try {
                readFromExcel(file);
            } catch (SQLException ex) {
                Logger.getLogger(ExamMarksController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    public void tblMouseClicked(){
        try {
            ExamMarksTableModel tbl=(ExamMarksTableModel)tblExamMarks.getSelectionModel().getSelectedItem();
            lblName.setText(tbl.getStudentName());
            String rid=tbl.getRid();
            String sid=ctrlReg.getSid(rid);
            StudentDTO student=ctrlStudent.getAll(sid);
            imgStudent.setImage(new Image(String.format("file:src/edu/ijse/gdse41/StudentMngSys/other/studentPic/%s.png", sid)));
            lblDob.setText(student.getDob());
            lblTel.setText(student.getTel1());
        } catch (SQLException ex) {
            Logger.getLogger(ExamMarksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
