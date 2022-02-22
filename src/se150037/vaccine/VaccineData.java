/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se150037.vaccine;

import java.io.IOException;
import se150037.utils.InterfaceMenu;
import se150037.utils.Menu;
import se150037.utils.Utils;

/**
 *
 * @author Huynh Trung Tin - SE150037
 */
public class VaccineData {
public void VaccineData() throws IOException {
        InterfaceMenu menu = new Menu();
        menu.addItem("Welcome to Vaccine Data - @ 2021 by <SE150037 - Huynh Trung Tin>");
        menu.addItem("Select the options below:");
        menu.addItem("1. Add a new vaccine.");
        menu.addItem("2. Update a vaccine.");
        menu.addItem("3. Delete a vaccine.");
        menu.addItem("4. Search a vaccine.");
        menu.addItem("5. Show all vaccines.");
        menu.addItem("6. Save data to file.");
        menu.addItem("7. Quit.");

        int choice = 0;
        String file = "vaccine.dat";
        VaccineList vList = new VaccineList();
        try {
            vList.readFileBinary(file);
        } catch (Exception e) {
        }

        do {
            menu.showMenu();
            choice = menu.getChoice();
            System.out.println("");
            switch (choice) {
                case 1:
                    vList.add("---ADD NEW VACCINE---");
                    vList.addAnother();
                    System.out.println("");
                    break;
                case 2:
                    vList.update("---UPDATE A VACCINE---");
                    System.out.println("");
                    break;
                case 3:
                    vList.removeByID();
                    System.out.println("");
                    break;
                case 4:
                    vList.search();
                    vList.searchAnother();
                    System.out.println("");
                    break;
                case 5:
                    vList.print();
                    System.out.println("");
                    break;
                case 6:
                    if (vList.writeFileBinary(file)) {
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
                            vList.writeFileBinary(file);
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
