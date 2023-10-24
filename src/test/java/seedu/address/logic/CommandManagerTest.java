package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandManagerTest {

    private CommandHistory commandHistory;

    @BeforeEach
    public void setUp() {
        commandHistory = new CommandHistory();
    }

    @Test
    public void testAddAndGetUserCommandHistory() {
        final String firstCommand = "list";
        final String secondCommand = "clear";

        commandHistory.add(firstCommand);
        commandHistory.add(secondCommand);

        LinkedList<String> expected = new LinkedList<>(Arrays.asList(firstCommand, secondCommand));

        assertEquals(expected, commandHistory.getUserCommandHistory());
    }

    @Test
    public void add_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> commandHistory.add(null));
    }

    @Test
    public void add_emptyString_success() {
        commandHistory.add("");
        assertEquals(new LinkedList<>(Arrays.asList("")), commandHistory.getUserCommandHistory());
    }

    @Test
    public void add_duplicateCommands_success() {
        String command = "list";
        commandHistory.add(command);
        commandHistory.add(command);
        assertEquals(new LinkedList<>(Arrays.asList(command, command)), commandHistory.getUserCommandHistory());
    }

    @Test
    public void equals() {
        final CommandHistory commandHistoryWithA = new CommandHistory();
        commandHistoryWithA.add("a");
        final CommandHistory anotherCommandHistoryWithA = new CommandHistory();
        anotherCommandHistoryWithA.add("a");
        final CommandHistory commandHistoryWithB = new CommandHistory();
        commandHistoryWithB.add("b");

        // same object -> returns true
        assertEquals(commandHistoryWithA, commandHistoryWithA);

        // same values -> returns true
        assertEquals(commandHistoryWithA, anotherCommandHistoryWithA);

        // null -> returns false
        assertNotEquals(null, commandHistoryWithA);

        // different values -> returns false
        assertNotEquals(commandHistoryWithA, commandHistoryWithB);
    }
}
