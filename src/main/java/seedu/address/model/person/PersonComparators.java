package seedu.address.model.person;

import java.util.Comparator;

/**
 * Utility class containing predefined comparators for sorting {@link Person} objects.
 */
public class PersonComparators {
    public static final Comparator<Person> COMPARATOR_ID = Comparator.comparing(Person::getId);
    public static final Comparator<Person> COMPARATOR_NAME = Comparator.comparing(Person::getName);
    public static final Comparator<Person> COMPARATOR_DEPARTMENT = Comparator.comparing(Person::getDepartment);
    public static final Comparator<Person> COMPARATOR_EMAIL = Comparator.comparing(Person::getEmail);
    public static final Comparator<Person> COMPARATOR_PHONE = Comparator.comparing(Person::getPhone);
    public static final Comparator<Person> COMPARATOR_ROLE = Comparator.comparing(Person::getRole);
    public static final Comparator<Person> COMPARATOR_SALARY = Comparator.comparing(Person::getSalary);
}
