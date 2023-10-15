package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEPARTMENT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DEPARTMENT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEPARTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SALARY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SALARY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SALARY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.Salary;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 x/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_ID_DESC, Id.MESSAGE_CONSTRAINTS); // invalid ID
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_DEPARTMENT_DESC, Department.MESSAGE_CONSTRAINTS); // invalid department
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC, Role.MESSAGE_CONSTRAINTS); // invalid role
        assertParseFailure(parser, "1" + INVALID_SALARY_DESC, Salary.MESSAGE_CONSTRAINTS); // invalid salary

        // invalid id followed by valid name
        assertParseFailure(parser, "1" + INVALID_ID_DESC + NAME_DESC_AMY, Id.MESSAGE_CONSTRAINTS);

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // invalid department followed by valid email
        assertParseFailure(parser, "1" + INVALID_DEPARTMENT_DESC + EMAIL_DESC_AMY, Department.MESSAGE_CONSTRAINTS);

        // invalid role followed by valid email
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC + EMAIL_DESC_AMY, Role.MESSAGE_CONSTRAINTS);

        // invalid salary followed by valid email
        assertParseFailure(parser, "1" + INVALID_SALARY_DESC + EMAIL_DESC_AMY, Salary.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_AMY
                + VALID_DEPARTMENT_AMY + INVALID_ROLE_DESC + VALID_SALARY_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY
                + ID_DESC_AMY + NAME_DESC_AMY + DEPARTMENT_DESC_AMY + ROLE_DESC_AMY + SALARY_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withId(VALID_ID_AMY)
                .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY)
                .withDepartment(VALID_DEPARTMENT_AMY).withRole(VALID_ROLE_AMY).withSalary(VALID_SALARY_AMY)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // ID
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + ID_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withId(VALID_ID_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // name
        targetIndex = INDEX_THIRD_PERSON;
        userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // department
        userInput = targetIndex.getOneBased() + DEPARTMENT_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withDepartment(VALID_DEPARTMENT_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // role
        userInput = targetIndex.getOneBased() + ROLE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withRole(VALID_ROLE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // salary
        userInput = targetIndex.getOneBased() + SALARY_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withSalary(VALID_SALARY_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // invalid followed by valid
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // valid followed by invalid
        userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple valid fields repeated
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + EMAIL_DESC_AMY + DEPARTMENT_DESC_AMY + SALARY_DESC_AMY
                + ROLE_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ID_DESC_AMY
                + DEPARTMENT_DESC_BOB + ROLE_DESC_BOB + SALARY_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ID_DESC_AMY;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DEPARTMENT,
                        PREFIX_ROLE, PREFIX_SALARY));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + INVALID_ID_DESC + INVALID_EMAIL_DESC
                + INVALID_DEPARTMENT_DESC + INVALID_ID_DESC
                + INVALID_ROLE_DESC + INVALID_SALARY_DESC + INVALID_PHONE_DESC + INVALID_EMAIL_DESC
                + INVALID_DEPARTMENT_DESC + INVALID_ROLE_DESC + INVALID_SALARY_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DEPARTMENT,
                        PREFIX_ROLE, PREFIX_SALARY));
    }
}
