package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class FileCommandTest {
    // Example Paths
    public static final String VALID_DATA_FILE_PATH = "data/persons.json";
    public static final String INVALID_DATA_FILE_PATH = "data/persons.txt";

    // Invalid File Paths
    private static final String ROOT_ONLY = "/";
    private static final String ROOT_ONLY_BACKSLASH = "\\";
    private static final String WINDOWS_DIR = "C:";
    private static final String UNIX_DIR = "/usr";
    private static final String EXT_TXT = "data.txt";
    private static final String EXT_JSONN = "data.jsonn";
    private static final String NO_EXT = "data";
    private static final String EXT_JSON_CAPS = "data.jSoN";

    // Valid File Paths
    private static final String ROOT_FILE = "/data.json";
    private static final String RELATIVE_FILE = "data.json";
    private static final String ROOT_FOLDER = "tmp/data.json";
    private static final String RELATIVE_FOLDER = "tmp/data.json";
    private static final String NESTED_FOLDER = "a/b/c/data.json";
    private static final String SPACED_FILE_NAME = "my data.json";
    private static final String SPACED_FOLDER_NAME = "hello world/my data.json";



    /**
     * Tests the isValidDataFilePath function of abstract class FileCommand.
     */
    @Test
    public void isValidDataFilePath_invalidPath_falseReturned() {
        // Directory Paths
        FileCommand fileCommand = new ImportCommand(Path.of(ROOT_ONLY));
        assertFalse(fileCommand.isValidDataFilePath());

        fileCommand = new ImportCommand(Path.of(ROOT_ONLY_BACKSLASH));
        assertFalse(fileCommand.isValidDataFilePath());

        fileCommand = new ImportCommand(Path.of(WINDOWS_DIR));
        assertFalse(fileCommand.isValidDataFilePath());

        fileCommand = new ImportCommand(Path.of(UNIX_DIR));
        assertFalse(fileCommand.isValidDataFilePath());

        fileCommand = new ImportCommand(Path.of(EXT_TXT));
        assertFalse(fileCommand.isValidDataFilePath());

        fileCommand = new ImportCommand(Path.of(EXT_JSONN));
        assertFalse(fileCommand.isValidDataFilePath());

        fileCommand = new ImportCommand(Path.of(NO_EXT));
        assertFalse(fileCommand.isValidDataFilePath());

        fileCommand = new ImportCommand(Path.of(EXT_JSON_CAPS));
        assertFalse(fileCommand.isValidDataFilePath());

        fileCommand = new ImportCommand(Path.of(INVALID_DATA_FILE_PATH));
        assertFalse(fileCommand.isValidDataFilePath());
    }

    @Test
    public void isValidDataFilePath_validPath_trueReturned() {
        FileCommand fileCommand = new ImportCommand(Path.of(ROOT_FILE));
        assertTrue(fileCommand.isValidDataFilePath());

        fileCommand = new ImportCommand(Path.of(RELATIVE_FILE));
        assertTrue(fileCommand.isValidDataFilePath());

        fileCommand = new ImportCommand(Path.of(ROOT_FOLDER));
        assertTrue(fileCommand.isValidDataFilePath());

        fileCommand = new ImportCommand(Path.of(RELATIVE_FOLDER));
        assertTrue(fileCommand.isValidDataFilePath());

        fileCommand = new ImportCommand(Path.of(NESTED_FOLDER));
        assertTrue(fileCommand.isValidDataFilePath());

        fileCommand = new ImportCommand(Path.of(SPACED_FILE_NAME));
        assertTrue(fileCommand.isValidDataFilePath());

        fileCommand = new ImportCommand(Path.of(SPACED_FOLDER_NAME));
        assertTrue(fileCommand.isValidDataFilePath());

        fileCommand = new ImportCommand(Path.of(VALID_DATA_FILE_PATH));
        assertTrue(fileCommand.isValidDataFilePath());
    }
}
