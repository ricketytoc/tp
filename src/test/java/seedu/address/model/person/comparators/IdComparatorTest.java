package seedu.address.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class IdComparatorTest {

    @Test
    void compare() {
        IdComparator comparator = new IdComparator();
        Person firstPerson = new PersonBuilder().withId("A001").build();
        Person secondPerson = new PersonBuilder().withId("B001").build();
        Person thirdPerson = new PersonBuilder().withId("B001").build();

        assertTrue(comparator.compare(firstPerson, secondPerson) < 0);
        assertTrue(comparator.compare(secondPerson, firstPerson) > 0);
        assertTrue(comparator.compare(secondPerson, thirdPerson) == 0);
    }

    @Test
    void equals() {
        IdComparator firstComparator = new IdComparator();
        IdComparator secondComparator = new IdComparator();
        DepartmentComparator otherComparator = new DepartmentComparator();

        assertTrue(firstComparator.equals(firstComparator));
        assertTrue(firstComparator.equals(secondComparator));
        assertFalse(firstComparator.equals(otherComparator));
        assertFalse(firstComparator.equals(null));
        assertFalse(firstComparator.equals(1));
    }
}
