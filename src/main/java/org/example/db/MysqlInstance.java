package org.example.db;

import java.sql.* ;  // for standard JDBC programs
import java.math.* ; // for BigDecimal and BigInteger support
public class MysqlInstance {

    private static MysqlInstance instance;
    private String url;
    private String user;
    private String password;
    private Connection connection;

    private MysqlInstance() throws SQLException {
        this.url = "jdbc:mysql://localhost:3306/universidad_investigacion";
        this.user = "root";
        this.password = "password";
        this.connection = DriverManager.getConnection(this.url, this.user, this.password);
    }

    public static MysqlInstance getInstance() throws SQLException {
        if (instance == null ) {
            instance = new MysqlInstance();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection.isClosed()) connection = DriverManager.getConnection(this.url, this.user, this.password);
        return connection;
    }

}
