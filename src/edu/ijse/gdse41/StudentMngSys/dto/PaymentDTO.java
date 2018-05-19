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
public class PaymentDTO extends SuperDTO{
    private String pid;
    private String pDate;
    private String rid;
    private double amount;

    public PaymentDTO() {
    }

    public PaymentDTO(String pid, String pDate, String rid, double amount) {
        this.pid = pid;
        this.pDate = pDate;
        this.rid = rid;
        this.amount = amount;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    
    /**
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return the rid
     */
    public String getRid() {
        return rid;
    }

    /**
     * @param rid the rid to set
     */
    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getpDate() {
        return pDate;
    }

    public void setpDate(String pDate) {
        this.pDate = pDate;
    }
    
    
    
    
}
