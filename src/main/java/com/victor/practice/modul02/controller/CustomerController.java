package com.victor.practice.modul02.controller;

import com.victor.practice.modul02.instance.Customer;
import com.victor.practice.modul02.instance.Project;

import java.util.List;

/**
 * Created by Sonikb on 08.06.2017.
 */
public interface CustomerController {
    List<Customer> readAllTable();

    Customer addData(String customerName);

    void update(int id, String newCustomerName);

    void delete(int id, List<Project> customerProjectList);
}
