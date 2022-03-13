package com.oops;

public interface IAddressBook {
    void displayListItems();

    String getcompanyName(String company);
}

class Contacts implements Comparable<Contacts> {


    public final String firstName;
    public final String lastName;
    public final String address;
    public final String city;
    public final String state;
    public final int zip;
    public final long phoneNo;
    public final String email;
    public String companyName;


    public Contacts(String myFirstName, String myLastName, String myAddress, String myCity, String myState, int myZip, long myPhoneNo, String myEmail) {
        firstName = myFirstName;
        lastName = myLastName;
        address = myAddress;
        city = myCity;
        state = myState;
        zip = myZip;
        phoneNo = myPhoneNo;
        email = myEmail;
        companyName = null;
    }

    @Override
    public String toString() {
        return
                '\n' + "firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", address='" + address + '\'' +
                        ", city='" + city + '\'' +
                        ", state='" + state + '\'' +
                        ", zip=" + zip +
                        ", phoneNo=" + phoneNo +
                        ", email='" + email + '\''
                ;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setCompanyName(String companyname) {
        companyName = companyname;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZip() {
        return zip;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Company for " + firstName + " is: " + companyName;
    }

    @Override
    public int compareTo(Contacts obj) {
        return this.firstName.compareTo(obj.firstName);
    }
}