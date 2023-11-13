---
layout: page
title: Weiennn's Project Portfolio Page
---

### Project: Employee Manager

EmployeeManager is a desktop application used by Human Resource (HR) staff in a small or medium-sized company (of 50 - 200 employees).
EmployeeManager has HR management features to provide HR staff a convenient way to access and update employee details.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=weiennn&breakdown=true)

* **Enhancements implemented**:
  * Improved `clear` Command
    * `clear` command has been improved to only clear the currently displayed list, instead of the whole list.
  * `history` Command
    * Added `history` command to allow users to view their previously entered valid commands.
  * Improved `help` Command
    * Updated the UI to make it match the style of EmployeeManager.
    * Added a command summary so users can easily reference how to use the different commands.
  * Updated `person` model
    * `person` model now contains an additional `department` field.
    
* **Documentation**:
    * User Guide:
      * Added the `history` command section.
      * Updated the `clear` command section.
      * Updated the `help` command section.
    * Developer Guide:
      * Updated the links to our repo GitHub page. 
      * Added the `history` command section and the `history` class diagram.
      * Updated the `clear` command section and the `clear` command sequence diagram.
      * Updated the `help` command section.
      * Added multiple Use Cases.
