Task Management API
Project Description - Brief Introduction
The Task Management API is a robust backend system built on Spring Boot, offering a comprehensive set of functionalities for managing tasks. It provides endpoints to create, retrieve, update, and delete tasks, empowering users to organize their work efficiently. Each task within the system contains essential attributes such as a title, description, due date, and status (e.g., pending, In Progress, completed).
This API facilitates user authentication, ensuring secure access and modification of tasks only by authorized users. Admin users possess full access rights to all tasks within the system.
The project emphasizes strong database integration, leveraging [mention your preferred database], and comprehensive documentation using Swagger for easy API exploration and usage.
Additional features include pagination, sorting, filtering options for task lists, error handling with appropriate status codes, and robust unit tests covering critical components, ensuring the reliability and scalability of the application.

Overview
The Task Management API is a comprehensive solution designed to streamline task organization and management. This API offers a range of functionalities tailored to facilitate smooth task handling for individuals and teams alike.
Key Features:
CRUD Operations for Tasks: Enables the creation, reading, updating, and deletion of tasks, ensuring flexibility in managing task-related data.
User Authentication and Authorization: Implements secure user authentication mechanisms, allowing only authorized users to access and modify their tasks. Admin users are granted full access rights to all tasks.
Essential Task Attributes: Each task within the system encompasses crucial details such as title, description, due date, and status (e.g., pending, In Progress, completed), providing comprehensive information for effective task management.

Emphasized Functionalities:
Database Integration: Utilizes MySql for robust and efficient data storage, ensuring reliability and scalability.
Swagger Documentation: Offers detailed API documentation using Swagger, simplifying API exploration, and usage for developers.

Features
List key features of the API, such as:
CRUD operations for tasks
User authentication and authorization
Pagination, sorting, and filtering options
Error handling and status codes

Technologies Used
List the technologies, frameworks, and tools used in the project:

Spring Boot
Database - MySql
Swagger for API documentation

API Documentation
Fisrt create a user
curl -X POST \
  http://your-api-url/user/add \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "Your Name",
    "username": "your_username",
    "password": "your_password",
    "role": "ADMIN"
}'
Access URL: (http://localhost:8080/swagger-ui)

