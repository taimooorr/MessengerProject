package Org.TheCodeGuy.Messenger;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Contact> contacts;
    private static Scanner scanner;
    private static void showOptions() {
        System.out.println("1. Manage Contact");
        System.out.println("2. Send Message");
        System.out.println("3. Exit");
        scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                manageContact();
                break;
            case 2:
                sendMessage();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Option");
                showOptions();
                break;
        }

    }
    private static void manageContact() {
        System.out.println("1. Show All Contacts"+
                "\n2. Add Contact"+
                "\n3. Search for a Contact"+
                "\n4. Delete Contact"+
                "\n5. Back");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                showAllContacts();
                break;
            case 2:
                addContact();
                break;
            case 3:
                searchContact();
                break;
            case 4:
                deleteContact();
                break;
            case 5:
                showOptions();
                break;
            default:
                System.out.println("Invalid Option");
                manageContact();
                break;
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
        }
        manageContact();
    }

    public static void main(String[] args) {
        contacts = new ArrayList<Contact>();
        System.out.println("Welcome to the Messenger App");
    }
}
