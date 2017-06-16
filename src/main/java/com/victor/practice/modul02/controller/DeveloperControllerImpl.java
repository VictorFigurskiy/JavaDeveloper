package com.victor.practice.modul02.controller;

import com.victor.practice.modul02.dao.DeveloperDAO;
import com.victor.practice.modul02.instance.Developer;
import com.victor.practice.modul02.instance.Skill;

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
    public Developer addData(String name, String surname, int salary, List<Skill> devSkillList, int projectID) {
        return dao.save(new Developer(name, surname, salary, devSkillList), projectID);
    }

    @Override
    public void update(int id, String newDevName, String newDevSurname, int newSalary, List<Skill> devSkillList, int newProjectID) {
        Developer developer = new Developer(newDevName, newDevSurname, newSalary, devSkillList);
        developer.setProjectID(newProjectID);
        dao.update(id, developer);
    }

    public void delete(int id){
        dao.delete(id);
    }
}
