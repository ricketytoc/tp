package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class DepartmentContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DepartmentContainsKeywordsPredicate firstPredicate =
                new DepartmentContainsKeywordsPredicate(firstPredicateKeywordList);
        DepartmentContainsKeywordsPredicate secondPredicate =
                new DepartmentContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DepartmentContainsKeywordsPredicate firstPredicateCopy =
                new DepartmentContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_departmentContainsKeywords_returnsTrue() {
        // One keyword
        DepartmentContainsKeywordsPredicate predicate =
                new DepartmentContainsKeywordsPredicate(Collections.singletonList("Finance"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Finance Marketing").build()));

        // Multiple keywords
        predicate = new DepartmentContainsKeywordsPredicate(Arrays.asList("Finance", "Marketing"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Finance Marketing").build()));

        // Only one matching keyword
        predicate = new DepartmentContainsKeywordsPredicate(Arrays.asList("Marketing", "CyberSecurity"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Finance CyberSecurity").build()));

        // Mixed-case keywords
        predicate = new DepartmentContainsKeywordsPredicate(Arrays.asList("fInAnCe", "MaRkeTiNg"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Finance Marketing").build()));
    }

    @Test
    public void test_departmentDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DepartmentContainsKeywordsPredicate predicate =
                new DepartmentContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withDepartment("Alice").build()));

        // Non-matching keyword
        predicate = new DepartmentContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withDepartment("Alice Bob").build()));

        // Keywords match ID, phone, email, address, name, and salary, but does not match department
        predicate = new DepartmentContainsKeywordsPredicate(Arrays.asList("A12345", "Alice", "12345", "alice@email.com",
                "Marketing", "Intern", "1000"));
        assertFalse(predicate.test(new PersonBuilder().withId("A12345").withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withDepartment("Finance").withRole("Intern").withSalary("1000").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        DepartmentContainsKeywordsPredicate predicate = new DepartmentContainsKeywordsPredicate(keywords);

        String expected = DepartmentContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
