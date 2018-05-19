/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.other.validation;

import javafx.scene.control.TextField;

/**
 *
 * @author oshan
 */
public class FieldValidator {
    
    
    public static boolean validateTelephoneNumber(TextField txt){
        if (txt.getText().matches("^(((00|[+])\\d{2}[-]?)?(\\d{3}|\\d{3})[-]?(\\d{7}))$")){
            return true;
        }else{
            txt.setText("");
            txt.requestFocus();
            return false;
        }
    }
    
    public static boolean validateNIC(TextField txt){
        if (txt.getText().matches("^(\\d{9}|\\d{12})[VvXx]$")){
            return true;
        }else{
            txt.setText("");
            txt.requestFocus();
            return false;
        }
    }
    
    public static boolean validateEMail(TextField txt){
        if (txt.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")){
            return true;
        }else{
            txt.setText("");
            txt.requestFocus();
            return false;
        }
    }
}

