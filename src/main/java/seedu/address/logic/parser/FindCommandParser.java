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
import java.util.Arrays;
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
import seedu.address.model.person.Salary;
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
            String keyword = argMultimap.getValue(PREFIX_ID).get();
            ParserUtil.checkFindArgs(keyword);
            if (keyword.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicateList.add(new IdContainsKeywordsPredicate(keyword));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_NAME).get().trim();
            ParserUtil.checkFindArgs(trimmedArgs);
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] nameKeywords = trimmedArgs.split("\\s+");
            predicateList.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_ROLE).get().trim();
            ParserUtil.checkFindArgs(trimmedArgs);
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] roleKeywords = trimmedArgs.split("\\s+");
            predicateList.add(new RoleContainsKeywordsPredicate(Arrays.asList(roleKeywords)));
        }
        if (argMultimap.getValue(PREFIX_DEPARTMENT).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_DEPARTMENT).get().trim();
            ParserUtil.checkFindArgs(trimmedArgs);
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] departmentKeywords = trimmedArgs.split("\\s+");
            predicateList.add(new DepartmentContainsKeywordsPredicate(Arrays.asList(departmentKeywords)));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_EMAIL).get().trim();
            ParserUtil.checkFindArgs(trimmedArgs);
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] emailKeywords = trimmedArgs.split("\\s+");
            predicateList.add(new EmailContainsKeywordsPredicate(Arrays.asList(emailKeywords)));
        }
        if (argMultimap.getValue(PREFIX_SALARY).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_SALARY).get().trim();
            ParserUtil.checkFindArgs(trimmedArgs);
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            if (!isValidFindSalaryArgs(trimmedArgs)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            double lowerBound = findLowerBound(trimmedArgs);
            double upperBound = findUpperBound(trimmedArgs);
            predicateList.add(new SalaryWithinRangePredicate(lowerBound, upperBound));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_PHONE).get().trim();
            ParserUtil.checkFindArgs(trimmedArgs);
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicateList.add(new PhoneContainsKeywordsPredicate(trimmedArgs));
        }
        assert !predicateList.isEmpty() : "predicate list is empty, please enter find command with valid prefix";
        GeneralPredicate generalPredicate = new GeneralPredicate(predicateList);
        return new FindCommand(generalPredicate);
    }

    /**
     * Checks whether salary arguments are valid. If the argument is not in the format of "400 - 5000" for example,
     * it will return false. If the user enters a number greater than can be assigned to a long or if user inputs number
     * greater than the maximum allowed salary, it will also return false.
     * @param salaryArgs user input for find command with PREFIX_SALARY.
     * @return true if argument is valid and false otherwise.
     */
    private boolean isValidFindSalaryArgs(String salaryArgs) {
        String validationRegex = "^\\d+\\s-\\s\\d+$";
        if (!salaryArgs.matches(validationRegex)) {
            return false;
        }
        String upperBound = salaryArgs.split(" - ", 2)[1];
        try {
            double upperBoundDouble = Double.parseDouble(upperBound);
            if (upperBoundDouble > Salary.MAXIMUM_SALARY) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private double findLowerBound(String salaryArgs) {
        return Double.parseDouble(salaryArgs.split(" - ", 2)[0]);
    }

    private double findUpperBound(String salaryArgs) {
        return Double.parseDouble(salaryArgs.split(" - ", 2)[1]);
    }

}
