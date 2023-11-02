package seedu.address.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class RoleComparatorTest {

    @Test
    void compare() {
        RoleComparator comparator = new RoleComparator();
        Person firstPerson = new PersonBuilder().withRole("Accountant").build();
        Person secondPerson = new PersonBuilder().withRole("CEO").build();
        Person thirdPerson = new PersonBuilder().withRole("CEO").build();

        assertTrue(comparator.compare(firstPerson, secondPerson) < 0);
        assertTrue(comparator.compare(secondPerson, firstPerson) > 0);
        assertTrue(comparator.compare(secondPerson, thirdPerson) == 0);
    }

    @Test
    void equals() {
        RoleComparator firstComparator = new RoleComparator();
        RoleComparator secondComparator = new RoleComparator();
        DepartmentComparator otherComparator = new DepartmentComparator();

        assertTrue(firstComparator.equals(firstComparator));
        assertTrue(firstComparator.equals(secondComparator));
        assertFalse(firstComparator.equals(otherComparator));
        assertFalse(firstComparator.equals(null));
        assertFalse(firstComparator.equals(1));
    }
}
