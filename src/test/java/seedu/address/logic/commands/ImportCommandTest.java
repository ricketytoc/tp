package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.storage.JsonAddressBookStorage;

class ImportCommandTest {
    private Model model;
    private CommandHistory commandHistory;

    @TempDir
    public Path temporaryFolder;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        commandHistory = new CommandHistory();
    }

    @Test
    public void execute_validFile() throws Exception {
        Path filePath = temporaryFolder.resolve("test.json");

        // Save a file in the temporary folder first
        AddressBook original = getTypicalAddressBook();
        JsonAddressBookStorage jsonAddressBookStorage = new JsonAddressBookStorage(filePath);
        jsonAddressBookStorage.saveAddressBook(original, filePath);

        // Attempt to import into app
        ImportCommand importCommand = new ImportCommand(filePath);
        importCommand.execute(model, commandHistory);

        assertTrue(model.getAddressBook().equals(original));
    }

    @Test
    public void execute_missingFile_throwsCommandException() throws Exception {
        Path filePath = temporaryFolder.resolve("missing.json");

        // Attempt to import into app
        ImportCommand importCommand = new ImportCommand(filePath);
        assertCommandFailure(importCommand, model, ImportCommand.MESSAGE_FILE_MISSING);
    }

    @Test
    public void execute_corruptedFile_throwsCommandException() throws Exception {
        Path filePath = temporaryFolder.resolve("test.json");

        // Save a file in the temporary folder first
        AddressBook original = getTypicalAddressBook();
        JsonAddressBookStorage jsonAddressBookStorage = new JsonAddressBookStorage(filePath);
        jsonAddressBookStorage.saveAddressBook(original, filePath);

        // Corrupt the file
        String content = new String(Files.readAllBytes(filePath));
        String modifiedContent = content.replace("id", "err");
        Files.write(filePath, modifiedContent.getBytes());

        // Attempt to import into app
        ImportCommand importCommand = new ImportCommand(filePath);
        assertCommandFailure(importCommand, model, ImportCommand.MESSAGE_FILE_ERROR);
    }

    @Test
    public void equals() {
        Path firstFilePath = Path.of("./data/persons.json");
        Path secondFilePath = Path.of("./data/persons2.json");
        ImportCommand importFirstCommand = new ImportCommand(firstFilePath);
        ImportCommand importSecondCommand = new ImportCommand(secondFilePath);

        // same object -> returns true
        assertTrue(importFirstCommand.equals(importFirstCommand));

        // same values -> returns true
        ImportCommand importFirstCommandCopy = new ImportCommand(firstFilePath);
        assertTrue(importFirstCommand.equals(importFirstCommandCopy));

        // different types -> returns false
        assertFalse(importFirstCommand.equals(1));

        // null -> returns false
        assertFalse(importFirstCommand.equals(null));

        // different path -> returns false
        assertFalse(importFirstCommand.equals(importSecondCommand));
    }
}
