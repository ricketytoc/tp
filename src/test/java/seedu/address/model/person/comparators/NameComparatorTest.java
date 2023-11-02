package seedu.address.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class NameComparatorTest {

    @Test
    void compare() {
        NameComparator comparator = new NameComparator();
        Person firstPerson = new PersonBuilder().withName("Alice Tan").build();
        Person secondPerson = new PersonBuilder().withName("Bob Lee").build();
        Person thirdPerson = new PersonBuilder().withName("Bob Lee").build();

        assertTrue(comparator.compare(firstPerson, secondPerson) < 0);
        assertTrue(comparator.compare(secondPerson, firstPerson) > 0);
        assertTrue(comparator.compare(secondPerson, thirdPerson) == 0);
    }

    @Test
    void equals() {
        NameComparator firstComparator = new NameComparator();
        NameComparator secondComparator = new NameComparator();
        DepartmentComparator otherComparator = new DepartmentComparator();

        assertTrue(firstComparator.equals(firstComparator));
        assertTrue(firstComparator.equals(secondComparator));
        assertFalse(firstComparator.equals(otherComparator));
        assertFalse(firstComparator.equals(null));
        assertFalse(firstComparator.equals(1));
    }
}
