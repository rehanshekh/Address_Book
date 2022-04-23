import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class AddressBookTest {

    @Test
    public void givenAddressBookInDB_WhenRetrieved_ShouldMatchContactCount(){
        AddressBookService addressBookService = new AddressBookService();
        List<ContactsData> employeePayrollData = addressBookService.readAddressBookData("read");
        Assert.assertEquals(5, employeePayrollData.size());
    }

    @Test
    public void givenNewContactDetail_WhenUpdated_ShouldSync() {
        AddressBookService addressBookService = new AddressBookService();
        List<ContactsData> addressBookData = addressBookService.readAddressBookData("read");
        int changes = addressBookService.updateContactName("Rehan", "Elon");
        Assert.assertEquals(1, changes);
       // boolean result = addressBookService.checkAddressBookInSyncWithDB("Elon");
        // Assert.assertTrue(result);
    }

    @Test
    public void givenContactData_WhenAskedByDateRange_ShouldReturnProperValue() {
        AddressBookService addressBookService = new AddressBookService();
        addressBookService.readAddressBookData("read");
        LocalDate startDate = LocalDate.of(2018, 01, 01);
        LocalDate endDate = LocalDate.now();
        List<ContactsData> addressBookData = addressBookService.readAddressBookDataForDateRange("read", startDate, endDate);
        Assert.assertEquals(4, addressBookData.size());
    }



}

