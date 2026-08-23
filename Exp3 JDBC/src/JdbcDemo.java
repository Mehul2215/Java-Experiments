import java.sql.*;

public class JdbcDemo {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/college",
                System.getenv().getOrDefault("JAVA_LAB_DB_USER", "root"),
                requireDatabasePassword());

        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO student (id, name, branch) VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE name = VALUES(name), branch = VALUES(branch)");

        ps.setInt(1, 101);
        ps.setString(2, "Rahul");
        ps.setString(3, "CSE");

        ps.executeUpdate();
        System.out.println("Record Inserted");

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from student");

        while (rs.next()) {
            System.out.println(
                    rs.getInt(1) + " " +
                    rs.getString(2) + " " +
                    rs.getString(3));
        }

        con.close();
    }

    private static String requireDatabasePassword() {
        String password = System.getenv("JAVA_LAB_DB_PASSWORD");
        if (password == null || password.isEmpty()) {
            throw new IllegalStateException(
                    "Set JAVA_LAB_DB_PASSWORD before running the application.");
        }
        return password;
    }
}
