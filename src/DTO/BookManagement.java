package DTO;

import static DTO.PublisherManagement.PublisherList;
import java.util.ArrayList;
import java.util.Scanner;
import MyUtils.Utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BookManagement {
    public static ArrayList<Book> Booklist = new ArrayList();
//    ArrayList<Book> BookSubList = new ArrayList();
    

    public ArrayList<Book> getList() {
        return Booklist;
    }

    public void setList(ArrayList<Book> list) {
        this.Booklist = Booklist;
    }
    
    public String checkDuplicateBookID(){
        String code;
        boolean check;
        do {
            check = false;
            code = Utils.getStringreg("Enter Book ID: ", "^B\\d{5}$",
                    "Book ID cannot be empty!!", "Wrong Book ID's formant!! - Book ID's Format(Bxxxxx), x is a digit");
            for (int i =0; i<= Booklist.size()-1; i++){
                if (Booklist.get(i).getBookID().equalsIgnoreCase(code)){
                    System.out.println("Book with ID "+ code +" already exist!!!");
                    check = true;
                }
            }        
        }while (check);
        return code;
    }
    
    public int checkQuantity(){
        double number;
        boolean check;
        do {
            check = false;
            number = MyUtils.Utils.getDouble("Enter Book's quantity: ", 0, "Book's quantity");
            if (Math.ceil(number) != Math.floor(number)){
                System.out.println("Quantity must be an integer number!!!");
                check = true;
            }
            
        }while (check);
        
        return (int)number;
    }
    
    public int checkQuantityEA(){
        double number;
        boolean check;
        do {
            check = false;
            number = MyUtils.Utils.getDoubleEAQuantity("Enter Book's quantity: ", 0, "Book's quantity", true);
            if (Math.ceil(number) != Math.floor(number)){
                System.out.println("Quantity must be an integer number!!!");
                check = true;
            }
            
        }while (check);
        
        return (int)number;
    }
    
    
        public double checkPrice(){
        double number;
        boolean check;
        do {
            check = false;
            number = MyUtils.Utils.getDouble("Enter Book's price: ", 0, "Book's price");
            if (Math.ceil(number) == Math.floor(number)){
                System.out.println("Price must a real number!!!");
                check = true;
            }
            
        }while (check);
        
        return number;
    }
        
        public double checkPriceEA(){
        double number;
        boolean check;
        do {
            check = false;
            number = MyUtils.Utils.getDoubleEAPrice("Enter Book's price: ", 0, "Book's price", true);
            if (Math.ceil(number) == Math.floor(number)){
                System.out.println("Price must a real number!!!");
                check = true;
            }
            
        }while (check);
        
        return number;
    }
        // kiểm tra xem PID có tồn tại không
        public boolean checkPublisherID(String PID){
            boolean check = false;
            for (int i =0; i<= PublisherList.size()-1; i++){
                if (PublisherList.get(i).getPublisherID().matches(PID)){
                    check = true;
                }
            }       
          return check; 
        }
        // Method quản lí sách
    public void BooksManagement(){
        int choice;
        do {
            System.out.println("**********************");
            System.out.println("** BOOKS MANAGEMENT **");
            System.out.println("2.1 Create a Book");
            System.out.println("2.2 Search the book");
            System.out.println("2.3 Update a book");
            System.out.println("2.4 Delete the book");
            System.out.println("2.5 Save the Books list to file");
            System.out.println("2.6 Print the Books list from the line");
            System.out.println("2.7 Exit");
            
            choice = Utils.getInt("Enter your choice from 1 to 7: ","Enter choice from 1 to 7 !!!", 0,7, "Choice");
            switch(choice){
                case 1: 
                    createNewBook();
//                    saveToFileBook();
                    break;
                case 2: 
                    searchTheBookV2();
                    break;
                case 3: 
                    updateBook();
//                    saveToFileBook();
                    break;
                case 4: 
                    deleteTheBook();
//                    saveToFileBook();
                    break;
                case 5: 
                    saveToFileBook();
                    break;
                case 6: 
                    printfromFileBookList();
                    break;    
                default:
                    System.out.println("Returning to the Main Menu !!!");
                    break;       
            }       
        }while(choice >=1 && choice <=6);
    }
    
    public void createNewBook(){
        System.out.println("***INPUT INFORMATION FOR A NEW BOOK***");
        String BookID = checkDuplicateBookID();
        String BookName = MyUtils.Utils.getStringV2("Enter Book's name: ", "Book's name cannot be empty",
                "Book's name has a length from 5 to 30 characters", 30, 5);
        double BookPrice = checkPrice();
        int BookQuantity = checkQuantity();
        String PID = MyUtils.Utils.getStringreg("Enter Publisher ID: ", "^P\\d{5}$",
                    "Publisher ID cannot be empty!!", "Wrong Publisher ID's format!! - ID's Format(Pxxxxx), x is a digit");
        boolean check = checkPublisherID(PID);
        if (check){
            String BookStatus = MyUtils.Utils.getStringregV2("Enter Book's status: ", "^[Aa]vailable$",
                "^[Nn]ot [Aa]vailable", "Book's status cannot be empty!!!", "Available or Not Available values!!!", false);
            Book bs = new Book(BookID, BookName,BookPrice, BookQuantity, PID, BookStatus);
            Booklist.add(bs);
//            BookSubList.add(bs);
            System.out.println("A new Book has been added successfully!!!");
            saveToFileBook();
        }else {
            System.out.println("Publisher’s Id is not found !!!");
            System.out.println("Creating a new book failed!!!");
        }
        
    }
    public void searchTheBook(){
        String str = String.format("%-10s|%-25s|%-15s|%-15s|%-15s|%-15s", "BookID","BookName", "Price", "Quantity", "Book's status", "Publisher'ID");
      if (Booklist.isEmpty()){
          System.out.println("Have no any books!!!");
          return;
      }
      // sắp xếp theo thứ tự của tên
         Collections.sort(Booklist, new Comparator<Book>() {
            @Override
            public int compare(Book t, Book t1) {
               return t.getBookName().compareTo(t1.getBookName());
            }
        });
      int choice;
      do {
            System.out.println("***SEARCHING THE BOOK***");
            System.out.println("1. Search by Book's name");
            System.out.println("2. Search by Publisher's ID");
            System.out.println("3. Exit");
            choice = MyUtils.Utils.getInt("Enter your searching method: ", 
                    "Enter choice from 1 to 3 !!!", 0, 3, "Choice");
            switch(choice){
                case 1: 
                    // search theo tên sách
                    System.out.println("***SEARCHING BY BOOK'S NAME***");
                    boolean check = false;
                    String name = MyUtils.Utils.getStringV2("Enter Book's name for searching: ", "Book's name cannot be empty!!!",
                            "Book's name has a length from 1 to 30 characters", 30, 1);
                    
                    // tạo biến hứng thg string.format rồi sout ra
//                    for (int i =0; i<= Booklist.size()-1; i++){
//                        System.out.println(Booklist.get(i).toStringHeader());
//                        break;
//                    }
                    for (int i =0; i<= Booklist.size()-1; i++){
                        if (Booklist.get(i).getBookName().equalsIgnoreCase(name) || 
                                Booklist.get(i).getBookName().toLowerCase().contains(name.toLowerCase())){
                            System.out.println(str);
                            break;
                                    
                        }
                    }
                    
                    for (int i =0; i<= Booklist.size() -1 ; i++){
                        if (Booklist.get(i).getBookName().equalsIgnoreCase(name) || 
                                Booklist.get(i).getBookName().toLowerCase().contains(name.toLowerCase())){
                            System.out.println(Booklist.get(i));
                            check = true;
                        }
                    }
                    if (!check) System.out.println("Book does not exist!!!");
                    break;
                case 2: 
                    // search dựa trên ID của publisher
                    System.out.println("***SEARCHING BY PUBLISHER'S ID***");
                    check = false;
                    String code = MyUtils.Utils.getStringreg("Enter Publisher ID for searching: ", "^P\\d{5}$",
                    "Publisher ID cannot be empty!!", "Wrong Publisher ID's format!! - ID's Format(Pxxxxx), x is a digit");
                    
                    for (int i =0 ; i<= Booklist.size()-1; i++){
                        if (Booklist.get(i).getPID().equalsIgnoreCase(code)){
                            System.out.println(str);
                            break;
                        }
                    }
                    
                    for (int i =0; i<= Booklist.size()-1 ; i++){
                        if (Booklist.get(i).getPID().equalsIgnoreCase(code)){
                            System.out.println(Booklist.get(i));
                            check = true;
                        }
                    }
                    if (!check) System.out.println("Have no any books belongs to the Publisher with ID "+code+"!!!");
                    break;
                default:
                    System.out.println("Returning...");
                    break;
            }
      }while (choice >= 1 && choice <= 2);
    }
    
    public void searchTheBookV2(){
        System.out.println("***SEARCHING THE BOOK***");        
        if (Booklist.isEmpty()){
            System.out.println("Have no any books !!!");
        }
           // Sắp xếp theo thứ tự tăng dần của tên
            Collections.sort(Booklist, new Comparator<Book>() {
            @Override
            public int compare(Book t, Book t1) {
                return t.getBookName().compareTo(t1.getBookName());
            }
        });
//         Booklist.sort(Comparator.comparing(Book::getBookName));
        String str = String.format("%-10s|%-25s|%-15s|%-15s|%-15s|%-15s", "Book's ID","Book's Name", "Price", "Quantity", "Book's status", "Publisher'ID");
        boolean check = false;
        String name = MyUtils.Utils.getStringV2("Enter Book's name for searching: ", "Book's name cannot be empty!!!",
        "Book's name has a length from 1 to 30 characters", 30, 1);
        String code = MyUtils.Utils.getStringregEA("Enter Publisher ID for searching: ", "^P\\d{5}$",
                    "Publisher ID cannot be empty!!", "Wrong Publisher ID's format!! - ID's Format(Pxxxxx), x is a digit", true);
        if (code.isEmpty()){
            for (int i =0; i<= Booklist.size()-1; i++){
                if (Booklist.get(i).getBookName().equalsIgnoreCase(name) ||
                        Booklist.get(i).getBookName().toLowerCase().contains(name.toLowerCase())){
                    System.out.println(str);
                    break;
                }
            }
            for (int i =0; i<= Booklist.size()-1; i++){
                if (Booklist.get(i).getBookName().equalsIgnoreCase(name) ||
                        Booklist.get(i).getBookName().toLowerCase().contains(name.toLowerCase())){
                    check = true;
                    System.out.println(Booklist.get(i));
                }
            }
        } else if (!(code.isEmpty())){
            for (int i =0; i<= Booklist.size()-1; i++){
                if ((Booklist.get(i).getBookName().equalsIgnoreCase(name) ||
                        Booklist.get(i).getBookName().toLowerCase().contains(name.toLowerCase())) &&
                        Booklist.get(i).getPID().equals(code)){
                    System.out.println(str);
                }
            }
            
            for (int i = 0; i<= Booklist.size()-1; i++){
                if ((Booklist.get(i).getBookName().equalsIgnoreCase(name) || 
                        Booklist.get(i).getBookName().toLowerCase().contains(name.toLowerCase())) && 
                        Booklist.get(i).getPID().equals(code)){
                    check = true;
                    System.out.println(Booklist.get(i));
                }
            }
        }
        if (!check){
            System.out.println("Have no any books !!!");
        }
    }
    public void updateBook(){
        boolean check = false;
        boolean flag;
        System.out.println("***UPDATING THE BOOK***");
        String code = MyUtils.Utils.getStringreg("Enter Book ID for updating: ", "^B\\d{5}$",
                    "Book ID cannot be empty!!", "Wrong Book ID's formant!! - ID's Format(Bxxxxx), x is a digit");
        for (int i =0; i<= Booklist.size()-1; i++){        
               if (Booklist.get(i).getBookID().equalsIgnoreCase(code)){
                System.out.println("---Updating information of book with ID "+code+"---");
                String name =  MyUtils.Utils.getStringV2EA("Enter Book's new name: ", "Book's name cannot be empty!!!",
                            "Book's name has a length from 5 to 30 characters", 30, 5, true);
                double price = checkPriceEA();
                int quantity = checkQuantityEA();
                
                String PID = MyUtils.Utils.getStringregEA("Enter Publisher ID: ", "^P\\d{5}$",
                    "Publisher ID cannot be empty!!", "Wrong Publisher ID's formant!! - ID's Format(Pxxxxx), x is a digit", true);
                if (PID.isEmpty()){
                    flag = checkPublisherID(Booklist.get(i).getPID());
                }else {
                    flag = checkPublisherID(PID);
                }
                
            if (flag){
               String BookStatus = MyUtils.Utils.getStringregV2("Enter Book's status: ", "^[Aa]vailable$",
                "^[Nn]ot [Aa]vailable", "Book's status cannot be empty!!!", "Available or Not Available values!!!", true);
//                Booklist.get(i).setBookName(name);
                // kiểm tra thông tin có bỏ trống không, nếu có thì vẫn giữ lại thông tin cũ, ko cập nhật
                // BookName
                if (name.isEmpty()){
                    Booklist.get(i).setBookName(Booklist.get(i).getBookName());
                }else {
                    Booklist.get(i).setBookName(name);
                }
//               Book's Price
                if (price == 1.5 ){
                    Booklist.get(i).setPrice(Booklist.get(i).getPrice());
                }else {
                    Booklist.get(i).setPrice(price);
                }
//                Book's Quantity
                if (quantity == 1){
                    Booklist.get(i).setQuantity(Booklist.get(i).getQuantity());
                }else {
                    Booklist.get(i).setQuantity(quantity);
                }
//               Book's PID
                if (PID.isEmpty()){
                    Booklist.get(i).setPID(Booklist.get(i).getPID());
                }else {
                    Booklist.get(i).setPID(PID);
                }
//               Book's Status
               if (BookStatus.isEmpty()){
                    Booklist.get(i).setStatus(Booklist.get(i).getStatus());
                }else {
                   Booklist.get(i).setStatus(BookStatus);
               }
                check = true;
                // Thông báo ra màn hình
                System.out.println("Book with ID "+code+" has been updated successfully!!!");
                
                // in ra thông tin sau khi update
                String str = String.format("%-10s|%-25s|%-15s|%-15s|%-15s|%-15s", "Book's ID","Book's Name","Price","Quantity","Book's Status","Publisher's ID");
                System.out.println("---Book's information after updating---");
                System.out.println(str);
                System.out.println(Booklist.get(i));
                
                // save để đồng bộ với file txt
                saveToFileBook();
                
            }else {
                 System.out.println("Publisher's ID does not exist!!!");
                 System.out.println("Updating a book failed!!!");
                 check = true;
            } 
        }
      }
        if (!check) {
              System.out.println("Book's ID does not exist!!!");
              System.out.println("Updating a book failed!!!");
          }
   }
    public void deleteTheBook(){
        boolean check = false;
        System.out.println("***DELETING THE BOOK***");
        String code = MyUtils.Utils.getStringreg("Enter Book ID for deleting: ", "^B\\d{5}$",
                    "Book ID cannot be empty!!", "Wrong Book ID's format!! - Book ID's Format(Bxxxxx), x is a digit");
        for (int i = 0; i<= Booklist.size()-1; i++){
            if (Booklist.get(i).getBookID().equalsIgnoreCase(code)){
                Booklist.remove(Booklist.get(i));
                check = true;
                System.out.println("Book with ID "+code+" has been deleted successfully!!!");
                saveToFileBook();
            }
        }
        if (!check) {
            System.out.println("Book with ID "+code+ " does not exist!!!");
            System.out.println("Deleting the book failed!!!");
        }
    }
    
    public void saveToFileBook() {
//          sắp xếp theo theo BookID tăng dần 
         Collections.sort(Booklist, new Comparator<Book>() {
            @Override
            public int compare(Book t, Book t1) {
               return t.getBookID().compareTo(t1.getBookID());
            }
        });
         
//         Booklist.sort(Comparator.comparing(Book::getBookID));
         
        if (Booklist.isEmpty()){
            System.out.println("The list is now empty!!!");   
            return;
        }
        System.out.println("Saving....");
         // tạo ra đối tượng file
            File f = new File("Book.txt");
        try {
            // tạo ra file
            FileWriter fw = new FileWriter(f);

            // viết vào file
            PrintWriter pw = new PrintWriter(f);

            for (int i = 0; i <= Booklist.size() - 1; i++) {
//                pw.println(PublisherList.get(i).getPublisherID() + " | " + PublisherList.get(i).getPublisherName() + " | " + PublisherList.get(i).getPhone());
                pw.println(Booklist.get(i));
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void addFromFileBook(String fileName, ArrayList<Book> List) {
        // reset lại BookList
        List.clear();
        File f = new File(fileName);
        try {  
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                // tách các thông tin 
                StringTokenizer stk = new StringTokenizer(details, "|");
                String BID = stk.nextToken().trim();
                String BName = stk.nextToken().trim();
                double Bprice = Double.parseDouble(stk.nextToken().trim());
                int Bquantity = Integer.parseInt(stk.nextToken().trim());
                String Status = stk.nextToken().trim();
                String PID = stk.nextToken().trim();
                
                // create Book đổ các giá trị lấy đc ở trên vào khuôn tạo book mới
                Book bk = new Book(BID, BName, Bprice, Bquantity, PID, Status);
                List.add(bk);
            }
            bf.close();
            fr.close();
        
        }catch (IOException | NumberFormatException e){
            System.out.println(e);
        }
    }
    
    public void printfromFileBookList(){
        ArrayList<Book> BookSubList = new ArrayList();
        addFromFileBook("Book.txt", BookSubList);
        String str = String.format("%-10s|%-25s|%-15s|%-15s|%-15s|%-20s|%-20s", "Book's ID", "Book's Name", "Price",
                "Quantity", "Sub total", "Publisher's name", "Book's status");
        
        // sắp xếp theo thứ tự Quantity giảm dần, nếu quantity bằng nhau thì xếp theo giá tăng dần
        BookSubList.sort(Comparator.comparing(Book::getQuantity).reversed().thenComparing(Book::getPrice));
//        BookSubList.sort(Comparator.comparing(Book::getPrice));
        System.out.println("***BOOK LIST***");
        for (int i =0; i<= BookSubList.size()-1; i++){
            for (int j = 0; j<= PublisherList.size()-1; j++){
                if (BookSubList.get(i).getPID().equals(PublisherList.get(j).getPublisherID())){
                    System.out.println(str);
                    break;
                }
            }
            break;
        }
        
        for (int i =0; i<= BookSubList.size()-1; i++){
            for (int j= 0; j <= PublisherList.size()-1; j++){
                if (BookSubList.get(i).getPID().equals(PublisherList.get(j).getPublisherID())){
                    System.out.printf("%-10s|%-25s|%-15.3f|%-15d|%-15.1f|%-20s|%-20s\n", BookSubList.get(i).getBookID(), BookSubList.get(i).getBookName(),
                             BookSubList.get(i).getPrice(), BookSubList.get(i).getQuantity(), BookSubList.get(i).SubTotal(), PublisherList.get(j).getPublisherName(), BookSubList.get(i).getStatus());
                }
            }
        }
    }
    
    public void display(){
        if (Booklist.isEmpty()){
            System.out.println("Nothing to print!!!");
        } 
        System.out.println("***Books List*** ");
        for (int i = 0; i<= Booklist.size()-1; i++){
            System.out.println(Booklist.get(i));
        }
    }
}
