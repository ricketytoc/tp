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

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.comparators.DepartmentComparator;
import seedu.address.model.person.comparators.EmailComparator;
import seedu.address.model.person.comparators.IdComparator;
import seedu.address.model.person.comparators.NameComparator;
import seedu.address.model.person.comparators.PhoneComparator;
import seedu.address.model.person.comparators.RoleComparator;
import seedu.address.model.person.comparators.SalaryComparator;

public class SortCommandParserTest {

    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser,
                "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, " invalid/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() {
        SortCommand expectedSortCommand = new SortCommand(new DepartmentComparator());
        assertParseSuccess(parser, " " + PREFIX_DEPARTMENT.getPrefix(), expectedSortCommand);

        expectedSortCommand = new SortCommand(new EmailComparator());
        assertParseSuccess(parser, " " + PREFIX_EMAIL.getPrefix(), expectedSortCommand);

        expectedSortCommand = new SortCommand(new IdComparator());
        assertParseSuccess(parser, " " + PREFIX_ID.getPrefix(), expectedSortCommand);

        expectedSortCommand = new SortCommand(new NameComparator());
        assertParseSuccess(parser, " " + PREFIX_NAME.getPrefix(), expectedSortCommand);

        expectedSortCommand = new SortCommand(new PhoneComparator());
        assertParseSuccess(parser, " " + PREFIX_PHONE.getPrefix(), expectedSortCommand);

        expectedSortCommand = new SortCommand(new RoleComparator());
        assertParseSuccess(parser, " " + PREFIX_ROLE.getPrefix(), expectedSortCommand);

        expectedSortCommand = new SortCommand(new SalaryComparator());
        assertParseSuccess(parser, " " + PREFIX_SALARY.getPrefix(), expectedSortCommand);
    }

    @Test
    public void parse_multiplePrefix_throwsParseException() {
        // Two different prefixes
        assertParseFailure(parser,
                " " + PREFIX_NAME + " " + PREFIX_DEPARTMENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // Three different prefixes
        assertParseFailure(parser,
                " " + PREFIX_NAME + " " + PREFIX_DEPARTMENT + " " + PREFIX_ID,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

}
