package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;

public class ImportCommandParserTest {

    private ImportCommandParser parser = new ImportCommandParser();

    @Test
    public void parse_validArgs_returnsImportCommand() {
        String currentDirectoryPath = "data.json";
        Path path = Path.of(currentDirectoryPath);
        assertParseSuccess(parser, currentDirectoryPath, new ImportCommand(path));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String path = "ABC" + '\0';
        assertParseFailure(parser, path, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ImportCommand.MESSAGE_USAGE));
    }
}
