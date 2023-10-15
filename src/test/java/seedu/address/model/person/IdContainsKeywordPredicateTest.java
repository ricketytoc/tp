package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IdContainsKeywordPredicateTest {

    @Test
    public void equals() {
        String firstKeyword = "A0001";
        String secondKeyword = "A0002";

        IdContainsKeywordPredicate firstPredicate = new IdContainsKeywordPredicate(firstKeyword);
        IdContainsKeywordPredicate secondPredicate = new IdContainsKeywordPredicate(secondKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IdContainsKeywordPredicate firstPredicateCopy = new IdContainsKeywordPredicate(firstKeyword);
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
        IdContainsKeywordPredicate predicate = new IdContainsKeywordPredicate("A0001");
        assertTrue(predicate.test(new PersonBuilder().withId("A0001").build()));

        // Mixed-case keywords
        predicate = new IdContainsKeywordPredicate("AaB");
        assertTrue(predicate.test(new PersonBuilder().withId("aAB").build()));
    }

    @Test
    public void test_idDoesNotContainKeyword_returnsFalse() {
        // Zero keywords
        IdContainsKeywordPredicate predicate = new IdContainsKeywordPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withId("A0001").build()));

        // Non-matching keyword
        predicate = new IdContainsKeywordPredicate("A0002");
        assertFalse(predicate.test(new PersonBuilder().withId("A0001").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "A0001";
        IdContainsKeywordPredicate predicate = new IdContainsKeywordPredicate(keyword);

        String expected = IdContainsKeywordPredicate.class.getCanonicalName() + "{keyword=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
