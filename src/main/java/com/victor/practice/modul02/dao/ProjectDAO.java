package com.victor.practice.modul02.dao;

import com.victor.practice.modul02.instance.Project;

import java.util.List;

/**
 * Created by Sonikb on 11.06.2017.
 */
public interface ProjectDAO {
    Project save(Project obj);

    Project read(int id);

    void update(int id, Project obj);

    void delete(Project project);

    List<Project> readAllTable();

    List<Project> readProjectsByCompanyID (int companyID);

    List<Project> readProjectsByCustomerID(int customerID);
}
