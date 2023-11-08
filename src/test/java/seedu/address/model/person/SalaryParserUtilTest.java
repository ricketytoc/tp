package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SalaryParserUtilTest {

    @Test
    public void convertLongSalaryToString() {
        // EP: negative value with sufficient digits to from a number with 2 decimals places
        long value = -500000;
        String expected = "-5000.00";
        assertEquals(expected, SalaryParserUtil.convertLongSalaryToString(value));

        value = -500; // boundary value: only three digits
        expected = "-5.00";
        assertEquals(expected, SalaryParserUtil.convertLongSalaryToString(value));

        // EP: negative value with insufficient digits to from a number with 2 decimals places
        value = -5; // only 1 digit
        expected = "-0.05";
        assertEquals(expected, SalaryParserUtil.convertLongSalaryToString(value));

        value = -50; // boundary value: only two digits
        expected = "-0.50";
        assertEquals(expected, SalaryParserUtil.convertLongSalaryToString(value));

        // EP: non-negative value with sufficient digits to from a number with 2 decimals places
        value = 500000;
        expected = "5000.00";
        assertEquals(expected, SalaryParserUtil.convertLongSalaryToString(value));

        value = 500; // boundary value: only three digits
        expected = "5.00";
        assertEquals(expected, SalaryParserUtil.convertLongSalaryToString(value));

        // EP: non-negative value with insufficient digits to from a number with 2 decimals places
        value = 5; // only 1 digit
        expected = "0.05";
        assertEquals(expected, SalaryParserUtil.convertLongSalaryToString(value));

        value = 50; // boundary value: only two digits
        expected = "0.50";
        assertEquals(expected, SalaryParserUtil.convertLongSalaryToString(value));
    }

    @Test
    public void parseStringToLong() {
        // null value
        assertThrows(NullPointerException.class, () -> SalaryParserUtil.parseStringToLong(null));

        // EP: no decimal point
        String value = "5000";
        long expected = 500000;
        assertEquals(expected, SalaryParserUtil.parseStringToLong(value));

        // EP: has decimal point and less than {@code Salary.NUM_OF_DECIMAL_PLACES} number of decimals
        // boundary value: no digits after decimal point
        value = "5000.";
        assertEquals(expected, SalaryParserUtil.parseStringToLong(value));

        // one digit after decimal point
        value = "5000.0";
        assertEquals(expected, SalaryParserUtil.parseStringToLong(value));

        // boundary value: two digits after decimal point
        value = "5000.00";
        assertEquals(expected, SalaryParserUtil.parseStringToLong(value));
    }

    @Test
    public void addZerosToBack() {
        // EP: non-positive count
        long salary = 500000;
        int count = -10;
        long expectedSalary = salary;
        assertEquals(expectedSalary, SalaryParserUtil.addZerosToBack(salary, count));

        // boundary value: 0
        count = 0;
        assertEquals(expectedSalary, SalaryParserUtil.addZerosToBack(salary, count));

        // EP: positive count
        count = 3;
        expectedSalary = 500000000;
        assertEquals(expectedSalary, SalaryParserUtil.addZerosToBack(salary, count));

        // boundary value: 1
        count = 1;
        expectedSalary = 5000000;
        assertEquals(expectedSalary, SalaryParserUtil.addZerosToBack(salary, count));
    }

    @Test
    public void getStringWithDecimals() {
        // no decimal point
        String value = "5000";
        String expected = "5000.00";
        assertEquals(expected, SalaryParserUtil.getStringWithDecimals(value));

        // only decimal point
        value = "5000.";
        assertEquals(expected, SalaryParserUtil.getStringWithDecimals(value));

        // 1 decimal
        value = "5000.0";
        assertEquals(expected, SalaryParserUtil.getStringWithDecimals(value));

        // 2 decimals
        value = "5000.00";
        assertEquals(expected, SalaryParserUtil.getStringWithDecimals(value));
    }
}
