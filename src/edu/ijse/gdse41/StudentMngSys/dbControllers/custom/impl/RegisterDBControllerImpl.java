/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl;

import edu.ijse.gdse41.StudentMngSys.db.ConnectionFactory;
import edu.ijse.gdse41.StudentMngSys.idGenerators.IDGenerator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.RegisterDBController;
import edu.ijse.gdse41.StudentMngSys.dto.RegistrationDTO;

/**
 *
 * @author oshan
 */
public class RegisterDBControllerImpl implements RegisterDBController{
    
    private Connection con;
    private PreparedStatement stm;

    public RegisterDBControllerImpl() {
        con=ConnectionFactory.getInstance().getConnection();
    }
    
    
    
    @Override
    public boolean RegisterForClass(String bid,String sid) throws SQLException{
        String sql="INSERT INTO reg_detail VALUES (?,?,?,?)";
        stm=con.prepareStatement(sql);
        stm.setObject(1, IDGenerator.getNewID("reg_detail", "rid", "R"));
        stm.setObject(2, bid);
        stm.setObject(3, sid);
        stm.setObject(4, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        return stm.executeUpdate()>0;
    }
    
    @Override
    public ArrayList<String> getIDs(String sid)throws SQLException{
        ArrayList<String> ids=new ArrayList<>();
        String sql="SELECT rid FROM reg_detail WHERE sid=?";
        stm=con.prepareStatement(sql);
        stm.setObject(1, sid);
        ResultSet rst=stm.executeQuery();
        while(rst.next()){
            ids.add(rst.getString(1));
        }
        return ids;
    }
    
    @Override
    public String getBatchId(String rid) throws SQLException {
        String sql="SELECT bid FROM reg_detail WHERE rid=?";
        stm=con.prepareStatement(sql);
        stm.setObject(1, rid);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            return rst.getString(1);
        }
        return "";
    }

    @Override
    public ArrayList<String> getBatchIdList(ArrayList<String> ridList) throws SQLException {
        ArrayList<String> bids=new ArrayList<>();
        for(String rid:ridList){
            bids.add(getBatchId(rid));
        }
        return bids;
    }

    @Override
    public String getId(String sid, String bid) throws SQLException {
        String sql="SELECT rid FROM reg_detail WHERE bid=? && sid=?";
        stm=con.prepareStatement(sql);
        stm.setObject(1, bid);
        stm.setObject(2, sid);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            return rst.getString(1);
        }
        return "";
    }

    @Override
    public boolean add(RegistrationDTO dto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RegistrationDTO getAll(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSid(String rid) throws SQLException {
        String sql="SELECT sid FROM reg_detail WHERE rid=?";
        stm=con.prepareStatement(sql);
        stm.setObject(1, rid);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            return rst.getString(1);
        }
        return "";
    }

}
