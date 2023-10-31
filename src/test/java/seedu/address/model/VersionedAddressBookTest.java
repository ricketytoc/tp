package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.exceptions.NoRedoableStateException;
import seedu.address.model.exceptions.NoUndoableStateException;
import seedu.address.testutil.AddressBookBuilder;

// Solution below inspired by https://github.com/se-edu/addressbook-level4
public class VersionedAddressBookTest {
    private final ReadOnlyAddressBook emptyAddressBook = new AddressBookBuilder().build();
    private final ReadOnlyAddressBook addressBookWithAmy = new AddressBookBuilder().withPerson(AMY).build();
    private final ReadOnlyAddressBook addressBookWithBob = new AddressBookBuilder().withPerson(BOB).build();

    @Test
    public void commit_noNewStatesAfterCurrentPointer() {
        VersionedAddressBook versionedAddressBook = createVersionedAddressBook(emptyAddressBook, addressBookWithAmy);
        versionedAddressBook.resetData(addressBookWithBob);
        versionedAddressBook.commit();

        List<ReadOnlyAddressBook> expectedPreviousStates = Arrays.asList(emptyAddressBook, addressBookWithAmy);
        List<ReadOnlyAddressBook> expectedNextStates = Collections.emptyList();
        assertAddressBookListStatus(
                versionedAddressBook, expectedPreviousStates, addressBookWithBob, expectedNextStates);
    }

    @Test
    public void commit_someNewStatesAfterCurrentPointer() {
        VersionedAddressBook versionedAddressBook = createVersionedAddressBook(emptyAddressBook, addressBookWithAmy);
        shiftStatePointerLeft(versionedAddressBook, 1);
        versionedAddressBook.resetData(addressBookWithBob);
        versionedAddressBook.commit();

        List<ReadOnlyAddressBook> expectedPreviousStates = Arrays.asList(emptyAddressBook);
        List<ReadOnlyAddressBook> expectedNextStates = Collections.emptyList();
        assertAddressBookListStatus(
                versionedAddressBook, expectedPreviousStates, addressBookWithBob, expectedNextStates);
    }

    @Test
    public void undo_pointerAtStartOfStateList_throwsNoUndoableStateException() {
        VersionedAddressBook versionedAddressBook = createVersionedAddressBook(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftStatePointerLeft(versionedAddressBook, 2);
        assertThrows(NoUndoableStateException.class, versionedAddressBook::undo);
    }

    @Test
    public void undo_pointerAtMiddleOfStateList_success() {
        VersionedAddressBook versionedAddressBook = createVersionedAddressBook(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftStatePointerLeft(versionedAddressBook, 1);

        List<ReadOnlyAddressBook> expectedPreviousStates = Collections.emptyList();
        List<ReadOnlyAddressBook> expectedNextStates = Arrays.asList(addressBookWithAmy, addressBookWithBob);

        versionedAddressBook.undo();
        assertAddressBookListStatus(
                versionedAddressBook, expectedPreviousStates, emptyAddressBook, expectedNextStates);
    }

    @Test
    public void undo_pointerAtEndOfStateList_success() {
        VersionedAddressBook versionedAddressBook = createVersionedAddressBook(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);

        List<ReadOnlyAddressBook> expectedPreviousStates = Arrays.asList(emptyAddressBook);
        List<ReadOnlyAddressBook> expectedNextStates = Arrays.asList(addressBookWithBob);

        versionedAddressBook.undo();
        assertAddressBookListStatus(
                versionedAddressBook, expectedPreviousStates, addressBookWithAmy, expectedNextStates);
    }

    @Test
    public void redo_pointerAtStartOfStateList_success() {
        VersionedAddressBook versionedAddressBook = createVersionedAddressBook(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftStatePointerLeft(versionedAddressBook, 2);

        List<ReadOnlyAddressBook> expectedPreviousStates = Arrays.asList(emptyAddressBook);
        List<ReadOnlyAddressBook> expectedNextStates = Arrays.asList(addressBookWithBob);

        versionedAddressBook.redo();
        assertAddressBookListStatus(
                versionedAddressBook, expectedPreviousStates, addressBookWithAmy, expectedNextStates);
    }

    @Test
    public void redo_pointerAtMiddleOfStateList_success() {
        VersionedAddressBook versionedAddressBook = createVersionedAddressBook(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftStatePointerLeft(versionedAddressBook, 1);

        List<ReadOnlyAddressBook> expectedPreviousStates = Arrays.asList(emptyAddressBook, addressBookWithAmy);
        List<ReadOnlyAddressBook> expectedNextStates = Collections.emptyList();

        versionedAddressBook.redo();
        assertAddressBookListStatus(
                versionedAddressBook, expectedPreviousStates, addressBookWithBob, expectedNextStates);
    }

    @Test
    public void redo_pointerAtEndOfStateList_throwsNoRedoableStateException() {
        VersionedAddressBook versionedAddressBook = createVersionedAddressBook(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        assertThrows(NoRedoableStateException.class, versionedAddressBook::redo);
    }

    @Test
    public void canUndo_pointerAtStartOfStateList_returnsFalse() {
        VersionedAddressBook versionedAddressBook = createVersionedAddressBook(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftStatePointerLeft(versionedAddressBook, 2);
        assertFalse(versionedAddressBook.canUndo());
    }

    @Test
    public void canUndo_pointerAtMiddleOfStateList_returnsTrue() {
        VersionedAddressBook versionedAddressBook = createVersionedAddressBook(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftStatePointerLeft(versionedAddressBook, 1);
        assertTrue(versionedAddressBook.canUndo());
    }

    @Test
    public void canUndo_pointerAtEndOfStateList_returnsTrue() {
        VersionedAddressBook versionedAddressBook = createVersionedAddressBook(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        assertTrue(versionedAddressBook.canUndo());
    }

    @Test
    public void canRedo_pointerAtStartOfStateList_returnsTrue() {
        VersionedAddressBook versionedAddressBook = createVersionedAddressBook(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftStatePointerLeft(versionedAddressBook, 2);
        assertTrue(versionedAddressBook.canRedo());
    }

    @Test
    public void canRedo_pointerAtMiddleOfStateList_returnsTrue() {
        VersionedAddressBook versionedAddressBook = createVersionedAddressBook(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftStatePointerLeft(versionedAddressBook, 1);
        assertTrue(versionedAddressBook.canRedo());
    }

    @Test
    public void canRedo_pointerAtEndOfStateList_returnsFalse() {
        VersionedAddressBook versionedAddressBook = createVersionedAddressBook(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        assertFalse(versionedAddressBook.canRedo());
    }

    @Test
    public void equals() {
        VersionedAddressBook versionedAddressBook = createVersionedAddressBook(emptyAddressBook, addressBookWithAmy);

        // same object -> return true
        assertTrue(versionedAddressBook.equals(versionedAddressBook));

        // different types -> return false
        assertFalse(versionedAddressBook.equals(3));

        // same state lists and state pointers -> return true
        VersionedAddressBook otherVersionedAddressBook =
                createVersionedAddressBook(emptyAddressBook, addressBookWithAmy);
        assertTrue(versionedAddressBook.equals(otherVersionedAddressBook));

        // same state lists and different state pointers -> return false
        shiftStatePointerLeft(otherVersionedAddressBook, 1);
        assertFalse(versionedAddressBook.equals(otherVersionedAddressBook));

        // different state lists and same state pointers -> return false
        otherVersionedAddressBook = createVersionedAddressBook(addressBookWithAmy, addressBookWithBob);
        assertFalse(versionedAddressBook.equals(otherVersionedAddressBook));

        // different state lists and state pointers -> return false
        shiftStatePointerLeft(otherVersionedAddressBook, 1);
        assertFalse(versionedAddressBook.equals(otherVersionedAddressBook));
    }

    /**
     * Returns a {@code VersionedAddressBook} that contains all the {@code addressBooks} and
     * {@code VersionedAddressBook#currentStatePointer} set to the end of the list.
     */
    private VersionedAddressBook createVersionedAddressBook(ReadOnlyAddressBook... addressBooks) {
        assert(addressBooks.length > 0);

        VersionedAddressBook versionedAddressBook = new VersionedAddressBook(addressBooks[0]);
        for (int i = 1; i < addressBooks.length; i++) {
            versionedAddressBook.resetData(addressBooks[i]);
            versionedAddressBook.commit();
        }
        return versionedAddressBook;
    }

    /**
     * Shifts {@code VersionedAddressBook#currentStatePointer} to the left of the given {@code versionedAddressBook}
     * by {@code count}.
     */
    private void shiftStatePointerLeft(VersionedAddressBook versionedAddressBook, int count) {
        for (int i = 0; i < count; i++) {
            versionedAddressBook.undo();
        }
    }

    /**
     * Checks that the current state of {@code versionedAddressBook} is equal to {@code expectedCurrentState},
     * checks that all previous states of {@code versionedAddressBook} is equal to {@code expectedPreviousStates}, and
     * checks that all next states of {@code versionedAddressBook} is equal to {@code expectedNextStates}.
     */
    private void assertAddressBookListStatus(
            VersionedAddressBook versionedAddressBook, List<ReadOnlyAddressBook> expectedPreviousStates,
            ReadOnlyAddressBook expectedCurrentState, List<ReadOnlyAddressBook> expectedNextStates) {
        assertEquals(expectedCurrentState, new AddressBook(versionedAddressBook));

        while (versionedAddressBook.canUndo()) {
            versionedAddressBook.undo();
        }

        for (ReadOnlyAddressBook expectedPreviousState : expectedPreviousStates) {
            assertEquals(expectedPreviousState, new AddressBook(versionedAddressBook));
            versionedAddressBook.redo();
        }

        for (ReadOnlyAddressBook expectedNextState : expectedNextStates) {
            versionedAddressBook.redo();
            assertEquals(expectedNextState, new AddressBook(versionedAddressBook));
        }

        assertFalse(versionedAddressBook.canRedo());

        for (int i = 0; i < expectedNextStates.size(); i++) {
            versionedAddressBook.undo();
        }
    }
}
