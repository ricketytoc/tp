package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.DepartmentContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.GeneralPredicate;
import seedu.address.model.person.IdContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.RoleContainsKeywordsPredicate;
import seedu.address.model.person.SalaryWithinRangePredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser,
                "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPrefix_throwsParseException() {
        assertParseFailure(parser,
                " " + PREFIX_ID,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser,
                " " + PREFIX_NAME,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser,
                " " + PREFIX_PHONE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser,
                " " + PREFIX_EMAIL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser,
                " " + PREFIX_DEPARTMENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser,
                " " + PREFIX_ROLE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser,
                " " + PREFIX_SALARY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidInput_throwsParseException() {
        // Invalid Salary input : no numbers
        assertParseFailure(parser,
                " " + PREFIX_SALARY + "abc - wxy",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Invalid Salary input : no space before dash
        assertParseFailure(parser,
                " " + PREFIX_SALARY + "500- 6000",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Invalid Salary input : no space after dash
        assertParseFailure(parser,
                " " + PREFIX_SALARY + "500 -6000",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Invalid Salary input : negative numbers
        assertParseFailure(parser,
                " " + PREFIX_SALARY + "-500 - 6000",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Invalid Find Command args : ID argument contains "/"
        assertParseFailure(parser,
                " " + PREFIX_ID + "A0001 INVALID/12345",
                FindCommand.INVALID_FIND_ARGS_MESSAGE);

    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // ID attribute - no leading and trailing whitespaces
        ArrayList<Predicate<Person>> predicateList = new ArrayList<>();
        predicateList.add(new IdContainsKeywordsPredicate("A0001"));
        FindCommand expectedFindCommand =
                new FindCommand(new GeneralPredicate(predicateList));
        assertParseSuccess(parser, " " + PREFIX_ID + "A0001", expectedFindCommand);

        // Phone attribute - no leading and trailing whitespaces
        predicateList.clear();
        predicateList.add(new PhoneContainsKeywordsPredicate("9001"));
        expectedFindCommand =
                new FindCommand(new GeneralPredicate(predicateList));
        assertParseSuccess(parser, " " + PREFIX_PHONE + "9001", expectedFindCommand);

        // Email attribute - no leading and trailing whitespaces
        predicateList.clear();
        predicateList.add(new EmailContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        expectedFindCommand =
                new FindCommand(new GeneralPredicate(predicateList));
        assertParseSuccess(parser, " " + PREFIX_EMAIL + "Alice Bob", expectedFindCommand);

        // Department attribute - no leading and trailing whitespaces
        predicateList.clear();
        predicateList.add(new DepartmentContainsKeywordsPredicate(Arrays.asList("Finance", "Marketing")));
        expectedFindCommand =
                new FindCommand(new GeneralPredicate(predicateList));
        assertParseSuccess(parser, " " + PREFIX_DEPARTMENT + "Finance Marketing", expectedFindCommand);

        // Role attribute - no leading and trailing whitespaces
        predicateList.clear();
        predicateList.add(new RoleContainsKeywordsPredicate(Arrays.asList("Manager", "Intern")));
        expectedFindCommand =
                new FindCommand(new GeneralPredicate(predicateList));
        assertParseSuccess(parser, " " + PREFIX_ROLE + "Manager Intern", expectedFindCommand);

        // Salary attribute - no leading and trailing whitespaces
        predicateList.clear();
        predicateList.add(new SalaryWithinRangePredicate(1000, 5000));
        expectedFindCommand =
                new FindCommand(new GeneralPredicate(predicateList));
        assertParseSuccess(parser, " " + PREFIX_SALARY + "1000 - 5000", expectedFindCommand);

        // Salary attribute - lowerBound bigger than upperBound
        predicateList.clear();
        predicateList.add(new SalaryWithinRangePredicate(5000, 1000));
        expectedFindCommand =
                new FindCommand(new GeneralPredicate(predicateList));
        assertParseSuccess(parser, " " + PREFIX_SALARY + "5000 - 1000", expectedFindCommand);

        // Name attribute - no leading and trailing whitespaces
        predicateList.clear();
        predicateList.add(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        expectedFindCommand =
                new FindCommand(new GeneralPredicate(predicateList));
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t", expectedFindCommand);

        // Multiple attributes - no leading and trailing whitespaces
        predicateList.clear();
        predicateList.add(new IdContainsKeywordsPredicate("A0001"));
        predicateList.add(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        predicateList.add(new RoleContainsKeywordsPredicate(Arrays.asList("Manager", "Intern")));
        predicateList.add(new DepartmentContainsKeywordsPredicate(Arrays.asList("Finance", "Marketing")));
        predicateList.add(new EmailContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        predicateList.add(new SalaryWithinRangePredicate(1000, 5000));
        predicateList.add(new PhoneContainsKeywordsPredicate("9001"));
        expectedFindCommand =
                new FindCommand(new GeneralPredicate(predicateList));
        assertParseSuccess(parser, " "
                + PREFIX_ID + "A0001 "
                + PREFIX_NAME + "Alice Bob "
                + PREFIX_PHONE + "9001 "
                + PREFIX_EMAIL + "Alice Bob "
                + PREFIX_DEPARTMENT + "Finance Marketing "
                + PREFIX_ROLE + "Manager Intern "
                + PREFIX_SALARY + "1000 - 5000 ",
                expectedFindCommand);

    }

}
