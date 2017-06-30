package com.victor.practice.modul02.dao.pooledJdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Sonikb on 13.06.2017.
 */
public class PooledJdbcUserDao {

    private DataSource dataSource;

    public PooledJdbcUserDao (DataSource dataSource){
        this.dataSource = dataSource;
    }

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
