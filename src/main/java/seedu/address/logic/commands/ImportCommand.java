package seedu.address.logic.commands;

import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Imports a data file from the specified path.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_SUCCESS = "Data successfully imported";
    public static final String MESSAGE_FILE_ERROR = "An error occurred during loading of data. The file "
            + "might be corrupted.";
    public static final String MESSAGE_FILE_MISSING = "The specified file does not exist";
    public static final String MESSAGE_INVALID_FILE_TYPE = "File should be of .json type. E.g. data.json";
    public static final String MESSAGE_MISSING_FILE_NAME = "The specified file path does not contain a file name";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports the data file from the specified location "
            + "into the application. "
            + "\n"
            + "Parameters: "
            + "FILE_PATH"
            + "\n"
            + "Example: " + COMMAND_WORD + " " + "./mydata/persons.json";
    public static final String MESSAGE_SUMMARY = "Import: " + COMMAND_WORD + " FILE_PATH" + "\n"
            + "Example: " + COMMAND_WORD + " " + "./mydata/persons.json" + "\n";

    private final Path filePath;

    public ImportCommand(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        Path fileNamePath = filePath.getFileName();
        // Check for missing file name
        if (fileNamePath == null) {
            throw new CommandException(MESSAGE_MISSING_FILE_NAME);
        }

        // Check for incorrect file extension
        if (!fileNamePath.toString().endsWith(".json")) {
            throw new CommandException(MESSAGE_INVALID_FILE_TYPE);
        }

        // Attempt to read address book from disk
        try {
            Optional<ReadOnlyAddressBook> addressBook = new JsonAddressBookStorage(filePath).readAddressBook(filePath);
            if (addressBook.isPresent()) {
                model.setAddressBook(addressBook.get());
                model.commitAddressBook();
                return new CommandResult(MESSAGE_SUCCESS);
            } else {
                throw new CommandException(MESSAGE_FILE_MISSING);
            }
        } catch (DataLoadingException ex) {
            throw new CommandException(MESSAGE_FILE_ERROR);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ImportCommand)) {
            return false;
        }

        ImportCommand otherImportCommand = (ImportCommand) other;
        return filePath.equals(otherImportCommand.filePath);
    }
}
