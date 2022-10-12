**Schedule API**

**Description:** Api to schedule and controll appointments.

**Objective:**

**Entity:**
  - Schedule:
    - id
    - date
    - startAt
    - endAt
    - attendantId
    - description
    - createAt
    - updatedAt

**feat/001:** 
  - save a schedule
  - update schedule
  - find all by date and attendant id
  - find by id

**Technologies**:
- Java 11
- Gradle
- Junit
- Mockito
- PostgreSQL
- H2 Database
- Spring Data JPA
- Spring Web
- Spring Validation


Design
- MVC architecture
- TDD

**How to run the project:**

Locally: You can simply clone this repository, open on your IDE and start the project.

Access the swagger api endpoint to use the resources
```http://localhost:8080/schedule/swagger-ui/index.html```
