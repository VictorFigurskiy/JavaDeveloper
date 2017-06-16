package com.victor.practice.modul02.controller;

import com.victor.practice.modul02.instance.Developer;
import com.victor.practice.modul02.instance.Skill;

import java.util.List;

/**
 * Created by Sonikb on 08.06.2017.
 */
public interface DeveloperController {
    List<Developer> readAllTable();

    Developer addData(String name, String surname, int salary, List<Skill> devSkillList, int projectID);

    void update(int id, String newDevName, String newDevSurname, int newSalary, List<Skill> devSkillList, int newProjectID);

    void delete(int id);
}
