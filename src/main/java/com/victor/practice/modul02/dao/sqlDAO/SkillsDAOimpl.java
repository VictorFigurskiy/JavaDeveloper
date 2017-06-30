package com.victor.practice.modul02.dao.sqlDAO;

import com.victor.practice.modul02.dao.SkillsDAO;
import com.victor.practice.modul02.dao.pooledJdbc.PooledJdbcUserDao;
import com.victor.practice.modul02.dao.simpleLogger.ExceptionLogger;
import com.victor.practice.modul02.instance.Skills;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonikb on 04.06.2017.
 */
public class SkillsDAOimpl extends PooledJdbcUserDao implements SkillsDAO {

    public SkillsDAOimpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Skills save(Skills skills) {
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement("INSERT INTO skills(skills) VALUE(?);")) {
            ps.setString(1, skills.getSkillName());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных!");
            ExceptionLogger.initLogger(e.toString());
        }
        return skills;
    }

    @Override
    public Skills read(int id) {
        Skills skills = null;
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement("SELECT skills.id, skills.skills FROM skills WHERE skills.id = ?;")) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    int skillID = resultSet.getInt("id");
                    String skillName = resultSet.getString("skills");
                    skills = new Skills(skillName);
                    skills.setId(skillID);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных!");
            ExceptionLogger.initLogger(e.toString());
        }
        return skills;
    }

    @Override
    public void update(int id, Skills skills) {
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement("UPDATE skills SET skills = ? WHERE id = ?;")) {
            ps.setString(1, skills.getSkillName());
            ps.setInt(2, id);
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

            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM developers_has_skills WHERE Skills_id = ?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }

            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM skills WHERE id = ?;")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
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
    }

    @Override
    public List<Skills> readAllTable() {
        List<Skills> skillsList = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement("SELECT * FROM skills")) {
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String skillName = resultSet.getString("skills");
                    Skills skills = new Skills(skillName);
                    skills.setId(id);
                    skillsList.add(skills);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных!");
            ExceptionLogger.initLogger(e.toString());
        }
        return skillsList;
    }
}
