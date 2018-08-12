package pl.reverseAuctions.model;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbUtil {
    private static DataSource dataSource;

    public static Connection getConn() throws SQLException {
        return getInstance().getConnection();
    }

    private static DataSource getInstance() {
        if (dataSource == null) {
            try {
                Context context = new InitialContext();
                dataSource = (DataSource) context.lookup("java:comp/env/jdbc/reverseAuction");
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return dataSource;
    }
}