package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

public class SampleDataUtilTest {

    @Test
    public void getSamplePersons_validData_returnsArrayOfPersons() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();
        assertEquals(6, samplePersons.length); // Adjust the number based on your sample data
    }

    @Test
    public void getSampleAddressBook_validData_returnsAddressBook() {
        ReadOnlyAddressBook sampleAddressBook = SampleDataUtil.getSampleAddressBook();
        assertEquals(6, sampleAddressBook.getPersonList().size()); // Adjust the number based on your sample data
    }
}
