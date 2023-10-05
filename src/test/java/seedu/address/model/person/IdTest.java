package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class IdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Id(null));
    }

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String invalidId = "";
        assertThrows(IllegalArgumentException.class, () -> new Id(invalidId));
    }

    @Test
    public void isValidId() {
        // null ID
        assertThrows(NullPointerException.class, () -> Id.isValidId(null));

        // invalid IDs
        assertFalse(Id.isValidId("")); // empty string
        assertFalse(Id.isValidId(" ")); // spaces only
        assertFalse(Id.isValidId("  ")); // multiple spaces
        assertFalse(Id.isValidId("^")); // only non-alphanumeric characters
        assertFalse(Id.isValidId("#012345")); // contains non-alphanumeric characters
        assertFalse(Id.isValidId("A012 345B")); // contains spacing
        assertFalse(Id.isValidId(" 345B")); // contains spacing at the start
        assertFalse(Id.isValidId("345B ")); // contains spacing at the end

        // valid IDs
        assertTrue(Id.isValidId("A")); // single character
        assertTrue(Id.isValidId("EMP")); // alphabets only
        assertTrue(Id.isValidId("12345")); // numbers only
        assertTrue(Id.isValidId("A1234567B")); // alphanumeric characters
    }

    @Test
    public void equals() {
        Id id = new Id("A12345");

        // same values -> returns true
        assertTrue(id.equals(new Id("A12345")));

        // same object -> returns true
        assertTrue(id.equals(id));

        // null -> returns false
        assertFalse(id.equals(null));

        // different types -> returns false
        assertFalse(id.equals(5.0f));

        // different values -> returns false
        assertFalse(id.equals(new Role("A38572")));
    }
}
