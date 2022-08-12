package Org.TheCodeGuy.Messenger;

public class Message {
    private  String text;
    private String recipientName;
    private String recipientNumber;
    private int id;

    public Message(String text,String recipientName, String recipientNumber, int id) {
        this.text = text;
        this.recipientName = recipientName;
        this.recipientNumber = recipientNumber;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientNumber() {
        return recipientNumber;
    }

    public void setRecipientNumber(String recipientNumber) {
        this.recipientNumber = recipientNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void GetDetails(){
        System.out.println("Contact Name : " + recipientName+"\nMessage : "+text+"\nMessage ID : "+id);
    }
}
