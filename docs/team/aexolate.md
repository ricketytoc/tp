---
layout: page
title: Leong Yuan Kun's Project Portfolio Page
---

### Project: EmployeeManager

EmployeeManager is a desktop application used by Human Resource (HR) staff in a small or medium-sized company (of 50 - 200 employees).
EmployeeManager has HR management features to provide HR staff a convenient way to access and update employee details.


Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=aexolate&breakdown=true)
* **New features implemented**:
  * Added `sort` command to sort the displayed persons list based on any of their attribute.
  * Added `export` command to allow the data file to be exported to another location on the disk.
  * Added `import` command to allow a data file to be imported into the application.
  * Added GUI option for importing and exporting of files.
  * The `export` and `import` features were challenging to implement as they were OS-dependent due to the
    different file systems. Handling the file path was one of the challenge as they have different restrictions and
    format. There were also many scenarios where the commands might fail. For example, the files and folders might not
    have the proper R/W permissions, certain folders might be protected etc.
* **Enhancements to existing features**:
  * Added `Id` as a field to the `Person` class.
  * Extended `Model` to support filtered and sorted person list for `find` and `sort` commands. Originally,
    AB3 only has a `FilteredList<Person>`. To support sorting as well, another `SortedList<Person>` is used.
  * Modified `FindCommand` to support searching by any attribute instead of just name.
* **Documentation**:
  * User Guide:
    * Added documentation for features: `sort`, `import`, `export`.
    * Added Prefix summary section.
    * Added Specifying data file path section.
  * Developer Guide:
    * Added implementation details for `sort`, `import`, and `export`.
    * Updated [Model](https://ay2324s1-cs2103t-t14-1.github.io/tp/DeveloperGuide.html#model-component) diagram and description.
    * Added [SortSequenceDiagram](https://ay2324s1-cs2103t-t14-1.github.io/tp/DeveloperGuide.html#sort-feature)
      and [FileCommandClassDiagram](https://ay2324s1-cs2103t-t14-1.github.io/tp/DeveloperGuide.html#exportimport-feature)
    * Updated target user profile & value proposition.
    * Added use cases for `sort`, `import` and `export`.
    * Added instructions for manual testing for `import` and `export`.
    * Added some planned enhancements.
* **Contributions to team-based tasks**:
  * Set up GitHub team organisation and repository.
  * Incorporated artifact building in the project workflow to streamline testing when reviewing pull requests.
  * Released v1.2 JAR File.
* **Review/mentoring contributions**:
  * 56 pull requests reviewed:
    e.g. [#251](https://github.com/AY2324S1-CS2103T-T14-1/tp/pull/251),
    [#155](https://github.com/AY2324S1-CS2103T-T14-1/tp/pull/155),
    [#107](https://github.com/AY2324S1-CS2103T-T14-1/tp/pull/107)
  * Helped to test new features and check for code quality issues.
* **Contributions beyond the project team**:
  * Reported bugs and suggestions for other team:
    e.g. [#193](https://github.com/AY2324S1-CS2103T-T17-4/tp/issues/193),
    [#91](https://github.com/AY2324S1-CS2103-F13-4/tp/issues/91),
    [#90](https://github.com/AY2324S1-CS2103-F13-4/tp/issues/90)
