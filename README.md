# Student Management System (Java MVC + JDBC + MySQL)

A learning project demonstrating a simple MVC web application with Java Servlets, JSP, JDBC, and MySQL.

## Requirements
- JDK 17
- Maven
- MySQL Server
- Apache Tomcat 10.1 (Jakarta Servlet 6 compatible)

## Database setup
Run `database.sql` in MySQL.

## Configure database credentials
`StudentDAO` reads these environment variables:
- `DB_URL` (default: `jdbc:mysql://localhost:3306/studentdb`)
- `DB_USER` (default: `root`)
- `DB_PASSWORD` (default: `your_password`)

Set `DB_PASSWORD` to your local MySQL password before running. For a local learning project, you can also edit the default in `StudentDAO.java`. Do not commit real credentials to a public repository.

## Build
From the project directory:

```bash
mvn clean package
```

This creates `target/StudentManagement.war`.

## Run
Deploy the WAR to Tomcat 10.1's `webapps` folder, start Tomcat, then open:

`http://localhost:8080/StudentManagement/students`

The app supports adding, viewing, editing, and deleting students.

## MVC flow
- **Model:** `Student.java` holds student data.
- **View:** `WEB-INF/students.jsp` renders the form and records.
- **Controller:** `StudentServlet.java` handles HTTP requests.
- **DAO:** `StudentDAO.java` runs SQL using JDBC.
- **Database:** MySQL stores the student records.

This is a learning example. For production, add authentication/authorization, CSRF protection, stronger validation, connection pooling, and centralized error handling.
