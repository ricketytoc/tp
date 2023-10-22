package seedu.address.commons.core.increment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IncrementTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Increment(null));
    }

    @Test
    public void constructor_invalidIncrement_throwsIllegalArgumentException() {
        String invalidIncrement = "";
        assertThrows(IllegalArgumentException.class, () -> new Increment(invalidIncrement));
    }

    @Test
    public void isValidIncrement() {
        // null increment
        assertThrows(NullPointerException.class, () -> Increment.isValidIncrement(null));

        // invalid increment
        assertFalse(Increment.isValidIncrement("")); // empty string
        assertFalse(Increment.isValidIncrement(" ")); // spaces only
        assertFalse(Increment.isValidIncrement("^")); // non-number
        assertFalse(Increment.isValidIncrement("1000.999")); // more than 2 decimal places

        // valid increment
        assertTrue(Increment.isValidIncrement("1000.99")); // non-negative number
        assertTrue(Increment.isValidIncrement("-150.1")); // negative number
    }

    @Test
    public void getValue() {
        double incrementAmount = 100;
        Increment increment = new Increment(Double.toString(incrementAmount));
        assertEquals(incrementAmount, increment.getValue());
    }

    @Test
    public void toStringMethod() {
        String incrementAmount = "100";
        String expected = String.format("%.2f", Double.parseDouble(incrementAmount));
        Increment increment = new Increment(incrementAmount);
        assertEquals(expected, increment.toString());
    }

    @Test
    public void equals() {
        Increment increment = new Increment("100");

        // same values -> returns true
        assertTrue(increment.equals(new Increment("100")));

        // same object -> returns true
        assertTrue(increment.equals(increment));

        // null -> returns false
        assertFalse(increment.equals(null));

        // different types -> returns false
        assertFalse(increment.equals(5.0f));

        // different values -> returns false
        assertFalse(increment.equals(new Increment("10")));
    }
}
