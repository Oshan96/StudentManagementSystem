/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl;

import edu.ijse.gdse41.StudentMngSys.db.ConnectionFactory;
import edu.ijse.gdse41.StudentMngSys.dbControllers.ControllerFactory;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.ExamDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.ExamDetailDBController;
import edu.ijse.gdse41.StudentMngSys.dto.ExamDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author oshan
 */
public class ExamDBControllerImpl implements ExamDBController{
    
    private Connection con;

    public ExamDBControllerImpl() {
        con=ConnectionFactory.getInstance().getConnection();
    }
    
    
    
    @Override
    public boolean add(ExamDTO dto) throws SQLException {
        ExamDetailDBController ctrlExDetail=(ExamDetailDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.EXAM_DETAIL);
        String sql="INSERT INTO exam VALUES(?,?,?)";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, dto.getExId());
        stm.setObject(2, dto.getExDate());
        stm.setObject(3, dto.getBid());
        
        try{
            con.setAutoCommit(false);
            if(stm.executeUpdate()>0){
                if(ctrlExDetail.add(dto.getDtoList())){
                    con.commit();
                    return true;
                }
            }
            con.rollback();
            return false;
            
        }finally{
            con.setAutoCommit(true);
        }
    }

    @Override
    public ExamDTO getAll(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
