import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/student_db?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = System.getenv().getOrDefault("JAVA_LAB_DB_USER", "root");
    private static final String PASSWORD = System.getenv("JAVA_LAB_DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        if (PASSWORD == null || PASSWORD.isEmpty()) {
            throw new SQLException("Set JAVA_LAB_DB_PASSWORD before running the application.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
