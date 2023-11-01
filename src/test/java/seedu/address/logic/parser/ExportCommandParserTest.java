package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExportCommand;

public class ExportCommandParserTest {

    private ExportCommandParser parser = new ExportCommandParser();

    @Test
    public void parse_validArgs_returnsExportCommand() {
        String currentDirectoryPath = "data.json";
        Path path = Path.of(currentDirectoryPath);
        assertParseSuccess(parser, currentDirectoryPath, new ExportCommand(path));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String currentDirectoryPath = "////";
        assertParseFailure(parser, currentDirectoryPath, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExportCommand.MESSAGE_USAGE));
    }

}
