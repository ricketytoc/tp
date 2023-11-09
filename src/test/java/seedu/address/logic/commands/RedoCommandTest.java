package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.deleteFirstPerson;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class RedoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    // Solution below inspired by https://github.com/se-edu/addressbook-level4
    @BeforeEach
    public void setUp() {
        deleteFirstPerson(model);
        deleteFirstPerson(model);
        model.undoAddressBook();
        model.undoAddressBook();

        deleteFirstPerson(expectedModel);
        deleteFirstPerson(expectedModel);
        expectedModel.undoAddressBook();
        expectedModel.undoAddressBook();
    }

    @Test
    public void execute() {
        // multiple available states to redo
        RedoCommand redoCommand = new RedoCommand();
        expectedModel.redoAddressBook();
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // only one available state to redo
        expectedModel.redoAddressBook();
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // no available states to redo
        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_FAILURE);
    }
}
