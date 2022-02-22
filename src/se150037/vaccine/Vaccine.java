/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se150037.vaccine;

import java.io.Serializable;
import se150037.utils.Utils;

/**
 *
 * @author Huynh Trung Tin - SE150037
 */
public abstract class Vaccine implements Serializable{
    private String vaccineID;
    private String vaccineName;

    public Vaccine() {
    }

    public Vaccine(String vaccineID, String vaccineName) {
        this.vaccineID = vaccineID;
        this.vaccineName = vaccineName;
    }

    public String getVaccineID() {
        return vaccineID;
    }

//    public void setVaccineID(String vaccineID) {
//        this.vaccineID = vaccineID;
//    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }
    
    public void updateVaccine() {
        this.vaccineName = Utils.updateString(this.vaccineName, "Update vaccine name: ");
    }
}
