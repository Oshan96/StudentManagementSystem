/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.dto;

import java.util.ArrayList;

/**
 *
 * @author oshan
 */
public class ExamDTO extends SuperDTO{
    private String exId;
    private String exDate;
    private String bid;
    private ArrayList<ExamDetailDTO> dtoList;

    public ExamDTO() {
    }

    public ExamDTO(String exId, String exDate, String bid, ArrayList<ExamDetailDTO> dtoList) {
        this.exId = exId;
        this.exDate = exDate;
        this.bid = bid;
        this.dtoList = dtoList;
    }

    public String getExId() {
        return exId;
    }

    public void setExId(String exId) {
        this.exId = exId;
    }

    public String getExDate() {
        return exDate;
    }

    public void setExDate(String exDate) {
        this.exDate = exDate;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public ArrayList<ExamDetailDTO> getDtoList() {
        return dtoList;
    }

    public void setDtoList(ArrayList<ExamDetailDTO> dtoList) {
        this.dtoList = dtoList;
    }
    
    
}
