package com.victor.practice.modul02.API;

/**
 * Created by Sonikb on 04.06.2017.
 */
public interface API {

    void readAllTable(int tableNumber);

    void addOperations(int tableNumber);

    void updateOperations(int tableNumber);

    void deleteOperations(int tableName);
}
