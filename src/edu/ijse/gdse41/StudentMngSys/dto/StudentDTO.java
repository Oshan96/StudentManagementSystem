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
public class StudentDTO extends SuperDTO{
    private String regId;
    private String sid;
    private String name;
    private String dob;
    private int age;
    private String nic;
    private String tel1;
    private String tel2;
    private String address;
    private String gender;
    private String guardianName;

    public StudentDTO() {
        
    }

    public StudentDTO(String sid, String name, String dob, int age, String nic, String tel1, String tel2, String address, String gender, String guardianName) {
        this.sid = sid;
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.nic = nic;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.address = address;
        this.gender = gender;
        this.guardianName = guardianName;
    }
    
    public StudentDTO(String name, String dob, String nic, String tel1, String address) {
        this.name = name;
        this.dob = dob;
        this.nic = nic;
        this.tel1 = tel1;
        this.address = address;
    }
    

    public StudentDTO(String name, String dob, String nic, String tel1, String address,String sid) {
        this.name = name;
        this.dob = dob;
        this.nic = nic;
        this.tel1 = tel1;
        this.address = address;
        this.sid=sid;
    }
    
    

    public StudentDTO(String regId, String sid, String name, String dob, int age, String nic, String tel1, String tel2, String address) {
        this.regId = regId;
        this.sid = sid;
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.nic = nic;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.address = address;
    }
    

    public StudentDTO(String regId, String sid, String name, String dob, int age, String nic, String tel1, String tel2, String address, String gender, String guardianName) {
        this.regId = regId;
        this.sid = sid;
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.nic = nic;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.address = address;
        this.gender = gender;
        this.guardianName = guardianName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }
 

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
