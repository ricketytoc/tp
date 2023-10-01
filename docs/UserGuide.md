---
layout: page
title: User Guide
---

Welcome to **EmployeeManager**! Do you need to manage employees? Are you having trouble using spreadsheets? Or does your
organisation not have a proper employee management system? EmployeeManager is the application for you!

EmployeeManager is a **desktop application** for **Human Resources (HR) staff** to **manage employees in a relatively
large organisation (about 50 - 200 employees)**.

Here’s an **overview** of EmployeeManager’s features:
* Consolidate and manage employee details in a single application 
* Conveniently view, add, or delete employee details

EmployeeManager is **optimised for use via a Command Line Interface** (CLI) while still having the benefits of a
Graphical User Interface (GUI). If you can type fast, EmployeeManager can get your employee management tasks done more
quickly than traditional GUI apps, so HR staff can be more efficient.

This User Guide aims to help both new and experienced users use EmployeeManager. Learn to leverage the features of 
EmployeeManager to make managing employees easier!

If you are a **new user**:
* Get started with EmployeeManager by going through [Quick start](#quick-start) and [Navigating the user guide](#navigating-the-user-guide)
* If you have no prior experience with CLI or require a refresher, refer to this guide *(coming soon)*

If you are an **experienced user**:
* Refer to the [Table of contents](#table-of-contents) to jump to the section that you are looking for

--------------------------------------------------------------------------------------------------------------------

## Table of contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Navigating the user guide
*Coming soon*

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed on your Computer.

1. Download the latest `employeemanager.jar` from [here](https://github.com/AY2324S1-CS2103T-T14-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your EmployeeManager.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar employeemanager.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all employees.

   * `add id/A035B name/John Doe position/Supervisor department/Marketing salary/5000` : Adds a contact named `John Doe` to EmployeeManager.

   * `delete id/A035B` : Deletes the employee with id `A035B`.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an employee: `add`

Adds employees into the EmployeeManager application. 
Employee ID can either be specified or not. If specified, the application will check the existing list of employees 
to check whether the entered ID is unique. If not unique it will show an error. If no Employer ID is specified then the 
application will automatically assign a unique ID to the employee.

Format: `add [id/EMPLOYEE_ID] name/NAME position/POSITION department/DEPARTMENT salary/SALARY`

Examples:
* `add id/A035B name/Richard Hew Jia Liang position/Supervisor department/Marketing salary/5000`
* `add name/Richard Hew Jia Liang position/Supervisor department/Marketing salary/5000`

Constraints:
* Employee ID must be unique if specified and up to 10 characters
* Salary must be numerical and >= 0
* Name, Position, and Department up to 100 characters each

Expected output when employee added successfully:
* `Employee EMPLOYEE_ID NAME successfully added!`

Expected output when add command fails:
* Invalid Salary : `Please input a non-negative salary as a numerical value (e.g. 4000)`
* Duplicate employee ID : `EMPLOYEE_ID already exists`
* Unable to assign employee ID : `There is no employee ID available to be assigned. Please delete unused employees 
to free up employee IDs`
* Too long name/position/department : `Please keep the <NAME/POSITION/DEPARTMENT up to 100 characters.`
* Missing inputs : `<Inputs that are missing> are missing from the command. Please input the add command in the format:
add [id/EMPLOYEE_ID] name/NAME position/POSITION department/DEPARTMENT salary/SALARY`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                      |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**  | `clear`                                                                                                                                                               |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **List**   | `list`                                                                                                                                                                |
| **Help**   | `help`                                                                                                                                                                |
