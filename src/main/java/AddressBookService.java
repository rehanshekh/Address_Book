import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddressBookService {
    public List<ContactsData> contactsDataList;
    private PreparedStatement contactCountStatement;

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
        return getEmployeePayrollDataUsingDB(sql);
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
                employeePayrollList.add(new ContactsData(id, firstname, lastname, address, city, state, zip, phone, email, LocalDate.now()));
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

    private ContactsData getContactsData(String namePresent) {
        return this.contactsDataList.stream().filter(employeePayrollDataItem -> employeePayrollDataItem.firstname.equals(namePresent)).findFirst().orElse(null);
    }

    public int updateContactName(String namePresent, String nameChange) {
        int result = updateEmployeeData(namePresent, nameChange);
        if (result == 0) return result;
        ContactsData contactsData = this.getContactsData(namePresent);
        if (contactsData != null) {
            contactsData.firstname = nameChange;
        }
        return result;
    }

    private int updateEmployeeData(String namePresent, String nameChange) {
        String sql = String.format("update contacts set firstname = '%s' where firstname = '%s';", nameChange, namePresent);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<ContactsData> readAddressBookDataForDateRange(String read, LocalDate startDate, LocalDate endDate) {
        if (read.equals("read"))
            return getEmployeePayrollForDateRange(startDate, endDate);
        return null;

    }

    private List<ContactsData> getEmployeePayrollForDateRange(LocalDate startDate, LocalDate endDate) {
        String sql = String.format("select * from contacts where start between '%s' and '%s';", Date.valueOf(startDate)
                , Date.valueOf(endDate));

        return this.getEmployeePayrollDataUsingDB(sql);
    }

    private List<ContactsData> getEmployeePayrollDataUsingDB(String sql) {
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

    public int getCountOfContactsByCityOrState(String city) {
        int count = 0;
        if (this.contactCountStatement == null)
            this.prepareStatementForContactCount();
        try {
            contactCountStatement.setString(1, city);
            ResultSet resultSet = contactCountStatement.executeQuery();
            count = this.getContactCountData(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    private void prepareStatementForContactCount() {

        try {
            Connection connection = this.getConnection();
            String sql = "select count(firstname) as count from contacts where city= ?;";
            contactCountStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getContactCountUsingDB(String sql) {
        int count = 0;
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            count = this.getContactCountData(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    private int getContactCountData(ResultSet result) {
        int count = 0;
        try {
            while (result.next()) {
                count = result.getInt("count");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int addContactToAddressBook(String firstname, String lastname, String address, String city, String state, int zip, int phone, String email) {
        int changes;
        contactsDataList.add(addToAddressBook(firstname, lastname, address, city, state, zip, phone, email));
        if (addToAddressBook(firstname, lastname, address, city, state, zip, phone, email) == null) {
            changes = 0;
        } else changes = 1;

        return changes;
    }

    private ContactsData addToAddressBook(String firstname, String lastname, String address, String city, String state, int zip, int phone, String email) {
        int contactId = -1;
        ContactsData contactsData = null;
        Connection connection = null;
        try {
            connection = this.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            assert connection != null;
            try (Statement statement = connection.createStatement()) {
                String sql = String.format("insert into contacts (firstname,lastname,address,city,state,zip,phone_no,email) values ('%s','%s','%s','%s','%s','%s','%s','%s')", firstname, lastname, address, city, state, zip, phone, email);
                int rowAffected = statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
                if (rowAffected == 1) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) contactId = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        try (Statement statement = connection.createStatement()) {
            String sql = String.format("insert into contact_type values ('%s', '%s');",
                    contactId, 3);
            int rowAffected = statement.executeUpdate(sql);
            if (rowAffected == 1) {
                contactsData = new ContactsData(contactId, firstname, lastname, address, city, state, zip, phone, email, LocalDate.now());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                return contactsData;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return contactsData;
        }
    }
}

