/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.dbControllers.custom;

import edu.ijse.gdse41.StudentMngSys.dbControllers.SuperController;
import edu.ijse.gdse41.StudentMngSys.dto.PaymentDTO;
import java.sql.SQLException;

/**
 *
 * @author oshan
 */
public interface PaymentDBController extends SuperController<PaymentDTO>{
    public String getLastPayment(String rid) throws SQLException;
    
}
