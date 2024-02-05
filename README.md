# User Authentication and Registration System

This project is a user authentication and registration system implemented in Java using Maven for dependency management. It communicates with a server-side Google Apps Script to handle user data.

## Features

- User authentication
- User registration
- Fingerprint registration

## Classes

- `User`: Represents a user in the system.
- `AppScriptRequest`: Handles communication with the server-side Google Apps Script.

## Usage

### User Authentication

To authenticate a user, call the `authenticate` method in the `AppScriptRequest` class with the username and password as parameters. If the authentication is successful, it returns a `User` object. If the authentication fails, it returns `null`.

### User Registration

To register a new user, call the `registerUser` method in the `AppScriptRequest` class with a `User` object and password as parameters. If the registration is successful, it returns `1`. If the registration fails, it returns `0`.

### Fingerprint Registration

To register a user's fingerprint, call the `registerFinger` method in the `AppScriptRequest` class with a `JsonObject` representing the fingerprint template and the user's ID as parameters. If the registration is successful, it returns `1`. If the registration fails, it returns `0`.

## Dependencies

- Jakarta JSON Processing: Used for handling JSON data.
- Java SE 11: The project is developed and tested with Java SE 11.

## Setup

1. Clone the repository.
2. Open the project in your preferred IDE.
3. Ensure that you have Maven and Java SE 11 installed on your machine.
4. Run the project.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License

[MIT](https://choosealicense.com/licenses/mit/)
