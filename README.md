# BrowserStack-TestNG

[TestNG](http://testng.org) Test project to integrate BrowserStack Automate in Java, TestNG using some sample scenarios.

![BrowserStack Logo](https://d98b8t1nnulk5.cloudfront.net/production/images/layout/logo-header.png?1469004780)

## Scenario Tested
 * Open the BrowserStack site
 * Search for Sign In link and click on it
 * Enter the email and password and click on Sign in button
 * Click on cross icon on the Local installation pop up
 * Select the browser you want to open live session for and click on it
 * Check whether the live session was successful by checking the title of the Live session page

## Type of Tests
 * Single test executing one capability (single.testng.xml)
 * Parallel test executing in parallel for the configured browsers in capability (parallel.testng.xml)
 * Local test executed to test the Automate Local connection (local.testng.xml)
 
## Notes
 * Test is written using TestNG framework for Java
 * Maven is used to build project
 * Jenkins used for CI