import com.opencsv.bean.CsvBindByName;

public class CSVUser {

    @CsvBindByName(column = "firstname", required = true)
    private String firstname;

    @CsvBindByName
    private String lastname;

    @CsvBindByName
    private String address;

    @CsvBindByName(column = "city")
    private String city;

    @CsvBindByName
    private String state;

    @CsvBindByName
    private int zip;

    @CsvBindByName
    private long phone;

    @CsvBindByName
    private String email;

    @Override
    public String toString() {
        return "CSVUser{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }
}
