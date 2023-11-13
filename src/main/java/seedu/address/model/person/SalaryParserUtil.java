package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Contains utility methods used for parsing strings and longs related to {@code Salary}.
 */
public class SalaryParserUtil {

    /**
     * Returns the salary value converted from a long to a string with 2 decimals.
     */
    public static String convertLongSalaryToString(long value) {
        String valueString = getPaddedString(value);
        return getStringWithDecimalPoint(valueString);
    }

    /**
     * Returns a string that has zeros padded in front such that there are
     * at least {@code Salary.NUM_OF_DECIMAL_PLACES + 1} digits in the resultant string.
     */
    private static String getPaddedString(long value) {
        // ensure that string has at least (Salary.NUM_OF_DECIMAL_PLACES + 1) digits
        // to insert the decimal point properly
        int minLengthOfString = value < 0 ? Salary.NUM_OF_DECIMAL_PLACES + 2 : Salary.NUM_OF_DECIMAL_PLACES + 1;
        String formatter = "%0" + minLengthOfString + "d";
        return String.format(formatter, value);
    }

    /**
     * Returns a string that has a decimal point inserted in the correct position to get
     * {@code Salary.NUM_OF_DECIMAL_PLACES} number of decimals.
     */
    private static String getStringWithDecimalPoint(String valueString) {
        StringBuilder stringBuilder = new StringBuilder(valueString);
        int decimalPlaceIndex = stringBuilder.length() - Salary.NUM_OF_DECIMAL_PLACES;
        stringBuilder.insert(decimalPlaceIndex, ".");
        return stringBuilder.toString();
    }

    /**
     * Returns a long that has a value equivalent to {@code value} with its decimal point shifted right by two digits.
     */
    public static long parseStringToLong(String value) {
        requireNonNull(value);
        int numOfZerosToAdd = getNumOfZerosToAdd(value);
        String salaryWithoutDecimalPoint = value.replace(".", "");
        Long result = addZerosToBack(Long.parseLong(salaryWithoutDecimalPoint), numOfZerosToAdd);
        return result;
    }

    /**
     * Returns number of zeros required to add to the back of {@code value}
     * to get {@code Salary.NUM_OF_DECIMAL_PLACES} of decimals.
     */
    private static int getNumOfZerosToAdd(String value) {
        int indexOfDecimal = value.indexOf(".");

        int numOfDecimals;
        if (indexOfDecimal == -1) {
            numOfDecimals = 0;
        } else {
            numOfDecimals = value.length() - 1 - indexOfDecimal;
        }

        int numOfZerosToAdd = Salary.NUM_OF_DECIMAL_PLACES - numOfDecimals;
        assert(numOfZerosToAdd >= 0);
        return numOfZerosToAdd;
    }

    /**
     * Add {@code count} number of zeros to the back of {@code salary}.
     */
    public static long addZerosToBack(long salary, int count) {
        while (count > 0) {
            salary *= 10;
            count -= 1;
        }
        return salary;
    }

    /**
     * Returns a string that has {@code Salary.NUM_OF_DECIMAL_PLACES} of decimals from {@code value}.
     */
    public static String getStringWithDecimals(String value) {
        int numOfZerosToAdd = getNumOfZerosToAdd(value);

        StringBuilder stringBuilder = new StringBuilder(value);
        if (numOfZerosToAdd == Salary.NUM_OF_DECIMAL_PLACES
                && stringBuilder.charAt(stringBuilder.length() - 1) != '.') {
            // no decimal point
            stringBuilder.append('.');
        }

        while (numOfZerosToAdd > 0) {
            stringBuilder.append('0');
            numOfZerosToAdd -= 1;
        }

        return stringBuilder.toString();
    }
}
