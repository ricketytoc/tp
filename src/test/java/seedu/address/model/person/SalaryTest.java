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

        // valid salary
        assertTrue(Salary.isValidSalary("1000")); // non-negative numbers only
        assertTrue(Salary.isValidSalary("12")); // numbers only
    }

    @Test
    public void isValidSalary_doubleSalary() {
        // invalid salary
        assertFalse(Salary.isValidSalary(-1000)); // only non-negative numbers
        assertFalse(Salary.isValidSalary(Salary.MAXIMUM_SALARY + Salary.MAXIMUM_SALARY)); // exceeds maximum salary

        // valid salary
        assertTrue(Salary.isValidSalary(1000)); // within range
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

        // positive increment -> returns true
        assertTrue(salary.isValidIncrement(new Increment("1000")));

        // negative increment for sufficient salary -> returns true
        assertTrue(salary.isValidIncrement(new Increment("-500")));

        // negative increment for insufficient salary -> returns false
        assertFalse(salary.isValidIncrement(new Increment("-2000")));

        // too big positive increment -> returns false
        assertFalse(salary.isValidIncrement(new Increment(String.format("%.2f", Salary.MAXIMUM_SALARY))));
    }

    @Test
    public void getValueAsString() {
        double value = 1000;
        Salary salary = new Salary(value);
        String expectedString = String.format("%.2f", value);
        assertEquals(expectedString, salary.getValueAsString());
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

