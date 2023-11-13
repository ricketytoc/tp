package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.nio.file.Path;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import seedu.address.commons.core.increment.Increment;
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
import seedu.address.model.person.SalaryWithinRangePredicate;

public class ParserUtilTest {
    private static final String INVALID_ID = "#000000";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_DEPARTMENT = "^Finance";
    private static final String INVALID_ROLE = "M@nager";
    private static final String INVALID_SALARY = "-5000";
    private static final String INVALID_INCREMENT = "^2";
    private static final String INVALID_HISTORY = "a";

    private static final String INVALID_FIND_KEYWORD = "  ";

    private static final String INVALID_SALARY_RANGE = "4000 - 3000";

    private static final String VALID_ID = "A000001";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_DEPARTMENT = "Finance";
    private static final String VALID_ROLE = "Manager";
    private static final String VALID_SALARY = "5000";
    private static final String VALID_INCREMENT = "1000";
    private static final String VALID_HISTORY = "5";
    private static final String VALID_SALARY_RANGE = "4000 - 5000";
    private static final long VALID_LOWER_BOUND = 400000;
    private static final long VALID_UPPER_BOUND = 500000;
    private static final String VALID_FIND_KEYWORD = "anything";

    private static final String VALID_PATH = "./data/persons.json";
    private static final String INVALID_PATH = "./data/per" + '\0' + "sons.json";
    private static final String INVALID_WINDOWS_PATH = "./data/per*sons.json";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parsePath_validPath_returnsPath() throws Exception {
        Path expectedPath = Path.of(VALID_PATH);
        assertEquals(expectedPath, ParserUtil.parsePath(VALID_PATH));
    }

    @Test
    public void parsePath_invalidPath_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePath(INVALID_PATH));
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    public void parsePath_invalidPathWindows_throwsParseException() {
        // '*' is not allowed in file path for Windows, but allowed in Linux and Mac
        assertThrows(ParseException.class, () -> ParserUtil.parsePath(INVALID_WINDOWS_PATH));
    }

    // Linux & Mac has almost no restrictions, so no other invalid tests are created

    @Test
    public void parseId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseId((String) null));
    }

    @Test
    public void parseId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseId(INVALID_ID));
    }

    @Test
    public void parseId_validValueWithoutWhitespace_returnsId() throws Exception {
        Id expectedId = new Id(VALID_ID);
        assertEquals(expectedId, ParserUtil.parseId(VALID_ID));
    }

    @Test
    public void parseId_validValueWithWhitespace_returnsTrimmedId() throws Exception {
        String idWithWhitespace = WHITESPACE + VALID_ID + WHITESPACE;
        Id expectedId = new Id(VALID_ID);
        assertEquals(expectedId, ParserUtil.parseId(idWithWhitespace));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseDepartment_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDepartment((String) null));
    }

    @Test
    public void parseDepartment_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDepartment(INVALID_DEPARTMENT));
    }

    @Test
    public void parseDepartment_validValueWithoutWhitespace_returnsDepartment() throws Exception {
        Department expectedDepartment = new Department(VALID_DEPARTMENT);
        assertEquals(expectedDepartment, ParserUtil.parseDepartment(VALID_DEPARTMENT));
    }

    @Test
    public void parseDepartment_validValueWithWhitespace_returnsTrimmedDepartment() throws Exception {
        String departmentWithWhitespace = WHITESPACE + VALID_DEPARTMENT + WHITESPACE;
        Department expectedDepartment = new Department(VALID_DEPARTMENT);
        assertEquals(expectedDepartment, ParserUtil.parseDepartment(departmentWithWhitespace));
    }

    @Test
    public void parseRole_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRole((String) null));
    }

    @Test
    public void parseRole_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRole(INVALID_ROLE));
    }

    @Test
    public void parseRole_validValueWithoutWhitespace_returnsRole() throws Exception {
        Role expectedRole = new Role(VALID_ROLE);
        assertEquals(expectedRole, ParserUtil.parseRole(VALID_ROLE));
    }

    @Test
    public void parseRole_validValueWithWhitespace_returnsTrimmedRole() throws Exception {
        String roleWithWhitespace = WHITESPACE + VALID_ROLE + WHITESPACE;
        Role expectedRole = new Role(VALID_ROLE);
        assertEquals(expectedRole, ParserUtil.parseRole(roleWithWhitespace));
    }

    @Test
    public void parseSalary_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSalary((String) null));
    }

    @Test
    public void parseSalary_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSalary(INVALID_SALARY));
    }

    @Test
    public void parseSalary_validValueWithoutWhitespace_returnsSalary() throws Exception {
        Salary expectedSalary = new Salary(VALID_SALARY);
        assertEquals(expectedSalary, ParserUtil.parseSalary(VALID_SALARY));
    }

    @Test
    public void parseSalary_validValueWithWhitespace_returnsTrimmedSalary() throws Exception {
        String salaryWithWhitespace = WHITESPACE + VALID_SALARY + WHITESPACE;
        Salary expectedSalary = new Salary(VALID_SALARY);
        assertEquals(expectedSalary, ParserUtil.parseSalary(salaryWithWhitespace));
    }

    @Test
    public void parseIncrement_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIncrement((String) null));
    }

    @Test
    public void parseIncrement_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIncrement(INVALID_INCREMENT));
    }

    @Test
    public void parseIncrement_validValueWithoutWhitespace_returnsIncrement() throws Exception {
        Increment expectedIncrement = new Increment(VALID_INCREMENT);
        assertEquals(expectedIncrement, ParserUtil.parseIncrement(VALID_INCREMENT));
    }

    @Test
    public void parseIncrement_validValueWithWhitespace_returnsTrimmedIncrement() throws Exception {
        String incrementWithWhitespace = WHITESPACE + VALID_INCREMENT + WHITESPACE;
        Increment expectedIncrement = new Increment(VALID_INCREMENT);
        assertEquals(expectedIncrement, ParserUtil.parseIncrement(incrementWithWhitespace));
    }

    @Test
    public void parseHistory_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseHistory((String) null));
    }

    @Test
    public void parseHistory_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseHistory(INVALID_HISTORY));
        assertThrows(ParseException.class, () -> ParserUtil.parseHistory("10 a"));
    }

    @Test
    public void parseHistory_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, HistoryCommand.MESSAGE_INVALID_N, ()
                -> ParserUtil.parseHistory(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseHistory_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(Integer.parseInt(VALID_HISTORY), ParserUtil.parseHistory(VALID_HISTORY));

        // Leading and trailing whitespaces
        assertEquals(1, ParserUtil.parseHistory("  1  "));
    }

    @Test
    public void parseIdKeyword_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIdKeyword((String) null));
    }

    @Test
    public void parseIdKeyword_validValueWithoutWhitespace_returnsIdContainsKeywordsPredicate() throws Exception {
        IdContainsKeywordsPredicate expectedPredicate = new IdContainsKeywordsPredicate(VALID_FIND_KEYWORD);
        assertEquals(expectedPredicate, ParserUtil.parseIdKeyword(VALID_FIND_KEYWORD));
    }

    @Test
    public void parseIdKeyword_validValueWithWhitespace_returnsIdContainsKeywordsPredicate() throws Exception {
        IdContainsKeywordsPredicate expectedPredicate = new IdContainsKeywordsPredicate(VALID_FIND_KEYWORD);
        String validKeywordWithWhitespaces = WHITESPACE + VALID_FIND_KEYWORD + WHITESPACE;
        assertEquals(expectedPredicate, ParserUtil.parseIdKeyword(validKeywordWithWhitespaces));
    }

    @Test
    public void parseIdKeyword_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIdKeyword(INVALID_FIND_KEYWORD));
    }

    @Test
    public void parseNameKeyword_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNameKeyword((String) null));
    }

    @Test
    public void parseNameKeyword_validValueWithoutWhitespace_returnsNameContainsKeywordsPredicate() throws Exception {
        NameContainsKeywordsPredicate expectedPredicate = new NameContainsKeywordsPredicate(Collections
                .singletonList(VALID_FIND_KEYWORD));
        assertEquals(expectedPredicate, ParserUtil.parseNameKeyword(VALID_FIND_KEYWORD));
    }

    @Test
    public void parseNameKeyword_validValueWithWhitespace_returnsNameContainsKeywordsPredicate() throws Exception {
        NameContainsKeywordsPredicate expectedPredicate = new NameContainsKeywordsPredicate(Collections
                .singletonList(VALID_FIND_KEYWORD));
        String validKeywordWithWhitespaces = WHITESPACE + VALID_FIND_KEYWORD + WHITESPACE;
        assertEquals(expectedPredicate, ParserUtil.parseNameKeyword(validKeywordWithWhitespaces));
    }

    @Test
    public void parseNameKeyword_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNameKeyword(INVALID_FIND_KEYWORD));
    }

    @Test
    public void parseRoleKeyword_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRoleKeyword((String) null));
    }

    @Test
    public void parseRoleKeyword_validValueWithoutWhitespace_returnsRoleContainsKeywordsPredicate() throws Exception {
        RoleContainsKeywordsPredicate expectedPredicate = new RoleContainsKeywordsPredicate(Collections
                .singletonList(VALID_FIND_KEYWORD));
        assertEquals(expectedPredicate, ParserUtil.parseRoleKeyword(VALID_FIND_KEYWORD));
    }

    @Test
    public void parseRoleKeyword_validValueWithWhitespace_returnsRoleContainsKeywordsPredicate() throws Exception {
        RoleContainsKeywordsPredicate expectedPredicate = new RoleContainsKeywordsPredicate(Collections
                .singletonList(VALID_FIND_KEYWORD));
        String validKeywordWithWhitespaces = WHITESPACE + VALID_FIND_KEYWORD + WHITESPACE;
        assertEquals(expectedPredicate, ParserUtil.parseRoleKeyword(validKeywordWithWhitespaces));
    }

    @Test
    public void parseRoleKeyword_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRoleKeyword(INVALID_FIND_KEYWORD));
    }

    @Test
    public void parseDepartmentKeyword_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDepartmentKeyword((String) null));
    }

    @Test
    public void parseDepartmentKeyword_validValueWithoutWhitespace_returnsDepartmentContainsKeywordsPredicate()
            throws Exception {
        DepartmentContainsKeywordsPredicate expectedPredicate = new DepartmentContainsKeywordsPredicate(Collections
                .singletonList(VALID_FIND_KEYWORD));
        assertEquals(expectedPredicate, ParserUtil.parseDepartmentKeyword(VALID_FIND_KEYWORD));
    }

    @Test
    public void parseDepartmentKeyword_validValueWithWhitespace_returnsDepartmentContainsKeywordsPredicate()
            throws Exception {
        DepartmentContainsKeywordsPredicate expectedPredicate = new DepartmentContainsKeywordsPredicate(Collections
                .singletonList(VALID_FIND_KEYWORD));
        String validKeywordWithWhitespaces = WHITESPACE + VALID_FIND_KEYWORD + WHITESPACE;
        assertEquals(expectedPredicate, ParserUtil.parseDepartmentKeyword(validKeywordWithWhitespaces));
    }

    @Test
    public void parseDepartmentKeyword_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDepartmentKeyword(INVALID_FIND_KEYWORD));
    }

    @Test
    public void parseEmailKeyword_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmailKeyword((String) null));
    }

    @Test
    public void parseEmailKeyword_validValueWithoutWhitespace_returnsEmailContainsKeywordsPredicate() throws Exception {
        EmailContainsKeywordsPredicate expectedPredicate = new EmailContainsKeywordsPredicate(Collections
                .singletonList(VALID_FIND_KEYWORD));
        assertEquals(expectedPredicate, ParserUtil.parseEmailKeyword(VALID_FIND_KEYWORD));
    }

    @Test
    public void parseEmailKeyword_validValueWithWhitespace_returnsEmailContainsKeywordsPredicate() throws Exception {
        EmailContainsKeywordsPredicate expectedPredicate = new EmailContainsKeywordsPredicate(Collections
                .singletonList(VALID_FIND_KEYWORD));
        String validKeywordWithWhitespaces = WHITESPACE + VALID_FIND_KEYWORD + WHITESPACE;
        assertEquals(expectedPredicate, ParserUtil.parseEmailKeyword(validKeywordWithWhitespaces));
    }

    @Test
    public void parseEmailKeyword_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmailKeyword(INVALID_FIND_KEYWORD));
    }

    @Test
    public void parsePhoneKeyword_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhoneKeyword((String) null));
    }

    @Test
    public void parsePhoneKeyword_validValueWithoutWhitespace_returnsPhoneContainsKeywordsPredicate() throws Exception {
        PhoneContainsKeywordsPredicate expectedPredicate = new PhoneContainsKeywordsPredicate(VALID_FIND_KEYWORD);
        assertEquals(expectedPredicate, ParserUtil.parsePhoneKeyword(VALID_FIND_KEYWORD));
    }

    @Test
    public void parsePhoneKeyword_validValueWithWhitespace_returnsPhoneContainsKeywordsPredicate() throws Exception {
        PhoneContainsKeywordsPredicate expectedPredicate = new PhoneContainsKeywordsPredicate(VALID_FIND_KEYWORD);
        String validKeywordWithWhitespaces = WHITESPACE + VALID_FIND_KEYWORD + WHITESPACE;
        assertEquals(expectedPredicate, ParserUtil.parsePhoneKeyword(validKeywordWithWhitespaces));
    }

    @Test
    public void parsePhoneKeyword_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhoneKeyword(INVALID_FIND_KEYWORD));
    }

    @Test
    public void parseSalaryKeyword_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSalaryKeyword((String) null));
    }

    @Test
    public void parseSalaryKeyword_validValueWithoutWhitespace_returnsSalaryWithinRangePredicate() throws Exception {
        SalaryWithinRangePredicate expectedPredicate = new SalaryWithinRangePredicate(VALID_LOWER_BOUND,
                VALID_UPPER_BOUND);
        assertEquals(expectedPredicate, ParserUtil.parseSalaryKeyword(VALID_SALARY_RANGE));
    }

    @Test
    public void parseSalaryKeyword_validValueWithWhitespace_returnsSalaryWithinRangePredicate() throws Exception {
        SalaryWithinRangePredicate expectedPredicate = new SalaryWithinRangePredicate(VALID_LOWER_BOUND,
                VALID_UPPER_BOUND);
        String validKeywordWithWhitespaces = WHITESPACE + VALID_SALARY_RANGE + WHITESPACE;
        assertEquals(expectedPredicate, ParserUtil.parseSalaryKeyword(validKeywordWithWhitespaces));
    }

    @Test
    public void parseSalaryKeyword_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSalaryKeyword(INVALID_SALARY_RANGE));
    }

}
