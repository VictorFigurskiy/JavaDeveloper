package com.victor.practice.modul02.dao;

import com.victor.practice.modul02.instance.Skills;

import java.util.List;

/**
 * Created by Sonikb on 11.06.2017.
 */
public interface SkillsDAO {
    Skills save(Skills skills);

    Skills read(int id);

    void update(int id, Skills skills);

    void delete(int id);

    List<Skills> readAllTable();
}
