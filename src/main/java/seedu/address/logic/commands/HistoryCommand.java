package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.LinkedList;
import java.util.ListIterator;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Displays up to the specified number of the most recent valid user commands.
 */
public class HistoryCommand extends Command {

    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows up to the last N valid user commands. "
            + "If the number of valid user commands is less than N, it shows all available commands.\n"
            + "Parameters: N (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 3";

    public static final String MESSAGE_SUCCESS = "Displayed up to the last %1$s valid user commands. "
            + "If fewer than %2$s commands are available, all are shown.\n"
            + "%3$s";
    public static final String MESSAGE_SUMMARY =  "History: " + COMMAND_WORD + " N";

    private final int numberOfUserCommands;

    public HistoryCommand(int numberOfUserCommands) {
        this.numberOfUserCommands = numberOfUserCommands;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(history);

        LinkedList<String> userCommandHistory = history.getUserCommandHistory();
        ListIterator<String> historyIterator = userCommandHistory.listIterator(userCommandHistory.size());
        int count = 0;
        StringBuilder sb = new StringBuilder();

        while (historyIterator.hasPrevious() && count < numberOfUserCommands) {
            if (count > 0) {
                sb.append("\n"); // Add a newline separator before appending the next command
            }
            String previousCommand = historyIterator.previous();
            sb.append(previousCommand);
            count++;
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, numberOfUserCommands, numberOfUserCommands, sb));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HistoryCommand)) {
            return false;
        }

        HistoryCommand otherHistoryCommand = (HistoryCommand) other;
        return numberOfUserCommands == otherHistoryCommand.numberOfUserCommands;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("numberOfUserCommands", numberOfUserCommands)
                .toString();
    }
}
