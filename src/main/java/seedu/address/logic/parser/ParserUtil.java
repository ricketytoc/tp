package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import seedu.address.commons.core.increment.Increment;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Department;
import seedu.address.model.person.DepartmentContainsKeywordsPredicate;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.Id;
import seedu.address.model.person.IdContainsKeywordsPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.Role;
import seedu.address.model.person.RoleContainsKeywordsPredicate;
import seedu.address.model.person.Salary;
import seedu.address.model.person.SalaryParserUtil;
import seedu.address.model.person.SalaryWithinRangePredicate;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_PATH = "The path is invalid.";
    public static final String MESSAGE_EMPTY_PATH = "The path is empty.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String path} into a {@code Path}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code path} is invalid.
     */
    public static Path parsePath(String path) throws ParseException {
        requireNonNull(path);
        String trimmedPath = path.trim();
        if (trimmedPath.isBlank()) {
            throw new ParseException(MESSAGE_EMPTY_PATH);
        }

        try {
            return Paths.get(trimmedPath);
        } catch (InvalidPathException ipe) {
            throw new ParseException(MESSAGE_INVALID_PATH + " " + ipe.getReason());
        }
    }

    /**
     * Parses a {@code String id} into a {@code Id}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static Id parseId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!Id.isValidId(trimmedId)) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }
        return new Id(trimmedId);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String department} into a {@code Department}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code department} is invalid.
     */
    public static Department parseDepartment(String department) throws ParseException {
        requireNonNull(department);
        String trimmedDepartment = department.trim();
        if (!Department.isValidDepartment(trimmedDepartment)) {
            throw new ParseException(Department.MESSAGE_CONSTRAINTS);
        }
        return new Department(trimmedDepartment);
    }

    /**
     * Parses a {@code String role} into a {@code Role}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code role} is invalid.
     */
    public static Role parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedRole = role.trim();
        if (!Role.isValidRole(trimmedRole)) {
            throw new ParseException(Role.MESSAGE_CONSTRAINTS);
        }
        return new Role(trimmedRole);
    }

    /**
     * Parses a {@code String salary} into a {@code Salary}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code salary} is invalid.
     */
    public static Salary parseSalary(String salary) throws ParseException {
        requireNonNull(salary);
        String trimmedSalary = salary.trim();
        if (!Salary.isValidSalary(trimmedSalary)) {
            throw new ParseException(Salary.MESSAGE_CONSTRAINTS);
        }
        return new Salary(trimmedSalary);
    }

    /**
     * Parses {@code String increment} into a {@code Increment}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code increment} is invalid.
     */
    public static Increment parseIncrement(String increment) throws ParseException {
        requireNonNull(increment);
        String trimmedIncrement = increment.trim();
        if (!Increment.isValidIncrement(trimmedIncrement)) {
            throw new ParseException(Increment.MESSAGE_CONSTRAINTS);
        }
        return new Increment(trimmedIncrement);
    }

    /**
     * Parses {@code String number} into an {@code int} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified number is invalid (not non-zero unsigned integer).
     */
    public static int parseHistory(String number) throws ParseException {
        String trimmedNumber = number.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedNumber)) {
            throw new ParseException(HistoryCommand.MESSAGE_INVALID_N);
        }
        int historyCommandNumber = Integer.parseInt(trimmedNumber);
        if (historyCommandNumber > Integer.parseInt(HistoryCommand.MAX_HISTORY_NUMBER)) {
            throw new ParseException(HistoryCommand.MESSAGE_INVALID_N);
        }
        return historyCommandNumber;
    }

    /**
     * Parses {@code String idKeyword} into an {@code IdContainsKeywordsPredicate}. Leading and trailing whitespaces
     * will be trimmed.
     * @param idKeyword keyword used to find employees with ID that contains keyword.
     * @throws ParseException if idKeyword is empty or if it contains '/' char.
     */
    public static IdContainsKeywordsPredicate parseIdKeyword(String idKeyword) throws ParseException {
        checkKeywordsAreValid(idKeyword);
        String trimmedIdKeyword = idKeyword.trim();
        return new IdContainsKeywordsPredicate(trimmedIdKeyword);
    }

    /**
     * Parses {@code String nameKeywords} into a {@code NameContainsKeywordsPredicate}. Leading and trailing whitespaces
     * will be trimmed.
     * @param nameKeywords keywords used to find employees with matching names.
     * @throws ParseException if nameKeywords is empty or if it contains '/' char.
     */
    public static NameContainsKeywordsPredicate parseNameKeyword(String nameKeywords) throws ParseException {
        checkKeywordsAreValid(nameKeywords);
        String trimmedNameKeywords = nameKeywords.trim();
        String[] nameKeywordsArray = trimmedNameKeywords.split("\\s+");
        return new NameContainsKeywordsPredicate(Arrays.asList(nameKeywordsArray));
    }

    /**
     * Parses {@code String roleKeywords} into a {@code RoleContainsKeywordsPredicate}. Leading and trailing whitespaces
     * will be trimmed.
     * @param roleKeywords keywords used to find employees with matching roles.
     * @throws ParseException if roleKeywords is empty or if it contains '/' char.
     */
    public static RoleContainsKeywordsPredicate parseRoleKeyword(String roleKeywords) throws ParseException {
        checkKeywordsAreValid(roleKeywords);
        String trimmedRoleKeywords = roleKeywords.trim();
        String[] roleKeywordsArray = trimmedRoleKeywords.split("\\s+");
        return new RoleContainsKeywordsPredicate(Arrays.asList(roleKeywordsArray));
    }

    /**
     * Parses {@code String departmentKeywords} into a {@code DepartmentContainsKeywordsPredicate}. Leading and trailing
     * whitespaces will be trimmed.
     * @param departmentKeywords keywords used to find employees with matching departments.
     * @throws ParseException if departmentKeywords is empty or if it contains '/' char.
     */
    public static DepartmentContainsKeywordsPredicate parseDepartmentKeyword(String departmentKeywords)
            throws ParseException {
        checkKeywordsAreValid(departmentKeywords);
        String trimmedDepartmentKeywords = departmentKeywords.trim();
        String[] departmentKeywordsArray = trimmedDepartmentKeywords.split("\\s+");
        return new DepartmentContainsKeywordsPredicate(Arrays.asList(departmentKeywordsArray));
    }

    /**
     * Parses {@code String emailKeywords} into a {@code EmailContainsKeywordsPredicate}. Leading and trailing
     * whitespaces will be trimmed.
     * @param emailKeywords keywords that are used to find employees whose emails contains at least one keyword.
     * @throws ParseException if emailKeywords is empty or if it contains '/' char.
     */
    public static EmailContainsKeywordsPredicate parseEmailKeyword(String emailKeywords) throws ParseException {
        checkKeywordsAreValid(emailKeywords);
        String trimmedEmailKeywords = emailKeywords.trim();
        String[] emailKeywordsArray = trimmedEmailKeywords.split("\\s+");
        return new EmailContainsKeywordsPredicate(Arrays.asList(emailKeywordsArray));
    }

    /**
     * Parses {@code String phoneKeyword} into a {@code PhoneContainsKeywordsPredicate}. Leading and trailing
     * whitespaces will be trimmed.
     * @param phoneKeyword keyword that is used to find employees with phone numbers that contain the keyword.
     * @throws ParseException if phoneKeyword is empty or if it contains '/' char.
     */
    public static PhoneContainsKeywordsPredicate parsePhoneKeyword(String phoneKeyword) throws ParseException {
        checkKeywordsAreValid(phoneKeyword);
        String trimmedPhoneKeyword = phoneKeyword.trim();
        return new PhoneContainsKeywordsPredicate(trimmedPhoneKeyword);
    }

    /**
     * Parses {@code String salaryKeyRange} into a {@code SalaryWithinRangePredicate}. Leading and trailing whitespaces
     * will be trimmed.
     * @param salaryKeyRange key range that is used to find employees whose salaries lies within the range.
     * @throws ParseException if salaryKeyRange is empty or if it contains '/' char or if it's not a valid salary range.
     */
    public static SalaryWithinRangePredicate parseSalaryKeyword(String salaryKeyRange) throws ParseException {
        checkKeywordsAreValid(salaryKeyRange);
        String trimmedSalaryKeyRange = salaryKeyRange.trim();
        if (!isValidFindSalaryRange(trimmedSalaryKeyRange)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        long lowerBound = findLowerBound(trimmedSalaryKeyRange);
        long upperBound = findUpperBound(trimmedSalaryKeyRange);
        return new SalaryWithinRangePredicate(lowerBound, upperBound);
    }

    private static long findLowerBound(String salaryArgs) {
        String lowerBoundArg = salaryArgs.split(" - ", 2)[0];
        return SalaryParserUtil.parseStringToLong(lowerBoundArg);
    }

    private static long findUpperBound(String salaryArgs) {
        String upperBoundArg = salaryArgs.split(" - ", 2)[1];
        return SalaryParserUtil.parseStringToLong(upperBoundArg);
    }

    /**
     * Checks whether {@code String salaryRange} is valid. Lower bound and upper bound must be non-negative integers.
     * If it is not in the format of [lower bound] - [upper bound] it will return false. If either the lower bound or
     * the upper bound is greater than the maximum allowed salary, it will also return false. If the lower bound is
     * greater than the upper bound it will also return false.
     * @param salaryRange key range that is used to find employees whose salary lies within the range.
     * @return true if {@code String salaryRange} is valid and false otherwise.
     */
    private static boolean isValidFindSalaryRange(String salaryRange) {
        String validationRegex = "^\\d+\\s-\\s\\d+$";
        if (!salaryRange.matches(validationRegex)) {
            return false;
        }
        String upperBound = salaryRange.split(" - ", 2)[1];
        String lowerBound = salaryRange.split(" - ", 2)[0];
        long upperBoundLong = SalaryParserUtil.parseStringToLong(upperBound);
        long lowerBoundLong = SalaryParserUtil.parseStringToLong(lowerBound);
        if (upperBoundLong > Salary.MAXIMUM_SALARY_LONG || lowerBoundLong > Salary.MAXIMUM_SALARY_LONG
                || lowerBoundLong > upperBoundLong) {
            return false;
        }
        return true;
    }

    /**
     * Checks that after trimming, {@code String keywords} is not empty and does not contain '/' character.
     * @throws ParseException if the given {@code keywords} is invalid.
     */
    private static void checkKeywordsAreValid(String keywords) throws ParseException {
        requireNonNull(keywords);

        String trimmedKeywords = keywords.trim();

        if (trimmedKeywords.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // Trimmed keywords should not contain '/' as it is reserved for use after a Prefix.
        if (trimmedKeywords.contains("/")) {
            throw new ParseException(FindCommand.INVALID_FIND_ARGS_MESSAGE);
        }
    }

}
