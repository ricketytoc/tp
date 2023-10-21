package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class GeneralPredicate implements Predicate<Person> {
    private final ArrayList<Predicate<Person>> predicateList;

    public GeneralPredicate(ArrayList<Predicate<Person>> predicateList) {
        this.predicateList = predicateList;
    }

    @Override
    public boolean test(Person person) {
        boolean result = true;
        for (Predicate<Person> predicate : predicateList) {
            if (!predicate.test(person)) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GeneralPredicate)) {
            return false;
        }

        GeneralPredicate otherGeneralPredicate =
                (GeneralPredicate) other;
        return predicateList.equals(otherGeneralPredicate.predicateList);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("predicateList", predicateList).toString();
    }

}
