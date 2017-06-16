package com.victor.practice.modul02.dao.jdbcDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Sonikb on 16.06.2017.
 */
public class ConnectDbDao {

    public static Connection getConnection() throws SQLException {
        final String URL = "jdbc:mysql://localhost:3306/developers_info_db?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String USER = "root";
        final String PASSWORD = "root";

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
