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
    public static final long MAXIMUM_SALARY_LONG = addZerosToBack(MAXIMUM_SALARY, NUM_OF_DECIMAL_PLACES);
    public static final String MESSAGE_CONSTRAINTS = String.format(
            "Salary should only contain numbers, be non-negative, contain at most 2 decimals, and be at most %d",
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
        value = convertStringToLong(salary);
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
        return test.matches(VALIDATION_REGEX) && isValidSalary(convertStringToLong(test));
    }

    /**
     * Returns true if a given double is a valid salary.
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
     * Returns the salary value converted from a long to a string with 2 decimals.
     */
    public static String convertLongSalaryToString(long value) {
        StringBuilder stringBuilder = new StringBuilder(Long.toString(value));

        // ensure that string has at least 3 digits to insert the decimal point properly
        if (stringBuilder.charAt(0) == '-') {
            addZerosToFront(stringBuilder, 1);
        } else {
            addZerosToFront(stringBuilder, 0);
        }

        int decimalPlace = stringBuilder.length() - NUM_OF_DECIMAL_PLACES;
        stringBuilder.insert(decimalPlace, ".");
        return stringBuilder.toString();
    }

    /**
     * Adds zeros to the front of the {@code stringBuilder} from index {@code start} until the string has at least
     * ({@code NUM_OF_DECIMAL_PLACES} + 1) digits.
     */
    private static void addZerosToFront(StringBuilder stringBuilder, int start) {
        int minimumNumOfDigits = NUM_OF_DECIMAL_PLACES + 1;
        int currNumOfDigits = stringBuilder.length() - start;
        int numOfZerosRequired = minimumNumOfDigits - currNumOfDigits;
        while (numOfZerosRequired > 0) {
            stringBuilder.insert(start, "0");
            numOfZerosRequired -= 1;
        }
    }

    /**
     * Returns a long that is parsed from {@code salary} by ensuring that there are 2 decimals
     * and removing the decimal point.
     */
    public static long convertStringToLong(String salary) {
        requireNonNull(salary);

        int indexOfDecimal = salary.indexOf(".");
        int numOfDecimals;
        if (indexOfDecimal == -1) {
            numOfDecimals = 0;
        } else {
            numOfDecimals = salary.length() - 1 - indexOfDecimal;
        }
        int numOfZerosToAdd = NUM_OF_DECIMAL_PLACES - numOfDecimals;

        String salaryWithoutDecimalPoint = salary.replace(".", "");
        Long result = addZerosToBack(Long.parseLong(salaryWithoutDecimalPoint), numOfZerosToAdd);
        return result;
    }

    /**
     * Add {@code count} number of zeros to the back of {@code salary}.
     */
    private static long addZerosToBack(long salary, int count) {
        assert(count >= 0);
        while (count > 0) {
            salary *= 10;
            count -= 1;
        }
        return salary;
    }

    /**
     * Returns the string representation of the salary value.
     */
    public String getValue() {
        return convertLongSalaryToString(value);
    }

    @Override
    public String toString() {
        return convertLongSalaryToString(value);
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
