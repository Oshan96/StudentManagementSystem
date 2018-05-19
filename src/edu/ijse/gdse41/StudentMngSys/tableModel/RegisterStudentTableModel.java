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
public class RegisterStudentTableModel {
    private SimpleStringProperty sid=new SimpleStringProperty("");
    private SimpleStringProperty name=new SimpleStringProperty("");
    private SimpleStringProperty dob=new SimpleStringProperty("");
    private SimpleIntegerProperty age=new SimpleIntegerProperty(0);
    private SimpleStringProperty nic=new SimpleStringProperty("");
    private SimpleStringProperty tel1=new SimpleStringProperty("");
    private SimpleStringProperty tel2=new SimpleStringProperty("");
    private SimpleStringProperty address=new SimpleStringProperty("");

    public RegisterStudentTableModel() {
    }
    
    

    /**
     * @return the address
     */
    public String getAddress() {
        return address.get();
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age.get();
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob.get();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic.get();
    }

    /**
     * @return the regId
     */
    public String getSid() {
        return sid.get();
    }

    /**
     * @return the tel1
     */
    public String getTel1() {
        return tel1.get();
    }

    /**
     * @return the tel2
     */
    public String getTel2() {
        return tel2.get();
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address.set(address);
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age.set(age);
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob.set(dob);
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic.set(nic);
    }

    /**
     * @param regId the regId to set
     */
    public void setSid(String sid) {
        this.sid.set(sid);
    }

    /**
     * @param tel1 the tel1 to set
     */
    public void setTel1(String tel1) {
        this.tel1.set(tel1);
    }

    /**
     * @param tel2 the tel2 to set
     */
    public void setTel2(String tel2) {
        this.tel2.set(tel2);
    }
    
    
    
}
