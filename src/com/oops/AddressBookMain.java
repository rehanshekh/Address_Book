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
            System.out.println("Press 1 to continue creating Address Book/Press 2 to edit a contact in an Address Book/Press 3 to delete a contact from an Address Book/" +
                    "Press 4 to Add a New Contact to an Address Book/Press 5 to Search a Contact by State or City/Press 0 to exit the program");
            System.out.println("Press 6 to display cityMap/Press 7 to sort and display Contacts by City/Press 8 to sort and display Contacts by State");
            int operation = in.nextInt();
            switch (operation) {
                case 1 -> {
                    contactInfo.setUpInfo();
                    contactInfo.displayListItems();
                }
                case 2 -> contactInfo.editContact();
                case 3 -> contactInfo.deleteContact();
                case 4 -> contactInfo.add();
                case 5 -> contactInfo.search();
                case 6 -> contactInfo.displayCityMap();
                case 7 -> contactInfo.sortByCity();
                case 8-> contactInfo.sortByState();
                default -> j = i + 2;
            }
        }
    }
}


