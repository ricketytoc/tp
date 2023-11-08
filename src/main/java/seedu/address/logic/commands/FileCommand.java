package seedu.address.logic.commands;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * An abstract class representing a command that operates on a data file path.
 * Provides common functionality for validating file paths.
 */
public abstract class FileCommand extends Command {
    /**
     * Checks whether the specified path is a valid data file path. A valid data file path should
     * contain a file name, and the file name should end with ".json".
     * @param path Data file path.
     */
    protected boolean isValidDataFilePath(Path path) {
        Path fileNamePath = path.getFileName();
        // Check for missing file name, or if the file path is a directory
        if (fileNamePath == null || Files.isDirectory(path)) {
            return false;
        }

        // Check for incorrect file extension
        return fileNamePath.toString().endsWith(".json");
    }
}
