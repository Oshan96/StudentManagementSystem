/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.dbControllers.custom;

import edu.ijse.gdse41.StudentMngSys.dbControllers.SuperController;
import edu.ijse.gdse41.StudentMngSys.dto.AttendanceDTO;
import java.util.ArrayList;

/**
 *
 * @author oshan
 */
public interface AttendanceDBController extends SuperController<AttendanceDTO>{
    public ArrayList<AttendanceDTO> getTableDetails() throws Exception;
       
}
