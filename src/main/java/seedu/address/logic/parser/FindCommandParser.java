package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.ArrayList;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DepartmentContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.GeneralPredicate;
import seedu.address.model.person.IdContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.RoleContainsKeywordsPredicate;
import seedu.address.model.person.SalaryWithinRangePredicate;

/**
 * Parses Find command input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Parses the given {@code String} into a FindCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DEPARTMENT,
                        PREFIX_ROLE, PREFIX_SALARY);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DEPARTMENT,
                PREFIX_ROLE, PREFIX_SALARY);

        // If no prefix is found, throw an error
        if (argMultimap.getPrefixCount() <= 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // Stores all the predicates that were parsed from the prefix keywords into an ArrayList.
        ArrayList<Predicate<Person>> predicateList = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            String idKeyword = argMultimap.getValue(PREFIX_ID).get();
            IdContainsKeywordsPredicate idContainsKeywordsPredicate = ParserUtil
                    .parseIdKeyword(idKeyword);
            predicateList.add(idContainsKeywordsPredicate);
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String nameKeyword = argMultimap.getValue(PREFIX_NAME).get();
            NameContainsKeywordsPredicate nameContainsKeywordsPredicate = ParserUtil
                    .parseNameKeyword(nameKeyword);
            predicateList.add(nameContainsKeywordsPredicate);
        }
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            String roleKeyword = argMultimap.getValue(PREFIX_ROLE).get();
            RoleContainsKeywordsPredicate roleContainsKeywordsPredicate = ParserUtil
                    .parseRoleKeyword(roleKeyword);
            predicateList.add(roleContainsKeywordsPredicate);
        }
        if (argMultimap.getValue(PREFIX_DEPARTMENT).isPresent()) {
            String departmentkeyword = argMultimap.getValue(PREFIX_DEPARTMENT).get();
            DepartmentContainsKeywordsPredicate departmentContainsKeywordsPredicate = ParserUtil
                    .parseDepartmentKeyword(departmentkeyword);
            predicateList.add(departmentContainsKeywordsPredicate);
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String emailKeyword = argMultimap.getValue(PREFIX_EMAIL).get();
            EmailContainsKeywordsPredicate emailContainsKeywordsPredicate = ParserUtil
                    .parseEmailKeyword(emailKeyword);
            predicateList.add(emailContainsKeywordsPredicate);
        }
        if (argMultimap.getValue(PREFIX_SALARY).isPresent()) {
            String salaryKeyword = argMultimap.getValue(PREFIX_SALARY).get();
            SalaryWithinRangePredicate salaryWithinRangePredicate = ParserUtil
                    .parseSalaryKeyword(salaryKeyword);
            predicateList.add(salaryWithinRangePredicate);
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String phoneKeyword = argMultimap.getValue(PREFIX_PHONE).get();
            PhoneContainsKeywordsPredicate phoneContainsKeywordsPredicate = ParserUtil
                    .parsePhoneKeyword(phoneKeyword);
            predicateList.add(phoneContainsKeywordsPredicate);
        }

        // predicateList should not be empty because we already ensured that argMultimap has at least one prefix keyword
        assert !predicateList.isEmpty();

        GeneralPredicate generalPredicate = new GeneralPredicate(predicateList);
        return new FindCommand(generalPredicate);
    }
}
