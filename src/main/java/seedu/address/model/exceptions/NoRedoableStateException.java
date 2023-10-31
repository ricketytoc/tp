package seedu.address.model.exceptions;

/**
 * Thrown when trying to {@code redo()} but can't.
 */
public class NoRedoableStateException extends RuntimeException {
    public NoRedoableStateException() {
        super("Current state pointer at end of addressBookState list, unable to redo.");
    }
}