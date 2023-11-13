---
layout: page
title: Richard Hew's Project Portfolio Page
---

### Project: EmployeeManager

EmployeeManager is a desktop application used by Human Resource (HR) staff in a small or medium-sized company (of 50 - 200 employees).
EmployeeManager has HR management features to provide HR staff a convenient way to access and update employee details.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=ricketytoc&breakdown=true)

* **Enhancements implemented**:
    * **New Feature**: Extended upon the Find feature so that users can find employees by all of their attributes instead of only by their names. 
    * What it does: This feature allows the user to find employees based on multiple attributes in one find command. 
    * Justification: This extension allows the user to find a more specific group of employees that they are interested in. This is useful when used in conjunction with the bulk features such as the Clear and Increment feature as it allows the user to narrow down the employees to the ones he is interested in and then execute a bulk command on all the found employees.
    * Highlights: This extension requires the user to enter the Find command in a specific format which differs from attribute to attribute. For example the parameter structure for finding an employee by name differs from that for finding by salary. It was challenging to decide what would be the best way to search for different attributes and it was also difficult to make the user guide clear so that users are clear about the nuances in the formatting.

* **Documentation**:
    * User Guide:
        * Add the glossary table to explain CLI and GUI.
        * Added documentation for features: `find`, `add` and `redo`.
    * Developer Guide:
        * Add documentation and sequence diagram for `find` command.
        * Add a use case for finding an employee.
        * Add some user stories.
        * Add manual testing for `find`.

* **Contributions to team-based tasks**:
    * Set up Github Pages for the team's project website.
    * Gave my insights during team meetings.
    * Tested EmployeeManager and communicated found bugs to teammates.

* **Review/mentoring contributions**:
    * Approved and reviewed 19 PRs from teammates.

* **Contributions beyond the project team**:
    * Reported bugs and suggestions for other team:
      e.g. [#146](https://github.com/AY2324S1-CS2103T-T12-3/tp/issues/146),
      [#150](https://github.com/AY2324S1-CS2103T-T12-3/tp/issues/150),
      [#160](https://github.com/AY2324S1-CS2103T-T12-3/tp/issues/160)
