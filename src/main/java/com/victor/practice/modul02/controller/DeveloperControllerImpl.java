package com.victor.practice.modul02.controller;

import com.victor.practice.modul02.dao.DeveloperDAO;
import com.victor.practice.modul02.instance.Developer;
import com.victor.practice.modul02.instance.Project;
import com.victor.practice.modul02.instance.Skills;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonikb on 07.06.2017.
 */
public class DeveloperControllerImpl implements DeveloperController {

    private DeveloperDAO dao;

    public DeveloperControllerImpl(DeveloperDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Developer> readAllTable() {
        List<Developer> developerList = dao.readAllTable();
        if (developerList == null || developerList.isEmpty()) {
            System.out.println("Список пуст!");
            return new ArrayList<>();
        }
        return dao.readAllTable();
    }

    @Override
    public Developer addData(String name, String surname, int salary, List<Skills> devSkillsList, int projectID) {
        Developer developer = new Developer(name, surname, salary, devSkillsList);
        developer.setProjectID(projectID);
        return dao.save(developer);
    }

    @Override
    public void update(int id, String newDevName, String newDevSurname, int newSalary, List<Skills> devSkillsList, int newProjectID) {
        Developer developer = new Developer(newDevName, newDevSurname, newSalary, devSkillsList);
        developer.setProjectID(newProjectID);
        dao.update(id, developer);
    }

    @Override
    public void update(int id, String newDevName, String newDevSurname, int newSalary, List<Skills> devSkillsList, Project project) {
        Developer developer = new Developer(newDevName,newDevSurname,newSalary,devSkillsList);
        developer.setProject(project);
        dao.update(id,developer);
    }

    public void delete(int id){
        dao.delete(id);
    }
}
