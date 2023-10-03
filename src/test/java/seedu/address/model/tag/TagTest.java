package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    private static final String VALID_TAG_NAME = "friend";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void equals() {
        Tag tag = new Tag(VALID_TAG_NAME);

        // same object -> returns true
        assertTrue(tag.equals(tag));

        // null -> returns false
        assertFalse(tag.equals(null));

        // different type -> returns false
        assertFalse(tag.equals(2));

        // same tag names -> returns true
        Tag duplicateTag = new Tag(VALID_TAG_NAME);
        assertTrue(tag.equals(duplicateTag));

        // different tag names -> returns false
        Tag differentTag = new Tag(VALID_TAG_NAME + "s");
        assertFalse(tag.equals(differentTag));
    }
}
