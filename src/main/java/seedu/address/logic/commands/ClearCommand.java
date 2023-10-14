package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Current list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Person> shownList = model.getFilteredPersonList();

        while (shownList.size() != 0) {
            Person personToDelete = shownList.get(0);
            model.deletePerson(personToDelete);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
