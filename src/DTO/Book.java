
package DTO;

public class Book {
    private String BookID;
    private String BookName;
    private double price;
    private int quantity;
    private String PID;
    private String Status;

    public Book() {
    }

    public Book(String BookID, String BookName, double price, int quantity, String PID, String Status) {
        this.BookID = BookID;
        this.BookName = BookName;
        this.price = price;
        this.quantity = quantity;
        this.PID = PID;
        this.Status = Status; 
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String BookID) {
        this.BookID = BookID;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String BookName) {
        this.BookName = BookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    public double SubTotal(){
        return price*quantity;
    }
    
    
    public String toString(){
        String str = String.format("%-10s|%-25s|%-15.3f|%-15d|%-15s|%-15s", BookID,BookName,price,quantity,Status,PID);
        return str;
    }
    
    
}
