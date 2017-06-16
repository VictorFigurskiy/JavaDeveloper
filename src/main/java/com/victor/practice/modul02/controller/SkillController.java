package com.victor.practice.modul02.controller;

import com.victor.practice.modul02.instance.Project;
import com.victor.practice.modul02.instance.Skill;

import java.util.List;

/**
 * Created by Sonikb on 08.06.2017.
 */
public interface SkillController {
    List<Skill> readAllTable();

    Skill addData(String skillName);

    void update(int id, String skillName);

    void delete(int id);
}
