package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIncrements.INCREMENT_OBJ_POS;
import static seedu.address.testutil.TypicalIncrements.INCREMENT_STRING_POS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.IncrementCommand;

public class IncrementCommandParserTest {

    private IncrementCommandParser parser = new IncrementCommandParser();

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_validArgs_returnsIncrementCommand() {
        assertParseSuccess(parser, INCREMENT_STRING_POS, new IncrementCommand(INCREMENT_OBJ_POS));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(
                parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncrementCommand.MESSAGE_USAGE));
    }
}
