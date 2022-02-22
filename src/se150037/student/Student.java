/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se150037.student;

import java.io.Serializable;
import se150037.utils.Utils;

/**
 *
 * @author Huynh Trung Tin - SE150037
 */
public abstract class Student implements Serializable{
    private String studentID;
    private String studentName;

    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }

    public Student() {
    }

    public String getStudentID() {
        return studentID;
    }

//    public void setStudentID(String studentID) {
//        this.studentID = studentID;
//    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public void updateVaccine() {
        this.studentName = Utils.updateString(this.studentName, "Update student name: ");
    }
    
}
