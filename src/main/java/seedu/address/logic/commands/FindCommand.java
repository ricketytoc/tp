package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose attribute contain any of the keywords
 * specified for those attributes.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Except for Salary attribute, finds all persons whose "
            + "attributes contain the keyword (case-insensitive) specified for those attributes and displays them as a "
            + "list with index numbers. For Salary, finds all persons whose salary is within a range instead.\n"
            + "Parameters: "
            + "[" + PREFIX_ID + "ID] " + "or [" + PREFIX_NAME + "NAME] " + "or [" + PREFIX_PHONE + "PHONE] "
            + "or [" + PREFIX_EMAIL + "EMAIL] " + "or [" + PREFIX_DEPARTMENT + "DEPARTMENT] "
            + "or [" + PREFIX_ROLE + "ROLE] " + "or [" + PREFIX_SALARY + "SALARY_LOWER_BOUND - SALARY_UPPER_BOUND] "
            + "\n"
            + "Examples: " + "\n" + COMMAND_WORD + " " + PREFIX_ID + "A0001 "
            + "\n" + COMMAND_WORD + " " + PREFIX_NAME + "Alex "
            + "\n" + COMMAND_WORD + " " + PREFIX_PHONE + "98765432 "
            + "\n" + COMMAND_WORD + " " + PREFIX_EMAIL + "alexyeoh@example.com "
            + "\n" + COMMAND_WORD + " " + PREFIX_DEPARTMENT + "Finance "
            + "\n" + COMMAND_WORD + " " + PREFIX_ROLE + "Manager "
            + "\n" + COMMAND_WORD + " " + PREFIX_SALARY + "400 - 5000 ";


    private final Predicate<Person> predicate;

    public FindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getSortedFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
