package MyUtils;

import java.util.Scanner;

public class Utils {

    Scanner sc = new Scanner(System.in);

    public static String getString(String welcome, String msg) {
        boolean check = true;
        String input = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println(msg);
            } else {
                check = false;
            }
        } while (check);
        return input;
    }

    public static String getStringV2(String welcome, String msg, String msg2, int max, int min) {
        boolean check = true;
        String input = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println(msg);
            } else if (input.length() < min || input.length() > max) {
                System.out.println(msg2);
            } else {
                check = false;
            }
        } while (check);
        return input;
    }
    
      public static String getStringV2EA(String welcome, String msg, String msg2, int max, int min , boolean permission) {
        boolean check = true;
        String input = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            input = sc.nextLine();
            if (input.isEmpty()) {
                if (permission){
                    check = false;
                }else System.out.println(msg);
            } else if (input.length() < min || input.length() > max) {
                System.out.println(msg2);
            } else {
                check = false;
            }
        } while (check);
        return input;
    }
    

    public static String getStringreg(String welcome, String pattern, String msg, String msgreg) {
        boolean check = true;
        String input = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println(msg);
            } else if (!input.matches(pattern)) {
                System.out.println(msgreg);
            } else {
                check = false;
            }
        } while (check);
        return input;
    }
    
     public static String getStringregEA(String welcome, String pattern, String msg, String msgreg, boolean permission) {
        boolean check = true;
        String input = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            input = sc.nextLine();
            if (input.isEmpty()) {
                if (permission){
                    check = false;
                }else System.out.println(msg);
            } else if (!input.matches(pattern)) {
                System.out.println(msgreg);
            } else {
                check = false;
            }
        } while (check);
        return input;
    }

    public static String getStringregV2(String welcome, String pattern, String pattern2,
            String msg, String msgreg, boolean permission) {
        boolean check = true;
        String input = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            input = sc.nextLine();
            if (input.isEmpty()) {
                if (permission){
                    check = false;
                }else System.out.println(msg);
            } else if (!input.matches(pattern) && !input.matches(pattern2)) {
                System.out.println(msgreg);
            } else {
                check = false;
            }
        } while (check);
        return input;
    }
    
    public static int getInt(String welcome, int min, String tmp) {
        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {

                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number <= min) {
                    System.out.println(tmp + " must be higher than " + min);
                } else {
                    check = false;
                }

            } catch (Exception e) {
                System.out.println(tmp + " cannot be empty!!!");
            }
        } while (check || number <= min);
        return number;
    }

    public static int getInt(String welcome,String msg, int min, int max, String tmp) {
        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {

                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number <= min) {
                    System.out.println(tmp + " must be higher than " + min + " and must be from 1 to " + max + "!!!!");
                } else if (number > max) {
                    System.out.println(tmp + " must be from 1 to " + max + "!!!!");
                } else {
                    check = false;
                }

            } catch (Exception e) {
                System.out.println(msg);
            }
        } while (check || number <= min || number > max);
        return number;
    }

    public static float getFloat(String welcome, int min, String tmp) {
        boolean check = true;
        float number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {

                System.out.print(welcome);
                number = Float.parseFloat(sc.nextLine());
                if (number <= min) {
                    System.out.println(tmp + " must be higher than " + min);
                } else {
                    check = false;
                }

            } catch (Exception e) {
                System.out.println(tmp + " cannot be empty!!!");
            }
        } while (check || number <= min);
        return number;
    }

    public static double getDouble(String welcome, int min, String tmp) {
        boolean check = true;
        double number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {

                System.out.print(welcome);
                number = Double.parseDouble(sc.nextLine());
                if (number <= min) {
                    System.out.println(tmp + " must be higher than " + min);
                } else {
                    check = false;
                }

            } catch (Exception e) {
                System.out.println(tmp + " cannot be empty!!!");
            }
        } while (check || number <= min);
        return number;
    }
    
    public static double getDoubleEAPrice(String welcome, int min, String tmp, boolean permission) {
        boolean check = true;
        double number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {

                System.out.print(welcome);
                number = Double.parseDouble(sc.nextLine());
                if (number <= min) {
                    System.out.println(tmp + " must be higher than " + min);
                } else {
                    check = false;
                }

            } catch (Exception e) {
                if (permission){
                    check = false;
                    number = 1.5;
                }else {
                    System.out.println(tmp + " cannot be empty!!!");
                }
            }
        } while (check || number <= min);
        return number;
    }
    
     public static double getDoubleEAQuantity(String welcome, int min, String tmp, boolean permission) {
        boolean check = true;
        double number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {

                System.out.print(welcome);
                number = Double.parseDouble(sc.nextLine());
                if (number <= min) {
                    System.out.println(tmp + " must be higher than " + min);
                } else {
                    check = false;
                }

            } catch (Exception e) {
                if (permission){
                    check = false;
                    number = 1.;
                }else {
                    System.out.println(tmp + " cannot be empty!!!");
                }
            }
        } while (check || number <= min);
        return number;
    }

}
