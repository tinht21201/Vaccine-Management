/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se150037.injection;

import se150037.student.StudentList;
import se150037.vaccine.VaccineList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import se150037.student.Student;
import se150037.utils.Utils;
import se150037.vaccine.Vaccine;

/**
 *
 * @author Huynh Trung Tin - SE150037
 */
public class InjectionList extends ArrayList<Injection> {

    VaccineList vList = new VaccineList();
    StudentList sList = new StudentList();

    private final String V_FILE = "vaccine.dat";
    private final String S_FILE = "student.dat";

    public boolean loadFile() throws IOException {
        try {
            vList.removeAll(vList);
            vList.readFileBinary(V_FILE);
            sList.removeAll(sList);
            sList.readFileBinary(S_FILE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void add(String message) {
        String injectionID = "";
        String firstPlace = "";
        String secondPlace = "";
        String firstDate = "";
        String secondDate = "";
        String studentID = "";
        String vaccineID = "";
        boolean check = true;

        do {
            System.out.println(message);
            injectionID = Utils.getString("Injection ID: ");
            if (findInjectionID(injectionID) != -1) {
                System.out.println("ID \"" + injectionID + "\" is Duplicate");
                continue;
            }
            firstPlace = Utils.getString("First Place of Injection: ");
            secondPlace = Utils.getStringEx("Second Place of Injection: ");
            firstDate = Utils.getFirstDate("First Date of Injection: ");
            secondDate = Utils.getSecondDate(firstDate, "Second Date of Injection: ");
            studentID = getStudentID();
            vaccineID = getVaccineID();
            check = false;
        } while (check);
        this.add(new Injection(injectionID, firstPlace, secondPlace, firstDate, secondDate, studentID, vaccineID) {
        });
        System.out.println("Added!");
    }

    private String getStudentID() {
        String studentID = "";
        boolean check = true;
        do {
            studentID = Utils.getString("Student ID: ");
            if (findStudentID(studentID) == -1) {
                System.out.println("Student ID \"" + studentID + "\" does not exist. Try again!");
                continue;
            }

            if (findThisStudentID(studentID) != -1) {
                System.out.println("This student is already on the list");
                continue;
            }
            check = false;
        } while (check);
        return studentID;
    }

    private String getVaccineID() {
        String vaccineID = "";
        boolean check = true;
        do {
            vaccineID = Utils.getString("Vaccine ID: ");
            if (findVaccineID(vaccineID) == -1) {
                System.out.println("Vaccine ID \"" + vaccineID + "\" does not exist. Try again!");
                continue;
            }

            check = false;
        } while (check);
        return vaccineID;
    }

    public void addAnother() {
        boolean check = true;
        String confirmMess = "";
        do {
            confirmMess = Utils.getString("Do you want to add another injection? (Y/N): ");
            if (confirmMess.equalsIgnoreCase("Y")) {
                this.add("---ADD NEW INJECTION---");
                check = true;
            } else if (confirmMess.equalsIgnoreCase("N")) {
                check = false;
            } else {
                check = true;
            }
        } while (check);
    }

    public int findStudentID(String id) {
        for (int i = 0; i < sList.size(); i++) {
            if (sList.get(i).getStudentID().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public int findVaccineID(String id) {
        for (int i = 0; i < vList.size(); i++) {
            if (vList.get(i).getVaccineID().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public int findInjectionID(String id) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getInjectionID().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public int findThisStudentID(String id) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getStudentID().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public int findThisVaccineID(String id) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getVaccineID().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void print() {
        if (this.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            System.out.printf("\n%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s\n",
                    "Injection ID", "First Place", "Second Place", "First Date", "Second Date",
                    "Student ID", "Vaccine ID");

            System.out.println("-------------------------------------------------"
                    + "--------------------------------------------------------------");

            for (Injection list : this) {
                System.out.printf("%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s\n", list.getInjectionID(),
                        list.getFirstPlace(), list.getSecondPlace(), list.getFirstDate(),
                        list.getSecondDate(), list.getStudentID(), list.getVaccineID());
            }
        }
    }

    public void printStudent() {
        if (sList.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            for (Student list : sList) {
                System.out.println("Student {" + "ID: " + list.getStudentID() + ", "
                        + "Name: " + list.getStudentName() + "}");
            }
        }
    }

    public void printVaccine() {
        if (vList.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            for (Vaccine list : vList) {
                System.out.println("Vaccine {" + "ID: " + list.getVaccineID() + ", "
                        + "Name: " + list.getVaccineName() + "}");
            }
        }
    }

    public void update(String message) {
        String injectionId = Utils.getString("Input Vaccine ID that you want to update: ");
        String secondPlace = "";
        String secondDate = "";
        int i = findInjectionID(injectionId);
        if (i == -1) {
            System.out.println("Injection does not exist");
        } else if (!this.get(i).getSecondPlace().isEmpty() && !this.get(i).getSecondDate().isEmpty()) {
            System.out.println("Student has completed 2 injections!");
        } else {
            Injection injection = this.get(i);
            injection.update();
            this.set(i, injection);
            System.out.println("[Updated]Student has completed 2 injections!");
        }
    }

    public void removeByID() {
        String id = "";
        id = Utils.getString("Input Vaccine ID that you want to delete: ");
        String confirmMess = "";
        boolean check = true;
        do {
            confirmMess = Utils.getString("Are you sure remove this Vaccine (Y/N)?");
            if (confirmMess.equalsIgnoreCase("Y")) {
                if (findInjectionID(id) != -1) {
                    this.remove(get(findInjectionID(id)));
                    System.out.println("Success.");
                } else {
                    System.out.println("Not Found!");
                }
                check = false;
            } else if (confirmMess.equalsIgnoreCase("N")) {
                System.out.println("Fail.");
                check = false;
            } else {
                check = true;
            }
        } while (check);
    }

    public void search() {
        String key = Utils.getString("Input Student ID that you want to search: ");
        int i = findThisStudentID(key);
        if (i != -1) {
            System.out.printf("\n%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s\n",
                    "Injection ID", "First Place", "Second Place", "First Date", "Second Date",
                    "Student ID", "Vaccine ID");

            System.out.println("-------------------------------------------------"
                    + "--------------------------------------------------------------");

            System.out.printf("%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s\n", this.get(i).getInjectionID(),
                    this.get(i).getFirstPlace(), this.get(i).getSecondPlace(), this.get(i).getFirstDate(),
                    this.get(i).getSecondDate(), this.get(i).getStudentID(), this.get(i).getVaccineID());
        } else {
            System.out.println("Not Found!");
        }
    }

    public void searchAnother() {
        boolean check = true;
        String confirmMess = "";
        do {
            confirmMess = Utils.getString("Do you want to search another injection? (Y/N): ");
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
            Injection injection = null;
            //System.out.println("Data from file:");
            while (fis.available() > 0) {
                injection = (Injection) ois.readObject();
                //Print when read file
                //System.out.println(food.toString());
                this.add(injection);
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
}
