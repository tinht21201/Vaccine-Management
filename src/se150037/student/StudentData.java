/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se150037.student;

import java.io.IOException;
import se150037.utils.InterfaceMenu;
import se150037.utils.Menu;
import se150037.utils.Utils;

/**
 *
 * @author Huynh Trung Tin - SE150037
 */
public class StudentData {

    public StudentData() {
    } 

    public void StudentData() throws IOException {
        InterfaceMenu menu = new Menu();
        menu.addItem("Welcome to Student Data - @ 2021 by <SE150037 - Huynh Trung Tin>");
        menu.addItem("Select the options below:");
        menu.addItem("1. Add a new student.");
        menu.addItem("2. Update a student.");
        menu.addItem("3. Delete a student.");
        menu.addItem("4. Search a student.");
        menu.addItem("5. Show all students.");
        menu.addItem("6. Save data to file.");
        menu.addItem("7. Quit.");

        int choice = 0;
        String file = "student.dat";
        StudentList sList = new StudentList();
        try {
            sList.readFileBinary(file);
        } catch (Exception e) {
        }

        do {
            menu.showMenu();
            choice = menu.getChoice();
            System.out.println("");
            switch (choice) {
                case 1:
                    sList.add("---ADD NEW STUDENT---");
                    sList.addAnother();
                    System.out.println("");
                    break;
                case 2:
                    sList.update("---UPDATE A STUDENT---");
                    System.out.println("");
                    break;
                case 3:
                    sList.removeByID();
                    System.out.println("");
                    break;
                case 4:
                    sList.search();
                    sList.searchAnother();
                    System.out.println("");
                    break;
                case 5:
                    sList.print();
                    System.out.println("");
                    break;
                case 6:
                    if (sList.writeFileBinary(file)) {
                        System.out.println("Saved.");
                    } else {
                        System.out.println("Save error.");
                    }
                    break;
                case 7:
                    boolean check = true;
                    String confirmMess = "";
                    do {                        
                        confirmMess = Utils.getString("Do you want to save the changes to file? (Y/N): ");
                        if (confirmMess.equalsIgnoreCase("Y")) {
                            sList.writeFileBinary(file);
                            System.out.println("Saved.");
                            check = false;
                        } else if (confirmMess.equalsIgnoreCase("N")) {
                            check = false;
                        } else {
                            check = true;
                        }
                    } while (check);
                    System.out.println("");
                    break;
            }
        } while (choice != 7);
    }
}
