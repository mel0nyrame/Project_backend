# Student Score Management

Student Score Management is a full-stack assessment project for maintaining student records, courses, and scores. The repository combines the original Spring Boot backend and Vue frontend so the project can be cloned, configured, and developed from one place.

> 中文说明：见 [README.zh-CN.md](README.zh-CN.md)。

## Features

- Student management: list, search, create, edit, delete, and CSV export.
- Course management: list, search, create, edit, and delete.
- Score management: list, search, create, edit, and delete.
- REST API built with Spring Boot, MyBatis, and MySQL.
- Vue 3 dashboard built with Vite, PrimeVue, and Axios.

## Repository layout

```text
.
├── frontend/                 # Vue 3 + Vite web application
├── src/main/java/            # Spring Boot API source
├── src/main/resources/       # Application and MyBatis mapper configuration
├── src/test/                 # Backend tests
├── table.sql                 # Database schema and seed SQL
├── pom.xml                   # Maven build configuration
├── README.md                 # English documentation (primary)
└── README.zh-CN.md           # Chinese documentation
```

## Prerequisites

- Java 21
- Maven Wrapper (included as `./mvnw`)
- Node.js and npm
- MySQL

## Quick start

### 1. Prepare the database

Create a MySQL database named `school`, then run [`table.sql`](table.sql). Update the local connection settings in `src/main/resources/application.yaml` as needed. Do not commit real credentials.

### 2. Start the backend

```bash
./mvnw spring-boot:run
```

The API is available at `http://127.0.0.1:8080`.

### 3. Start the frontend

```bash
cd frontend
npm install
npm run dev
```

The Vite development server is normally available at `http://localhost:5173`. If the backend runs elsewhere, copy `frontend/.env.example` to `frontend/.env.local` and set `VITE_API_BASE_URL`.

## Verification

```bash
# From the repository root
./mvnw test

# From frontend/
npm run build
```

## API overview

| Resource | Base path | Supported operations |
| --- | --- | --- |
| Students | `/api/student/` | list, search, create, read, update, delete |
| Courses | `/api/course/` | list, search, create, update, delete |
| Scores | `/api/score/` | list, search, create, update, delete |

## Project naming

The recommended unified project name is **Student Score Management**. Suggested GitHub description: **Full-stack student, course, and score management system built with Spring Boot, MyBatis, MySQL, Vue 3, and PrimeVue.** Suggested topics: `spring-boot`, `mybatis`, `mysql`, `vue3`, `vite`, `primevue`, `student-management`, `full-stack`.

## License

No license has been declared yet. Add one before distributing the project publicly.
