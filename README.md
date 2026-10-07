# WholeCart Marketplace – QA Automation Framework

## Overview

This repository contains a scalable **QA Automation Framework** developed for testing the **WholeCart Marketplace** application.

The framework supports:

- UI Automation
- API Automation
- Page Object Model (POM)
- JSON-driven locators and configuration
- Reusable common actions
- Centralized API authentication and token handling
- TestNG-based test execution
- Gradle build and dependency management
- Automated screenshots
- Extent HTML reporting
- Selenium action logging
- Single-command execution for UI and API tests

The framework is designed with clear separation of responsibilities to improve **maintainability, reusability, scalability, and readability**.

---

# Application Under Test

**Application:** WholeCart Marketplace  
**Application Type:** B2B Marketplace  
**Release:** Marketplace Release 1.0  
**Specification:** WC-FDD-1.0-cc34  
**Release Notes:** WC-RN-1.0-cc34

---

# Technology Stack

| Category | Technology |
|---|---|
| Programming Language | Java 17 |
| UI Automation | Selenium WebDriver |
| API Automation | REST Assured |
| Test Framework | TestNG |
| Build Tool | Gradle |
| Design Pattern | Page Object Model |
| Page Management | PageManager |
| JSON Processing | Jackson |
| Reporting | Extent Reports |
| Screenshot | Selenium / Custom Screenshot Utility |
| Version Control | Git / GitHub |

---

# Framework Architecture

The framework is organized into separate layers for UI automation, API automation, reusable actions, utilities, test data, locators, and reporting.

```text
                    WholeCart QA Automation
                              |
              +---------------+---------------+
              |                               |
         UI Automation                   API Automation
              |                               |
        Page Objects                    API Layer
              |                               |
        CommonActions                 REST Assured
              |                               |
              +---------------+---------------+
                              |
                       Common Utilities
                              |
              +---------------+---------------+
              |               |               |
          JSON Reader      Reporting      Screenshots
              |
       Locator / Data
          Handling
                              |
                           TestNG
                              |
                           Gradle
```

---

# Project Structure

```text
WholeCart/
│
├── build.gradle.kts
├── settings.gradle.kts
├── README.md
│
├── src/
│   │
│   ├── main/
│   │   └── java/
│   │       └── org/
│   │           └── example/
│   │               └── wholecart/
│   │                   │
│   │                   ├── actions/
│   │                   │   └── CommonActions.java
│   │                   │
│   │                   ├── api/
│   │                   │   └── ApiBase.java
│   │                   │
│   │                   ├── base/
│   │                   │   ├── BaseTest.java
│   │                   │   └── DriverFactory.java
│   │                   │
│   │                   ├── pages/
│   │                   │   ├── LoginPage.java
│   │                   │   ├── CartPage.java
│   │                   │   ├── CheckoutPage.java
│   │                   │   └── PageManager.java
│   │                   │
│   │                   └── utils/
│   │                       ├── LocatorReader.java
│   │                       ├── JsonDataReader.java
│   │                       └── ScreenshotUtils.java
│   │
│   └── test/
│       │
│       ├── java/
│       │   └── org/
│       │       └── example/
│       │           └── wholecart/
│       │               │
│       │               ├── api/
│       │               │
│       │               └── ui/
│       │
│       └── resources/
│           │
│           ├── locators/
│           │
│           ├── testdata/
│           │
│           └── testng.xml
│
├── reports/
│
└── screenshots/
```

---

# UI Automation

The UI automation layer is implemented using **Selenium WebDriver with Java**.

The framework follows the **Page Object Model** to separate page-specific implementation from test execution.

The UI architecture follows:

```text
Test Class
    ↓
PageManager
    ↓
Page Object
    ↓
CommonActions
    ↓
Selenium WebDriver
```

This keeps the test classes focused on business validations rather than low-level Selenium operations.

---

# Page Object Model

The current framework contains the following Page Objects:

```text
LoginPage
CartPage
CheckoutPage
```

## LoginPage

Responsible for login-related operations and validations.

Responsibilities include:

- Entering login information
- Performing login
- Validating login-related UI behavior

---

## CartPage

Responsible for cart-related operations and validations.

Responsibilities include:

- Cart item validation
- Product quantity validation
- Unit validation
- Price validation
- Cart total validation
- Stock-related validations

---

## CheckoutPage

Responsible for checkout-related operations and validations.

Responsibilities include:

- Checkout information
- Delivery date
- Delivery slot
- Order placement
- Checkout validations

---

# PageManager

`PageManager` provides centralized access to the application's Page Objects.

The current PageManager manages:

```text
PageManager
    |
    +── LoginPage
    |
    +── CartPage
    |
    +── CheckoutPage
```

This avoids unnecessary page-object creation inside individual test methods and provides a consistent way for tests to access application pages.

---

# CommonActions

`CommonActions` provides reusable Selenium operations that are shared across Page Objects.

The common action layer includes operations such as:

```text
Click
Enter Text
Get Text
Verify Text
Compare Text
Check Element Display
Verify Element Display
```

The purpose of this layer is to avoid duplicating Selenium implementation across multiple Page Objects.

The architecture is:

```text
Page Object
     ↓
CommonActions
     ↓
Selenium WebDriver
```

This improves consistency and makes future changes to common Selenium behavior easier to manage.

---

# JSON-Driven Locators

The framework keeps UI locators separate from Java implementation.

Locators are maintained under the test resources and loaded dynamically by the framework.

The Page Objects use the locator reader to retrieve the required locator instead of maintaining all locators directly inside the Java classes.

Architecture:

```text
JSON Locator
      ↓
LocatorReader
      ↓
Page Object
      ↓
CommonActions
      ↓
Selenium
```

### Benefits

- Keeps Page Objects clean
- Reduces hardcoded locator usage
- Makes locator maintenance easier
- Provides centralized locator management
- Allows locator changes without modifying the overall framework design

---

# JSON Data Handling

The framework uses JSON-based data handling for values that need to be externalized from the Java implementation.

Jackson is used to parse and read JSON content.

The framework separates:

```text
Test Logic
     +
Application Locators
     +
Externalized Data
```

This improves maintainability and allows test implementation to remain independent from changing values.

---

# API Automation

The API automation layer is implemented using **REST Assured**.

The API framework provides reusable infrastructure for API requests, authentication, response validation, and token handling.

Architecture:

```text
API Test
    ↓
ApiBase
    ↓
REST Assured
    ↓
Application API
```

The API layer is kept independent from the Selenium Page Object layer while sharing common framework utilities where applicable.

---

# API Authentication and Token Management

API authentication is handled centrally within the API framework.

The authentication flow is:

```text
Authentication Request
        ↓
Authentication Response
        ↓
Access Token
        ↓
Token Handling
        ↓
Authenticated API Requests
```

The token is managed centrally so that individual API tests do not need to duplicate authentication logic.

This provides:

- Reusability
- Cleaner API tests
- Centralized authentication
- Reduced code duplication
- Easier maintenance

---

# UI and API Integration

UI and API automation are maintained within the same automation repository while keeping their implementation layers separate.

```text
                 WholeCart Framework
                         |
              +----------+----------+
              |                     |
          UI Layer              API Layer
              |                     |
          Selenium             REST Assured
              |                     |
       Page Objects              ApiBase
              |                     |
       CommonActions        Authentication
              |                     |
              +----------+----------+
                         |
                       TestNG
                         |
                       Gradle
```

This allows both automation layers to be executed through the same build and test execution mechanism.

---

# BaseTest

`BaseTest` provides common test lifecycle management for UI automation.

The UI execution lifecycle follows:

```text
@BeforeTest
     ↓
Initialize Browser
     ↓
Initialize WebDriver
     ↓
Initialize PageManager
     ↓
Execute Test
     ↓
@AfterTest
     ↓
Close Browser
```

This centralizes browser setup and teardown rather than repeating it in every UI test class.

---

# DriverFactory

`DriverFactory` is responsible for creating and configuring the Selenium WebDriver.

Browser initialization is centralized to maintain consistent driver management across UI tests.

The structure also allows future support for different browser execution strategies without changing individual Page Objects or test cases.

---

# TestNG

**TestNG** is used as the primary test execution framework.

The framework uses a centralized TestNG suite to control test execution.

TestNG provides:

- Test lifecycle management
- Test grouping
- Assertions
- Test configuration
- Suite execution
- Reporting integration

The overall execution flow is:

```text
TestNG Suite
      |
      +── UI Tests
      |
      +── API Tests
```

---

# Gradle

Gradle is used as the build and dependency management tool.

The project uses **Gradle Kotlin DSL**.

The framework dependencies are managed centrally through the Gradle build configuration.

Major dependencies include:

- Selenium Java
- REST Assured
- Jackson
- TestNG
- Extent Reports
- Apache Commons IO

The framework uses:

```text
Java 17
```

---

# Reporting

The framework uses **Extent Reports** for HTML-based test execution reporting.

The report captures important execution information such as:

- Test name
- Test status
- Execution details
- Validation results
- Screenshots where applicable
- Selenium execution information

The reporting layer provides a consolidated view of automation execution.

---

# Selenium Action Logging

The framework uses Selenium's listener/event mechanism to capture WebDriver actions.

This allows Selenium activity to be monitored centrally without adding manual logging code around every action.

Examples of activities that can be captured include:

```text
Browser navigation
Element interaction
Click operations
Text entry
Page navigation
Other WebDriver actions
```

This improves debugging and provides better visibility during test execution.

---

# Screenshots

The framework provides centralized screenshot handling.

Screenshots can be captured during important validation points and test failures.

The screenshot flow is:

```text
Test Execution
      ↓
Failure / Required Validation
      ↓
Screenshot Utility
      ↓
Screenshot
      ↓
Report
```

This provides visual evidence for failed or important test scenarios.

---

# Business Validation

The framework automates important WholeCart business validations across the UI and API layers.

Examples of validation areas include:

- Login behavior
- Product information
- Cart operations
- Quantity validation
- Unit validation
- Stock validation
- Price validation
- Cart total validation
- Checkout information
- Delivery date
- Delivery slot
- Order placement
- API response validation
- Authentication validation

Critical business scenarios identified during functional testing are prioritized for automation based on:

- Business impact
- Regression frequency
- Stability
- Automation feasibility
- End-to-end business value

---

# Framework Design Principles

## Reusability

Common functionality is centralized into reusable components.

```text
BaseTest
DriverFactory
CommonActions
ApiBase
PageManager
LocatorReader
JsonDataReader
ScreenshotUtils
```

---

## Maintainability

The framework separates:

```text
Test Logic
Page Objects
Locators
Externalized Data
Common Actions
API Infrastructure
Reporting
```

This minimizes changes required when application behavior or locators are updated.

---

## Separation of Concerns

Each framework component has a specific responsibility.

```text
Base Layer
    → Test lifecycle

Page Layer
    → UI page behavior

Action Layer
    → Reusable Selenium actions

API Layer
    → API communication and authentication

Utility Layer
    → JSON, locators, screenshots and supporting functionality

Test Layer
    → Business validations
```

---

## Scalability

The framework is designed so that additional pages, APIs, utilities, and test scenarios can be added without redesigning the complete architecture.

---

## Centralized Configuration

Browser setup, API authentication, locator handling, JSON processing, screenshots, and reporting are managed through reusable framework components.

---

## Reduced Code Duplication

The framework avoids repeating common Selenium and API implementation inside individual test cases.

Tests remain focused on:

```text
Business Flow
      +
Business Validation
```

rather than low-level automation implementation.

---

# Test Execution

## Execute the Complete Test Suite

From the project root:

```bash
./gradlew clean test
```

On Windows:

```bash
gradlew.bat clean test
```

The command performs the Gradle build and executes the configured TestNG tests.

---

# Overall Execution Flow

```text
                 Gradle
                    ↓
                TestNG
                    ↓
              Test Suite
                    |
          +---------+---------+
          |                   |
      UI Tests            API Tests
          |                   |
      Selenium           REST Assured
          |                   |
    Page Objects          API Layer
          |                   |
    CommonActions       Authentication
          |                   |
          +---------+---------+
                    ↓
              Test Results
                    ↓
             Extent Report
                    ↓
               Screenshots
```

---

# Repository Organization

The repository maintains a clear separation between:

```text
Application Automation
Framework Components
Test Cases
Locators
Externalized Data
Reports
Screenshots
```

Generated files such as build output, reports, and screenshots should not be mixed with the core framework implementation.

---

# Automation Approach

The framework follows a layered automation approach:

```text
Business Requirement
        ↓
Test Scenario
        ↓
Test Case
        ↓
UI / API Automation
        ↓
Reusable Framework Components
        ↓
Validation
        ↓
Reporting
```

The objective is to ensure that automation is not only functional but also maintainable and scalable for future regression cycles.

---

# Key Framework Features

- Java 17 based framework
- Selenium WebDriver
- REST Assured API automation
- TestNG
- Gradle Kotlin DSL
- Page Object Model
- Centralized PageManager
- LoginPage
- CartPage
- CheckoutPage
- Reusable CommonActions
- JSON-driven locator management
- JSON data handling
- Centralized API authentication
- Token management
- Selenium action logging
- Automated screenshots
- Extent HTML reporting
- Centralized browser lifecycle
- Separation of UI and API layers
- Reusable framework utilities
- Single-command test execution

---

# Future Scalability

The existing framework architecture can be extended with:

- Additional Page Objects
- Additional API validations
- Additional test scenarios
- Parallel execution
- Cross-browser execution
- CI/CD pipeline integration
- Enhanced reporting
- Additional reusable utilities

The existing layered design allows these capabilities to be added without introducing unnecessary changes to the current automation implementation.

---

# Author

**Deepika**  
Senior QA Automation Engineer

### Automation Skills Demonstrated

```text
Java
Selenium WebDriver
REST Assured
TestNG
Gradle
Page Object Model
API Automation
UI Automation
JSON-driven Automation
Framework Design
Extent Reporting
Selenium Event Handling
Test Automation
```

---

# Conclusion

The WholeCart QA Automation Framework provides a maintainable and scalable solution for **UI and API automation**.

The framework is built around:

```text
                    Reusability
                         +
                   Maintainability
                         +
                    Scalability
                         +
                Separation of Concerns
                         +
                 Data-driven Design
                         +
                  Centralized Actions
                         +
                     Reporting
                         +
                Single Test Execution
```

The architecture keeps reusable automation infrastructure separate from application-specific test logic, allowing the framework to support current regression requirements while remaining extensible for future automation coverage.