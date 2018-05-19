/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl;

import edu.ijse.gdse41.StudentMngSys.db.ConnectionFactory;
import edu.ijse.gdse41.StudentMngSys.dbControllers.ControllerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.BatchDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.ClassesDBController;
import edu.ijse.gdse41.StudentMngSys.dto.SuperDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author oshan
 */
public class ClassesDBControllerImpl implements ClassesDBController{
    
    Connection con;

    public ClassesDBControllerImpl() {
        
        con=ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public ArrayList<String> getClassBatches(String className)throws SQLException{
        BatchDBController ctrlBatch=(BatchDBControllerImpl) ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.BATCH);
        ArrayList<String> batches=new ArrayList<>();
        String sql="SELECT cid FROM classes WHERE name=?";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, className);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            batches=ctrlBatch.getBatchIds(rst.getString(1));
        }
        return batches;
    }
    
    @Override
    public ArrayList<String> getClassNames(String section) throws SQLException{
        ArrayList<String> classes=new ArrayList<>();
        String sql="SELECT name FROM classes WHERE stream=?";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, section);
        ResultSet rst=stm.executeQuery();
        while(rst.next()){
            classes.add(rst.getString(1));
        }
        return classes;
    }
        
    @Override
    public ArrayList<String> getSections() throws SQLException{
        ArrayList<String> section=new ArrayList<>();
        String sql="SELECT DISTINCT stream FROM classes";
        Statement stm=con.createStatement();
        ResultSet rst=stm.executeQuery(sql);
        while(rst.next()){
            section.add(rst.getString(1));
        }
        return section;         
    }

    @Override
    public String getName(String cid) throws SQLException {
        String sql="SELECT name FROM classes WHERE cid=?";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, cid);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            return rst.getString(1);
        }
        return "";
    }

    @Override
    public String getCid(String name) throws SQLException {
        String sql="SELECT cid FROM classes WHERE name=?";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, name);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            return rst.getString(1);
        }
        return ""; 
    }

    @Override
    public boolean add(SuperDTO dto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<PieChart.Data> getChartData(String sid) throws SQLException {
        ObservableList<PieChart.Data> data=FXCollections.observableArrayList();
        String sql="SELECT classes.name, SUM(exam_detail.marks) FROM student,reg_detail,batch,classes,exam,exam_detail\n"
                + "WHERE student.sid=reg_detail.sid\n"
                + "&& reg_detail.bid=batch.bid\n"
                + "&& batch.cid=classes.cid\n"
                + "&& batch.bid=exam.bid\n"
                + "&& exam.exId=exam_detail.exId\n"
                + "&& student.sid=?\n"
                + "GROUP BY classes.cid";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, sid);
        ResultSet rst=stm.executeQuery();
        while(rst.next()){
            PieChart.Data dt=new PieChart.Data(rst.getString(1), rst.getInt(2));
            data.add(dt);
        }
        return data;
    }

    @Override
    public SuperDTO getAll(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getSubjects() throws SQLException {
        ArrayList<String> subjects=new ArrayList<>();
        String sql="SELECT name FROM classes";
        PreparedStatement stm=con.prepareStatement(sql);
        ResultSet rst=stm.executeQuery();
        while(rst.next()){
            subjects.add(rst.getString(1));
        }
        return subjects;
    }

    @Override
    public ObservableList<BarChart.Series> getExamData(String rid) throws SQLException {
        ObservableList<BarChart.Series> data=FXCollections.observableArrayList();

        String sql="SELECT exam.exId, exam_detail.marks FROM exam,exam_detail\n"
                + "WHERE exam.exId=exam_detail.exId\n"
                + "&& exam_detail.rid=?";
        
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, rid);
        ResultSet rst=stm.executeQuery();
        while(rst.next()){
            BarChart.Series ser=new BarChart.Series<>();
            ser.getData().add(new XYChart.Data(rst.getString(1),rst.getInt(2)));
            ser.setName(rst.getString(1));
            data.add(ser);
        }
        return data;
    }

}
