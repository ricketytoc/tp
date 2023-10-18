package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * Sorts the list that is displayed to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "List successfully sorted.";
    public static final String MESSAGE_MULTIPLE_PREFIXES = "Sorting by multiple prefixes is not supported.";

    public static final String MESSAGE_USAGE = "Sorts the list displayed based on the prefix provided."
            + "\n" + "Parameters: "
            + "[" + PREFIX_ID + "] " + "or " + "[" + PREFIX_DEPARTMENT + "] " + "or " + "[" + PREFIX_EMAIL + "] "
            + "or " + "[" + PREFIX_SALARY + "] " + "or " + "[" + PREFIX_ROLE + "] "
            + "or " + "[" + PREFIX_NAME + "] " + "or " + "[" + PREFIX_PHONE + "] "
            + "\n"

            + "Examples: " + "\n" + COMMAND_WORD + " " + PREFIX_ID
            + "\n" + COMMAND_WORD + " " + PREFIX_NAME;
    private final Comparator<Person> comparator;

    public SortCommand(Comparator<Person> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedPersonList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
