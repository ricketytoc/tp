package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class PhoneContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        String firstKeyword = "A0001";
        String secondKeyword = "A0002";

        PhoneContainsKeywordsPredicate firstPredicate = new PhoneContainsKeywordsPredicate(firstKeyword);
        PhoneContainsKeywordsPredicate secondPredicate = new PhoneContainsKeywordsPredicate(secondKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PhoneContainsKeywordsPredicate firstPredicateCopy = new PhoneContainsKeywordsPredicate(firstKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsKeyword_returnsTrue() {
        // match the first 4 numbers
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate("9001");
        assertTrue(predicate.test(new PersonBuilder().withPhone("90018989").build()));

        // match the last 4 numbers
        predicate = new PhoneContainsKeywordsPredicate("8989");
        assertTrue(predicate.test(new PersonBuilder().withPhone("90018989").build()));

        // match the middle 4 numbers
        predicate = new PhoneContainsKeywordsPredicate("0189");
        assertTrue(predicate.test(new PersonBuilder().withPhone("90018989").build()));

        // matches the whole number
        predicate = new PhoneContainsKeywordsPredicate("90018989");
        assertTrue(predicate.test(new PersonBuilder().withPhone("90018989").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeyword_returnsFalse() {
        // Zero keywords
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withPhone("90018989").build()));

        // Non-matching keyword
        predicate = new PhoneContainsKeywordsPredicate("9002");
        assertFalse(predicate.test(new PersonBuilder().withPhone("90018989").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "90018989";
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate(keyword);

        String expected = PhoneContainsKeywordsPredicate.class.getCanonicalName() + "{keyword=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
