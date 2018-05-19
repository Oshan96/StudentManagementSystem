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
public class ExamDetailDTO extends SuperDTO{
    private String eid;
    private String eDate;
    private int marks;
    private String rid;
    private String bid;
    
    private String studentName;
    private String nic;
    private String sid;
    private String exId;

    public ExamDetailDTO() {
    }

    /**
     *
     * @param eid
     * @param rid
     * @param bid
     * @param studentName
     * @param nic
     * @param marks
     */
    public ExamDetailDTO(String eid, String rid, String bid, String studentName, String nic, int marks) {
        this.eid = eid;
        this.marks = marks;
        this.rid = rid;
        this.bid = bid;
        this.studentName = studentName;
        this.nic = nic;
    }
    
    

    public ExamDetailDTO(String eid, String eDate, int marks, String rid, String bid) {
        this.eid = eid;
        this.eDate = eDate;
        this.marks = marks;
        this.rid = rid;
        this.bid = bid;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getExId() {
        return exId;
    }

    public void setExId(String exId) {
        this.exId = exId;
    }
    
    
    
    
    
}
