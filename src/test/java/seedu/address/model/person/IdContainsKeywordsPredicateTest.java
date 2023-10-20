package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IdContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstKeyword = "A0001";
        String secondKeyword = "A0002";

        IdContainsKeywordsPredicate firstPredicate = new IdContainsKeywordsPredicate(firstKeyword);
        IdContainsKeywordsPredicate secondPredicate = new IdContainsKeywordsPredicate(secondKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IdContainsKeywordsPredicate firstPredicateCopy = new IdContainsKeywordsPredicate(firstKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_idContainsKeyword_returnsTrue() {
        IdContainsKeywordsPredicate predicate = new IdContainsKeywordsPredicate("A0001");
        assertTrue(predicate.test(new PersonBuilder().withId("A0001").build()));

        // Mixed-case keywords
        predicate = new IdContainsKeywordsPredicate("AaB");
        assertTrue(predicate.test(new PersonBuilder().withId("aAB").build()));
    }

    @Test
    public void test_idDoesNotContainKeyword_returnsFalse() {
        // Zero keywords
        IdContainsKeywordsPredicate predicate = new IdContainsKeywordsPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withId("A0001").build()));

        // Non-matching keyword
        predicate = new IdContainsKeywordsPredicate("A0002");
        assertFalse(predicate.test(new PersonBuilder().withId("A0001").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "A0001";
        IdContainsKeywordsPredicate predicate = new IdContainsKeywordsPredicate(keyword);

        String expected = IdContainsKeywordsPredicate.class.getCanonicalName() + "{keyword=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
