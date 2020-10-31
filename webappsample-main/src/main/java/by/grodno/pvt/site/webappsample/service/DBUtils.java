package by.grodno.pvt.site.webappsample.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBUtils {
    private static final String DB_BUNDLE_NAME = "db";

    private static final String RESOURCE_BUNDLE_NAME = "db";
    private static HikariDataSource ds;

    public static Connection getConnection() throws Exception {
        if (ds == null) {
            ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME);
            Properties props = new Properties();
            props.put("driverClassName", "org.postgresql.Driver");
            props.setProperty("dataSource.user", rb.getString("username"));
            props.setProperty("dataSource.password", rb.getString("password"));
            props.setProperty("dataSource.databaseName", rb.getString("dbname"));
            props.put("dataSource.logWriter", new PrintWriter(System.out));
            HikariConfig config = new HikariConfig(props);
            config.setJdbcUrl(rb.getString("url"));
            ds = new HikariDataSource(config);
        }
        return ds.getConnection();
    }

}
