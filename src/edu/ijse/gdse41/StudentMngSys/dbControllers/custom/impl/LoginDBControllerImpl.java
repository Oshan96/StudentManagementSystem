/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl;

import edu.ijse.gdse41.StudentMngSys.db.ConnectionFactory;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.LoginDBController;
import edu.ijse.gdse41.StudentMngSys.dto.SuperDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author oshan
 */
public class LoginDBControllerImpl implements LoginDBController{

    @Override
    public boolean Login(String username, String password) throws SQLException{
        String SQL = "SELECT * FROM users WHERE username=?";
        System.out.println(username);
        Connection con = ConnectionFactory.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement(SQL);
        stm.setObject(1, username);
        ResultSet rst = stm.executeQuery(); 
        if (rst.next()) {
            if(!rst.getString(1).equals(username)){
                return false;
            }
            System.out.println(rst.getString(1));
            String pwd = rst.getString(2);
            System.out.println(password);
            System.out.println(pwd);
            if (pwd.equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(SuperDTO dto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SuperDTO getAll(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
