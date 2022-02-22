/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se150037.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Huynh Trung Tin - SE150037
 */
public class Utils {

    public static int getIntLimit(String message, int min, int max) {
        int result = 0;
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        do {
            try {
                System.out.print(message);
                result = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Error! Integer number: ");
            }
            if (result < min || result > max) {
                System.out.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Try again: ");
                check = true;
            } else {
                check = false;
            }
        } while (check);

        return result;
    }

    public static String getString(String message) {
        String result = "";
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        do {
            System.out.print(message);
            String tmp = sc.nextLine();
            if (!tmp.isEmpty()) {
                result = tmp;
                check = false;
            }
        } while (check);
        return result;
    }

    public static int getInt(String message) {
        int result = 0;
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        do {
            try {
                System.out.print(message);
                result = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Error! Integer number: ");
            }
        } while (check);
        return result;
    }

    public static int getPositiveInt(String message) {
        int result = -1;
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        do {
            try {
                System.out.print(message);
                result = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Error! Integer number: ");
            }
            if (result < 0) {
                System.out.println("Number must be great than 0! Try again:");
                check = true;
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static float getPositiveFloat(String message) {
        float result = -1;
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        do {
            try {
                System.out.print(message);
                result = Float.parseFloat(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Error! Float number: ");
            }
            if (result < 0) {
                System.out.println("Number must be great than 0! Try again:");
                check = true;
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String updateString(String oldValue, String message) {
        String result = oldValue;
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static String getFirstDate(String message) {
        String result = "";
        String defaultDate = "2019/12/31";
        Scanner sc = new Scanner(System.in);

        boolean check = true;
        do {
            System.out.print(message);
            result = sc.nextLine();
            SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
            date.setLenient(false);
            try {
                Date covidDate = date.parse(defaultDate);
                Date firstDate = date.parse(result);
                if (firstDate.before(covidDate)) {
                    System.err.println("Injection date must be after COVID-19 virus spread date!");
                    check = true;
                } else {
                    check = false;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date.");
            }
        } while (check);
        return result;
    }

    public static String getSecondDate(String compareDate, String message) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(message);
            String tmp = sc.nextLine();
            if (tmp.isEmpty()) {
                result = tmp;
                check = false;
            } else {
                Calendar fourWeeks = Calendar.getInstance();
                Calendar twelveWeeks = Calendar.getInstance();

                SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
                date.setLenient(false);

                try {
                    Date secondDate = date.parse(tmp);
                    Date minDate = date.parse(compareDate);
                    Date maxDate = date.parse(compareDate);

                    fourWeeks.setTime(minDate);
                    fourWeeks.add(Calendar.WEEK_OF_MONTH, 3);
                    twelveWeeks.setTime(maxDate);
                    twelveWeeks.add(Calendar.WEEK_OF_MONTH, 11);

                    minDate = fourWeeks.getTime();
                    maxDate = twelveWeeks.getTime();

                    if (secondDate.before(minDate) || secondDate.after(maxDate)) {
                        System.out.println("The second dose of vaccine must be "
                                + "given 4 to 12 weeks after the first injection!");

                        System.out.println("From " + date.format(minDate) + " to "
                                + date.format(maxDate) + ". Try again: ");
                        check = true;
                    } else {
                        result = tmp;
                        check = false;
                    }
                } catch (ParseException e) {
                    System.out.println("Invalid date.");
                    check = true;
                }

            }
        } while (check);
        return result;
    }

    public static String updateSecondDate(String compareDate, String message) {
        String result = "";

        Scanner sc = new Scanner(System.in);
        boolean check = true;

        Calendar fourWeeks = Calendar.getInstance();
        Calendar twelveWeeks = Calendar.getInstance();

        do {
            result = getString(message);
            SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
            date.setLenient(false);

            try {
                Date secondDate = date.parse(result);
                Date minDate = date.parse(compareDate);
                Date maxDate = date.parse(compareDate);

                fourWeeks.setTime(minDate);
                fourWeeks.add(Calendar.WEEK_OF_MONTH, 3);
                twelveWeeks.setTime(maxDate);
                twelveWeeks.add(Calendar.WEEK_OF_MONTH, 11);

                minDate = fourWeeks.getTime();
                maxDate = twelveWeeks.getTime();

                if (secondDate.before(minDate) || secondDate.after(maxDate)) {
                    System.out.println("The second dose of vaccine must be "
                            + "given 4 to 12 weeks after the first injection!");

                    System.out.println("From " + date.format(minDate) + " to "
                            + date.format(maxDate) + ". Try again: ");
                    check = true;
                } else {
                    check = false;
                }

            } catch (ParseException e) {
                System.out.println("Invalid date.");
            }
        } while (check);
        return result;
    }

    public static String getStringEx(String message) {
        String result = "";
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }
}
