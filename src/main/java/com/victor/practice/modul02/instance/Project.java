package com.victor.practice.modul02.instance;

import java.util.List;

/**
 * Created by Sonikb on 04.06.2017.
 */
public class Project {
    private int id;
    private String projectName;
    private int projectCost;
    private Company company;
    private Customer customer;
    private List<Developer> developerList;

    public Project(String projectName, int projectCost, Company company, Customer customer, List<Developer> developerList) {
        this.projectName = projectName;
        this.projectCost = projectCost;
        this.company = company;
        this.customer = customer;
        this.developerList = developerList;
    }

    public List<Developer> getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(List<Developer> developerList) {
        this.developerList = developerList;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(int projectCost) {
        this.projectCost = projectCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Название проекта \"" + projectName + "\"" +
                ", стоимость проекта " + projectCost +
                ", компания подрядчик \"" + company + "\"" +
                ", заказчик проекта \"" + customer + "\"";
    }
}
