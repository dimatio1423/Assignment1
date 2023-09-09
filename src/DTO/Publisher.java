
package DTO;


public class Publisher {
    private String PublisherID;
    private String PublisherName;
    private String Phone;

    public Publisher() {
    }

    public Publisher(String PublisherID, String PublisherName, String Phone) {
        this.PublisherID = PublisherID;
        this.PublisherName = PublisherName;
        this.Phone = Phone;
    }

    public String getPublisherID() {
        return PublisherID;
    }

    public void setPublisherID(String PublisherID) {
        this.PublisherID = PublisherID;
    }

    public String getPublisherName() {
        return PublisherName;
    }

    public void setPublisherName(String PublisherName) {
        this.PublisherName = PublisherName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
   
    public String toString(){
        String str = String.format("%-15s|%-20s|%-15s", PublisherID, PublisherName, Phone);
        return str;
    }
}
