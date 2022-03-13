package com.oops;

import java.util.*;

public class AddressBook {
    Map<String, List<Contacts>> map = new LinkedHashMap<>();
    List<Contacts> list = new LinkedList<>();
    Scanner in = new Scanner(System.in);

    public void setUpInfo() {
        //
        System.out.println("How many Address Book you want to create");
        //
        int bookCount = in.nextInt();
        //
        for (int b = 1; b <= bookCount; b++) {
            //
            System.out.println("Create an Address Book");
            //
            String bookName = in.next();
            //
            map.put(bookName, new LinkedList<Contacts>());
            System.out.println("How many contacts you want to create");
            int contactcount = in.nextInt();
            for (int i = 1; i <= contactcount; i++) {
                System.out.println("Enter the First Name:");
                String firstname = in.next();
                System.out.println("Enter the Last Name:");
                String lastname = in.next();
                System.out.println("Enter the Address in words:");
                String address = in.next();
                System.out.println("Enter the City:");
                String city = in.next();
                System.out.println("Enter the State Name:");
                String state = in.next();
                System.out.println("Enter the ZIP code:");
                int zip = in.nextInt();
                System.out.println("Enter the Phone No");
                long phone = in.nextLong();
                System.out.println("Enter email id:");
                String email = in.next();
                Contacts contact = new Contacts(firstname, lastname, address, city, state, zip, phone, email);
                list.add(contact);
                Collections.addAll(list);

                map.get(bookName).add(contact);
            }
        }
    }

    public void displayListItems() {
        map.forEach((k, Contacts) -> System.out.println(k + "  " + '\n' + Contacts.toString()));
        System.out.println();
        list.sort(Contacts.ConNameComparator);
        for (Contacts someinfo : list) {
            System.out.println(someinfo.getFirstName() + someinfo.getLastName() + "  " + someinfo.getAddress() + "  " + someinfo.getCity() + "  " + someinfo.getState() + "  " + someinfo.getZip() + "  " + someinfo.getPhoneNo() + "  " + someinfo.getEmail());
        }
    }

    public void editContact() {
        System.out.println("Enter the first name of the contact to be edited");
        String newName = in.next();
        for (Contacts someinfo : list) {
            if (someinfo.getFirstName().contains(newName)) {
                int index = list.indexOf(someinfo);
                System.out.println(someinfo.getFirstName() + someinfo.getLastName() + "  " + someinfo.getAddress() + "  " + someinfo.getCity() + "  " + someinfo.getState() + "  " + someinfo.getZip() + "  " + someinfo.getPhoneNo() + "  " + someinfo.getEmail());
                System.out.println("Enter the First Name:");
                String firstname = in.next();
                System.out.println("Enter the Last Name:");
                String lastname = in.next();
                System.out.println("Enter the Address in words:");
                String address = in.next();
                System.out.println("Enter the City:");
                String city = in.next();
                System.out.println("Enter the State Name:");
                String state = in.next();
                System.out.println("Enter the ZIP code:");
                int zip = in.nextInt();
                System.out.println("Enter the Phone No");
                long phone = in.nextLong();
                System.out.println("Enter email id:");
                String email = in.next();
                list.set(index, new Contacts(firstname, lastname, address, city, state, zip, phone, email));
                Collections.addAll(list);
            }
        }
    }

    public void deleteContact() {
        System.out.println("Enter the first name of the contact to be deleted");
        String newName = in.next();
        list.removeIf(someinfo -> someinfo.getFirstName().contains(newName));
        displayListItems();
    }
}