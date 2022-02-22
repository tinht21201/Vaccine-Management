/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se150037.utils;

import java.util.ArrayList;

/**
 *
 * @author Huynh Trung Tin - SE150037
 */
public class Menu extends ArrayList<String> implements InterfaceMenu {

    public Menu() {
        super();
    }

    @Override
    public void addItem(String s) {
        this.add(s);
    }

    @Override
    public int getChoice() {
        int choice = 0;
        choice = Utils.getInt("Input your choice: ");
        return choice;
    }

    @Override
    public void showMenu() {
        for (String menu : this) {
            System.out.println(menu);
        }
    }

}
