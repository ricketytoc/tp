package seedu.address.testutil;

import seedu.address.commons.core.increment.Increment;

/**
 * A utility class containing a list of {@code Increment} objects to be used in tests.
 */
public class TypicalIncrements {
    public static final String INCREMENT_STRING_POS = "1000";
    public static final Increment INCREMENT_OBJ_POS = new Increment(INCREMENT_STRING_POS);
    public static final String INCREMENT_STRING_NEG = "-2000";
    public static final Increment INCREMENT_OBJ_NEG = new Increment(INCREMENT_STRING_NEG);
}
