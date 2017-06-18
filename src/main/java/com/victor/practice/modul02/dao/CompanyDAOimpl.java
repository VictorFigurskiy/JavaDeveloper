package com.victor.practice.modul02.dao;

import com.victor.practice.modul02.dao.PooledJdbc.PooledJdbcUserDao;
import com.victor.practice.modul02.dao.simpleLogger.ExeptionLogger;
import com.victor.practice.modul02.instance.Company;
import com.victor.practice.modul02.instance.Developer;
import com.victor.practice.modul02.instance.Project;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonikb on 05.06.2017.
 */
public class CompanyDAOimpl extends PooledJdbcUserDao implements CompanyDAO {

    public CompanyDAOimpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Company save(Company obj) {
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement("INSERT INTO companies(company_name) VALUE (?)")) {
            ps.setString(1, obj.getCompanyName());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных!");
            ExeptionLogger.initLogger(e.toString());
        }
        return obj;
    }

    @Override
    public Company read(int id) {
        Company company = null;
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement("SELECT companies.id, company_name FROM companies WHERE companies.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    int companyID = resultSet.getInt("id");
                    String companyName = resultSet.getString("company_name");
                    company = new Company(companyName);
                    company.setId(companyID);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных!");
            ExeptionLogger.initLogger(e.toString());
        }
        return company;
    }

    @Override
    public void update(int id, Company obj) {
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement("UPDATE companies SET company_name = ? WHERE id = ?;")) {
            ps.setString(1, obj.getCompanyName());
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных!");
            ExeptionLogger.initLogger(e.toString());
        }
    }

    @Override
    public void delete(int id, List<Project> projectList) {
        try (Connection connection = getConnection()) {

            connection.setAutoCommit(false);

            for (Project project : projectList) {

                for (Developer developer : project.getDeveloperList()) {
                    try (PreparedStatement ps = connection.prepareStatement("DELETE FROM developers_has_skills WHERE Developers_id = ?")) {
                        ps.setInt(1, developer.getId());
                        ps.executeUpdate();
                    }

                    try (PreparedStatement ps2 = connection.prepareStatement("DELETE FROM developers WHERE developers.id = ?")) {
                        ps2.setInt(1, developer.getId());
                        ps2.executeUpdate();
                    }
                }

                try (PreparedStatement ps = connection.prepareStatement("DELETE FROM projects WHERE projects.id = ?")) {
                    ps.setInt(1, project.getId());
                    ps.executeUpdate();
                }
            }

            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM companies WHERE id = ?;")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных!");
            ExeptionLogger.initLogger(e.toString());
        }
    }

    @Override
    public List<Company> readAllTable() {
        List<Company> companyList = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement("SELECT * FROM companies4444")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String companyName = resultSet.getString("company_name");
                Company company = new Company(companyName);
                company.setId(id);
                companyList.add(company);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных!");
            ExeptionLogger.initLogger(e.toString());
        }
        return companyList;
    }
}
