/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se150037.injection;

import se150037.student.StudentData;
import se150037.vaccine.VaccineData;
import java.io.IOException;
import se150037.utils.InterfaceMenu;
import se150037.utils.Menu;

/**
 *
 * @author Huynh Trung Tin - SE150037
 */
public class InjectionData {

    public void InjectionData() throws IOException {
        InterfaceMenu menu = new Menu();
        menu.addItem("Welcome to Vaccine Management - @ 2021 by <SE150037 - Huynh Trung Tin>");
        menu.addItem("Select the options below:");
        menu.addItem("1. Build data structure.");
        menu.addItem("2. Show information all students have been injected.");
        menu.addItem("3. Add student's vaccine injection information.");
        menu.addItem("4. Updating information of students' vaccine injection.");
        menu.addItem("5. Delete student vaccine injection information.");
        menu.addItem("6. Search for injection information by studentID.");
        menu.addItem("7. Reload data from file.");
        menu.addItem("8. Save data to file.");
        menu.addItem("Others - Quit.");

        int choice = 0;
        String file = "injection.dat";
        InjectionList iList = new InjectionList();

        try {
            iList.readFileBinary(file);
        } catch (Exception e) {
        }
        iList.loadFile();

        do {
            menu.showMenu();
            choice = menu.getChoice();
            System.out.println("");
            switch (choice) {
                case 1:
                    BuildingData();
                    System.out.println("");
                    break;
                case 2:
                    iList.print();
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("Students Data: ");
                    iList.printStudent();
                    System.out.println("");
                    System.out.println("Vaccine Data: ");
                    iList.printVaccine();
                    iList.add("---ADD NEW INJECTION---");
                    iList.addAnother();
                    System.out.println("");
                    break;
                case 4:
                    iList.update("---UPPDATE AN INJECTION---");
                    System.out.println("");
                    break;
                case 5:
                    iList.removeByID();
                    System.out.println("");
                    break;
                case 6:
                    iList.search();
                    iList.searchAnother();
                    System.out.println("");
                    break;
                case 7:
                   if (iList.loadFile()) {
                       System.out.println("Load data successfully.");
                   } else {
                       System.err.println("Some files do not exist to load data.");
                   }
                    System.out.println("");
                break;
                case 8:
                    if (iList.writeFileBinary(file)) {
                        System.out.println("Saved.");
                    } else {
                        System.out.println("Save error.");
                    }
                    break;
            }
        } while (choice >= 1 && choice <= 8);
    }

    private void BuildingData() throws IOException {
        InterfaceMenu menu = new Menu();
        menu.addItem("Welcome to Building Data - @ 2021 by <SE150037 - Huynh Trung Tin>");
        menu.addItem("Select the options below:");
        menu.addItem("1. Vaccine Data.");
        menu.addItem("2. Student Data.");
        menu.addItem("Others - Quit.");

        int choice = 0;
        String file = "injection.dat";

        VaccineData vData = new VaccineData();
        StudentData sData = new StudentData();

        do {
            menu.showMenu();
            choice = menu.getChoice();
            System.out.println("");
            switch (choice) {
                case 1:
                    vData.VaccineData();
                    System.out.println("");
                    break;
                case 2:
                    sData.StudentData();
                    System.out.println("");
                    break;
            }

        } while (choice >= 1 && choice <= 2);
    }
}
