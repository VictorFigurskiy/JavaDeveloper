package com.victor.practice.modul02.dao;

import com.victor.practice.modul02.instance.Customer;
import com.victor.practice.modul02.instance.Project;

import java.util.List;

/**
 * Created by Sonikb on 11.06.2017.
 */
public interface CustomerDAO {
    Customer save(Customer customer);

    Customer read(int id);

    void update(int id, Customer customer);

    void delete(int id, List<Project> projectList);

    List<Customer> readAllTable();
}
