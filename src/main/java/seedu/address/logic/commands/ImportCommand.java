package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

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
public class ImportCommand extends FileCommand {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_SUCCESS = "Data successfully imported";
    public static final String MESSAGE_FILE_ERROR = "An error occurred during loading of data. The file "
            + "might be corrupted.";
    public static final String MESSAGE_FILE_MISSING = "The specified file does not exist";
    public static final String MESSAGE_INVALID_FILE_PATH = "The file path must contain a file name of .json type. "
            + "E.g. data.json";
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

    /**
     * Creates an ImportCommand to import a data file from the specified {@code Path}
     */
    public ImportCommand(Path filePath) {
        requireNonNull(filePath);
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        if (!isValidDataFilePath(filePath)) {
            throw new CommandException(MESSAGE_INVALID_FILE_PATH);
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
