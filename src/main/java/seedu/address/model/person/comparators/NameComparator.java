package seedu.address.model.person.comparators;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * Comparator for sorting {@link Person} objects based on their name.
 */
public class NameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof NameComparator;
    }
}
