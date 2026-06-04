# ParaBank Selenium POM Automation Project

Professional Eclipse/Maven/TestNG project based on the ParaBank manual test cases suite.

## Stack
Java 17, Selenium WebDriver 4, TestNG, Maven, WebDriverManager, Page Object Model.

## How to Run in Eclipse
1. Extract ZIP.
2. Eclipse > File > Import > Existing Maven Projects.
3. Select project folder.
4. Wait for Maven dependencies.
5. Right-click `testng.xml` > Run As > TestNG Suite.

## Terminal
```bash
mvn clean test
```

## Structure
- `base`: setup/teardown and screenshot on failure.
- `pages`: Page Object Model files.
- `tests`: separated test classes based on modules.
- `utils`: driver, data, screenshots.
- `config`: properties reader.

Notes: Chrome notifications/popups are disabled. ParaBank is a public demo app, so some data/reset behavior can affect execution.
