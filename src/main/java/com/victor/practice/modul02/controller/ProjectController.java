package com.victor.practice.modul02.controller;

import com.victor.practice.modul02.instance.Company;
import com.victor.practice.modul02.instance.Customer;
import com.victor.practice.modul02.instance.Developer;
import com.victor.practice.modul02.instance.Project;

import java.util.List;

/**
 * Created by Sonikb on 08.06.2017.
 */
public interface ProjectController {
    List<Project> readAllTable();

    Project addData(String projectName, int projectCost, Company company, Customer customer, List<Developer> developerList);

    Project readByID(int projectID);

    void update(int id, String projectName, int projectCost, Customer customer, Company company, List<Developer> developerList);

    List<Project> readProjectsByCompanyID (int companyID);

    List<Project> readProjectsByCustomerID(int customerID);

    void delete(Project project);
}
