# 🏦 ParaBank QA Automation Project

Professional QA Automation Framework for the ParaBank Demo Banking Application.

---

# 📌 Overview

This project is a complete end-to-end QA Automation framework developed for the ParaBank banking web application.

The framework was designed using industry-standard automation practices including:

* Selenium WebDriver
* Java
* Maven
* TestNG
* Page Object Model (POM)

The project focuses on validating critical banking workflows, security validations, form validations, and full end-to-end transaction scenarios.

---

#  Tech Stack

* Java 17
* Selenium WebDriver 4
* Maven
* TestNG
* WebDriverManager
* Page Object Model (POM)

---

# 🧠 Automation Scope

The framework automates major banking functionalities including:

## Authentication

* User Registration
* Login / Logout
* Invalid Login Validation
* SQL Injection Validation

## Accounts

* Accounts Overview
* Open Checking Account
* Open Savings Account

## Banking Transactions

* Transfer Funds
* Bill Payment
* Request Loan
* Find Transactions

## Validation & Security

* Empty Form Validation
* Invalid Input Validation
* Long Input Validation
* Session Validation
* Unauthorized Access Validation

## Navigation & Contact

* Navigation Links
* Contact Form

## End-to-End Workflow

* Complete Banking Workflow Automation

---

# 📂 Project Structure

```text
parabank-qa-automation-project
│
├── docs
│   ├── SRS
│   ├── Test Plan
│   ├── RTM
│   ├── Test Cases
│   └── Bug Report
│
├── src/test/java
│
│   ├── base
│   ├── config
│   ├── pages
│   ├── tests
│   └── utils
│
├── test-output
├── screenshots
├── pom.xml
├── testng.xml
└── README.md
```

---

# ⚙️ Framework Features

* Professional POM Architecture
* Reusable Utility Classes
* Screenshot Capture on Failure
* Dynamic Test Data Generation
* Maven Execution Support
* Cross Browser Ready
* Centralized Configuration
* Organized TestNG Suite Execution

---

# 🧪 Test Execution Summary

| Metric                     | Value |
| -------------------------- | ----- |
| Total Automated Test Cases | 36    |
| Passed Tests               | 29    |
| Failed Tests               | 7     |
| Skipped Tests              | 0     |
| Reported Bugs              | 7     |

---

# 🐞 Reported Bugs

The automation process successfully identified multiple functional and security-related defects including:

* Session handling issue after logout
* Potential SQL injection vulnerability
* Bill payment workflow failure
* Loan request processing issue
* Transfer workflow instability
* Input validation weakness

Detailed bug reports are included in the project documentation.

---

# 📸 Screenshots & Reports

The framework automatically captures screenshots for failed test cases.

Generated artifacts include:

* Screenshots
* Maven Surefire Reports
* TestNG Reports
* Execution Logs

---

# ▶️ How To Run

## Clone Repository

```bash
git clone https://github.com/mohammedswesy/parabank-qa-automation-project.git
```

## Navigate to Project

```bash
cd parabank-qa-automation-project
```

## Run Automation Suite

```bash
mvn clean test
```

---

# 📖 Documentation

The project includes:

* Software Requirements Specification (SRS)
* Test Plan
* Test Cases
* RTM (Requirement Traceability Matrix)
* Bug Report

---

# 👨‍💻 Author

Mohammed AlSwesy

Software QA Automation Engineer

GitHub:
https://github.com/mohammedswesy
