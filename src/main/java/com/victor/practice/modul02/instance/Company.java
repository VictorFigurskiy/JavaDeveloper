package com.victor.practice.modul02.instance;

/**
 * Created by Sonikb on 04.06.2017.
 */
public class Company {
    private int id;
    private String companyName;

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return companyName;
    }
}
