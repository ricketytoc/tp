---
layout: page
title: Goh Li Ting's Project Portfolio Page
---

### Project: EmployeeManager

EmployeeManager is a desktop application used by Human Resource (HR) staff in a small or medium-sized company (of 50 - 200 employees).
EmployeeManager has HR management features to provide HR staff a convenient way to access and update employee details.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=goh-li-ting&breakdown=true)

* **Enhancements implemented**:
  * **New Feature**: Added the ability to increment the salaries of all employees in the displayed list.
    * What it does: This feature allows the user to increment the salaries of all employees in the displayed list by the same amount at once.
    * Justification: This feature helps the user to save time as they do not have to repeat similar edit commands for multiple employees when incrementing their salaries by the same amount.
    This feature can be used together with the `find` command to increment salaries of a specific group of employees in the company.
    * Highlights: This enhancement ensures that all salaries after incrementation are valid before the salaries are incremented and saved.
    The implementation was challenging due to the limitations of Java's `long` and `double` data types.
    The `double` data type could not handle proper addition of the salary and the increment due to precision issues while
    the `long` data type has a maximum value that could not handle users' large inputs.
  * _Coming soon_

* **Documentation**:
  * User Guide:
    * Wrote the introduction
    * Enhanced quick start to be more user-friendly
    * Added section on how to use command line interface
    * Added documentation for the features: `increment` and `undo`
  * Developer Guide:
    * Added documentation and activity diagram for `add`
    * Added documentation and sequence diagram for `increment`
    * Added use cases for `view`, `add`, `delete` and `increment`
    * Added manual test cases for `increment`, `undo` and `redo`

* **Contributions to team-based tasks**:
  * To be added soon

* **Review/mentoring contributions**:
  * To be added soon

* **Contributions beyond the project team**:
  * To be added soon
