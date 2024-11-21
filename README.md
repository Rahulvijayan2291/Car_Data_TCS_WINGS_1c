# Car Data API – Spring Boot

This project is a **REST API service** built using **Spring Boot** to manage car data. The API supports CRUD (Create, Read, Update, Delete) operations for car details, allowing users to perform operations such as adding, updating, retrieving, and deleting car information.

---

## Features
- **Add Car Data**: Add details of a new car to the system.
- **Update Car Data**: Modify existing car information.
- **Retrieve All Car Data**: Fetch details of all cars in the system.
- **Delete Car Data**: Remove car details by their unique ID.

---

## Car Data Attributes
Each car data entry includes the following attributes:

| Attribute           | Type     | Description                                                  |
|---------------------|----------|--------------------------------------------------------------|
| `id`                | `int`    | Unique identifier for the car data.                         |
| `model`             | `String` | Name of the car model.                                      |
| `manufacturedYear`  | `int`    | The year the car model was manufactured.                    |
| `company`           | `String` | Name of the car's manufacturing company.                    |
| `bodyType`          | `String` | Type of car body (e.g., sedan, SUV).                        |
| `price`             | `double` | Price of the car model.                                     |
| `fuelType`          | `String` | Type of fuel the car uses (e.g., petrol, diesel).           |
| `seatingCapacity`   | `int`    | Number of passengers the car can seat.                      |

---

## Sample Car Data JSON
Here is an example of the JSON representation of a car data object:

```json
{
  "model": "City",
  "manufacturedYear": 2024,
  "company": "Honda",
  "bodyType": "sedan",
  "price": 2000000.0,
  "fuelType": "petrol",
  "seatingCapacity": 5
}
```

---

## API Endpoints

### 1. **Add New Car Data**
- **Endpoint**: `POST /carData/add`
- **Request Body**: JSON object containing car data details.
- **Response**:
  - `201`: Successfully added.
  - `400`: Data validation failed.

### 2. **Retrieve All Car Data**
- **Endpoint**: `GET /carData/list`
- **Response**:
  - `200`: Successfully retrieved all car data.
  - `400`: No data found.

### 3. **Update Car Data**
- **Endpoint**: `PATCH /carData/update/{id}`
- **Request Body**: JSON object with updated car data details.
- **Response**:
  - `200`: Successfully updated.
  - `400`: No data provided.
  - `404`: Car data with the given ID not found.

### 4. **Delete Car Data by ID**
- **Endpoint**: `DELETE /carData/delete/{id}`
- **Response**:
  - `200`: Successfully deleted.
  - `404`: Car data with the given ID not found.

---

## Prerequisites
- **Java 11 or higher**
- **Maven**

---

## Getting Started

### Running the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/car-data-api.git
   cd car-data-api
   ```

2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

3. Test the application:
   ```bash
   mvn clean test
   ```

---

## Database
This application uses an **in-memory H2 database** for testing purposes. No additional setup is required as the database resets between application restarts.

To view the database during runtime:
1. Enable the H2 console in `application.properties`:
   ```properties
   spring.h2.console.enabled=true
   ```
2. Access the console at `http://localhost:8080/h2-console`.
3. Use the following credentials:
   - **URL**: `jdbc:h2:mem:testdb`
   - **User**: `sa`
   - **Password**: (leave blank)

---

## Running Tests
The project includes unit tests for the CRUD operations using Spring's `MockMvc`. The tests are located in the `src/test/java/com/example/carData/` directory.

To execute tests:
```bash
mvn clean test
```

---

## Troubleshooting
### Port Already in Use
If you encounter a `port already in use` error, terminate the process using:
```bash
fuser -k 8080/tcp
```
Alternatively, update the port in `application.properties`:
```properties
server.port=8081
```

---

## Technologies Used
- **Spring Boot**: Framework for building REST APIs.
- **H2 Database**: In-memory database for testing.
- **JUnit**: Testing framework.
- **Maven**: Dependency and build management.

---

## Contributions
Contributions are welcome! Please feel free to submit issues or pull requests to improve this project.

If you'd like to support the development of this project, you can **buy me a coffee**:

- **GPay**: +91-9074023334

Your support is greatly appreciated!

---

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
