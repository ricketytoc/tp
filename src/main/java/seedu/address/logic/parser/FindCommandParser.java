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
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DEPARTMENT,
                        PREFIX_ROLE, PREFIX_SALARY);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DEPARTMENT,
                PREFIX_ROLE, PREFIX_SALARY);

        if (argMultimap.getPrefixCount() <= 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArrayList<Predicate<Person>> predicateList = new ArrayList<>();
        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_ID).get().trim();
            IdContainsKeywordsPredicate idContainsKeywordsPredicate = ParserUtil
                    .parseIdKeyword(trimmedArgs);
            predicateList.add(idContainsKeywordsPredicate);
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_NAME).get().trim();
            NameContainsKeywordsPredicate nameContainsKeywordsPredicate = ParserUtil
                    .parseNameKeyword(trimmedArgs);
            predicateList.add(nameContainsKeywordsPredicate);
        }
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_ROLE).get().trim();
            RoleContainsKeywordsPredicate roleContainsKeywordsPredicate = ParserUtil
                    .parseRoleKeyword(trimmedArgs);
            predicateList.add(roleContainsKeywordsPredicate);
        }
        if (argMultimap.getValue(PREFIX_DEPARTMENT).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_DEPARTMENT).get().trim();
            DepartmentContainsKeywordsPredicate departmentContainsKeywordsPredicate = ParserUtil
                    .parseDepartmentKeyword(trimmedArgs);
            predicateList.add(departmentContainsKeywordsPredicate);
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_EMAIL).get().trim();
            EmailContainsKeywordsPredicate emailContainsKeywordsPredicate = ParserUtil
                    .parseEmailKeyword(trimmedArgs);
            predicateList.add(emailContainsKeywordsPredicate);
        }
        if (argMultimap.getValue(PREFIX_SALARY).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_SALARY).get().trim();
            SalaryWithinRangePredicate salaryWithinRangePredicate = ParserUtil
                    .parseSalaryKeyword(trimmedArgs);
            predicateList.add(salaryWithinRangePredicate);
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_PHONE).get().trim();
            PhoneContainsKeywordsPredicate phoneContainsKeywordsPredicate = ParserUtil
                    .parsePhoneKeyword(trimmedArgs);
            predicateList.add(phoneContainsKeywordsPredicate);
        }
        assert !predicateList.isEmpty() : "predicate list is empty, please enter find command with valid prefix";
        GeneralPredicate generalPredicate = new GeneralPredicate(predicateList);
        return new FindCommand(generalPredicate);
    }
}
