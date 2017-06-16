package com.victor.practice.modul02.controller;


import com.victor.practice.modul02.dao.ProjectDAO;
import com.victor.practice.modul02.instance.Company;
import com.victor.practice.modul02.instance.Customer;
import com.victor.practice.modul02.instance.Developer;
import com.victor.practice.modul02.instance.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonikb on 07.06.2017.
 */
public class ProjectControllerImpl implements ProjectController {
    private ProjectDAO dao;

    public ProjectControllerImpl(ProjectDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Project> readAllTable() {
        List<Project> projectList = dao.readAllTable();
        if (projectList == null || projectList.isEmpty()) {
            System.out.println("Список пуст!");
            return new ArrayList<>();
        }
        return dao.readAllTable();
    }

    @Override
    public Project addData(String projectName, int projectCost, Company company, Customer customer, List<Developer> developerList) {
        return dao.save(new Project(projectName, projectCost, company, customer, developerList));
    }

    @Override
    public Project readByID(int projectID) {
        return dao.read(projectID);
    }

    @Override
    public void update(int id, String projectName, int projectCost, Customer customer, Company company, List<Developer> developerList) {
        dao.update(id, new Project(projectName, projectCost, company, customer, developerList));
    }

    @Override
    public List<Project> readProjectsByCompanyID(int companyID) {
        return dao.readProjectsByCompanyID(companyID);
    }

    @Override
    public List<Project> readProjectsByCustomerID(int customerID) {
        return dao.readProjectsByCustomerID(customerID);
    }

    @Override
    public void delete(Project project) {
        dao.delete(project);
    }

}
