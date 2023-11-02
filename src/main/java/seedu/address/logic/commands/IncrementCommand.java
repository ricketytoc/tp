package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.increment.Increment;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.Salary;

/**
 * Increments the salary of all persons in the displayed person list by the given increment.
 */
public class IncrementCommand extends Command {
    public static final String COMMAND_WORD = "increment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Increases the salary of all persons in the displayed person list by the input value.\n"
            + "Parameters: INCREMENT (" + Increment.MESSAGE_CONSTRAINTS + ")\n"
            + "Example: " + COMMAND_WORD + " 100";

    public static final String MESSAGE_INCREMENT_SUCCESS = "Incremented salary of %1$s person(s) by: %2$s";

    public static final String MESSAGE_INVALID_INCREMENT = "Increment causes salary of {%1$s} to fall below 0 "
            + "or exceed the maximum salary of %2$.2f";
    public static final String MESSAGE_SUMMARY = "Increment: " + COMMAND_WORD + " INCREMENT" + "\n"
            + "Example: " + COMMAND_WORD + " 100" + "\n";

    private final Increment increment;

    /**
     * Constructs a {@code IncrementCommand}
     *
     * @param increment A {@code Increment}.
     */
    public IncrementCommand(Increment increment) {
        this.increment = increment;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getSortedFilteredPersonList();
        checkValidIncrement(lastShownList);

        for (Person personToEdit : lastShownList) {
            Person editedPerson = incrementPersonSalary(personToEdit);
            model.setPerson(personToEdit, editedPerson);
        }
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_INCREMENT_SUCCESS, lastShownList.size(), increment.toString()));
    }

    /**
     * Checks if the persons in the given {@code personList} all have a salary that is greater than or equal to
     * the {@code increment}.
     * @throws CommandException If at least one of the persons have a salary that is less than the {@code increment}.
     */
    private void checkValidIncrement(List<Person> personList) throws CommandException {
        requireNonNull(personList);
        for (Person person : personList) {
            if (!person.getSalary().isValidIncrement(increment)) {
                throw new CommandException(
                        String.format(MESSAGE_INVALID_INCREMENT, Messages.format(person), Salary.MAXIMUM_SALARY));
            }
        }
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit} and with the incremented salary.
     */
    private Person incrementPersonSalary(Person personToEdit) {
        assert personToEdit != null;

        Id id = personToEdit.getId();
        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Department department = personToEdit.getDepartment();
        Role role = personToEdit.getRole();
        Salary updatedSalary = personToEdit.getSalary().getIncrementedSalary(increment);

        return new Person(id, name, phone, email, department, role, updatedSalary);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IncrementCommand)) {
            return false;
        }

        IncrementCommand otherIncrementCommand = (IncrementCommand) other;
        return increment.equals(otherIncrementCommand.increment);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("incrementAmount", increment)
                .toString();
    }
}
