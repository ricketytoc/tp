package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class ExportCommandTest {
    @Test
    public void equals() {
        Path firstFilePath = Path.of("./data/persons.json");
        Path secondFilePath = Path.of("./data/persons2.json");
        ExportCommand exportFirstCommand = new ExportCommand(firstFilePath);
        ExportCommand exportSecondCommand = new ExportCommand(secondFilePath);

        // same object -> returns true
        assertTrue(exportFirstCommand.equals(exportFirstCommand));

        // same values -> returns true
        ExportCommand exportFirstCommandCopy = new ExportCommand(firstFilePath);
        assertTrue(exportFirstCommand.equals(exportFirstCommandCopy));

        // different types -> returns false
        assertFalse(exportFirstCommand.equals(1));

        // null -> returns false
        assertFalse(exportFirstCommand.equals(null));

        // different path -> returns false
        assertFalse(exportFirstCommand.equals(exportSecondCommand));
    }
}
