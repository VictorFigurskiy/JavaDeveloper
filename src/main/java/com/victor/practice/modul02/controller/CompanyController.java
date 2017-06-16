package com.victor.practice.modul02.controller;

import com.victor.practice.modul02.instance.Company;
import com.victor.practice.modul02.instance.Project;

import java.util.List;

/**
 * Created by Sonikb on 04.06.2017.
 */
public interface CompanyController {

    List<Company> readAllTable();

    Company addData(String companyName);

    void update(int id, String newCompanyName);

    void delete(int id, List<Project> projectList);
}
