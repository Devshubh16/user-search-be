# User Search Backend (Spring Boot API)

## 📌 Project Overview
This is a Spring Boot-based backend API that:
- Loads user data from an external dataset into an in-memory H2 database.
- Provides RESTful endpoints for searching and retrieving user details.
- Implements resilience mechanisms (Retry, Circuit Breaker) to handle API failures.
- Includes Swagger documentation for easy API exploration.(In Progress)

---

## 🚀 Features
- **Load Users**: Fetches and stores users from [dummyjson.com/users](https://dummyjson.com/users).
- **Search Users**: Supports free-text search on `firstName`, `lastName`, and `ssn`.
- **Retrieve User Details**: Fetch a user by `ID` or `Email`.
- **Resilience**: Implements **Spring Retry** & **Resilience4J Circuit Breaker**.
- **Logging & Exception Handling**: Structured logging and meaningful error responses.
- **Unit Tests**: JUnit & Mockito-based testing.

---

## 🛠️ Tech Stack
- **Backend**: Java 21, Spring Boot, Spring Data JPA, Spring Web
- **Database**: H2 (In-Memory)
- **Resilience**: Spring Retry, Resilience4J
- **Testing**: JUnit 5, Mockito
- **Documentation**: OpenAPI (Swagger UI)

---

## 📖 API Endpoints
### **User Management**
| HTTP Method | Endpoint | Description |
|------------|---------|-------------|
| `POST` | `/api/users/load` | Load users from external API into H2 DB |
| `GET` | `/api/users/search?q={query}` | Search users by first name, last name, or SSN |
| `GET` | `/api/users/{id}` | Retrieve user by ID |
| `GET` | `/api/users/email/{email}` | Retrieve user by Email |

### **H2 Database Console**
| HTTP Method | Endpoint | Description |
|------------|---------|-------------|
| `GET` | `/h2-console` | Access H2 database GUI |

### **Swagger API Documentation**
| HTTP Method | Endpoint | Description |
|------------|---------|-------------|
| `GET` | `/swagger-ui.html` | Explore API docs using Swagger UI |

---

## 🔧 Setup & Installation
### **1️⃣ Clone Repository**
```sh
git clone https://github.com/Devshubh16/user-search-be.git
cd user-search-be
```

### **2️⃣ Build & Run**
#### **Using Maven**
```sh
mvn clean install
mvn spring-boot:run
```
#### **Using Java**
```sh
java -jar target/springbe-0.0.1-SNAPSHOT.jar
```

### **3️⃣ Access API**
- **Base URL:** `http://localhost:8080`
- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **H2 Console:** `http://localhost:8080/h2-console`

---

## 🧪 Running Tests
To execute unit tests, run:
```sh
mvn test
```

---

## 📂 Project Structure
```
user-search-be/
│── src/main/java/com/springbe/usersapi/
│   ├── controller/    # API Controllers
│   ├── service/       # Business Logic
│   ├── model/         # Data Models
│   ├── repository/    # JPA Repositories
│   ├── config/        # Configurations (Retry, Circuit Breaker)
│── src/test/java/com/springbe/usersapi/  # Unit Tests
│── src/main/resources/
│   ├── application.yml  # Configurations
│── pom.xml  # Maven Dependencies
│── README.md  # Project Documentation
```

---

## 🚀 Future Enhancements
- ✅ Add JWT-based authentication
- ✅ Implement caching for API responses
- ✅ Improve search capabilities with Elasticsearch

---

## 🤝 Contributing
Feel free to fork and submit a pull request! 😊

