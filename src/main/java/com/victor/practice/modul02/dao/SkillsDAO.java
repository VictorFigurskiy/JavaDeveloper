package com.victor.practice.modul02.dao;

import com.victor.practice.modul02.instance.Skill;

import java.util.List;

/**
 * Created by Sonikb on 11.06.2017.
 */
public interface SkillsDAO {
    Skill save(Skill obj);

    Skill read(int id);

    void update(int id, Skill obj);

    void delete(int id);

    List<Skill> readAllTable();
}
