package com.victor.practice.modul02.dao;

import com.victor.practice.modul02.instance.Developer;

import java.util.List;

/**
 * Created by Sonikb on 11.06.2017.
 */
public interface DeveloperDAO {
    Developer save(Developer developer, int projectID);

    Developer read(int id);

    void update(int id, Developer developer);

    void delete(int id);

    List<Developer> readAllTable();
}
