package com.victor.practice.modul02.consoleView;

import com.victor.practice.modul02.API.API;
import com.victor.practice.modul02.dao.simpleLogger.ExeptionLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Created by Sonikb on 04.06.2017.
 */
public class ConsoleHelper {
    private API api;
    private BufferedReader buffRead;

    public ConsoleHelper(API api, BufferedReader buffRead) {
        this.api = api;
        this.buffRead = buffRead;
    }

    public void chooseMainOperations() {

        while (true) {

            System.out.println("*-------------------------------------------------*\n" +
                    "*------> Система работы с SQL базой данных <------*\n" +
                    "*-------------------------------------------------*");

            System.out.println("1. * Меню вывода данных из БД");
            System.out.println("2. * Меню добавления данных в БД");
            System.out.println("3. * Меню изменения данных в БД");
            System.out.println("4. * Меню удаления из БД");
            System.out.println("0. * ВЫХОД");
            System.out.println("*-------------------------------------------*");

            System.out.println("\nВведите номер операции которую вы хотите произвести!!!");
            try {
                switch (Integer.parseInt(buffRead.readLine())) {
                    case 1:
                        System.out.println("*-------------------------------------------------*\n" +
                                "*------> Меню выбора таблицы для просмотра <------*\n" +
                                "*-------------------------------------------------*");
                        readOperations();
                        break;
                    case 2:
                        System.out.println("\n*-----------------------------------------------------*\n" +
                                "*-----------> Меню добавления данных в БД <-----------*\n" +
                                "*-----------------------------------------------------*\n");
                        addOperations();
                        break;
                    case 3:
                        System.out.println("\n*-----------------------------------------------------*\n" +
                                "*-----------> Меню изменения данных в БД <-----------*\n" +
                                "*-----------------------------------------------------*\n");
                        updateOperations();
                        break;
                    case 4:
                        System.out.println("\n*-----------------------------------------------------*\n" +
                                "*-----------> Редактирование данных отеля <-----------*\n" +
                                "*-----------------------------------------------------*\n");
                        deleteOperations();
                        break;
                    case 0:
                        System.out.println("\n*------------------------------------------------------------------------------*\n" +
                                "*---------------> Программа завершена, все изменения сохранены! <--------------*\n" +
                                "*------------------------------------------------------------------------------*\n");
                        return;
                    default:
                        System.out.println("Не верный номер операции! Повторите попытку!" + " \nДля выхода нажмите \"0\"");
                }
            } catch (IOException e) {
                System.err.println("Ошибка ввода/вывода данных!");
                ExeptionLogger.initLogger(e.toString());
            } catch (NoSuchElementException | NumberFormatException n) {
                System.out.println("Команда введена неверно! Повторите выбор!" + " \nДля выхода нажмите \"0\"");
            }
        }
    }

    private void readOperations() {
        while (true) {
            System.out.println("1. * Считать все данные по заказчикам");
            System.out.println("2. * Считать все данные по компаниям");
            System.out.println("3. * Считать все данные по проектам");
            System.out.println("4. * Считать все данные по разработчикам");
            System.out.println("5. * Считать все данные по навыкам");
            System.out.println("Для возврата в предыдущее меню введите \"0\"!");

            System.out.println("\nВведите номер операции которую вы хотите произвести:");

            try {
                int tableNumber = Integer.parseInt(buffRead.readLine());
                if (tableNumber == 0) {
                    return;
                }
                api.readAllTable(tableNumber);
                System.out.println("Для повторного выбора таблиц для просмотра нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (IOException e) {
                System.err.println("Ошибка ввода/вывода данных!");
                ExeptionLogger.initLogger(e.toString());
            }

        }
    }

    private void addOperations() {
        while (true) {
            System.out.println("1. * Добавить заказчика");
            System.out.println("2. * Добавить компанию");
            System.out.println("3. * Добавить проект");
            System.out.println("4. * Добавить разработчика");
            System.out.println("5. * Добавить навик");
            System.out.println("Для возврата в предыдущее меню введите \"0\"!");

            System.out.println("\nВведите номер операции которую вы хотите произвести:");

            try {
                int tableNumber = Integer.parseInt(buffRead.readLine());
                if (tableNumber == 0) {
                    return;
                }
                api.addOperations(tableNumber);
                System.out.println("Для повторного выбора таблиц для добавления нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (IOException e) {
                System.err.println("Ошибка ввода/вывода данных!");
                ExeptionLogger.initLogger(e.toString());
            }
        }
    }

    private void updateOperations() {
        while (true) {
            System.out.println("1. * Изменить данные про заказчика");
            System.out.println("2. * Изменить данные про компанию");
            System.out.println("3. * Изменить данные про проект");
            System.out.println("4. * Изменить данные про разработчика");
            System.out.println("5. * Изменить данные про навык");
            System.out.println("Для возврата в предыдущее меню введите \"0\"!");

            System.out.println("\nВведите номер операции которую вы хотите произвести:");

            try {
                int tableNumber = Integer.parseInt(buffRead.readLine());
                if (tableNumber == 0) {
                    return;
                }
                api.updateOperations(tableNumber);
                System.out.println("Для повторного выбора таблиц для изменения нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (IOException e) {
                System.err.println("Ошибка ввода/вывода данных!");
                ExeptionLogger.initLogger(e.toString());
            }

        }
    }

    private void deleteOperations() {
        while (true) {
            System.out.println("1. * Удалить заказчика");
            System.out.println("2. * Удалить компанию");
            System.out.println("3. * Удалить проект");
            System.out.println("4. * Удалить разработчика");
            System.out.println("5. * Удалить навык");
            System.out.println("Для возврата в предыдущее меню введите \"0\"!");

            System.out.println("\nВведите номер операции которую вы хотите произвести:");

            try {
                int tableNumber = Integer.parseInt(buffRead.readLine());
                if (tableNumber == 0) {
                    return;
                }
                api.deleteOperations(tableNumber);
                System.out.println("Для повторного выбора таблиц для удаления нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (IOException e) {
                System.err.println("Ошибка ввода/вывода данных!");
                ExeptionLogger.initLogger(e.toString());
            }

        }
    }
}
