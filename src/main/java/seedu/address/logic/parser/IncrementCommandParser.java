package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.increment.Increment;
import seedu.address.logic.commands.IncrementCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new IncrementCommand object
 */
public class IncrementCommandParser implements Parser<IncrementCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the IncrementCommand
     * and returns an IncrementCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public IncrementCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Increment increment = ParserUtil.parseIncrement(args);
            return new IncrementCommand(increment);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncrementCommand.MESSAGE_USAGE), pe);
        }
    }
}
