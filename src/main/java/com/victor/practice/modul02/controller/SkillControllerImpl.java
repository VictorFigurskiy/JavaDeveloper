package com.victor.practice.modul02.controller;

import com.victor.practice.modul02.dao.SkillsDAO;
import com.victor.practice.modul02.instance.Skill;

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
    public List<Skill> readAllTable() {
        List<Skill> skillList = dao.readAllTable();
        if (skillList == null || skillList.isEmpty()){
            System.out.println("Список пуст!");
            return new ArrayList<>();
        }
        return dao.readAllTable();
    }

    @Override
    public Skill addData(String skillName) {
        return dao.save(new Skill(skillName));
    }

    @Override
    public void update(int id, String skillName) {
        dao.update(id,new Skill(skillName));
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
