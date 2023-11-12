---
layout: page
title: Leong Yuan Kun's Project Portfolio Page
---

### Project: EmployeeManager

EmployeeManager is a desktop application for Human Resources (HR) staff to manage employees in a small or 
medium-sized enterprise (SME).

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=aexolate&breakdown=true)
* **New features implemented**:
  * Added `sort` command to sort the displayed persons list based on any of their attribute.
  * Added `export` command to allow the data file to be exported to another location on the disk.
  * Added `import` command to allow a data file to be imported into the application.
  * Added GUI option for importing and exporting of files.
* **Enhancements to existing features**:
  * Added `Id` as a field to the `Person` class.
  * Extended `Model` to support filtered and sorted person list for `find` and `sort` commands. Originally, 
    AB3 only has a `FilteredList<Person>`. To support sorting as well, another `SortedList<Person>` is used.
  * Modified `FindCommand` to support searching by any attribute instead of just name.
* **Documentation**:
  * User Guide:
    * `sort` command
    * `export` command
    * `import` command
    * Prefix Summary
  * Developer Guide:
    * Sort feature
    * Use cases for `import` and `export`
    * Instructions for manual testing for `import` and `export`
* **Contributions to team-based tasks**:
  * Set up GitHub team organisation and repository.
  * Incorporated artifact building in the project workflow to streamline testing when reviewing pull requests.

