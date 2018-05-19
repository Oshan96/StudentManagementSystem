/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.dbControllers.custom;

import edu.ijse.gdse41.StudentMngSys.dbControllers.SuperController;
import edu.ijse.gdse41.StudentMngSys.dto.SuperDTO;
import java.sql.SQLException;

/**
 *
 * @author oshan
 */
public interface LoginDBController extends SuperController<SuperDTO>{
    public boolean Login(String username, String password) throws SQLException;
}
