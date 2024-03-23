# SimpleJavaMicroservices

This repository contains an application demonstrating synchronous and asynchronous communication in the Spring framework. It consists of a student database service running on port 8070, which implements several HTTP methods for data handling (GET, POST, DELETE, etc.). The application, specifically the PUBLISHER service, upon receiving a specific request from a client (e.g., through a browser or Postman), at the endpoint "/notifications/{student_id_from_database}", retrieves the corresponding student from the database and prepares a notification to be sent to RabbitMQ. The RECEIVER service (running on port 8080) then retrieves all previously created notifications from the RabbitMQ queue.

## Components:

1. **Student Database Service** (Port: 8070):
   - Provides CRUD operations for managing student data.
   - Endpoints:
     - `GET /students` - Get all students
     - `GET /students/{id}` - Get student by ID
     - `POST /students` - Add a new student
     - `PUT /students/{id}` - Update student by ID
     - `DELETE /students/{id}` - Delete student by ID
     - `PATCH /students/{id}` - Edit data (not all)
    - For example we can add student to database by :  `http://localhost:8070/students` with
      JSON containing - `{ "name":"Maciej", "surname":"Bogdaniecki", "email":"bogdan@gmail.com"}`
      

       

2. **PUBLISHER Service**:
   - Receives requests at `/notifications/{student_id_from_database}`.
   - Retrieves a student from the database based on the provided ID.
   - Prepares a notification and sends it to RabbitMQ.
   - For example if we do HTTP GET like : `http://localhost:8090/notifications?id=2` Publisher service will connect with database and download data about student with ID=2.
     After that, notification will be prepared and sent to RabbitMQ.
     

3. **RabbitMQ**:
   - Message broker for handling communication between services.
   - Notifications are sent here from the PUBLISHER service.
   - It is possible to see generated traffic and queues in GUI interface on CloudAQMP site.

4. **RECEIVER Service** (Port: 8080):
   - Listens for notifications from RabbitMQ.
   - Retrieves and processes notifications.
   - Service only needs to RUN in the background - all notifications from que will be showed in console.

![430055964_1761693447676740_8037789869945500776_n (2)](https://github.com/ursus164/SimpleJavaMicroservices/assets/101526933/723009aa-9da1-4e3a-8ad3-86ffc6c3d395)

   


## Running the Application:

### Prerequisites:
- Java 8 or higher
- Maven
- RabbitMQ (CloudAMQP account)

### What I have used?
 - JPA
 - RabbitMQ
 - H2Database

### Steps:
1. Download or clone repository

2. Open project in your IDE, and then navigate to application.properties files in Publisher and Reciever services.

3. Add your own AMQP address which you can generate on  CloudAMQP website after creating account - it is neccesary in order to get RabbitMQ working.

4. Independently start 3 services - Publisher, Reciever and Students.

5. Publisher service will send synchronously HTTP request to database to retrieve data for provided ID.

6. Notification will be prepared with student data, and then sent to RabbitMQ.

7. Reciever will collect all notifications from RabbitMQ que and show them in console

8. Once it has been done - you can add students to database, and check notifications being sent and recieved as I mentioned above.

### Additional:
It is possible to add SMTP client and have notifications sent by email :)
