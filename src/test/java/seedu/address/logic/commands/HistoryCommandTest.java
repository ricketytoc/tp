package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandHistory;

/**
 * Contains both unit tests for {@code HistoryCommand}
 * and integration testing for {@code HistoryCommand} with {@code CommandHistory}.
 */
public class HistoryCommandTest {

    private static final String VALID_ADD_COMMAND =
            "add i/A00001 n/John Doe p/98765432 e/johnd@example.com d/Finance r/Manager s/5000";
    private CommandHistory commandHistory;

    @BeforeEach
    public void setUp() {
        commandHistory = new CommandHistory();
    }

    @Test
    public void execute_validNumberOfUserCommands() {
        String expectedMessage1 = "Displayed up to the last 2 valid user commands. "
                + "If fewer than 2 commands are available, all are shown." + "\n"
                + "clear" + "\n"
                + "list";
        commandHistory.add("list");
        commandHistory.add("clear");
        HistoryCommand historyCommand = new HistoryCommand(2);
        assertCommandSuccess(historyCommand, commandHistory, expectedMessage1);
        String expectedMessage2 = "Displayed up to the last 2 valid user commands. "
                + "If fewer than 2 commands are available, all are shown." + "\n"
                + VALID_ADD_COMMAND + "\n"
                + "clear";
        commandHistory.add(VALID_ADD_COMMAND);
        assertCommandSuccess(historyCommand, commandHistory, expectedMessage2);
    }

    @Test
    public void execute_moreCommandsThanHistory() {
        String expectedMessage = "Displayed up to the last 5 valid user commands. "
                + "If fewer than 5 commands are available, all are shown."
                + "\nlist"
                + "\n" + VALID_ADD_COMMAND;
        commandHistory.add(VALID_ADD_COMMAND);
        commandHistory.add("list");
        HistoryCommand historyCommand = new HistoryCommand(5);
        assertCommandSuccess(historyCommand, commandHistory, expectedMessage);
    }

    @Test
    public void equals() {
        HistoryCommand historyCommand1 = new HistoryCommand(2);
        HistoryCommand historyCommand2 = new HistoryCommand(2);
        HistoryCommand historyCommand3 = new HistoryCommand(3);

        // same object -> returns true
        assertEquals(historyCommand1, historyCommand1);

        // same values -> returns true
        assertEquals(historyCommand1, historyCommand2);

        // null -> returns false
        assertNotEquals(null, historyCommand1);

        // diff types -> returns false
        assertFalse(historyCommand1.equals("String"));

        // different values -> returns false
        assertNotEquals(historyCommand1, historyCommand3);
    }

    @Test
    public void testToString() {
        HistoryCommand historyCommand = new HistoryCommand(3);
        String expectedString = HistoryCommand.class.getCanonicalName() + "{numberOfUserCommands=3}";
        assertEquals(expectedString, historyCommand.toString());
    }
}
