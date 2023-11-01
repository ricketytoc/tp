package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExportCommand;

public class ExportCommandParserTest {

    private ExportCommandParser parser = new ExportCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        String currentDirectoryPath = "data.json";
        Path path = Path.of(currentDirectoryPath);
        assertParseSuccess(parser, currentDirectoryPath, new ExportCommand(path));
    }

}
