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

import java.util.Comparator;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

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

        // TODO: Check if multiple prefixes appear in the command.

        if (argMultimap.hasPrefix(PREFIX_ID)) {
            return new SortCommand(Comparator.comparing(p -> p.getId().value));
        }

        if (argMultimap.hasPrefix(PREFIX_NAME)) {
            return new SortCommand(Comparator.comparing(p -> p.getName().fullName));
        }

        if (argMultimap.hasPrefix(PREFIX_DEPARTMENT)) {
            return new SortCommand(Comparator.comparing(p -> p.getDepartment().value));
        }

        if (argMultimap.hasPrefix(PREFIX_EMAIL)) {
            return new SortCommand(Comparator.comparing(p -> p.getEmail().value));
        }

        if (argMultimap.hasPrefix(PREFIX_ROLE)) {
            return new SortCommand(Comparator.comparing(p -> p.getRole().value));
        }

        if (argMultimap.hasPrefix(PREFIX_SALARY)) {
            return new SortCommand(Comparator.comparing(p -> Double.parseDouble(p.getSalary().value)));
        }

        if (argMultimap.hasPrefix(PREFIX_PHONE)) {
            return new SortCommand(Comparator.comparing(p -> p.getPhone().value));
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

}
