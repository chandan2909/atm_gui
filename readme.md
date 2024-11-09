# ATM System

This is a simple Java-based ATM system with a GUI interface. It allows users to log in, check their balance, deposit money, withdraw money, and view transaction history. The application connects to a MySQL database for user authentication and transaction management.

## Features

- User login with username and PIN
- View account balance
- Deposit and withdraw money
- View transaction history

## Technologies Used

- **Java Swing**: For creating the graphical user interface (GUI)
- **MySQL Database**: For storing user data, account balances, and transaction history
- **JDBC**: For connecting Java to the MySQL database

## Project Structure

- `ATM.java`: Main application window, managing the login and main menu panels
- `LoginPanel.java`: Login screen for user authentication
- `MainPanel.java`: Main menu with options to check balance, deposit, withdraw, and view transaction history
- `ATMDatabase.java`: Database operations class for connecting to MySQL and executing queries

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- **MySQL Server**: [Download MySQL](https://dev.mysql.com/downloads/installer/)

### Database Setup

1. Start your MySQL server.
2. Create a new database called `ATM_System`.
3. Run the following SQL script to create the required tables:

    ```sql
    CREATE TABLE users (
        user_id INT PRIMARY KEY AUTO_INCREMENT,
        username VARCHAR(50) NOT NULL UNIQUE,
        pin VARCHAR(4) NOT NULL,
        balance DECIMAL(15, 2) DEFAULT 0.00
    );

    CREATE TABLE transactions (
        transaction_id INT PRIMARY KEY AUTO_INCREMENT,
        user_id INT NOT NULL,
        transaction_type ENUM('Deposit', 'Withdrawal') NOT NULL,
        amount DECIMAL(15, 2) NOT NULL,
        timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (user_id) REFERENCES users(user_id)
    );
    ```

4. Insert sample data into the `users` table:

    ```sql
    INSERT INTO users (username, pin, balance) VALUES ('user1', '1234', 5000.00);
    ```

### Project Setup

1. Clone the repository:

    ```bash
    git clone https://github.com/chandan2909/ATM-System.git
    cd ATM-System
    ```

2. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA or Eclipse).

3. Update the database credentials in `ATMDatabase.java`:

    ```java
    return DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM_System", "<Username_here", "<password_here");
    ```

4. Run the `ATM.java` file to start the application.

## Usage

1. Launch the application by running `ATM.java`.
2. On the login screen, enter the username and PIN you configured in the database.
3. Once logged in, you can:
    - **View Balance**: Check your current account balance.
    - **Deposit Money**: Add funds to your account.
    - **Withdraw Money**: Withdraw funds from your account (subject to balance limits).
    - **Transaction History**: View a history of deposits and withdrawals.

## Screenshots

### Login Screen
![Login Screen](./screenshots/login_screen.png)

### Main Menu
![Main Menu](./screenshots/main_menu.png)

### Balance Screen
![Balance Screen](./screenshots/balance_screen.png)

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

### Contributions

Contributions are welcome! Please feel free to submit issues and pull requests for bug fixes or new features.

## Acknowledgments

- [Oracle JDBC Documentation](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/) - For JDBC guidance
- [Java Swing Documentation](https://docs.oracle.com/javase/tutorial/uiswing/) - For building GUI with Swing
