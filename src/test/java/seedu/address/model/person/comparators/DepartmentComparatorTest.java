package seedu.address.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class DepartmentComparatorTest {

    @Test
    void compare() {
        DepartmentComparator comparator = new DepartmentComparator();
        Person firstPerson = new PersonBuilder().withDepartment("Accounting").build();
        Person secondPerson = new PersonBuilder().withDepartment("Marketing").build();
        Person thirdPerson = new PersonBuilder().withDepartment("Marketing").build();

        assertTrue(comparator.compare(firstPerson, secondPerson) < 0);
        assertTrue(comparator.compare(secondPerson, firstPerson) > 0);
        assertTrue(comparator.compare(secondPerson, thirdPerson) == 0);
    }

    @Test
    void equals() {
        DepartmentComparator firstComparator = new DepartmentComparator();
        DepartmentComparator secondComparator = new DepartmentComparator();
        IdComparator otherComparator = new IdComparator();

        assertTrue(firstComparator.equals(firstComparator));
        assertTrue(firstComparator.equals(secondComparator));
        assertFalse(firstComparator.equals(otherComparator));
        assertFalse(firstComparator.equals(null));
        assertFalse(firstComparator.equals(1));
    }
}
