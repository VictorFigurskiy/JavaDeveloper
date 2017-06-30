package com.victor.practice.modul02.controller;

import com.victor.practice.modul02.instance.Skills;

import java.util.List;

/**
 * Created by Sonikb on 08.06.2017.
 */
public interface SkillController {
    List<Skills> readAllTable();

    Skills addData(String skillName);

    void update(int id, String skillName);

    void delete(int id);
}
