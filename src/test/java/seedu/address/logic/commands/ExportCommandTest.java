package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class ExportCommandTest {

    @TempDir
    public Path temporaryFolder;

    private Model model;
    private CommandHistory commandHistory;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        commandHistory = new CommandHistory();
    }

    @Test
    public void execute_validPath() throws Exception {
        // Export to location
        Path validPath = temporaryFolder.resolve("test.json");
        ExportCommand exportCommand = new ExportCommand(validPath);
        exportCommand.execute(model, commandHistory);

        // Check if file was exported to location
        assertTrue(Files.exists(validPath));

        // Clear test file
        Files.delete(validPath);
    }

    @Test
    public void equals() {
        Path firstFilePath = temporaryFolder.resolve("test1.json");
        Path secondFilePath = temporaryFolder.resolve("test2.json");
        ExportCommand exportFirstCommand = new ExportCommand(firstFilePath);
        ExportCommand exportSecondCommand = new ExportCommand(secondFilePath);

        // same object -> returns true
        assertTrue(exportFirstCommand.equals(exportFirstCommand));

        // same values -> returns true
        ExportCommand exportFirstCommandCopy = new ExportCommand(firstFilePath);
        assertTrue(exportFirstCommand.equals(exportFirstCommandCopy));

        // different types -> returns false
        assertFalse(exportFirstCommand.equals(1));

        // null -> returns false
        assertFalse(exportFirstCommand.equals(null));

        // different path -> returns false
        assertFalse(exportFirstCommand.equals(exportSecondCommand));
    }
}
