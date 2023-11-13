package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Exports the data file into the specified path.
 */
public class ExportCommand extends FileCommand {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_SUCCESS = "Data successfully exported";
    public static final String MESSAGE_FAILURE = "Data could not be exported";
    public static final String MESSAGE_INVALID_FILE_PATH = "The file path must contain a file name of .json type. "
            + "E.g. data.json";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports the data file to the specified location. "
            + "\n"
            + "Parameters: "
            + "FILE_PATH"
            + "\n"
            + "Example: " + COMMAND_WORD + " " + "./mydata/persons.json";
    public static final String MESSAGE_SUMMARY = "Export: " + COMMAND_WORD + " FILE_PATH" + "\n"
            + "Example: " + COMMAND_WORD + " " + "./mydata/persons.json" + "\n";

    /**
     * Creates an ExportCommand to export the data file to the specified {@code Path}
     */
    public ExportCommand(Path filePath) {
        super(filePath);
        requireNonNull(filePath);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        if (!isValidDataFilePath()) {
            throw new CommandException(MESSAGE_INVALID_FILE_PATH);
        }

        // Attempt to save address book into disk
        try {
            new JsonAddressBookStorage(getFilePath()).saveAddressBook(model.getAddressBook(), getFilePath());
        } catch (IOException ex) {
            throw new CommandException(MESSAGE_FAILURE + ": " + ex.getMessage());
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExportCommand)) {
            return false;
        }

        ExportCommand otherExportCommand = (ExportCommand) other;
        return getFilePath().equals(otherExportCommand.getFilePath());
    }
}
