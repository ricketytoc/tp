package seedu.address.commons.core.increment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.person.Salary;
import seedu.address.model.person.SalaryParserUtil;

/**
 * Represents an increment.
 */
public class Increment {
    public static final String MESSAGE_CONSTRAINTS = "Increment should only contain numbers and an optional "
            + "negative sign in front of the numbers and have at most 2 decimals";
    public static final String VALIDATION_REGEX = "^-?[0-9]+(\\.[0-9]{1,2})?$";

    private long increment;
    private String incrementString;

    /**
     * Constructs a {@code Increment}
     *
     * @param increment A valid increment.
     */
    public Increment(String increment) {
        requireNonNull(increment);
        checkArgument(isValidIncrement(increment), MESSAGE_CONSTRAINTS);

        // due to feature freeze, bug involving too long values are handled here
        // should throw an error in isValidIncrement instead
        try {
            this.increment = SalaryParserUtil.parseStringToLong(increment);
        } catch (NumberFormatException e) {
            this.increment = Salary.MAXIMUM_SALARY_LONG + 1;
        }
        incrementString = SalaryParserUtil.getStringWithDecimals(increment);
    }

    /**
     * Returns true if a given string is a valid increment.
     */
    public static boolean isValidIncrement(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the long value of the increment.
     */
    public long getLongValue() {
        return increment;
    }

    @Override
    public String toString() {
        return incrementString;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Increment)) {
            return false;
        }

        Increment otherIncrement = (Increment) other;
        return increment == otherIncrement.increment
                && incrementString.equals(otherIncrement.incrementString);
    }
}
