package com.victor.practice.modul02.dao.sqlDAO;

import com.victor.practice.modul02.dao.DeveloperDAO;
import com.victor.practice.modul02.dao.pooledJdbc.PooledJdbcUserDao;
import com.victor.practice.modul02.dao.simpleLogger.ExceptionLogger;
import com.victor.practice.modul02.instance.Developer;
import com.victor.practice.modul02.instance.Skills;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonikb on 04.06.2017.
 */
public class DeveloperDAOimpl extends PooledJdbcUserDao implements DeveloperDAO {

    public DeveloperDAOimpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Developer save(Developer developer) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO developers(name, surname, salary, Projects_id) VALUES (?,?,?,?);")) {
                ps.setString(1, developer.getName());
                ps.setString(2, developer.getSurname());
                ps.setInt(3, developer.getSalary());
                ps.setInt(4, developer.getProjectID());
                ps.execute();
            }

            for (Skills skills : developer.getSkillsList()) {
                try (PreparedStatement ps = connection.prepareStatement("INSERT INTO developers_has_skills(Developers_id, Skills_id) VALUES ((SELECT developers.id FROM developers WHERE name = ? AND surname = ? AND developers.salary = ? AND developers.Projects_id = ?),?);")) {
                    ps.setString(1, developer.getName());
                    ps.setString(2, developer.getSurname());
                    ps.setInt(3, developer.getSalary());
                    ps.setInt(4, developer.getProjectID());
                    ps.setInt(5, skills.getId());
                    ps.execute();
                }
            }

            connection.commit();

        } catch (SQLException e) {
            if (connection != null){
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    ExceptionLogger.initLogger(e1.toString());
                }
            }
            System.err.println("Ошибка при работе с базой данных!");
            ExceptionLogger.initLogger(e.toString());
        }finally {
            if (connection != null){
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    ExceptionLogger.initLogger(e.toString());
                }
                try {
                    connection.close();
                } catch (SQLException e) {
                    ExceptionLogger.initLogger(e.toString());
                }
            }
        }
        return developer;
    }

    @Override
    public Developer read(int id) {
        Connection connection = null;
        Developer developer = null;

        try  {
            connection = getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM developers WHERE Projects_id = ?;")) {
                ps.setInt(1, id);
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        int developID = resultSet.getInt("id");
                        String developName = resultSet.getString("name");
                        String developSurname = resultSet.getString("surname");
                        int salary = resultSet.getInt("salary");
                        int projectID = resultSet.getInt("Projects_id");
                        developer = new Developer(developName, developSurname, salary, new ArrayList<Skills>());
                        developer.setId(developID);
                        developer.setProjectID(projectID);
                    }
                }
            }

            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM skills WHERE skills.id IN (SELECT Skills_id FROM developers_has_skills WHERE Developers_id = ?);")) {
                ps.setInt(1, developer != null ? developer.getId() : 0);
                try (ResultSet resultSet2 = ps.executeQuery()) {
                    while (resultSet2.next()) {
                        int skillID = resultSet2.getInt("id");
                        String skillName = resultSet2.getString("skills");
                        Skills skills = new Skills(skillName);
                        skills.setId(skillID);
                        if (developer != null) {
                            developer.getSkillsList().add(skills);
                        }
                    }
                }
            }
            connection.commit();

        } catch (Exception e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                ExceptionLogger.initLogger(e1.toString());
            }
            System.err.println("Ошибка при работе с базой данных!");
            ExceptionLogger.initLogger(e.toString());
        }finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    ExceptionLogger.initLogger(e.toString());
                }
                try {
                    connection.close();
                } catch (SQLException e) {
                    ExceptionLogger.initLogger(e.toString());
                }
            }
        }
        return developer;
    }

    @Override
    public void update(int id, Developer developer) {
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement("UPDATE developers SET name = ?, surname = ?, salary = ?, Projects_id = ? WHERE id = ?;")) {
            ps.setString(1, developer.getName());
            ps.setString(2, developer.getSurname());
            ps.setInt(3, developer.getSalary());
            ps.setInt(4, developer.getProjectID());
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных!");
            ExceptionLogger.initLogger(e.toString());
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM developers_has_skills WHERE Developers_id = ?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }

            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM developers WHERE id = ?;")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }

            connection.commit();

        } catch(Exception e){
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                ExceptionLogger.initLogger(e1.toString());
            }
            System.err.println("Ошибка при работе с базой данных!");
            ExceptionLogger.initLogger(e.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    ExceptionLogger.initLogger(e.toString());
                }
                try {
                    connection.close();
                } catch (SQLException e) {
                    ExceptionLogger.initLogger(e.toString());
                }
            }
        }
    }

    @Override
    public List<Developer> readAllTable() {
        Connection connection = null;
        List<Developer> developerList = new ArrayList<>();

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM developers")) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        int developID = resultSet.getInt("id");
                        String developName = resultSet.getString("name");
                        String developSurname = resultSet.getString("surname");
                        int salary = resultSet.getInt("salary");
                        int projectID = resultSet.getInt("Projects_id");
                        Developer developer = new Developer(developName, developSurname, salary, new ArrayList<Skills>());
                        developer.setId(developID);
                        developer.setProjectID(projectID);
                        developerList.add(developer);
                    }
                }
            }

            try (PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM skills WHERE skills.id IN (SELECT Skills_id FROM developers_has_skills WHERE Developers_id = ?);")) {
                for (Developer developer : developerList) {
                    ps2.setInt(1, developer.getId());
                    try (ResultSet resultSet2 = ps2.executeQuery()) {
                        while (resultSet2.next()) {
                            int skillID = resultSet2.getInt("id");
                            String skillName = resultSet2.getString("skills");
                            Skills skills = new Skills(skillName);
                            skills.setId(skillID);
                            developer.getSkillsList().add(skills);
                        }
                    }
                }
            }

            connection.commit();

        } catch (Exception e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                ExceptionLogger.initLogger(e1.toString());
            }
            System.err.println("Ошибка при работе с базой данных!");
            ExceptionLogger.initLogger(e.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    ExceptionLogger.initLogger(e.toString());
                }
                try {
                    connection.close();
                } catch (SQLException e) {
                    ExceptionLogger.initLogger(e.toString());
                }
            }
        }
        return developerList;
    }
}
