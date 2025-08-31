

\# Employee Rating System



A Spring Boot application for employee performance rating and evaluation.



\## Features



\- Employee management

\- Performance rating system

\- Email notifications

\- PDF and Excel report generation



\## Technology Stack



\- \*\*Backend\*\*: Spring Boot 2.7.18

\- \*\*Database\*\*: PostgreSQL (Production), MySQL (Development)

\- \*\*Build Tool\*\*: Maven



\## Environment Variables



For production deployment on Render:



\### Database Configuration

```

SPRING\_PROFILES\_ACTIVE=prod

SPRING\_DATASOURCE\_URL=jdbc:postgresql://your-host:5432/your-database

SPRING\_DATASOURCE\_USERNAME=your\_username

SPRING\_DATASOURCE\_PASSWORD=your\_password

SPRING\_JPA\_HIBERNATE\_DDL\_AUTO=update

SPRING\_JPA\_PROPERTIES\_HIBERNATE\_DIALECT=org.hibernate.dialect.PostgreSQLDialect

```



\### Email Configuration

```

MAIL\_HOST=smtp.gmail.com

MAIL\_PORT=587

MAIL\_USERNAME- mail id

MAIL\_PASSWORD=app password

```

>>>>>>> dev-log-reg



