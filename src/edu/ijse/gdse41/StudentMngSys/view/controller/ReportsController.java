/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import edu.ijse.gdse41.StudentMngSys.db.ConnectionFactory;
import edu.ijse.gdse41.StudentMngSys.dbControllers.ControllerFactory;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.BatchDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.ClassesDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.RegisterDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.StudentDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.BatchDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.ClassesDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.RegisterDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.StudentDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dto.StudentDTO;
import edu.ijse.gdse41.StudentMngSys.main.StartProject;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * FXML Controller class
 *
 * @author oshan
 */
public class ReportsController implements Initializable {
    
    @FXML
    private TabPane pnTab;
    @FXML
    Tab tbIncomeReports;
    @FXML
    Tab tbExamReports;
    @FXML
    AnchorPane pnIncome;
    @FXML
    AnchorPane pnExam;
    @FXML
    JFXButton btnGen;        
    @FXML
    JFXButton btnTotalIncome;
    @FXML
    JFXButton btnMonthlyIncome;        
    @FXML
    TextField txtMonth;
    @FXML
    TextField txtYear;
    
    @FXML
    TextField txtSearch;
    @FXML
    Label txtStudentName;
    @FXML
    Label txtAddress;
    @FXML
    Label txtDob;
    @FXML
    Label txtGender;
    @FXML
    Label txtNic;
    @FXML
    Label txtSTel;
    @FXML
    Label txtGuardianName;
    @FXML
    Label txtGTel;

    @FXML
    JFXButton btnAnnualIncome;
    @FXML
    ImageView imgStudent;
    @FXML
    JFXComboBox cmbClass;
    
    @FXML
    JFXDrawer drawer;
    @FXML
    AnchorPane anchDrawer;
    
    @FXML
    Label lblYear;
    @FXML
    Label lblMonth;

    
    @FXML
    BarChart chrtBar;
    
    ObservableList<BarChart.Series> data=FXCollections.observableArrayList();
    
    private ClassesDBController ctrlClasses;
    private StudentDBController ctrlStudent;
    private RegisterDBController ctrlReg;
    private BatchDBController ctrlBatch;
    
    /**
     * Initializes the controller class.
     */
    
    Connection con;
    
    private void setStudentDetails(String sid) throws SQLException{
        StudentDTO student=ctrlStudent.getAll(sid);
        imgStudent.setImage(new Image(String.format("file:src/edu/ijse/gdse41/StudentMngSys/other/studentPic/%s.png", sid),109,130,true,true));
        txtStudentName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtDob.setText(student.getDob());
        txtGender.setText(student.getGender());
        txtNic.setText(student.getNic());
        txtSTel.setText(student.getTel1());
        txtGuardianName.setText(student.getGuardianName());
        txtGTel.setText(student.getTel2());
    }
    
    private void setChartData(String sid) throws SQLException{
        
        String cid = ctrlClasses.getCid((String)cmbClass.getValue());
        String bid = ctrlBatch.getFilteredId(sid,cid);
        String rid = ctrlReg.getId(sid,bid);
        
        if(sid.equals("")){
            return;
        }
        data.clear();
        data=ctrlClasses.getExamData(rid);
        chrtBar.setData(data);
        
        chrtBar.setTitle((String)cmbClass.getValue());
        
    }
   @FXML
    private void btnGenAction(ActionEvent actionEvent){
//        setContent();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con=ConnectionFactory.getInstance().getConnection();
        
        
        ctrlClasses=(ClassesDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.CLASSES);
        ctrlStudent=(StudentDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.STUDENT);
        ctrlBatch=(BatchDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.BATCH);
        ctrlReg=(RegisterDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.REGISTER);
        
        chrtBar.setData(data);
        btnTotalIncome.setGraphic(new ImageView("/edu/ijse/gdse41/StudentMngSys/resources/icons/TotalIncome.png"));
        
        drawer.setSidePane(anchDrawer);
        drawer.close();
    }
    
    @FXML
    private void btnSearchAction(ActionEvent event){
        try {
            String sid=txtSearch.getText();
            setStudentDetails(sid);
            loadSubjects(sid);
        } catch (SQLException ex) {
            Logger.getLogger(StudentProgressController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void cmbClassState(){
        try {
            setChartData(txtSearch.getText());
        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadSubjects(String sid) throws SQLException{
        ArrayList<String> bids=ctrlReg.getBatchIdList(ctrlReg.getIDs(sid));
        
        ArrayList<String> subjects=ctrlBatch.getSubject(bids);
        ObservableList<String> subs=FXCollections.observableArrayList(subjects);
        cmbClass.getItems().clear();
        for(String sub:subs){
            cmbClass.getItems().add(sub);
        }
    }
    
    @FXML
    private void btnTotalIncomeAction(ActionEvent event){
        try {
            JasperReport compiledReport=(JasperReport)JRLoader.loadObject(StartProject.class.getResourceAsStream("/edu/ijse/gdse41/StudentMngSys/reports/TotalIncome.jasper"));
            JasperPrint filledReport=JasperFillManager.fillReport(compiledReport, null,con);

            FXMLLoader fXMLLoader=new FXMLLoader(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/JasperReportViewer.fxml"));
            
            Parent root=(Parent)fXMLLoader.load();
            
            JasperReportViewerController cont=fXMLLoader.getController();

            Stage stage=new Stage();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            cont.setView(filledReport);
            stage.setResizable(false);
            
            stage.setTitle("Total Income Report");
            stage.requestFocus();
            //stage.getIcons().add(new Image(getClass().getResourceAsStream("/edu/ijse/gdse41/StudentMngSys/resources/icons/LogoIcon.png")));
            stage.show();
   
        } catch (JRException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void txtYearAction(ActionEvent actionEvent){
        if(txtMonth.isVisible()){
            txtMonth.requestFocus();
        }else{
            try {
                JasperReport compiledReport=(JasperReport)JRLoader.loadObject(StartProject.class.getResourceAsStream("/edu/ijse/gdse41/StudentMngSys/reports/AnnualIncomeReport.jasper"));
                Map<String,Object> parameters=new HashMap<>();
                parameters.put("paraYear",txtYear.getText());
                JasperPrint filledReport=JasperFillManager.fillReport(compiledReport, parameters,con);
                FXMLLoader fXMLLoader=new FXMLLoader(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/JasperReportViewer.fxml"));
                Parent root=(Parent)fXMLLoader.load();
                JasperReportViewerController cont=fXMLLoader.getController();

                Stage stage=new Stage();
                Scene scene=new Scene(root);
                stage.setScene(scene);
                cont.setView(filledReport);
                stage.setResizable(false);
                stage.setTitle("Annual Income Report");

                stage.requestFocus();
                //stage.getIcons().add(new Image(getClass().getResourceAsStream("/edu/ijse/gdse41/StudentMngSys/resources/icons/LogoIcon.png")));
                stage.show();

            } catch (JRException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            clear();
            drawer.close();
        }
    }
    
    @FXML
    public void txtMonthAction(ActionEvent actionEvent){
        try {
            JasperReport compiledReport=(JasperReport)JRLoader.loadObject(StartProject.class.getResourceAsStream("/edu/ijse/gdse41/StudentMngSys/reports/MonthlyIncomeReport.jasper"));            
            Map<String,Object> parameters=new HashMap<>();           
            parameters.put("paraYear",txtYear.getText());
            parameters.put("paraMonth",txtMonth.getText());
            JasperPrint filledReport=JasperFillManager.fillReport(compiledReport, parameters,con);
            FXMLLoader fXMLLoader=new FXMLLoader(getClass().getResource("/edu/ijse/gdse41/StudentMngSys/view/fxml/JasperReportViewer.fxml"));            
            Parent root=(Parent)fXMLLoader.load();        
            JasperReportViewerController cont=fXMLLoader.getController();

            Stage stage=new Stage();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            cont.setView(filledReport);
            stage.setResizable(false);
            stage.setTitle("Monthly Income Report");
            
            stage.requestFocus();
            //stage.getIcons().add(new Image(getClass().getResourceAsStream("/edu/ijse/gdse41/StudentMngSys/resources/icons/LogoIcon.png")));
            stage.show();
   
        } catch (JRException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        clear();
        drawer.close();
    }
    
    private void clear(){
        txtYear.setText("");
        txtMonth.setText("");
    }

    @FXML
    private void pnlMainAction(MouseEvent event){
        drawer.close();
        clear();
    }
    
    @FXML
    private void btnAnnualIncomeAction(ActionEvent event){
        checkDrawer(event);
    }
    
    private void checkDrawer(ActionEvent event){
        if(drawer.isShown()){
            drawer.close();
        }else{
            if(event.getSource().equals(btnAnnualIncome)){
                txtMonth.setVisible(false);
                lblMonth.setVisible(false);
            }else{
                txtMonth.setVisible(true);
                lblMonth.setVisible(true);
            }
            drawer.open();
            
        }
    }
    
    @FXML
    private void btnMonthlyIncomeAction(ActionEvent event){
        checkDrawer(event);
    }

}


