package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAddressBookStorage;

class ImportCommandTest {
    @TempDir
    public Path temporaryFolder;
    private Model model;
    private CommandHistory commandHistory;

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

        ObservableList<Person> importedPersons = model.getAddressBook().getPersonList();
        ObservableList<Person> originalPersons = original.getPersonList();
        assertEquals(importedPersons, originalPersons);
    }

    @Test
    public void execute_missingFile_throwsCommandException() {
        assertTrue(Files.notExists(temporaryFolder.resolve("missing.json")));
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
    public void execute_invalidPath_throwsException() throws Exception {
        // Root folder with forward slash
        Path invalidPath = Path.of(FileCommandTest.INVALID_DATA_FILE_PATH);
        ImportCommand importCommand = new ImportCommand(invalidPath);
        assertCommandFailure(importCommand, model, ImportCommand.MESSAGE_INVALID_FILE_PATH);
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
