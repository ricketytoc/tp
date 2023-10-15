package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.Salary;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Id("A0001"), new Name("Alex Yeoh"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Department("Finance"), new Role("Supervisor"),
                new Salary("5000"), getTagSet("friends")),

            new Person(new Id("A0002"), new Name("Bernice Yu"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Department("Marketing"), new Role("Manager"),
                new Salary("4000"), getTagSet("colleagues", "friends")),

            new Person(new Id("A0003"), new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Department("Sales"), new Role("Supervisor"),
                new Salary("6000"), getTagSet("neighbours")),

            new Person(new Id("A0004"), new Name("David Li"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Department("Management"), new Role("Senior Executive"),
                new Salary("10000"), getTagSet("family")),

            new Person(new Id("A0005"), new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Email("irfan@example.com"), new Department("Accounting"), new Role("Executive"),
                new Salary("9000"), getTagSet("classmates")),

            new Person(new Id("A0006"), new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Email("royb@example.com"), new Department("Human Resources"), new Role("Team Lead"),
                new Salary("4000"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
