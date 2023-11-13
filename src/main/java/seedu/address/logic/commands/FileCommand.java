package seedu.address.logic.commands;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * An abstract class representing a command that operates on a data file path.
 * Provides common functionality for validating file paths.
 */
public abstract class FileCommand extends Command {
    private final Path filePath;
    FileCommand(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks whether the specified path is a valid data file path. A valid data file path should
     * contain a file name, and the file name should end with ".json". Note that a valid file path
     * does not imply the file exists or can be read/written to.
     */
    protected boolean isValidDataFilePath() {
        Path fileNamePath = getFilePath().getFileName();
        // Check for missing file name, or if the file path is a directory
        if (fileNamePath == null || Files.isDirectory(getFilePath())) {
            return false;
        }

        // Check for incorrect file extension
        return fileNamePath.toString().endsWith(".json");
    }

    protected Path getFilePath() {
        return this.filePath;
    }
}
