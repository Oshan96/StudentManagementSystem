/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.dbControllers.custom;

import edu.ijse.gdse41.StudentMngSys.dbControllers.SuperController;
import edu.ijse.gdse41.StudentMngSys.dto.RegistrationDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author oshan
 */
public interface RegisterDBController extends SuperController<RegistrationDTO>{
    public boolean RegisterForClass(String bid,String sid) throws SQLException;
    
    public ArrayList<String> getIDs(String sid)throws SQLException;
    
    public String getBatchId(String rid) throws SQLException;
    
    public ArrayList<String> getBatchIdList(ArrayList<String> ridList) throws SQLException;
    
    public String getId(String sid,String bid) throws SQLException;
    
    public String getSid(String rid) throws SQLException;
}
