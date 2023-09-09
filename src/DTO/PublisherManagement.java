package DTO;

import MyUtils.Utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class PublisherManagement {

    public static ArrayList<Publisher> PublisherList = new ArrayList();
    
    

    public static ArrayList<Publisher> getPublisherList() {
        return PublisherList;
    }

    public void setPublisherList(ArrayList<Publisher> PublisherList) {
        this.PublisherList = PublisherList;
    }

    public String checkDuplicatePublisherID() {
        String code;
        boolean check;
        do {
            check = false;
            code = Utils.getStringreg("Enter Publisher ID: ", "^P\\d{5}$",
                    "Publisher ID cannot be empty!!", "Wrong Publisher ID's format!! - ID's Format(Pxxxxx), x is a digit");
            for (int i = 0; i<= PublisherList.size()-1; i++){
            if (PublisherList.get(i).getPublisherID().equalsIgnoreCase(code)){
                check = true;
                System.out.println("Publisher with ID "+code+" already exist!!!");
            }
          }
        } while (check);
        return code;
    }

    public void PublisherManagement() {
        int choice;
        do {
            System.out.println("***************************");
            System.out.println("** PUBLISHERS MANAGEMENT **");
            System.out.println("1.1 Create a publisher");
            System.out.println("1.2 Delete a publisher");
            System.out.println("1.3 Save the publisher list to the file");
            System.out.println("1.4 Print the publisher list from the file");
            System.out.println("1.5 Exit");
            choice = MyUtils.Utils.getInt("Enter your choice from 1 to 5: ", "Enter choice from 1 to 5 !!!", 0, 5, "Choice");

            switch (choice) {
                case 1:
                    createNewPublisher();
                    saveToFile();
//                    display();
                    break;
                case 2:
                    deleteAPublisher();
//                    saveToFile();
                    break;
                case 3:
                    saveToFile();
//                    display();
                    break;
                case 4:
                    printFromFile();
                    break;
                default:
                    System.out.println("Returning to the Main Menu !!!");
                    break;
            }
        } while (choice >= 1 && choice <= 4);
    }

    public void createNewPublisher() {
        System.out.println("***INPUT INFORMATION FOR A NEW PUBLISHER***");
        String PublisherID = checkDuplicatePublisherID();
        String PublisherName = MyUtils.Utils.getStringV2("Enter publisher's name: ", "Publisher's name cannot be empty",
                "Publisher'name contains 5 to 30 characters", 30, 5);
        String PublisherPhone = MyUtils.Utils.getStringV2("Enter publisher's phone number: ", "Publisher's phone number cannot be empty",
                "Publisher's phone number contains 10 to 12 numbers", 12, 10);
        Publisher ps = new Publisher(PublisherID, PublisherName,PublisherPhone);
        PublisherList.add(ps);
        System.out.println("A new Publisher has been added successfully!!!");
       
    }

    public void deleteAPublisher() {
        boolean check = false;
//        String answer;
        System.out.println("***DELETING THE PUBLISHER***");
        String code = MyUtils.Utils.getStringreg("Enter Publisher's ID for deleting: ", "^P\\d{5}$",
                "This part cannot be empty", "Wrong Publisher ID format(Pxxxxx), x is a digit");
        for (int i = 0; i <= PublisherList.size() - 1; i++) {
            if (PublisherList.get(i).getPublisherID().equals(code)) {
                PublisherList.remove(PublisherList.get(i));
                check = true;
                System.out.println("Publisher with ID " + code + " has been deleted successfully!!!");
                saveToFile();
            }
        }
        if (!check) {
            System.out.println("Publisher’s Id does not exist");
            System.out.println("Delete the Publisher failed!!!");
        }
        
//        System.out.println("Do you want to continue (Y/N): ");
//        answer = MyUtils.Utils.getStringreg("Do you want to continue (Y/N): ", "^[YN]", "This part cannot be empty!!", "Y or N");
//        if (answer.equals("Y")){
//            deleteAPublisher();
//        }
    }

    public void addFromFile(String fileName, ArrayList<Publisher> List) {
        List.clear();
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                // tách các thông tin 
                StringTokenizer stk = new StringTokenizer(details, "|");
                String PID = stk.nextToken().trim();
                String PName = stk.nextToken().trim();
                String PPhoneNumber = stk.nextToken().trim();
                // create Publisher
                Publisher ps = new Publisher(PID, PName, PPhoneNumber);
                List.add(ps);
            }
            bf.close();
            fr.close();
        
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public void saveToFile() {
        // Sắp xếp tên theo thứ tự tăng dần của tên
//        PublisherList.sort(Comparator.comparing(Publisher::getPublisherName));
       
        Collections.sort(PublisherList, new Comparator<Publisher>() {
            @Override
            public int compare(Publisher t, Publisher t1) {
                return t.getPublisherID().compareTo(t1.getPublisherID());
            }
        });
        
        if (PublisherList.isEmpty()){
            System.out.println("The list is now empty!!!");    
        }
        System.out.println("Saving....");
         // tạo ra đối tượng file
            File f = new File("Publisher.txt");
        try {
            // tạo ra file
            FileWriter fw = new FileWriter(f);

            // viết vào file
            PrintWriter pw = new PrintWriter(f);

            for (int i = 0; i <= PublisherList.size() - 1; i++) {
//                pw.println(PublisherList.get(i).getPublisherID() + " | " + PublisherList.get(i).getPublisherName() + " | " + PublisherList.get(i).getPhone());
                pw.println(PublisherList.get(i));
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printFromFile() {
        ArrayList<Publisher> publishersSubList = new ArrayList();
        addFromFile("Publisher.txt", publishersSubList);
            
            // sắp xếp tên publisher theo thứ tự tăng dần
//            publishersSubList.sort(Comparator.comparing(Publisher::getPublisherName));
            
            Collections.sort(publishersSubList, new Comparator<Publisher>() {
            @Override
            public int compare(Publisher t, Publisher t1) {
               return t.getPublisherName().compareTo(t1.getPublisherName());
            }
        });
            
            // In ra danh sách 
            String str = String.format("%-15s|%-20s|%-15s","Publisher's ID", "Publisher's Name", "PhoneNumber");
            System.out.println("***PUBLISHER LIST***");
            System.out.println(str);
            for (int i=0; i<= publishersSubList.size()-1; i++){
                System.out.println(publishersSubList.get(i));
            }
          
    }

    public void display() {
        if (PublisherList.isEmpty()) {
            System.out.println("Nothing to print !!!");
        } else {
            System.out.println("***PUBLISHER LIST***");
            for (int i = 0; i <= PublisherList.size() - 1; i++) {
                System.out.println(PublisherList.get(i));
            }
        }
    }
}
