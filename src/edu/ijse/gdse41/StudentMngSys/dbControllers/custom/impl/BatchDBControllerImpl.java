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
import java.util.ArrayList;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.BatchDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.ClassesDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.RegisterDBController;
import edu.ijse.gdse41.StudentMngSys.dto.BatchDTO;

/**
 *
 * @author oshan
 */
public class BatchDBControllerImpl implements BatchDBController{
    Connection con;

    public BatchDBControllerImpl() {
        con=ConnectionFactory.getInstance().getConnection();
        
    }

    @Override
    public String getAdmission(String batchId) throws SQLException{
        String sql="SELECT admission FROM batch WHERE bid=?";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, batchId);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            return "Rs. "+rst.getString(1);
        }
        return null;
    }
    
    @Override
    public ArrayList<String> getBatchIds(String cid) throws SQLException{
        ArrayList<String> batches=new ArrayList<>();
        String sql="SELECT bid FROM batch WHERE cid=?";
        Connection con=ConnectionFactory.getInstance().getConnection();
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, cid);
        ResultSet rst=stm.executeQuery();
        while(rst.next()){
            batches.add(rst.getString(1));
        }
        return batches;
    }

    @Override
    public ArrayList<String> getSubject(ArrayList<String> bids) throws SQLException {
        ClassesDBController ctrlClasses=(ClassesDBControllerImpl) ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.CLASSES);
        ArrayList<String> subjects=new ArrayList<>();
        for(String bid:bids){
            String cid=getCid(bid);
            subjects.add(ctrlClasses.getName(cid));
        }
        return subjects;
    }

    @Override
    public String getCid(String bid) throws SQLException {
        String sql="SELECT cid FROM batch WHERE bid=?";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, bid);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            return rst.getString(1);
        }
        return "";
    }

    @Override
    public String getId(String cid) throws SQLException {
        String sql="SELECT bid FROM batch WHERE cid=?";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, cid);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            return rst.getString(1);
        }
        return "";
    }

    @Override
    public String getFilteredId(String sid,String cid)throws SQLException{
        RegisterDBController ctrlReg=(RegisterDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.REGISTER);
        ArrayList<String> ids=new ArrayList<>();
        String sql="SELECT bid FROM batch WHERE cid=?";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, cid);
        ResultSet rst=stm.executeQuery();
        while(rst.next()){
            ids.add(rst.getString(1));
        }
        for(String bid:ids){
            String id=ctrlReg.getId(sid,bid);
            if(id!=""){
                return bid;
            }
        }
        return "";
    }

    @Override
    public String getFee(String batchId) throws SQLException {
        String sql="SELECT fee FROM batch WHERE bid=?";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, batchId);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean add(BatchDTO dto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BatchDTO getAll(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getYear(String subName) throws SQLException {
        ArrayList<String> yearList=new ArrayList<>();
        String sql="SELECT DISTINCT byear FROM batch,classes WHERE batch.cid=classes.cid && classes.name=?";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, subName);
        ResultSet rst=stm.executeQuery();
        while(rst.next()){
            yearList.add(rst.getString(1));
        }
        return yearList;
        
    }

    @Override
    public ArrayList<String> getBatchIds(String subName, String year) throws SQLException {
        ArrayList<String> bidList=new ArrayList<>();
        String sql="SELECT bid FROM batch,classes WHERE classes.name=? && batch.byear=? && classes.cid=batch.cid";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, subName);
        stm.setObject(2, year);
        ResultSet rst=stm.executeQuery();
        while(rst.next()){
            bidList.add(rst.getString(1));
        }
        return bidList; 
    }

}
