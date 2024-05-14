# Lego Robot Control System

## Project Documentation

📄 Further documentation can be found in:
- Daily meeting notes on Confluence
- Weekly Sprint goals and achievements on Jira

## Introduction

The Lego Robot Control System is a Java-based project that enables interaction with a Lego robot through a web application. It allows for the sending and receiving of data between a database and the robot via web URLs. The primary objective is to control the robot by sending specific data through these URLs.

## Project Components

### Database Connection

💾 We utilize a MySQL database to store information about the Lego robot and its settings. The connection to this database is managed using a `persistence.xml` file in Java.

### Web Services

🌐 A RESTful web service was created to send information via URLs, enabling interaction with the Lego robot. Users can send commands and receive information through these web addresses. Data from the web service is set by the user through a user interface.

### LeJOS EV3

🤖 The LeJOS code consists of two threads:
1. One thread retrieves data via URL and sets values for the robot.
2. The other thread performs actions based on the newly set values and sends data back to the web service via another URL.

## Project Participation

### Contributions
- Developed the base for `LegoService.java`:
  - `getValues()`: Provides the latest settings retrieved from the database.
  - `getLego()`: Retrieves the desired Lego from the database (hardcoded to always use ID 1).
  - `getSpeed()`: Retrieves the latest speed set for the robot.
  - `setValues(LegoSetting ls)`: Sends values from the HTML form to the database.
- Created `Lego.java` and `LegoSetting.java` (Folder: `legoproject`).

- Created `MainWeb.java` and threads `MotorRunWeb.java` and `ReadValuesWeb.java` (Folder: `EV3LeJOSProjectTeam17_0324`).
- Implemented:
  - `printReceivedJson(String json)` in `LegoService.java` to retrieve and print values sent from the robot.
  - `getdirSpeed()` in `LegoService.java` to send values to the robot.
- Enhanced the HTML form for user input and added scripts to get values, send them to `LegoService.java`, and stop the robot if `run` equals 0 (Folder: `legoproject`).

- Created the method `getTime()` in `LegoService.java` to get the latest timestamp from the database.
  - Used in `index` to read the time and calculate the robot's running duration with `readTime()`.
  - Printed the running time with `printTime(list)`.
- Developed methods `updateSpeedDisplay()`, `readSpeed()`, and `printSpeed(list)` to print out speed history in real time.

### Collaborative Efforts

- Worked on database creation based on existing classes `Lego.java` and `LegoSetting.java`.
- Modified `persistence.xml` accordingly.

## Conclusion

By utilizing a RESTful web service and a MySQL database, this project provides an efficient way to send commands to the Lego robot and receive feedback. This system simplifies controlling the robot, enabling it to perform desired tasks in various scenarios.

## Getting Started

To get started with the Lego Robot Control System:

1. ⚙️ Set up the MySQL database and configure the `persistence.xml` file with the appropriate database settings.
2. 🚀 Deploy the RESTful web service.
3. 📋 Use the provided HTML form to send commands to the robot.
4. 🔍 Monitor the robot's actions and feedback via the web service.

For detailed steps and further documentation, refer to the Confluence and Jira links provided above.
