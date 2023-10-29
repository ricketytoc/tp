package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.core.increment.Increment;

/**
 * Represents an Employee's Salary in EmployeeManager.
 * Guarantees: immutable; is valid as declared in {@link #isValidSalary(String)}
 */

public class Salary implements Comparable<seedu.address.model.person.Salary> {
    public static final double MAXIMUM_SALARY = 1000000000;
    public static final String MESSAGE_CONSTRAINTS = String.format(
            "Salary should only contain numbers, be non-negative, contain at most 2 decimals, and be at most %.2f",
            MAXIMUM_SALARY);

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^?[0-9]+(\\.[0-9]{1,2})?$";
    public final double value;

    /**
     * Constructs a {@code Salary}.
     *
     * @param salary A valid salary string.
     */
    public Salary(String salary) {
        requireNonNull(salary);
        checkArgument(isValidSalary(salary), MESSAGE_CONSTRAINTS);
        value = Double.parseDouble(salary);
    }

    /**
     * Constructs a {@code Salary}.
     *
     * @param salary A valid salary.
     */
    public Salary(double salary) {
        checkArgument(isValidSalary(salary), MESSAGE_CONSTRAINTS);
        value = salary;
    }

    /**
     * Returns true if a given string is a valid salary.
     */
    public static boolean isValidSalary(String test) {
        return test.matches(VALIDATION_REGEX) && isValidSalary(Double.parseDouble(test));
    }

    /**
     * Returns true if a given double is a valid salary.
     */
    public static boolean isValidSalary(double test) {
        return test <= MAXIMUM_SALARY && test >= 0;
    }

    /**
     * Returns the {@code Salary} incremented by the given {@code increment}.
     */
    public Salary getIncrementedSalary(Increment increment) {
        requireNonNull(increment);
        double updatedSalary = value + increment.getValue();
        return new Salary(updatedSalary);
    }

    /**
     * Returns true if the given {@code increment} is valid for the salary.
     * An {@code increment} is valid if the resulting salary after the increment is valid.
     */
    public boolean isValidIncrement(Increment increment) {
        double resultantSalary = value + increment.getValue();
        return isValidSalary(resultantSalary);
    }

    /**
     * Returns the salary value as a string.
     */
    public String getValueAsString() {
        return String.format("%.2f", value);
    }

    @Override
    public String toString() {
        return String.format("%.2f", value);
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
        return Double.hashCode(value);
    }

    @Override
    public int compareTo(Salary other) {
        return Double.compare(this.value, other.value);
    }
}
