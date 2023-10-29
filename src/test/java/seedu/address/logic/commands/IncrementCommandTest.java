package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIncrements.INCREMENT_OBJ_NEG;
import static seedu.address.testutil.TypicalIncrements.INCREMENT_OBJ_POS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookWithIncrementedSalary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Salary;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code IncrementCommand}.
 */
public class IncrementCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_invalidIncrement_throwsCommandException() {
        IncrementCommand incrementCommand = new IncrementCommand(INCREMENT_OBJ_NEG);
        assertCommandFailure(incrementCommand, model,
                String.format(IncrementCommand.MESSAGE_INVALID_INCREMENT, Messages.format(ALICE),
                        Salary.MAXIMUM_SALARY));
    }

    @Test
    public void execute_validIncrementUnfilteredList_success() {
        IncrementCommand incrementCommand = new IncrementCommand(INCREMENT_OBJ_POS);
        String expectedMessage = String.format(
                IncrementCommand.MESSAGE_INCREMENT_SUCCESS, model.getSortedFilteredPersonList().size(),
                INCREMENT_OBJ_POS);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(getTypicalAddressBookWithIncrementedSalary(INCREMENT_OBJ_POS));
        expectedModel.commitAddressBook();

        assertCommandSuccess(incrementCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIncrementFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        IncrementCommand incrementCommand = new IncrementCommand(INCREMENT_OBJ_POS);

        String expectedMessage = String.format(
                IncrementCommand.MESSAGE_INCREMENT_SUCCESS, model.getSortedFilteredPersonList().size(),
                INCREMENT_OBJ_POS);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person person = model.getSortedFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(person)
                .withSalary(person.getSalary().getIncrementedSalary(INCREMENT_OBJ_POS).getValueAsString()).build();
        expectedModel.setPerson(person, editedPerson);
        expectedModel.commitAddressBook();
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(incrementCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        IncrementCommand incrementCommand = new IncrementCommand(INCREMENT_OBJ_POS);

        // same object -> returns true
        assertTrue(incrementCommand.equals(incrementCommand));

        // same values -> returns true
        IncrementCommand incrementCommandCopy = new IncrementCommand(INCREMENT_OBJ_POS);
        assertTrue(incrementCommand.equals(incrementCommandCopy));

        // different types -> returns false
        assertFalse(incrementCommand.equals(1));

        // null -> returns false
        assertFalse(incrementCommand.equals(null));

        // different values -> returns false
        assertFalse(incrementCommand.equals(new IncrementCommand(INCREMENT_OBJ_NEG)));
    }

    @Test
    public void toStringMethod() {
        IncrementCommand incrementCommand = new IncrementCommand(INCREMENT_OBJ_POS);
        String expected = IncrementCommand.class.getCanonicalName() + "{incrementAmount=" + INCREMENT_OBJ_POS + "}";
        assertEquals(expected, incrementCommand.toString());
    }
}
