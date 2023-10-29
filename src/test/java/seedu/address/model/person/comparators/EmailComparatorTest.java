package seedu.address.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class EmailComparatorTest {

    @Test
    void compare() {
        EmailComparator comparator = new EmailComparator();
        Person firstPerson = new PersonBuilder().withEmail("alice@mail.com").build();
        Person secondPerson = new PersonBuilder().withEmail("bob@mail.com").build();
        Person thirdPerson = new PersonBuilder().withEmail("bob@mail.com").build();

        assertTrue(comparator.compare(firstPerson, secondPerson) < 0);
        assertTrue(comparator.compare(secondPerson, firstPerson) > 0);
        assertTrue(comparator.compare(secondPerson, thirdPerson) == 0);
    }

    @Test
    void equals() {
        EmailComparator firstComparator = new EmailComparator();
        EmailComparator secondComparator = new EmailComparator();
        IdComparator otherComparator = new IdComparator();

        assertTrue(firstComparator.equals(firstComparator));
        assertTrue(firstComparator.equals(secondComparator));
        assertFalse(firstComparator.equals(otherComparator));
        assertFalse(firstComparator.equals(null));
        assertFalse(firstComparator.equals(1));
    }
}
