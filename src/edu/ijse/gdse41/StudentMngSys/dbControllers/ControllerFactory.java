/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.dbControllers;

import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.AttendanceDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.BatchDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.ClassesDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.ExamDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.ExamDetailDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.LoginDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.PaymentDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.RegisterDBControllerImpl;
import edu.ijse.gdse41.StudentMngSys.dbControllers.custom.impl.StudentDBControllerImpl;

/**
 *
 * @author oshan
 */
public class ControllerFactory{
    private static ControllerFactory controllerFactory;
    
    private BatchDBControllerImpl ctrlBatch;
    
    public enum ControllerType{
        BATCH,CLASSES,LOGIN,REGISTER,STUDENT,ATTENDANCE,PAYMENT,EXAM,EXAM_DETAIL;
    }

    private ControllerFactory() {
    }
    
    public static ControllerFactory getInstance(){
       if(controllerFactory==null){
           controllerFactory=new ControllerFactory();
       } 
       return controllerFactory;
    }
    
    public SuperController getController(ControllerType type){
        switch(type){
            case BATCH:
                return new BatchDBControllerImpl();
            case CLASSES:
                return new ClassesDBControllerImpl();
            case LOGIN:
                return new LoginDBControllerImpl();
            case REGISTER:
                return new RegisterDBControllerImpl();
            case STUDENT:
                return new StudentDBControllerImpl();
            case ATTENDANCE:
                return new AttendanceDBControllerImpl();
            case PAYMENT:
                return new PaymentDBControllerImpl();
            case EXAM_DETAIL:
                return new ExamDetailDBControllerImpl();
            case EXAM:
                return new ExamDBControllerImpl();
            default :
                return null;
        }
    }
    
}
