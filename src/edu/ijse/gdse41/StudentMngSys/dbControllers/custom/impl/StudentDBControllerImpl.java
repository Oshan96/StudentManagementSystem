/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl;

import edu.ijse.gdse41.StudentMngSys.db.ConnectionFactory;
import edu.ijse.gdse41.StudentMngSys.dbControllers.ControllerFactory;
import edu.ijse.gdse41.StudentMngSys.dto.StudentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.RegisterDBController;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.StudentDBController;

/**
 *
 * @author oshan
 */
public class StudentDBControllerImpl implements StudentDBController{

    Connection con;

    public StudentDBControllerImpl() {
        con=ConnectionFactory.getInstance().getConnection();
    }
    
    @Override
    public ArrayList<StudentDTO> getTableDetails() throws SQLException{
        ArrayList<StudentDTO> array=new ArrayList<>();
        String sql="SELECT rid,student.sid,name,dob,age,nic,tel1,tel2,address FROM reg_detail,student WHERE reg_detail.sid=student.sid GROUP BY student.sid";
        Statement stm=con.createStatement();
        ResultSet rst=stm.executeQuery(sql);
        while(rst.next()){
            StudentDTO student=new StudentDTO(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getInt(5),rst.getString(6),rst.getString(7),rst.getString(8),rst.getString(9));
            array.add(student);
        }
        return array;
    }
    
    @Override
    public boolean searchNIC(String nic) throws SQLException{
        String sql="SELECT sid FROM student WHERE nic=?";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, nic);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            return false;
        }
        return true;
    }
    
    @Override
    public boolean addStudent(StudentDTO student, String bid) throws SQLException{
        RegisterDBController ctrlReg=(RegisterDBControllerImpl)ControllerFactory.getInstance().getController(ControllerFactory.ControllerType.REGISTER);
        String sql="INSERT INTO student VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, student.getSid());
        stm.setObject(2, student.getName());
        stm.setObject(3, student.getDob());
        stm.setObject(4, student.getAge());
        stm.setObject(5, student.getNic());
        stm.setObject(6, student.getTel1());
        stm.setObject(7, student.getTel2());
        stm.setObject(8, student.getAddress());
        stm.setObject(9, student.getGender());
        stm.setObject(10, student.getGuardianName());
        try{
            con.setAutoCommit(false);
            if(stm.executeUpdate()>0){
                if(ctrlReg.RegisterForClass(bid,student.getSid())){
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
    public StudentDTO getCardDetails(String nic) throws SQLException{
        
        String sql="SELECT name,dob,nic,tel1,address,sid FROM student WHERE nic=?";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, nic);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
          return new StudentDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5),rst.getString(6));
        }
        
        return null;
        
    }

    @Override
    public StudentDTO getCardDetails() throws SQLException {
        String sql="SELECT name,dob,nic,tel1,address,sid FROM student ORDER BY sid DESC LIMIT 1";
        PreparedStatement stm=con.prepareStatement(sql);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
          return new StudentDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5),rst.getString(6));
        }
        return null;
    }

    @Override
    public String getName(String sid) throws SQLException {
        String sql="SELECT name FROM student WHERE sid=?";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, sid);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
          return rst.getString(1);
        }
        return "";
    }

    @Override
    public boolean add(StudentDTO dto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getId(String nic) throws SQLException {
        String sql="SELECT sid FROM student WHERE nic=?";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, nic);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public StudentDTO getAll(String id) throws SQLException {
        StudentDTO student=null;
        String sql="SELECT * FROM student WHERE sid = ?";
        PreparedStatement stm=con.prepareStatement(sql);
        stm.setObject(1, id);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            student=new StudentDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10));
        }
        return student;
    }
    
}
