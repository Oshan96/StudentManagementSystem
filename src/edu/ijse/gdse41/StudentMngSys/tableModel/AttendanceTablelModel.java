/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.tableModel;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author oshan
 */
public class AttendanceTablelModel {
    private SimpleStringProperty studentName=new SimpleStringProperty("");
    private SimpleStringProperty nic=new SimpleStringProperty("");
    private SimpleStringProperty tel=new SimpleStringProperty("");
    private SimpleStringProperty subject=new SimpleStringProperty("");
    private SimpleStringProperty hall=new SimpleStringProperty(""); 

    public String getStudentName() {
        return studentName.get();
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }

    public String getNic() {
        return nic.get();
    }

    public void setNic(String nic) {
        this.nic.set(nic);
    }

    public String getTel() {
        return tel.get();
    }

    public void setTel(String tel) {
        this.tel.set(tel);
    }

    public String getSubject() {
        return subject.get();
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public String getHall() {
        return hall.get();
    }

    public void setHall(String hall) {
        this.hall.set(hall);
    }
    
    
}
