/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.dbControllers.custom;

import edu.ijse.gdse41.StudentMngSys.dbControllers.SuperController;
import edu.ijse.gdse41.StudentMngSys.dto.SuperDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;

/**
 *
 * @author oshan
 */
public interface ClassesDBController extends SuperController<SuperDTO>{
    public ArrayList<String> getClassBatches(String className)throws SQLException;
    
    public ArrayList<String> getClassNames(String section) throws SQLException;
    
    public ArrayList<String> getSections() throws SQLException;
    
    public String getName(String cid) throws SQLException;
    
    public String getCid(String name) throws SQLException;
    
    public ObservableList<PieChart.Data> getChartData(String sid) throws SQLException;
    
    public ArrayList<String> getSubjects() throws SQLException;
    
    public ObservableList<BarChart.Series> getExamData(String rid) throws SQLException;
}
