# Fundraising Manager

REST API service for managing fundraising events and tracking collected money.
This project was created as a recruitment task for SII internship program.

---

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Example Queries](#example-queries)
- [Tech Stack](#tech-stack)

---

## Features

- Create fundraising events and collection boxes
- Accept donations in multiple currencies
- Handle currency exchange to event's default currency
- Error responses are human-readable and contain descriptive messages

---

## Getting Started

### Requirements

- Java 17
- Maven

### Running the Application

In the project directory run:

```sh
mvnw spring-boot:run
```

or compile:

```sh
mvnw package
java -jar target/fundraising-1.0.0.jar
```

By default, the app run on the port `8080`.

### H2 Database Console

You can access the H2 database console at:
[http://localhost:8080/h2-database](http://localhost:8080/h2-database)

**Default Credentials:**
- JDBC URL: `jdbc:h2:mem:fundraising`
- User Name: `sa`
- no password

### Tests

You can execute tests set by running:

```sh
mvn test
```

---

## API Endpoints

| Method | Endpoint               | Description                                            |
|--------|------------------------|--------------------------------------------------------|
| POST   | `/events`              | Create a new fundraising event                         |
| GET    | `/events`              | Get a financial report of all events                   |
| POST   | `/boxes`               | Register a new collection box                          |
| GET    | `/boxes`               | List all collection boxes                              |
| POST   | `/boxes/{id}/donate`   | Deposit money into a collection box                    |
| POST   | `/boxes/{id}/transfer` | Transfer money from a collection box to an event       |
| DELETE | `/boxes/{id}`          | Unregister a collection box without transferring funds |

---

## Example Queries

### Create Event

`POST /events`

```json
{
  "name": "Test Event",
  "defaultCurrencyCode": "EUR"
}
```

### Register Box

`POST /boxes`

No request body required.

### Assign box to an event

`POST /boxes/1/assign`

```json
{
  "eventId": 1
}
```

### Donate money

`POST /boxes/1/donate`

```json
{
  "currency": "USD",
  "amount": 10
}
```

---

## Tech Stack

- Java 17
- Maven
- Spring Boot
- H2 in-memory database
- Lombok
- Mockito