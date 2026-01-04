## JSON Parser (Java + Spring Boot CLI)

A command-line JSON validation tool built using **Java**, **Spring Boot**, and **Maven**.  
This project implements a **custom JSON lexer and parser** (no Jackson/Gson) and validates JSON input provided via a file.

---

### Features

- Custom JSON **tokenizer (lexer)** and **parser**
- Validates JSON syntax
- Command-line interface using Spring Boot `CommandLineRunner`
- Accepts JSON input via file path
- Packaged as an executable JAR

---

### Tech Stack

- Java 17+
- Spring Boot
- Maven

---

### Build the Project

From the project root directory:

```bash
mvn clean package -DskipTests
```

This generates an executable JAR in:

```
target/jsonparser-0.0.1-SNAPSHOT.jar
```

---

### Run the Application

#### Validate a JSON file

```bash
java -jar target/jsonparser-0.0.1-SNAPSHOT.jar <path-to-json-file>
```

#### Example

```bash
java -jar target/jsonparser-0.0.1-SNAPSHOT.jar sample.json"
```

---

### Output

- **Valid JSON**
  ```
  JSON is valid
  ```

- **Invalid JSON**
  ```
  JSON is not valid
  ```

Exit codes:
- `0` → valid JSON
- `1` → invalid JSON or error

---

### Notes

- Input must be a file path
- Uses a custom-built parser (no third-party JSON libraries)
- Designed as a learning project for parsing and compiler fundamentals


