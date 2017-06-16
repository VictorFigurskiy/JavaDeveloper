package com.victor.practice.modul02.instance;

import java.util.List;

/**
 * Created by Sonikb on 04.06.2017.
 */
public class Developer {
    private int id;
    private String name;
    private String surname;
    private int salary;
    private List<Skill> skillList;
    private int projectID;

    public Developer(String name, String surname, int salary, List<Skill> skillList) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.skillList = skillList;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    @Override
    public String toString() {
        return "Имя " + name +
                ", Фамилия " + surname +
                ", зарплата " + salary +
                ", список навыков " + skillList;
    }
}
