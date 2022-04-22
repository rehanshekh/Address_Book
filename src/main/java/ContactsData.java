public class ContactsData {
    int id;
    String firstname;
    String lastname;
    String address;
    String city;
    String state;
    int zip;
    long phone;
    String email;

    public ContactsData(int id, String firstname, String lastname, String address, String city, String state, int zip, long phone, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactsData that = (ContactsData) o;
        return id == that.id &&
                (that.firstname.compareTo(firstname)) == 0 &&
                firstname.equals(that.firstname);
    }
}
