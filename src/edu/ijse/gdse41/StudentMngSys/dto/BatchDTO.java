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
public class BatchDTO extends SuperDTO{
    private String bid;
    private String hall;
    private double admission;
    private int bYear;
    private String cid;
    private String cDay;
    

    public BatchDTO() {
    }

    public BatchDTO(String bid, String hall, double admission, int bYear, String cid, String cDay) {
        this.bid = bid;
        this.hall = hall;
        this.admission = admission;
        this.bYear = bYear;
        this.cid = cid;
        this.cDay = cDay;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public double getAdmission() {
        return admission;
    }

    public void setAdmission(double admission) {
        this.admission = admission;
    }

    public int getbYear() {
        return bYear;
    }

    public void setbYear(int bYear) {
        this.bYear = bYear;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getcDay() {
        return cDay;
    }

    public void setcDay(String cDay) {
        this.cDay = cDay;
    }
    
}
