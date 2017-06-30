package com.victor.practice.modul02.instance;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sonikb on 04.06.2017.
 */
@Entity
@NamedQuery(name = "Company.findAll",query = "select c from Company c")
@Table(name = "companies")
public class Company implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "company_name",length = 55,nullable = true)
    private String companyName;

    public Company() {
    }

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
