import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
//import wipb.jdbcexample.exception.DataAccessException;
public class ConnectionFactory {
    private static HikariConfig config;
    private static HikariDataSource ds;

    static {
        // odczytanie pliku konfiguracyjnego z classpath
        config = new HikariConfig("/hikari.properties");
        ds = new HikariDataSource( config );

    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }
}
