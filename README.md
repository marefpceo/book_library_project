# book_library_project

This is a guide on how to run the Book Library Project dev server.<br>
This will be broken up into three parts the back-end, database and front-end.

## Tools 
Make sure you have the following install on you computer:
* Latest Java
* Maven
* Node js (w/npm)*
* Eclipse IDE

## Back-end Setup

Get dependenies - In the following project directory `/booktracker`, you can run:

### `mvn clean install`

Open eclipse in the `/booktracker` directory, navigate to `src/main/java/booktracker`

In the main file run the project as a Java Application

Server will be up and running. Make sure there are no errors on terminal.

## Database Setup

In the project directory navigate to `/hsqldb/lib`

Open a terminal in the following directory, you can run:

### `java -classpath ./hsqldb.jar org.hsqldb.server.Server`

Server will be up and running. Make sure there are no errors on terminal.

Open a separate terminal in the same directory, you can run:

### `java -cp ./hsqldb.jar org.hsqldb.util.DatabaseManagerSwing`

This will allow you to execute SQL commands and view results/data in tables

## Front-end Setup

Get dependenies - In the following project directory `/booktracker/src/main/ui`, you can run:

### `npm install`

To start a local development server, you can run:

### `npm run start`

Check out the READ.me in the same directory to get a list of commands to run
