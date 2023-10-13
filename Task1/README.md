# Task-1
Create a simple command-line To-Do List application in Java that 
allows users to manage their tasks. Users can add tasks, mark tasks as complete, view 
their tasks, and remove tasks from the list. 

>The "**_task1.java_**" contains the code to the solution for Task-1 of the java development intership at Coding Samurai.
___
I have used MySQL as a database to store the task list data. Using a databse made more sense to me to store data, instead of an ordinary data structure like ArrayList.<br />
For this purpose I have made the use of the JDBC driver for **Java** and **MySQL** connectivity.
##
The MySQL database, by default, looks like this:-

```
+------------+--------------------+------------+---------------+
| taskname   | description        | due_date   | completed     |
+------------+--------------------+------------+---------------+
| Test       | Mathematics        | 2.11.2023  | Not Completed |
| Assignment | Physics assignment | 15.10.2023 | Not Comleted  |
+------------+--------------------+------------+---------------+
```
To create the above database, copy and paste the following queries on MySQL shell or workbench and run all.
```
CREATE SCHEMA codingsamurai;
USE codingsamurai;
CREATE TABLE task1(taskname text, description text, due_date text, completed text);
INSERT INTO task1 VALUES("Test","Mathematics","2.11.2023","Not Comleted");
INSERT INTO task1 VALUES("Assignment","Physics assignment","15.10.2023","Not Comleted");
SELECT * FROM task1;
```
