package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
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

    private void exportToPath(Path path) throws Exception {
        ExportCommand exportCommand = new ExportCommand(path);
        exportCommand.execute(model, commandHistory);
    }

    private void deleteFile(Path path) throws Exception {
        Files.delete(path);
    }

    /**
     * Used to test if the data file can be exported to the specified file path.
     * Performs three actions, exporting, asserting, and deleting the test file.
     * @param path File path where the data file will be written to
     */
    private void testExport(Path path) throws Exception {
        // Export to location
        exportToPath(path);

        // Check if file was exported to location
        assertTrue(Files.exists(path));

        // Clear test file
        deleteFile(path);
    }

    @Test
    public void execute_validPath() throws Exception {
        // Test writing to a temporary directory
        Path validPath = temporaryFolder.resolve("test.json");
        testExport(validPath);

    }

    @Test
    public void execute_invalidPath_throwsException() throws Exception {
        // Root folder with forward slash
        Path invalidPath = Path.of("/");
        ExportCommand exportCommand = new ExportCommand(invalidPath);
        assertCommandFailure(exportCommand, model, ExportCommand.MESSAGE_INVALID_FILE_PATH);

        // Root folder with backward slash
        invalidPath = Path.of("\\");
        exportCommand = new ExportCommand(invalidPath);
        assertCommandFailure(exportCommand, model, ExportCommand.MESSAGE_INVALID_FILE_PATH);

        invalidPath = Path.of("C:");
        exportCommand = new ExportCommand(invalidPath);
        assertCommandFailure(exportCommand, model, ExportCommand.MESSAGE_INVALID_FILE_PATH);

        invalidPath = Path.of("/usr");
        exportCommand = new ExportCommand(invalidPath);
        assertCommandFailure(exportCommand, model, ExportCommand.MESSAGE_INVALID_FILE_PATH);

        // Nested folder (truncated slash at the end)
        invalidPath = Path.of("/a/b/c/d/");
        exportCommand = new ExportCommand(invalidPath);
        assertCommandFailure(exportCommand, model, ExportCommand.MESSAGE_INVALID_FILE_PATH);

        // Missing data type
        invalidPath = Path.of("/cs2103-employee-manager-tmp");
        exportCommand = new ExportCommand(invalidPath);
        assertCommandFailure(exportCommand, model, ExportCommand.MESSAGE_INVALID_FILE_PATH);

        // Missing data type
        // Note: the last '/' will be truncated from the path
        invalidPath = Path.of("/cs2103-employee-manager-tmp/");
        exportCommand = new ExportCommand(invalidPath);
        assertCommandFailure(exportCommand, model, ExportCommand.MESSAGE_INVALID_FILE_PATH);

        // Nested folder
        invalidPath = Path.of("/a/b");
        exportCommand = new ExportCommand(invalidPath);
        assertCommandFailure(exportCommand, model, ExportCommand.MESSAGE_INVALID_FILE_PATH);

        // Nested folder
        invalidPath = Path.of("\\a\\b\\c");
        exportCommand = new ExportCommand(invalidPath);
        assertCommandFailure(exportCommand, model, ExportCommand.MESSAGE_INVALID_FILE_PATH);
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
