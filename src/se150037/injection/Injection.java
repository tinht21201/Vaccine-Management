/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se150037.injection;

import java.io.Serializable;
import se150037.utils.Utils;

/**
 *
 * @author Huynh Trung Tin - SE150037
 */
public abstract class Injection implements Serializable{
    private String injectionID;
    private String firstPlace;
    private String secondPlace;
    private String firstDate;
    private String secondDate;
    private String studentID;
    private String vaccineID;

    public Injection() {
    }

    public Injection(String injectionID, String firstPlace, String secondPlace, String firstDate, String secondDate, String studentID, String vaccineID) {
        this.injectionID = injectionID;
        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
        this.studentID = studentID;
        this.vaccineID = vaccineID;
    }

    public String getInjectionID() {
        return injectionID;
    }

//    public void setInjectionID(String injectionID) {
//        this.injectionID = injectionID;
//    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public String getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(String secondDate) {
        this.secondDate = secondDate;
    }

    public String getStudentID() {
        return studentID;
    }

//    public void setStudentID(String studentID) {
//        this.studentID = studentID;
//    }

    public String getVaccineID() {
        return vaccineID;
    }

//    public void setVaccineID(String vaccineID) {
//        this.vaccineID = vaccineID;
//    }
    
    public void update() {
        this.secondPlace = Utils.getString("Update 2nd Place: ");
        this.secondDate = Utils.updateSecondDate(this.firstDate, "Update 2nd Date: ");
    }
    
}
