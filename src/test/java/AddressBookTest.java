import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AddressBookTest {

    @Test
    public void givenAddressBookInDB_WhenRetrieved_ShouldMatchContactCount(){
        AddressBookService addressBookService = new AddressBookService();
        List<ContactsData> employeePayrollData = addressBookService.readAddressBookData("read");
        Assert.assertEquals(5, employeePayrollData.size());
    }
}
