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

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.DepartmentContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.IdContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
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
    public void parse_invalidSalaryInput_throwsParseException() {
        // no numbers
        assertParseFailure(parser,
                "abc - wxy" + PREFIX_SALARY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // no space before dash
        assertParseFailure(parser,
                "500- 6000" + PREFIX_SALARY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // no space after dash
        assertParseFailure(parser,
                "500 -6000" + PREFIX_SALARY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // negative numbers
        assertParseFailure(parser,
                "-500 - 6000" + PREFIX_SALARY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Salary attribute - lowerBound bigger than upperBound
        assertParseFailure(parser,
                "5000 - 4000" + PREFIX_SALARY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // ID attribute - no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new IdContainsKeywordsPredicate("A0001"));
        assertParseSuccess(parser, " " + PREFIX_ID + "A0001", expectedFindCommand);

        // Phone attribute - no leading and trailing whitespaces
        expectedFindCommand =
                new FindCommand(new PhoneContainsKeywordsPredicate("9001"));
        assertParseSuccess(parser, " " + PREFIX_PHONE + "9001", expectedFindCommand);

        // Email attribute - no leading and trailing whitespaces
        expectedFindCommand =
                new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " " + PREFIX_EMAIL + "Alice Bob", expectedFindCommand);

        // Department attribute - no leading and trailing whitespaces
        expectedFindCommand =
                new FindCommand(new DepartmentContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " " + PREFIX_DEPARTMENT + "Alice Bob", expectedFindCommand);

        // Role attribute - no leading and trailing whitespaces
        expectedFindCommand =
                new FindCommand(new RoleContainsKeywordsPredicate(Arrays.asList("Manager", "Intern")));
        assertParseSuccess(parser, " " + PREFIX_ROLE + "Manager Intern", expectedFindCommand);

        // Salary attribute - no leading and trailing whitespaces
        expectedFindCommand =
                new FindCommand(new SalaryWithinRangePredicate(1000, 5000));
        assertParseSuccess(parser, " " + PREFIX_SALARY + "1000 - 5000", expectedFindCommand);


        // Name attribute - no leading and trailing whitespaces
        expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

}
