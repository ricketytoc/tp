package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class GeneralPredicateTest {
    @Test
    public void equals() {
        Predicate<Person> firstPredicate = new NameContainsKeywordsPredicate(Collections
                .singletonList("Alex"));
        Predicate<Person> secondPredicate = new DepartmentContainsKeywordsPredicate(Collections
                .singletonList("Finance"));
        ArrayList<Predicate<Person>> firstPredicateList = new ArrayList<>();
        ArrayList<Predicate<Person>> secondPredicateList = new ArrayList<>();
        firstPredicateList.add(firstPredicate);
        secondPredicateList.add(firstPredicate);
        secondPredicateList.add(secondPredicate);
        GeneralPredicate firstGeneralPredicate = new GeneralPredicate(firstPredicateList);
        GeneralPredicate secondGeneralPredicate = new GeneralPredicate(secondPredicateList);

        // same object -> returns true
        assertTrue(firstGeneralPredicate.equals(firstGeneralPredicate));

        // same values -> returns true
        GeneralPredicate firstGeneralPredicateCopy =
                new GeneralPredicate(firstPredicateList);
        assertTrue(firstGeneralPredicate.equals(firstGeneralPredicateCopy));

        // different types -> returns false
        assertFalse(firstGeneralPredicate.equals(1));

        // null -> returns false
        assertFalse(firstGeneralPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstGeneralPredicate.equals(secondGeneralPredicate));
    }

    @Test
    public void test_personMatchesPredicates_returnsTrue() {
        // matches one predicate
        ArrayList<Predicate<Person>> predicateList = new ArrayList<>();
        predicateList.add(new IdContainsKeywordsPredicate("A0001"));
        GeneralPredicate namePredicate =
                new GeneralPredicate(predicateList);
        assertTrue(namePredicate.test(new PersonBuilder().withId("A0001").build()));

        // matches all predicates
        predicateList.add(new NameContainsKeywordsPredicate(Collections.singletonList("Alice")));
        predicateList.add(new PhoneContainsKeywordsPredicate("90018989"));
        predicateList.add(new EmailContainsKeywordsPredicate(Collections.singletonList("aliceyeoh@example.com")));
        predicateList.add(new DepartmentContainsKeywordsPredicate(Collections.singletonList("Marketing")));
        predicateList.add(new RoleContainsKeywordsPredicate(Collections.singletonList("Manager")));
        predicateList.add(new SalaryWithinRangePredicate(1000, 5000));
        GeneralPredicate generalPredicate =
                new GeneralPredicate(predicateList);
        assertTrue(generalPredicate.test(new PersonBuilder().withId("A0001").withName("Alice")
                .withPhone("90018989").withEmail("aliceyeoh@example.com").withDepartment("Marketing")
                .withRole("Manager").withSalary("4000").build()));
    }

    @Test
    public void test_personAndPredicateDoNotMatch_returnsFalse() {
        // Zero keywords
        GeneralPredicate predicate = new GeneralPredicate(new ArrayList<>());
        assertFalse(predicate.test(new PersonBuilder().withEmail("aliceyeoh@example.com").build()));

        // Non-matching predicate
        ArrayList<Predicate<Person>> predicateList = new ArrayList<>();
        predicateList.add(new EmailContainsKeywordsPredicate(Collections.singletonList("Bernice")));
        predicate = new GeneralPredicate(predicateList);
        assertFalse(predicate.test(new PersonBuilder().withEmail("aliceyeoh@example.com").build()));

        // Keywords match ID, phone, address, name, department and salary, but does not match email
        predicateList.clear();
        predicateList.add(new IdContainsKeywordsPredicate("A0001"));
        predicateList.add(new NameContainsKeywordsPredicate(Collections.singletonList("Alice")));
        predicateList.add(new PhoneContainsKeywordsPredicate("90018989"));
        predicateList.add(new EmailContainsKeywordsPredicate(Collections.singletonList("aliceyeoh@example.com")));
        predicateList.add(new DepartmentContainsKeywordsPredicate(Collections.singletonList("Marketing")));
        predicateList.add(new RoleContainsKeywordsPredicate(Collections.singletonList("Manager")));
        predicateList.add(new SalaryWithinRangePredicate(1000, 5000));
        predicate = new GeneralPredicate(predicateList);
        assertFalse(predicate.test(new PersonBuilder().withId("A0001").withName("Alice").withPhone("90018989")
                .withEmail("bernice@email.com").withDepartment("Marketing").withRole("Manager").withSalary("4000")
                .build()));
    }

    @Test
    public void toStringMethod() {
        Predicate<Person> firstPredicate = new NameContainsKeywordsPredicate(Collections
                .singletonList("Alex"));
        Predicate<Person> secondPredicate = new DepartmentContainsKeywordsPredicate(Collections
                .singletonList("Finance"));
        ArrayList<Predicate<Person>> predicateList = new ArrayList<>();
        predicateList.add(firstPredicate);
        predicateList.add(secondPredicate);
        GeneralPredicate predicate = new GeneralPredicate(predicateList);

        String expected = GeneralPredicate.class.getCanonicalName() + "{predicateList=" + predicateList + "}";
        assertEquals(expected, predicate.toString());
    }
}
