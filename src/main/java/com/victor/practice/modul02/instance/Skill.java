package com.victor.practice.modul02.instance;

/**
 * Created by Sonikb on 04.06.2017.
 */
public class Skill {
    private int id;
    private String skillName;

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return skillName;
    }
}
