/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.tableModel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author oshan
 */
public class ExamMarksTableModel {
    private SimpleStringProperty eid=new SimpleStringProperty("");
    private SimpleStringProperty rid=new SimpleStringProperty("");
    private SimpleStringProperty studentName=new SimpleStringProperty("");
//    private SimpleStringProperty nic=new SimpleStringProperty("");
    private SimpleIntegerProperty marks=new SimpleIntegerProperty(0);

    public String getEid() {
        return eid.get();
    }

    public void setEid(String eid) {
        this.eid.set(eid);
    }

    public String getRid() {
        return rid.get();
    }

    public void setRid(String rid) {
        this.rid.set(rid);
    }

    public String getStudentName() {
        return studentName.get();
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }

//    public String getNic() {
//        return nic.get();
//    }
//
//    public void setNic(String nic) {
//        this.nic.set(nic);
//    }

    public int getMarks() {
        return marks.get();
    }

    public void setMarks(int marks) {
        this.marks.set(marks);
    }
    
    
}
