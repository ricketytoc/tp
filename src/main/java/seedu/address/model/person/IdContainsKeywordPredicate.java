package seedu.address.model.person;

import seedu.address.commons.util.ToStringBuilder;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Id} matches any the keyword given.
 */
public class IdContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public IdContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        // The check is case-insensitive
        return person.getId()
                .value.toUpperCase()
                .contains(keyword.toUpperCase());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IdContainsKeywordPredicate)) {
            return false;
        }

        IdContainsKeywordPredicate otherNameContainsKeywordsPredicate = (IdContainsKeywordPredicate) other;
        return keyword.equals(otherNameContainsKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}
