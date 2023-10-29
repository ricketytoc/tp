package seedu.address.model.person.comparators;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * Comparator for sorting {@link Person} objects based on their phone number.
 */
public class PhoneComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getPhone().compareTo(p2.getPhone());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof PhoneComparator;
    }
}
