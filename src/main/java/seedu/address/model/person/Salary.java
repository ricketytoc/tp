package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.core.increment.Increment;
import seedu.address.commons.util.StringUtil;

/**
 * Represents a Employee's Salary in EmployeeManager.
 * Guarantees: immutable; is valid as declared in {@link #isValidSalary(String)}
 */
public class Salary {
    public static final String MESSAGE_CONSTRAINTS =
            "Salary should only contain numbers, and it should be non-negative";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[0-9]+(\\.[0-9]+)?$";
    public final String value;

    /**
     * Constructs a {@code Salary}.
     *
     * @param salary A valid salary.
     */
    public Salary(String salary) {
        requireNonNull(salary);
        checkArgument(isValidSalary(salary), MESSAGE_CONSTRAINTS);
        value = salary;
    }

    /**
     * Returns true if a given string is a valid salary.
     */
    public static boolean isValidSalary(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the {@code Salary} incremented by the given {@code increment}.
     */
    public Salary getIncrementedSalary(Increment increment) {
        requireNonNull(increment);
        double updatedSalary = Double.parseDouble(value) + increment.getValue();
        String updatedSalaryString = StringUtil.removeTrailingZero(Double.toString(updatedSalary));
        return new Salary(updatedSalaryString);
    }

    /**
     * Returns true if the salary is less than the given {@code increment}.
     */
    public boolean isNegativeAfterIncrement(Increment increment) {
        return Double.parseDouble(value) + increment.getValue() < 0;
    }

    @Override
    public String toString() {
        return value;
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
        return value.equals(otherSalary.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
