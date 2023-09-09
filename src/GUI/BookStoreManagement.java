package GUI;

import DTO.BookManagement;
import DTO.PublisherManagement;

public class BookStoreManagement {

    public static void main(String[] args) {
        String FilePublisher = "Publisher.txt";
        String FileBook = "Book.txt";
        BookManagement BSM = new BookManagement();
        PublisherManagement PSM = new PublisherManagement();
        PSM.addFromFile(FilePublisher, PublisherManagement.PublisherList);
        BSM.addFromFileBook(FileBook, BookManagement.Booklist);
//        PSM.display();
//        BSM.display();
        int choice;
        do {
            System.out.println("*** BOOK STORE MANAGEMENT ***");
            System.out.println(" 1. PUBLISHERS' MANAGEMNET ");
            System.out.println(" 2. BOOKS' MANAGEMENT ");
            System.out.println(" 3. EXIT THE PROGRAM  ");
            System.out.println("-----------------------------");
            choice = MyUtils.Utils.getInt("Enter your choice: ","Enter choice from 1 to 3 !!!", 0,3, "Choice");
            switch (choice) {
                case 1:
                    PSM.PublisherManagement();
                    break;
                case 2:
                    BSM.BooksManagement();
                    break;
                default:
                    System.out.println("Exiting the program...");
                    System.out.println("See you again !!!");
                    break;
            }
        } while (choice >= 1 && choice <= 2);
    }
}
