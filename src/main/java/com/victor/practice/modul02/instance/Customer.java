package com.victor.practice.modul02.instance;

/**
 * Created by Sonikb on 04.06.2017.
 */
public class Customer {
    private int id;
    private String customerName;

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return customerName;
    }
}
