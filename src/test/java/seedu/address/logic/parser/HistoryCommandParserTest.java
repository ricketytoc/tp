package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HistoryCommand;

public class HistoryCommandParserTest {
    private HistoryCommandParser parser = new HistoryCommandParser();

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser,
                "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HistoryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsHistoryCommand() {
        assertParseSuccess(parser, "1", new HistoryCommand(1));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // alphabetical inputs
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, HistoryCommand.MESSAGE_USAGE));

        // alphanumeric inputs
        assertParseFailure(parser, "a1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, HistoryCommand.MESSAGE_USAGE));

        // negative inputs
        assertParseFailure(parser, "-5", String.format(MESSAGE_INVALID_COMMAND_FORMAT, HistoryCommand.MESSAGE_USAGE));

        // zero input
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT, HistoryCommand.MESSAGE_USAGE));
    }
}
