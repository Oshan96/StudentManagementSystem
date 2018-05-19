/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.view.controller;

import com.jfoenix.controls.JFXButton;
import edu.ijse.gdse41.StudentMngSys.dbControllers.ControllerFactory;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.ClassesDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.StudentDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.ClassesDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.StudentDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dto.StudentDTO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author oshan
 */
public class StudentProgressController implements Initializable {
    
    @FXML
    PieChart pieChart;
    
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
    JFXButton btnSearch;
    @FXML
    ImageView imgStudent;
    
    @FXML
    AnchorPane anchM;
    
//    private Label lbl=new Label();
    
    private ObservableList<PieChart.Data> data=FXCollections.observableArrayList();
    private ClassesDBController ctrlClasses;
    private StudentDBController ctrlStudent;
   
    private void setChartData(String sid) throws SQLException{
        if(sid.equals("")){
            return;
        }
        data.clear();
        data=ctrlClasses.getChartData(sid);
        pieChart.setData(data);
    }
    
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
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ctrlClasses=(ClassesDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.CLASSES);
        ctrlStudent=(StudentDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.STUDENT);
        pieChart.setData(data);
//        anchM.getChildren().add(lbl);
    }

    @FXML
    private void btnSearchAction(ActionEvent event){
        try {
            String sid=txtSearch.getText();
            setChartData(sid);
            setStudentDetails(sid);
        } catch (SQLException ex) {
            Logger.getLogger(StudentProgressController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    @FXML
//    private void itemDetailsEvent(MouseEvent event) {
//
//        pieChart.getData().stream()
//                .forEach(data -> {data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,event1 -> {
//                    lbl.setLayoutX(event.getSceneX());
//                    lbl.setLayoutY(event.getSceneY());
//
//                    lbl
//                            .setText(
////                                    data.getName()+" ("+
////                                            data.getPieValue()+")"+"\nPresentage : "+
//                                            Double.parseDouble(String.valueOf(((
//                                                    data.getPieValue()/360))))+"%");
//                    
//                });
//        });
//    }
    
    
}
