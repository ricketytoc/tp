package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class SalaryWithinRangePredicateTest {
    @Test
    public void equals() {
        int firstRangeLowerBound = 1000;
        int firstRangeUpperBound = 5000;
        int secondRangeLowerBound = 2000;
        int secondRangeUpperBound = 6000;

        SalaryWithinRangePredicate firstPredicate =
                new SalaryWithinRangePredicate(firstRangeLowerBound, firstRangeUpperBound);
        SalaryWithinRangePredicate secondPredicate =
                new SalaryWithinRangePredicate(secondRangeLowerBound, secondRangeUpperBound);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SalaryWithinRangePredicate firstPredicateCopy =
                new SalaryWithinRangePredicate(firstRangeLowerBound, firstRangeUpperBound);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_salaryWithinRange_returnsTrue() {
        // Salary between the range
        SalaryWithinRangePredicate predicate = new SalaryWithinRangePredicate(1000, 5000);
        assertTrue(predicate.test(new PersonBuilder().withSalary("4000").build()));

        // Salary at the lower end of the range
        predicate = new SalaryWithinRangePredicate(1000, 5000);
        assertTrue(predicate.test(new PersonBuilder().withSalary("1000").build()));

        // Salary at the upper end of the range
        predicate = new SalaryWithinRangePredicate(1000, 5000);
        assertTrue(predicate.test(new PersonBuilder().withSalary("5000").build()));
    }

    @Test
    public void test_salaryNotWithinRange_returnsFalse() {
        // salary is outside of range
        SalaryWithinRangePredicate predicate = new SalaryWithinRangePredicate(1000, 5000);
        assertFalse(predicate.test(new PersonBuilder().withSalary("7000").build()));
    }

    @Test
    public void toStringMethod() {
        double keywordLowerBound = 1000.0;
        double keywordUpperBound = 5000.0;
        SalaryWithinRangePredicate predicate = new SalaryWithinRangePredicate(keywordLowerBound, keywordUpperBound);

        String expected = SalaryWithinRangePredicate.class.getCanonicalName() + "{lowerBound=" + keywordLowerBound
                + ", upperBound=" + keywordUpperBound + "}";
        assertEquals(expected, predicate.toString());
    }
}
