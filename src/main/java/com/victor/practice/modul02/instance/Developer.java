package com.victor.practice.modul02.instance;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Sonikb on 04.06.2017.
 */
@Entity
@NamedQuery(name = "Developer.findAll", query = "select d from Developer d")
@Table(name = "developers")
public class Developer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private int salary;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Skills> skillsList;
    @Transient
    private int projectID;
    @ManyToOne
    private Project project;

    public Developer() {
    }

    public Developer(String name, String surname, int salary, List<Skills> skillsList) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.skillsList = skillsList;
    }

    public List<Skills> getSkillsList() {
        return skillsList;
    }

    public void setSkillsList(List<Skills> skillsList) {
        this.skillsList = skillsList;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Имя " + name +
                ", Фамилия " + surname +
                ", зарплата " + salary +
                ", список навыков " + skillsList;
    }
}
