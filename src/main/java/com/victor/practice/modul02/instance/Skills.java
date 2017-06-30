package com.victor.practice.modul02.instance;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by Sonikb on 04.06.2017.
 */
@Entity
@NamedQuery(name = "Skills.findAll",query = "select s from Skills s")
@Table
public class Skills implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "skills")
    private String skillName;

    public Skills() {
    }

    public Skills(String skillName) {
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
