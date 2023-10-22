package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.EVE;
import static seedu.address.testutil.TypicalPersons.MALLORY;
import static seedu.address.testutil.TypicalPersons.TRUDY;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonComparators;


/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        AddressBook addressBook = new AddressBook();
        addressBook.setPersons(List.of(EVE, MALLORY, TRUDY));
        model = new ModelManager(addressBook, new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        Comparator<Person> firstComparator = PersonComparators.COMPARATOR_ID;
        Comparator<Person> secondComparator = PersonComparators.COMPARATOR_EMAIL;

        SortCommand sortFirstCommand = new SortCommand(firstComparator);
        SortCommand sortSecondCommand = new SortCommand(secondComparator);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCommand sortFirstCommandCopy = new SortCommand(firstComparator);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different sort attribute -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }


    // For quick reference of the details of EVE, MALLORY, and TRUDY:
    // Mallory Tan (ID: A001080) - Finance - Salesperson, Phone: 90003333, Email: mallory@example.com, Salary: $5000
    // Eve Lim (ID: A001060) - Admin - Personal Assistant, Phone: 90004444, Email: eve@example.com, Salary: $10000
    // Trudy Koh (ID: A000FFF) - Technical - Developer, Phone: 90002222, Email: trudy@example.com, Salary: $4750

    @Test
    public void execute_id_multiplePersons() {
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        Comparator<Person> comparator = Comparator.comparing(p -> p.getId().value);
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(TRUDY, EVE, MALLORY), model.getSortedFilteredPersonList());
    }

    @Test
    public void execute_name_multiplePersons() {
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        Comparator<Person> comparator = Comparator.comparing(p -> p.getName().fullName);
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(EVE, MALLORY, TRUDY), model.getSortedFilteredPersonList());
    }


    @Test
    public void execute_phone_multiplePersons() {
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        Comparator<Person> comparator = Comparator.comparing(p -> p.getPhone().value);
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(TRUDY, MALLORY, EVE), model.getSortedFilteredPersonList());
    }

    @Test
    public void execute_department_multiplePersons() {
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        Comparator<Person> comparator = Comparator.comparing(p -> p.getDepartment().value);
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(EVE, MALLORY, TRUDY), model.getSortedFilteredPersonList());
    }

    @Test
    public void execute_role_multiplePersons() {
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        Comparator<Person> comparator = Comparator.comparing(p -> p.getRole().value);
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(TRUDY, EVE, MALLORY), model.getSortedFilteredPersonList());
    }

    @Test
    public void execute_email_multiplePersons() {
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        Comparator<Person> comparator = Comparator.comparing(p -> p.getEmail().value);
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(EVE, MALLORY, TRUDY), model.getSortedFilteredPersonList());
    }

    @Test
    public void execute_salary_multiplePersons() {
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        Comparator<Person> comparator = Comparator.comparing(p -> Double.parseDouble(p.getSalary().value));
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(TRUDY, MALLORY, EVE), model.getSortedFilteredPersonList());
    }
}
