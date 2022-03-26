package conexiones;

import java.sql.*;

public class Conexion {

    private static final String URL_HOST = System.getenv("url_host_mysql");
    private static final String PUERTO = System.getenv("url_puerto_mysql");
    private static final String JDBC_USER = System.getenv("user_mysql");
    private static final String JDBC_PASSWORD = System.getenv("user_password_mysql");
    private static final String DATA_BASE = System.getenv("url_data_base");
    private static final String JDBC_URL = "jdbc:mysql://"+URL_HOST+":"+PUERTO+"/"+DATA_BASE+"?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublikeyRetrieval=true";

    public static Connection getConexionJDBC() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static void close(ResultSet resultSet) throws SQLException{
        resultSet.close();
    }

    public static void close(Statement statement)throws SQLException{
        statement.close();
    }

    public static void close(Connection connection)throws SQLException{
        connection.close();
    }
}
