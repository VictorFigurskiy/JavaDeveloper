package com.victor.practice.modul02.controller;


import com.victor.practice.modul02.dao.CustomerDAO;
import com.victor.practice.modul02.instance.Customer;
import com.victor.practice.modul02.instance.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonikb on 07.06.2017.
 */
public class CustomerControllerImpl implements CustomerController {
    private CustomerDAO dao;

    public CustomerControllerImpl(CustomerDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Customer> readAllTable() {
        List<Customer> customersList = dao.readAllTable();
        if (customersList == null || customersList.isEmpty()){
            System.out.println("Список пуст!");
            return new ArrayList<>();
        }
        return dao.readAllTable();
    }

    @Override
    public Customer addData(String customerName) {
        return dao.save(new Customer(customerName));
    }

    @Override
    public void update(int id, String newCustomerName) {
        dao.update(id,new Customer(newCustomerName));
    }

    @Override
    public void delete(int id, List<Project> customerProjectList) {
        dao.delete(id, customerProjectList);
    }
}
