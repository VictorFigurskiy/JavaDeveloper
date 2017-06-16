package com.victor.practice.modul02.dao;

import com.victor.practice.modul02.dao.PooledJdbc.PooledJdbcUserDao;
import com.victor.practice.modul02.dao.simpleLogger.ExeptionLogger;
import com.victor.practice.modul02.instance.Skill;

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
    public Skill save(Skill obj) {
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement("INSERT INTO skills(skills) VALUE(?);")) {
            ps.setString(1, obj.getSkillName());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных!");
            ExeptionLogger.initLogger(e.toString());
        }
        return obj;
    }

    @Override
    public Skill read(int id) {
        Skill skill = null;
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement("SELECT skills.id, skills.skills FROM skills WHERE skills.id = ?;")) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    int skillID = resultSet.getInt("id");
                    String skillName = resultSet.getString("skills");
                    skill = new Skill(skillName);
                    skill.setId(skillID);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных!");
            ExeptionLogger.initLogger(e.toString());
        }
        return skill;
    }

    @Override
    public void update(int id, Skill obj) {
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement("UPDATE skills SET skills = ? WHERE id = ?;")) {
            ps.setString(1, obj.getSkillName());
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных!");
            ExeptionLogger.initLogger(e.toString());
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = getConnection()) {

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
            System.err.println("Ошибка при работе с базой данных!");
            ExeptionLogger.initLogger(e.toString());
        }
    }

    @Override
    public List<Skill> readAllTable() {
        List<Skill> skillList = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement("SELECT * FROM skills")) {
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String skillName = resultSet.getString("skills");
                    Skill skill = new Skill(skillName);
                    skill.setId(id);
                    skillList.add(skill);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных!");
            ExeptionLogger.initLogger(e.toString());
        }
        return skillList;
    }
}
