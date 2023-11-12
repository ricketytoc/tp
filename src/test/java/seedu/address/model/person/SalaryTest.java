package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.increment.Increment;

public class SalaryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Salary(null));
    }

    @Test
    public void constructor_invalidSalary_throwsIllegalArgumentException() {
        String invalidSalary = "";
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary));
    }

    @Test
    public void isValidSalary_stringSalary() {
        // null salary
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // invalid salary
        assertFalse(Salary.isValidSalary("")); // empty string
        assertFalse(Salary.isValidSalary(" ")); // spaces only
        assertFalse(Salary.isValidSalary("^")); // only numbers
        assertFalse(Salary.isValidSalary("-1000")); // only non-negative numbers
        assertFalse(Salary.isValidSalary("1000.999")); // contains more than 2 decimals
        assertFalse(Salary.isValidSalary(Integer.toString(Salary.MAXIMUM_SALARY + 1))); // too large salary

        // valid salary
        assertTrue(Salary.isValidSalary("0")); // boundary value: 0
        assertTrue(Salary.isValidSalary("1000")); // non-negative numbers only
        assertTrue(Salary.isValidSalary("12.99")); // non-negative numbers with decimal
    }

    @Test
    public void isValidSalary_longSalary() {
        // invalid salary
        // only non-negative numbers
        assertFalse(Salary.isValidSalary(-1000));
        // exceeds maximum salary
        assertFalse(Salary.isValidSalary(Salary.MAXIMUM_SALARY_LONG + Salary.MAXIMUM_SALARY_LONG));
        // boundary value: maximum salary + 1
        assertFalse(Salary.isValidSalary(Salary.MAXIMUM_SALARY_LONG + 1));
        // boundary value: -1
        assertFalse(Salary.isValidSalary(-1));

        // valid salary
        assertTrue(Salary.isValidSalary(1000)); // within range
        assertTrue(Salary.isValidSalary(Salary.MAXIMUM_SALARY_LONG)); // boundary value: maximum salary
        assertTrue(Salary.isValidSalary(0)); // boundary value: 0
    }

    @Test
    public void getIncrementedSalary() {
        Salary salary = new Salary("1000");
        Increment increment = new Increment("1000");

        // null increment
        assertThrows(NullPointerException.class, () -> salary.getIncrementedSalary(null));

        // valid increment
        assertEquals(new Salary("2000"), salary.getIncrementedSalary(increment));
    }

    @Test
    public void isValidIncrement() {
        Salary salary = new Salary("1000");

        // negative increment for sufficient salary -> returns true
        assertTrue(salary.isValidIncrement(new Increment("-500")));

        // negative increment for sufficient salary - boundary value: resultant salary is zero -> returns true
        assertTrue(salary.isValidIncrement(new Increment("-1000")));

        // negative increment for insufficient salary -> returns false
        assertFalse(salary.isValidIncrement(new Increment("-2000")));

        // negative increment for insufficient salary - boundary value: resultant salary is -0.01 -> returns false
        assertFalse(salary.isValidIncrement(new Increment("-1000.01")));

        // positive increment that does not cause salary to exceed maximum salary -> returns true
        assertTrue(salary.isValidIncrement(new Increment("1000")));

        // too big positive increment -> returns false
        assertFalse(salary.isValidIncrement(new Increment(Integer.toString(Salary.MAXIMUM_SALARY))));

        // positive increment - boundary value: causes salary to reach maximum salary -> returns true
        Salary zeroSalary = new Salary("0");
        assertTrue(zeroSalary.isValidIncrement(new Increment(Integer.toString(Salary.MAXIMUM_SALARY))));

        // too big positive increment - boundary value: causes salary to exceed maximum salary by 0.01 -> returns false
        assertFalse(zeroSalary.isValidIncrement(new Increment(Salary.MAXIMUM_SALARY + "0.01")));
    }

    @Test
    public void equals() {
        Salary salary = new Salary("1000");

        // same values -> returns true
        assertTrue(salary.equals(new Salary("1000")));

        // same object -> returns true
        assertTrue(salary.equals(salary));

        // null -> returns false
        assertFalse(salary.equals(null));

        // different types -> returns false
        assertFalse(salary.equals(5.0f));

        // different values -> returns false
        assertFalse(salary.equals(new Salary("9999")));
    }
}

