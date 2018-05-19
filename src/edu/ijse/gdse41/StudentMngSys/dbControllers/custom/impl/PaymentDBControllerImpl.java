/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl;

import edu.ijse.gdse41.StudentMngSys.db.ConnectionFactory;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.PaymentDBController;
import edu.ijse.gdse41.StudentMngSys.dto.PaymentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author oshan
 */
public class PaymentDBControllerImpl implements PaymentDBController{

    Connection con;

    public PaymentDBControllerImpl() {
        con=ConnectionFactory.getInstance().getConnection();
    }
    
    
    
    @Override
    public String getLastPayment(String rid) throws SQLException {
        String sql="SELECT pDate FROM payment WHERE rid=? ORDER BY pDate DESC LIMIT 1";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, rid);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            return rst.getString(1);
        }
        return "";
    }

    @Override
    public boolean add(PaymentDTO dto) throws SQLException {
        String sql="INSERT INTO payment VALUES(?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, dto.getPid());
        stm.setObject(2, dto.getpDate());
        stm.setObject(3, dto.getRid());
        stm.setObject(4, dto.getAmount());
        
        return stm.executeUpdate()>0;
    }

    @Override
    public PaymentDTO getAll(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
