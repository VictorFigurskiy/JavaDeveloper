package com.victor.practice.modul02.controller;

import com.victor.practice.modul02.instance.Developer;
import com.victor.practice.modul02.instance.Project;
import com.victor.practice.modul02.instance.Skills;

import java.util.List;

/**
 * Created by Sonikb on 08.06.2017.
 */
public interface DeveloperController {
    List<Developer> readAllTable();

    Developer addData(String name, String surname, int salary, List<Skills> devSkillsList, int projectID);

    void update(int id, String newDevName, String newDevSurname, int newSalary, List<Skills> devSkillsList, int newProjectID);

    void update(int id, String newDevName, String newDevSurname, int newSalary, List<Skills> devSkillsList, Project project);

    void delete(int id);
}
