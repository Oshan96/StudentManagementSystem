/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl;

import edu.ijse.gdse41.StudentMngSys.db.ConnectionFactory;
import edu.ijse.gdse41.StudentMngSys.dto.AttendanceDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.AttendanceDBController;
import java.sql.SQLException;

/**
 *
 * @author oshan
 */
public class AttendanceDBControllerImpl implements AttendanceDBController{
    
    private Connection con;
    private PreparedStatement stm;

    public AttendanceDBControllerImpl() {
        con=ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public ArrayList<AttendanceDTO> getTableDetails() throws Exception {
        ArrayList<AttendanceDTO> attendanceList=new ArrayList<>();
        String sql="SELECT student.name,nic,tel1,classes.name,batch.hall FROM student,reg_detail,classes,batch,attendance WHERE aDate=? && student.sid=reg_detail.sid && reg_detail.bid=batch.bid && batch.cid=classes.cid && reg_detail.rid=attendance.rid";
        stm=con.prepareStatement(sql);
        stm.setObject(1, LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        ResultSet rst=stm.executeQuery();
        while(rst.next()){
            AttendanceDTO attendace=new AttendanceDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
            attendanceList.add(attendace);
        }   
        return attendanceList;
    }

    @Override
    public boolean add(AttendanceDTO dto) throws SQLException {
        String sql="INSERT INTO attendance VALUES(?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, dto.getAid());
        stm.setObject(2, dto.getRid());
        stm.setObject(3, dto.getBid());
        stm.setObject(4, dto.getaDate());
        
        return stm.executeUpdate()>0;
    }

    @Override
    public AttendanceDTO getAll(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}