package com.victor.practice.modul02.dao;

import com.victor.practice.modul02.instance.Company;
import com.victor.practice.modul02.instance.Project;

import java.util.List;

/**
 * Created by Sonikb on 11.06.2017.
 */
public interface CompanyDAO {
    Company save(Company obj);

    Company read(int id);

    void update(int id, Company obj);

    void delete(int id, List<Project> projectList);

    List<Company> readAllTable();
}
