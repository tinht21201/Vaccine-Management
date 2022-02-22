/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se150037.student;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import se150037.utils.Utils;

/**
 *
 * @author Huynh Trung Tin - SE150037
 */
public class StudentList extends ArrayList<Student>{
    
    public void add(String message) {
        String studentID = "";
        String studentName = "";
        boolean check = true;
        System.out.println(message);
        do {            
            studentID = Utils.getString("Student ID: ");
            if(findByID(studentID) != -1) {
                System.out.println("ID \"" + studentID +"\" is Duplicate");
                continue;
            }
            studentName = Utils.getString("Student Name: ");
            check = false;
        } while (check);
        this.add(new Student(studentID, studentName) {});
        System.out.println("Added!");
    }
    
    public int findByID(String id) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getStudentID().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    
    public void search() {
        String key = Utils.getString("Input Student ID that you want to search: ");
        int i = findByID(key);
        if (i != -1) {
            System.out.println("Student {" + "ID: " + this.get(i).getStudentID()+ ", "
                    + "Name: " + this.get(i).getStudentID() + "}");
        } else {
            System.out.println("Not Found!");
        }
    }
    
    public void searchAnother() {
        boolean check = true;
        String confirmMess = "";
        do {
            confirmMess = Utils.getString("Do you want to search another student? (Y/N): ");
            if (confirmMess.equalsIgnoreCase("Y")) {
                this.search();
                check = true;
            } else if (confirmMess.equalsIgnoreCase("N")) {
                check = false;
            } else {
                check = true;
            }
        } while (check);
    }
    
    public void removeByID() {
        String id = "";
        id = Utils.getString("Input Student ID that you want to delete: ");
        String confirmMess = "";
        boolean check = true;
        do {
            confirmMess = Utils.getString("Are you sure remove this Student (Y/N)?");
            if (confirmMess.equalsIgnoreCase("Y")) {
                if (findByID(id) != -1) {
                    this.remove(get(findByID(id)));
                    System.out.println("Success.");
                } else {
                    System.out.println("Not Found!");
                }
                check = false;
            } else if (confirmMess.equalsIgnoreCase("N")) {
                System.out.println("Didn't remove.");
                check = false;
            } else {
                check = true;
            }
        } while (check);
    }
    
    public void print() {
        if (this.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            for (Student list : this) {
            System.out.println("Student {" + "ID: " + list.getStudentID()+ ", "
                    + "Name: " + list.getStudentName()+ "}");
        }
        }
    }
    
    public void addAnother() {
        boolean check = true;
        String confirmMess = "";
        do {
            confirmMess = Utils.getString("Do you want to add another vaccine? (Y/N): ");
            if (confirmMess.equalsIgnoreCase("Y")) {
                this.add("---ADD NEW STUDENT---");
                check = true;
            } else if (confirmMess.equalsIgnoreCase("N")) {
                check = false;
            } else {
                check = true;
            }
        } while (check);
    }
    
    public boolean writeFileBinary(String url) throws IOException {
        boolean check = false;
        FileOutputStream fos = new FileOutputStream(url);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        try {
            for (int i = 0; i < this.size(); i++) {
                oos.writeObject(this.get(i));
            }
            check = true;
        } catch (Exception e) {
        } finally {
            if (oos != null) {
                oos.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
        return check;
    }
    
    public void readFileBinary(String url) throws IOException {
        FileInputStream fis = new FileInputStream(url);
        ObjectInputStream ois = new ObjectInputStream(fis);
        try {
            Student student = null;
            //System.out.println("Data from file:");
            while (fis.available() > 0) {
                student =  (Student) ois.readObject();
                //Print when read file
                //System.out.println(food.toString());
                this.add(student);
            }
        } catch (Exception e) {
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (ois != null) {
                ois.close();
            }
        }
    }
    
    public void update(String message) {
        System.out.println(message);
        String studentID = Utils.getString("Input Student ID that you want to update: ");
        int i = findByID(studentID);
        if (i != -1) {
            Student student = this.get(i);
            student.updateVaccine();
            this.set(i, student);
            System.out.println("Updated.");
        } else {
            System.out.println("This Student does not exist.");
        }
    }
}
