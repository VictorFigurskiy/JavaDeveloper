package com.victor.practice.modul02.controller;

import com.victor.practice.modul02.dao.CompanyDAO;
import com.victor.practice.modul02.instance.Company;
import com.victor.practice.modul02.instance.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonikb on 07.06.2017.
 */
public class CompanyControllerImpl implements CompanyController {
    private CompanyDAO dao;

    public CompanyControllerImpl(CompanyDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Company> readAllTable() {
        List<Company> companyList = dao.readAllTable();
        if (companyList == null || companyList.isEmpty()) {
            System.out.println("Список пуст!");
            return new ArrayList<>();
        }
        return companyList;
    }

    @Override
    public Company addData(String companyName) {
        return dao.save(new Company(companyName));
    }

    @Override
    public void update(int id, String newCompanyName) {
        dao.update(id, new Company(newCompanyName));
    }

    @Override
    public void delete(int id, List<Project> projectList) {
        dao.delete(id, projectList);
    }
}
