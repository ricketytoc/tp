package seedu.address.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class PhoneComparatorTest {

    @Test
    void compare() {
        PhoneComparator comparator = new PhoneComparator();
        Person firstPerson = new PersonBuilder().withPhone("12345678").build();
        Person secondPerson = new PersonBuilder().withPhone("22345678").build();
        Person thirdPerson = new PersonBuilder().withPhone("22345678").build();

        assertTrue(comparator.compare(firstPerson, secondPerson) < 0);
        assertTrue(comparator.compare(secondPerson, firstPerson) > 0);
        assertTrue(comparator.compare(secondPerson, thirdPerson) == 0);
    }

    @Test
    void equals() {
        PhoneComparator firstComparator = new PhoneComparator();
        PhoneComparator secondComparator = new PhoneComparator();
        DepartmentComparator otherComparator = new DepartmentComparator();

        assertTrue(firstComparator.equals(firstComparator));
        assertTrue(firstComparator.equals(secondComparator));
        assertFalse(firstComparator.equals(otherComparator));
        assertFalse(firstComparator.equals(null));
        assertFalse(firstComparator.equals(1));
    }
}
