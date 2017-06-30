package com.victor.practice.modul02.controller;

import com.victor.practice.modul02.dao.SkillsDAO;
import com.victor.practice.modul02.instance.Skills;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonikb on 07.06.2017.
 */
public class SkillControllerImpl implements SkillController {

    private SkillsDAO dao;

    public SkillControllerImpl(SkillsDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Skills> readAllTable() {
        List<Skills> skillsList = dao.readAllTable();
        if (skillsList == null || skillsList.isEmpty()){
            System.out.println("Список пуст!");
            return new ArrayList<>();
        }
        return dao.readAllTable();
    }

    @Override
    public Skills addData(String skillName) {
        return dao.save(new Skills(skillName));
    }

    @Override
    public void update(int id, String skillName) {
        dao.update(id,new Skills(skillName));
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
