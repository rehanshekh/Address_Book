import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookService {
    public List<ContactsData> contactsDataList;

    public AddressBookService() {
    }

    public List<ContactsData> readAddressBookData(String read) {
        if (read.equals("read")) {
            this.contactsDataList = readData();
        }
        return this.contactsDataList;
    }

    private List<ContactsData> readData() {
        String sql = "select * from contacts; ";
        List<ContactsData> contactDataList = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            contactDataList = this.getContactData(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactDataList;
    }

    private List<ContactsData> getContactData(ResultSet result) {
        List<ContactsData> employeePayrollList = new ArrayList<>();
        try {
            while (result.next()) {
                int id = result.getInt("id");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String address = result.getString("address");
                String city = result.getString("city");
                String state = result.getString("state");
                int zip = result.getInt("zip");
                long phone = result.getLong("phone_no");
                String email = result.getString("email");
                employeePayrollList.add(new ContactsData(id, firstname, lastname, address, city, state, zip, phone, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }

    private Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/Address_Book_Service?useSSL=false";
        String userName = "root";
        String password = "virgin@44";
        Connection connection;
        System.out.println("Connecting to Database:" + jdbcUrl);
        connection = DriverManager.getConnection(jdbcUrl, userName, password);
        System.out.println("Connection is Successful!! " + connection);
        return connection;

    }
}
