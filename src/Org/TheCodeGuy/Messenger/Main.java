package Org.TheCodeGuy.Messenger;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Contact> contacts;
    private  static int id = 0;
    private static Scanner scanner;
    private static void showOptions() {
        System.out.println("1. Manage Contact");
        System.out.println("2. Send Message");
        System.out.println("3. Exit");
        scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> manageContact();
            case 2 -> Messager();
            case 3 -> System.exit(0);
            default -> {
                System.out.println("Invalid Option");
                showOptions();
            }
        }

    }

    private static void Messager() {
        System.out.println("""
                Please Select One :\s
                1. Show All Messges
                2. Send a  New Message
                3. Go Back\s""");
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> showAllMessages();
            case 2 -> sendNewMessage();
            case 3 -> showOptions();
            default -> {
                System.out.println("Invalid Option");
                Messager();
            }
        }
    }

    private static void sendNewMessage() {
        System.out.println("Enter Recipient Name: ");
        String recipient = scanner.next();
        System.out.println("Enter Recipient Number: ");
        String number = scanner.next();
        if(number.equals("")){
            System.out.println("Invalid Number");
            sendNewMessage();
        }
        else{
            boolean doesExist = false;
            for(Contact contact : contacts){
                if(contact.getNumber().equals(number)){
                    doesExist = true;
                    break;
                }
            }
            if(doesExist){
                System.out.println("Enter Message: ");
                String message = scanner.next();
                if(message.equals("")){
                    System.out.println("Can't Send Empty Message");
                    sendNewMessage();
                }
                else {
                    id++;
                    Message newMessage = new Message(message, recipient, number, id);
                    for(Contact contact : contacts){
                        if(contact.getNumber().equals(number)){
                           ArrayList<Message>  newMessages = contact.getMessages();
                           newMessages.add(newMessage);
                           Contact currentContact = contact;
                           currentContact.setMessages(newMessages);
                           contacts.remove(contact);
                            contacts.add(currentContact);


                        }
                    }
                }

            }
            else{
            System.out.println("There is no such contact");
            }

        }
        System.out.println("Message Sent");
        Messager();
    }

    private static void showAllMessages() {
        ArrayList<Message> allMessages = new ArrayList<Message>();
        for (Contact contact : contacts) {
            allMessages.addAll(contact.getMessages());
        }
        if (allMessages.size() > 0) {
            for (Message message : allMessages) {
                message.GetDetails();
                System.out.println("---------------");
            }
        } else {
            System.out.println("No Messages Found");
        }
    }

    private static void manageContact() {
        System.out.println("""
                1. Show All Contacts
                2. Add Contact
                3. Search for a Contact
                4. Delete Contact
                5. Back""");
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> showAllContacts();
            case 2 -> addContact();
            case 3 -> searchContact();
            case 4 -> deleteContact();
            case 5 -> showOptions();
            default -> {
                System.out.println("Invalid Option");
                manageContact();
            }
        }

    }

    private static void deleteContact() {
        System.out.println("Enter Contact Name to Delete");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Can not Search Null Value");
            deleteContact();
        }
        else{
            boolean found = false;
            for (Contact contact : contacts) {
                if (contact.getName().equals(name)) {
                    found = true;
                    contacts.remove(contact);
                    System.out.println("Contact Deleted");
                    break;
                }
            }
            if (!found) {
                System.out.println("Contact Not Found");
            }
        }
        manageContact();
    }

    private static void searchContact() {
        System.out.println("Enter Contact Name to Search");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Invalid Name");
            searchContact();
        }
        else{
            boolean doesExist = false;
            for(Contact contact : contacts){
                if(contact.getName().equals(name)){
                    System.out.println("Contact Found");
                    doesExist = true;
                    contact.GetDetails();

                }
            }
            if(!doesExist){
                System.out.println("Contact Not Found");
            }

        }
        manageContact();
    }

    private static void addContact() {
        System.out.println("Enter Contact Name");
        String name = scanner.next();
        System.out.println("Enter Contact Number");
        String number = scanner.next();
        System.out.println("Enter Contact Email");
        String email = scanner.next();
        if(name.equals("") || number.equals("") ){
            System.out.println("Please Enter Valid Details");
            addContact();
        }
        else{
            boolean doesExist = false;
            for(Contact contact : contacts){
                if(contact.getName().equals(number)){
                    doesExist = true;
                    break;
                }
            }
            if(!doesExist){
                Contact contact = new Contact(name, number, email);
                contacts.add(contact);
                System.out.println("Contact Added Successfully");
                System.out.println("Contact Name : " + name+
                        "\nContact Number : "+number+
                        "\nContact Email : "+email);
            }
            else{
                System.out.println("Contact Already Exists");
                addContact();
            }

        }
        manageContact();
    }

    private static void showAllContacts() {
        for (Contact contact : contacts) {
            contact.GetDetails();
            System.out.println("----------------------------------------------------");
        }
        manageContact();
    }

    public static void main(String[] args) {
        contacts = new ArrayList<Contact>();
        System.out.println("Welcome to the Messenger App");
        showOptions();
    }
}
