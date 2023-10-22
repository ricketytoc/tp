package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Id} matches any the keyword given.
 */
public class IdContainsKeywordsPredicate implements Predicate<Person> {
    private final String keyword;

    public IdContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        // The check is case-insensitive
        return (!this.keyword.isEmpty())
                && (person.getId()
                .value.toUpperCase()
                .contains(keyword.toUpperCase()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IdContainsKeywordsPredicate)) {
            return false;
        }

        IdContainsKeywordsPredicate otherIdContainsKeywordsPredicate = (IdContainsKeywordsPredicate) other;
        return keyword.equals(otherIdContainsKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}
