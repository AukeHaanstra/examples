package nl.pancompany.unicorn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabaseConnectionFactory {

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("<url>"); // What do you do here? Mock a static method?
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
