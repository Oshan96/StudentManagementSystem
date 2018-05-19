/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.dto;

/**
 *
 * @author oshan
 */
public class RegistrationDTO extends SuperDTO{
    private String rid;
    private String bid;
    private String sid;
    private String reg_date;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String rid, String bid, String sid, String reg_date) {
        this.rid = rid;
        this.bid = bid;
        this.sid = sid;
        this.reg_date = reg_date;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }
    
    
}
