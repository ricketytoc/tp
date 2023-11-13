package seedu.address.logic;

import static java.util.Objects.requireNonNull;

import java.util.LinkedList;

/**
 * Stores the command history of the user.
 * Class below inspired by AB4
 * https://github.com/se-edu/addressbook-level4/blob/master/src/main/java/seedu/address/logic/CommandHistory.java
 */
public class CommandHistory {

    private LinkedList<String> userCommandHistory;

    public CommandHistory() {
        userCommandHistory = new LinkedList<>();
    }

    /**
     * Appends {@code userCommand} to the list of user command entered.
     */
    public void add(String userCommand) {
        requireNonNull(userCommand);
        this.userCommandHistory.add(userCommand);
    }

    /**
     * Returns a defensive copy of {@code userInputHistory}.
     */
    public LinkedList<String> getUserCommandHistory() {
        return new LinkedList<>(userCommandHistory);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof CommandHistory)) {
            return false;
        }

        CommandHistory other = (CommandHistory) obj;
        return userCommandHistory.equals(other.userCommandHistory);
    }

    @Override
    public int hashCode() {
        return userCommandHistory.hashCode();
    }
}
