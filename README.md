# AppFollow_UI_tests

## About
Here is an example of one test case for the Registration process on the [AppFollow](https://appfollow.io/) site.


## Tech Stack
* Java 11
* Maven 3.6.3
* Selenide 5.18
* TestNG 7.3.0
* Allure 2.13

## How to run
#### Pre-requisites:
*you will need Java 11 or higher to run this project. The Maven is not necessary since the project utilized a Maven wrapper. Make sure you have a proper java version in the JAVA_HOME path otherwise Maven most likely fails to run the project.*

To run the project just execute the following in command-line from the project's root folder:
for Unix-like systems
```
./mvnw clean test
```
for Windows systems
```
mvnw.cmd clean test
``` 

By default the test will be executed in the Chrome browser if you have it installed in your system but you can specify Firefox or Edge browsers to be used for running the test. To do so, just add the '-Dbrowser=<your browser>' parameter where <your browser> should be 'Chrome', 'Firefox', or 'Edge' to the execution command, e.g.:
```
mvnw -Dbrowser=Firefox clean test
```
## To see results
After tests execution will be complete, you can check the results with the *emailable-report.html* from the /test-output folder or run Allure report dashboard from the same folder by executing the following command:
```
allure serve
```
but this will require [Allure installation](https://docs.qameta.io/allure/#_installing_a_commandline) on your system in the first place.
