/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl;

import edu.ijse.gdse41.StudentMngSys.db.ConnectionFactory;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.ExamDetailDBController;
import edu.ijse.gdse41.StudentMngSys.dto.ExamDetailDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author oshan
 */
public class ExamDetailDBControllerImpl implements ExamDetailDBController{
    
    private Connection con;

    public ExamDetailDBControllerImpl() {
        con=ConnectionFactory.getInstance().getConnection();
    }
    
    

    @Override
    public boolean add(ExamDetailDTO dto) throws SQLException {
        String sql="INSERT INTO exam_detail VALUES(?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, dto.getEid());
        stm.setObject(2, dto.getExId());
        stm.setObject(3, dto.getRid());
        stm.setObject(4, dto.getMarks());
        
        return stm.executeUpdate()>0;
    }
    

    @Override
    public boolean add(ArrayList<ExamDetailDTO> dtoList) throws SQLException {
        for(ExamDetailDTO dto:dtoList){
           boolean isTrue = add(dto);
           if(!isTrue){
               return false;
           }
        }
        return true;
    }

    @Override
    public ExamDetailDTO getAll(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
