---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of employees
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: The application streamlines the process of handling 
large-scale employee data. With rapid typing capabilities, users
can efficiently input and update employee details, reducing time
spent on data entry tasks.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                             | I want to …​                                                                                              | So that I can…​                                                                         |
|----------|-------------------------------------|-----------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------|
| `*`      | potential user exploring the app    | see the app populated with sample data                                                                    | easily see how the app will look when it is in use                                      |
| `* *`    | new user                            | always find solutions from the User Guide                                                                 | use this app easily when I encounter difficulties                                       |
| `* *`    | user paranoid about losing progress | have the progress automatically saved                                                                     | be reassured that I will not lose any progress when my computer shuts down unexpectedly |
| `* *`    | new user                            | purge all current data                                                                                    | get rid of sample/experimental data I used for exploring the app                        |
| `* *`    | new user                            | have the application provide a user-friendly tutorial                                                     | understand its features                                                                 |
| `* * *`  | user ready to start using the app   | add existing employees in the company to the application                                                  | keep track of the employee details                                                      |
| `*`      | meticulous user                     | let the app accurately compare and point out differences between employees                                | not have to manually check each employee one by one                                     |
| `* *`    | careless user                       | edit employee details easily that I have keyed in wrongly                                                 | change erroneous data                                                                   |
| `* * *`  | HR staff member                     | remove employees who have left the company                                                                | ensure their data does not clutter the application                                      |
| `*`      | HR staff member                     | search for employees using their names                                                                    | find the employee that I am looking for                                                 |
| `*`      | HR staff member                     | filter employees based on their department, salary, etc.                                                  | narrow down my search to a target group of employees                                    |
| `*`      | HR staff member                     | find employees based on their departments                                                                 | obtain the contact information of the employees in a department                         |
| `*`      | HR staff member                     | find employees based on their roles                                                                       | easily increment the pay of all the staff with that role efficiently                    |
| `*`      | HR staff member                     | view who I have looked at recently when I click the search bar                                            | easily fetch employees that I have analyzed recently and make my work more convenient   |
| `* * *`  | HR staff member                     | easily access and update employee information, including roles, salaries, departments and contact details | ensure that employee records are accurate                                               |
| `*`      | HR staff member                     | generate reports                                                                                          | so that the upper management can have better insights into their employees.             |
| `* *`    | Expert user                         | combine functions together                                                                                | I can save time on commonly performed tasks                                             |
| `* *`    | Expert user                         | bind keyboard shortcuts for certain frequently used functions                                             | I can work more efficiently                                                             |
| `* * *`  | Advanced user                       | order my search results by various indicators (such as name and department)                               | view a list of employees in my desired manner.                                          |
| `* * *`  | long-time user                      | remove unused data                                                                                        | I am not distracted by irrelevant data.                                                 |
| `* *`    | HR staff member                     | know the history of changes, including who and when the change was made                                   | I can use them for auditing purposes.                                                   |
| `* *`    | HR staff member                     | add notes to employee’s profiles                                                                          | I can document important information and interactions                                   |
| `*`      | advanced user                       | edit the saved files manually without using the application                                               | it’s more convenient to update and organise new information                             |
| `*`      | user with poor eyesight             | view the information easily                                                                               | it is not difficult for me to use the application.                                      |
| `*`      | HR staff member                     | collect and analyze diversity metrics                                                                     | I can promote a more diverse workplace                                                  |
| `* *`    | HR staff member                     | export and import employee details                                                                        | I can create backups and recover from data corruption                                   |
| `*`      | HR staff member                     | set performance goals for employees                                                                       | I’m able to align them with the company’s objectives.                                   |
| `*`      | HR staff member                     | use the application to create analytics that will give me insights into the employee's performances       | I can give proper recognition to employees with good performances                       |
| `* *`    | busy HR staff member                | use the application with a minimal number of inputs                                                       | I can be more efficient with my work.                                                   |
| `* *`    | careless HR staff member            | reverse the history                                                                                       | if there are any mistakes I can easily revert to a copy with no mistakes                |

### Use cases

(For all use cases below, the **System** is the `EmployeeManager` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC1 - View all employees**

**MSS**

1. User requests to list all employees.
1. EmployeeManager shows a list of employees.
1. Use case ends.

**Use case: UC2 - Add a new employee**

**MSS**

1. User requests to add a new employee with employee details.
1. EmployeeManager adds the employee.
1. EmployeeManager shows an updated list of employees.
1. Use case ends.

**Extensions**

* 1a. EmployeeManager detects that not all required information is entered.
  * 1a1. EmployeeManager informs user that some information is missing.
  * 1a2. EmployeeManager requests user to enter details of the employee.
  * 1a3. User enters the requested details.
  * Steps 1a1 - 1a3 are repeated until the data entered are correct.
  * Use case resumes from step 2.
* 1b. EmployeeManager detects invalid information.
  * 1b1. EmployeeManager informs user that some information is invalid.
  * 1b2. EmployeeManager requests user to enter details of the employee.
  * 1b3. User enters the requested details.
  * Steps 1b1 - 1b3 are repeated until the data entered are correct.
  * Use case resumes from step 2.
* 1c. EmployeeManager detects a duplicate employee.
  * 1c1. EmployeeManager informs user that the employee already exists.
  * Use case ends.
* 1d. EmployeeManager detects that there are no employee IDs available.
  * 1d1. EmployeeManager informs user that there are no employee IDs available.
  * Use case ends.

**Use case: UC3 - Delete an employee**

**MSS**

1. User requests to delete an employee with an employee ID.
1. EmployeeManager deletes the employee.
1. EmployeeManager shows an updated list of employees.
1. Use case ends.

**Extensions**

* 1a. The given employee ID is invalid.
  * 1a1. EmployeeManager informs user that the employee ID is invalid.
  * 1a2. User enters new employee ID.
  * Steps 1a1-1a2 are repeated until the employee ID entered is valid.
  * Use case resumes from step 2.

### Non-Functional Requirements

1.  **Cross-platform Capability**: Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  **Storage Capacity**: Should be able to store up to `100` persons without a noticeable sluggishness in performance for typical usage.
3.  **UI Responsiveness**: The UI should respond to user interaction. 
4.  **Error Handling**: Should gracefully handle input errors without system crash and data loss.
5.  **Code Documentation**: Code should be well-documented to facilitate maintenance and updates.
6.  **Code Version Control**: The software codebase should be managed using _Git Milestone_ to track changes.
7.  **Keyboard Efficiency**: A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **UI**: User Interface, the graphical or textual interface through which a user interacts with a software application.
--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
