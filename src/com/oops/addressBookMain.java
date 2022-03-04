package com.oops;

public class addressBookMain {

    public static void main(String[] args) {
        System.out.println("Welcome to the Address Book Program");
        addressBook contactinfo = new addressBook();
        contactinfo.setUpInfo();
        contactinfo.displayListItems();

    }
}
