package com.oops;


import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Welcome to the Address Book Program");
        Scanner in = new Scanner(System.in);
        AddressBook contactInfo = new AddressBook();
        contactInfo.setUpInfo();
        contactInfo.displayListItems();
        int i;
        int j = 0;
        for (i = 1; i > j; i++) {
            System.out.println("Press 1 to continue adding contacts/Press 2 to edit a contact/Press 3 to delete a contact/Press 0 to exit the program");
            int operation = in.nextInt();
            if (operation == 1) {
                contactInfo.setUpInfo();
                contactInfo.displayListItems();
                System.out.println("Total Contacts for Address Book 1:"+contactInfo.getcompanyName("DMart"));
            } else if (operation == 2) {
                contactInfo.editContact();
                contactInfo.displayListItems();
            } else if (operation == 0) {
                j = i + 2;
            } else if (operation == 3) {
                contactInfo.deleteContact();
            }
        }
    }
}


