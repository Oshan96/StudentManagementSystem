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
public class AttendanceDTO extends SuperDTO{
    
    private String studentName;
    private String nic;
    private String tel;
    private String subject;
    private String hall;

    private String aid;
    private String rid;
    private String bid;
    private String aDate;

    public AttendanceDTO(String aid, String rid, String bid, String aDate) {
        this.aid = aid;
        this.rid = rid;
        this.bid = bid;
        this.aDate = aDate;
    }
    
    

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
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

    public String getaDate() {
        return aDate;
    }

    public void setaDate(String aDate) {
        this.aDate = aDate;
    }
    
    
    
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public AttendanceDTO() {
    }

    public AttendanceDTO(String studentName, String nic, String tel, String subject, String hall) {
        this.studentName = studentName;
        this.nic = nic;
        this.tel = tel;
        this.subject = subject;
        this.hall = hall;
    }
    
    
    
}
