package seedu.address.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class SalaryComparatorTest {

    @Test
    void compare() {
        SalaryComparator comparator = new SalaryComparator();
        Person firstPerson = new PersonBuilder().withSalary("500.00").build();
        Person secondPerson = new PersonBuilder().withSalary("600.50").build();
        Person thirdPerson = new PersonBuilder().withSalary("600.50").build();

        assertTrue(comparator.compare(firstPerson, secondPerson) < 0);
        assertTrue(comparator.compare(secondPerson, firstPerson) > 0);
        assertTrue(comparator.compare(secondPerson, thirdPerson) == 0);
    }

    @Test
    void equals() {
        SalaryComparator firstComparator = new SalaryComparator();
        SalaryComparator secondComparator = new SalaryComparator();
        DepartmentComparator otherComparator = new DepartmentComparator();

        assertTrue(firstComparator.equals(firstComparator));
        assertTrue(firstComparator.equals(secondComparator));
        assertFalse(firstComparator.equals(otherComparator));
        assertFalse(firstComparator.equals(null));
        assertFalse(firstComparator.equals(1));
    }
}
