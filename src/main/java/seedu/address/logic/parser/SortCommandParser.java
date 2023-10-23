package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonComparators;


/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    private static final int EXPECTED_PREFIX_COUNT = 1;

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DEPARTMENT,
                        PREFIX_ROLE, PREFIX_SALARY);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DEPARTMENT,
                PREFIX_ROLE, PREFIX_SALARY);

        if (argMultimap.getPrefixCount() != EXPECTED_PREFIX_COUNT) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        if (argMultimap.hasPrefix(PREFIX_ID)) {
            return new SortCommand(PersonComparators.COMPARATOR_ID);
        }

        if (argMultimap.hasPrefix(PREFIX_NAME)) {
            return new SortCommand(PersonComparators.COMPARATOR_NAME);
        }

        if (argMultimap.hasPrefix(PREFIX_DEPARTMENT)) {
            return new SortCommand(PersonComparators.COMPARATOR_DEPARTMENT);
        }

        if (argMultimap.hasPrefix(PREFIX_EMAIL)) {
            return new SortCommand(PersonComparators.COMPARATOR_EMAIL);
        }

        if (argMultimap.hasPrefix(PREFIX_ROLE)) {
            return new SortCommand(PersonComparators.COMPARATOR_ROLE);
        }

        if (argMultimap.hasPrefix(PREFIX_SALARY)) {
            return new SortCommand(PersonComparators.COMPARATOR_SALARY);
        }

        if (argMultimap.hasPrefix(PREFIX_PHONE)) {
            return new SortCommand(PersonComparators.COMPARATOR_PHONE);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

}
