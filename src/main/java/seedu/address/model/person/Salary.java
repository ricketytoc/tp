package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.core.increment.Increment;

/**
 * Represents an Employee's Salary in EmployeeManager.
 * Guarantees: immutable; is valid as declared in {@link #isValidSalary(String)}
 */

public class Salary implements Comparable<seedu.address.model.person.Salary> {
    public static final int MAXIMUM_SALARY = 1000000000;
    public static final int NUM_OF_DECIMAL_PLACES = 2;
    public static final long MAXIMUM_SALARY_LONG =
            SalaryParserUtil.addZerosToBack(MAXIMUM_SALARY, NUM_OF_DECIMAL_PLACES);
    public static final String MESSAGE_CONSTRAINTS = String.format(
            "Salary should only contain numbers, be non-negative, contain at most 2 decimals, and be at most %d.00",
            MAXIMUM_SALARY);

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^?[0-9]+(\\.[0-9]{1,2})?$";
    public final long value;

    /**
     * Constructs a {@code Salary}.
     *
     * @param salary A valid salary string.
     */
    public Salary(String salary) {
        requireNonNull(salary);
        checkArgument(isValidSalary(salary), MESSAGE_CONSTRAINTS);
        value = SalaryParserUtil.parseStringToLong(salary);
    }

    /**
     * Constructs a {@code Salary}.
     *
     * @param salary A valid salary.
     */
    public Salary(long salary) {
        checkArgument(isValidSalary(salary), MESSAGE_CONSTRAINTS);
        value = salary;
    }

    /**
     * Returns true if a given string is a valid salary.
     */
    public static boolean isValidSalary(String test) {
        return test.matches(VALIDATION_REGEX)
                && isValidLength(test)
                && isValidSalary(SalaryParserUtil.parseStringToLong(test));
    }

    private static boolean isValidLength(String test) {
        int maxLengthWithoutDecimals = Integer.toString(MAXIMUM_SALARY).length();
        int lengthWithoutDecimals = test.split("\\.")[0].length();
        return lengthWithoutDecimals <= maxLengthWithoutDecimals;
    }

    /**
     * Returns true if a given {@code test} is a valid salary.
     */
    public static boolean isValidSalary(long test) {
        return test <= MAXIMUM_SALARY_LONG && test >= 0;
    }

    /**
     * Returns the {@code Salary} incremented by the given {@code increment}.
     */
    public Salary getIncrementedSalary(Increment increment) {
        requireNonNull(increment);
        long updatedSalary = value + increment.getLongValue();
        return new Salary(updatedSalary);
    }

    /**
     * Returns true if the given {@code increment} is valid for the salary.
     * An {@code increment} is valid if the resulting salary after the increment is valid.
     */
    public boolean isValidIncrement(Increment increment) {
        long resultantSalary = value + increment.getLongValue();
        return isValidSalary(resultantSalary);
    }

    /**
     * Returns the string representation of the salary value.
     */
    public String getValue() {
        return SalaryParserUtil.convertLongSalaryToString(value);
    }

    @Override
    public String toString() {
        return SalaryParserUtil.convertLongSalaryToString(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Salary)) {
            return false;
        }

        Salary otherSalary = (Salary) other;
        return value == otherSalary.value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public int compareTo(Salary other) {
        return Long.compare(this.value, other.value);
    }
}
