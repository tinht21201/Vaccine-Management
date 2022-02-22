/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se150037.vaccine;

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
public class VaccineList extends ArrayList<Vaccine>{
   
    public void add(String message) {
        String vaccineID = "";
        String vaccineName = "";
        boolean check = true;
        System.out.println(message);
        do {            
            vaccineID = Utils.getString("Vaccine ID: ");
            if(findByID(vaccineID) != -1) {
                System.out.println("ID \"" + vaccineID +"\" is Duplicate");
                continue;
            }
            vaccineName = Utils.getString("Vaccine Name: ");
            check = false;
        } while (check);
        this.add(new Vaccine(vaccineID, vaccineName) {});
        System.out.println("Added!");
    }
    
    public int findByID(String id) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getVaccineID().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    
    public void search() {
        String key = Utils.getString("Input Vaccine ID that you want to search: ");
        int i = findByID(key);
        if (i != -1) {
            System.out.println("Vaccine {" + "ID: " + this.get(i).getVaccineID() + ", "
                    + "Name: " + this.get(i).getVaccineName() + "}");
        } else {
            System.out.println("Not Found!");
        }
    }
    
    public void searchAnother() {
        boolean check = true;
        String confirmMess = "";
        do {
            confirmMess = Utils.getString("Do you want to search another vaccine? (Y/N): ");
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
        id = Utils.getString("Input Vaccine ID that you want to delete: ");
        String confirmMess = "";
        boolean check = true;
        do {
            confirmMess = Utils.getString("Are you sure remove this Vaccine (Y/N)?");
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
            for (Vaccine list : this) {
            System.out.println("Vaccine {" + "ID: " + list.getVaccineID() + ", "
                    + "Name: " + list.getVaccineName() + "}");
        }
        }
    }
    
    public void addAnother() {
        boolean check = true;
        String confirmMess = "";
        do {
            confirmMess = Utils.getString("Do you want to add another vaccine? (Y/N): ");
            if (confirmMess.equalsIgnoreCase("Y")) {
                this.add("---ADD NEW VACCINE---");
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
            Vaccine vaccine = null;
            //System.out.println("Data from file:");
            while (fis.available() > 0) {
                vaccine =  (Vaccine) ois.readObject();
                //Print when read file
                //System.out.println(food.toString());
                this.add(vaccine);
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
        String vaccineID = Utils.getString("Input Vaccine ID that you want to update: ");
        int i = findByID(vaccineID);
        if (i != -1) {
            Vaccine vaccine = this.get(i);
            vaccine.updateVaccine();
            this.set(i, vaccine);
            System.out.println("Updated.");
        } else {
            System.out.println("This vaccine does not exist.");
        }
    }
    
}
