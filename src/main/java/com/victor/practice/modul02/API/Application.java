package com.victor.practice.modul02.API;

import com.victor.practice.modul02.controller.*;
import com.victor.practice.modul02.instance.*;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Sonikb on 07.06.2017.
 */
public class Application implements API {
    private CompanyController companyController;
    private CustomerController customerController;
    private DeveloperController developerController;
    private ProjectController projectController;
    private SkillController skillController;
    private BufferedReader br;

    private final static Logger logger = Logger.getLogger(Application.class);

    public Application(CompanyController companyController, CustomerController customerController, DeveloperController developerController, ProjectController projectController, SkillController skillController, BufferedReader br) {
        this.companyController = companyController;
        this.customerController = customerController;
        this.developerController = developerController;
        this.projectController = projectController;
        this.skillController = skillController;
        this.br = br;
    }

    public void readAllTable(int tableNumber) {
        try {
            switch (tableNumber) {

                case 1:
                    List<Customer> customersList = customerController.readAllTable();
                    System.out.println("Список заказчиков в системе:");
                    customersList.forEach(System.out::println);
                    break;

                case 2:
                    List<Company> companyList = companyController.readAllTable();
                    System.out.println("Список компаний в системе:");
                    companyList.forEach(System.out::println);
                    break;

                case 3:
                    List<Project> projectList = projectController.readAllTable();
                    System.out.println("Список проектов в системе:");
                    projectList.stream().map(project -> {
                        System.out.println("------------------------------------------------------------------------------\n");
                        System.out.println("\n" + project);
                        System.out.println("------------------------------------------------------------------------------");
                        return project.getDeveloperList();
                    }).forEach(developers -> developers.stream()
                            .sorted(Comparator.comparing(Developer::getName))
                            .forEach(System.out::println));
                    break;

                case 4:
                    List<Developer> developerList = developerController.readAllTable();
                    System.out.println("Список разработчиков в системе:");
                    developerList.forEach(System.out::println);
                    break;

                case 5:
                    List<Skills> skillsList = skillController.readAllTable();
                    System.out.println("Список навыков в системе:");
                    skillsList.forEach(System.out::println);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Не верный номер операции! Повторите попытку! \nДля выхода нажмите \"0\"");
            }
        } catch (NoSuchElementException | NumberFormatException n) {
            System.out.println("Команда введена неверно! Повторите выбор!" + " \nДля выхода нажмите \"0\"");
        }
    }

    public void addOperations(int tableNumber) {
        try {

            switch (tableNumber) {

                case 1:
                    System.out.println("Для добавления нового заказчика введите его название:");
                    String customerName = br.readLine();
                    Customer customer = customerController.addData(customerName);
                    System.out.println("Новый заказчик " + customer.getCustomerName() + " успешно сохранен!");
                    break;


                case 2:
                    System.out.println("Для добавления новой компании введите ее название:");
                    String companyName = br.readLine();
                    Company company = companyController.addData(companyName);
                    System.out.println("Новый заказчик " + company.getCompanyName() + " успешно сохранен!");
                    break;


                case 3:
                    System.out.println("Для добавления нового проекта выберите номер заказчика проекта:");
                    List<Customer> customerList = customerController.readAllTable();
                    int i = 1;
                    for (Customer customerFromList : customerList) {
                        System.out.println(i++ + ". " + customerFromList.getCustomerName());
                    }
                    int customerIndex = Integer.parseInt(br.readLine());
                    Customer customerForProject = customerList.get(customerIndex - 1);


                    System.out.println("Введите название нового проекта:");
                    String projectName = br.readLine();
                    System.out.println("Введите стоимость нового проекта:");
                    int projectCost = Integer.parseInt(br.readLine());


                    System.out.println("Выберите компанию которая будет разрабатывать проект:");
                    List<Company> companyList = companyController.readAllTable();
                    i = 1;
                    for (Company companyFromList : companyList) {
                        System.out.println(i++ + ". " + companyFromList.getCompanyName());
                    }
                    int companyIndex = Integer.parseInt(br.readLine());
                    Company companyForProject = companyList.get(companyIndex - 1);


                    System.out.println("Добавление разработчиков на новый проект!");
                    List<Developer> developerList = new ArrayList<>();
                    int back = -1;
                    while (back != 0) {
                        System.out.println("Введите имя разработчика:");
                        String name = br.readLine();
                        System.out.println("Введите фамилию:");
                        String surname = br.readLine();
                        System.out.println("Введите зарплату разработчика за месяц:");
                        int salary = Integer.parseInt(br.readLine());

                        System.out.println("Выберите номер навыка для разработчика из списка:");
                        List<Skills> fullSkillsList = skillController.readAllTable();
                        i = 1;
                        int skillIndex = -1;
                        List<Skills> devSkillsList = new ArrayList<>();
                        while (skillIndex != 0) {
                            for (Skills skills : fullSkillsList) {
                                System.out.println(i++ + ". " + skills.getSkillName());
                            }
                            i = 1;
                            skillIndex = Integer.parseInt(br.readLine());
                            if (skillIndex != 0) {
                                Skills devSkills = fullSkillsList.get(skillIndex - 1);
                                devSkillsList.add(devSkills);
                            }
                            System.out.println("Для окончания добавления навыков введите 0, для дальнейшего добавления навыков выберите навык:");
                        }
                        developerList.add(new Developer(name, surname, salary, devSkillsList));
                        System.out.println("Для продолжения добавления разработчиков нажмите 1, в противном случае данные по проекту будут сохранены и Вы перейдете в главное меню");
                        String answer = br.readLine();
                        switch (answer) {
                            case "1":
                                continue;
                            default:
                                back = 0;
                        }
                    }
                    Project projectSave = projectController.addData(projectName, projectCost, companyForProject, customerForProject, developerList);
                    System.out.println("Новый проект " + projectSave.getProjectName() + " успешно сохранен!");
                    break;


                case 4:
                    System.out.println("Выберите номер проекта из списка к которому вы хотели бы добавить данного разработчика:");
                    List<Project> projectList = projectController.readAllTable();
                    i = 1;
                    for (Project project : projectList) {
                        System.out.println(i++ + ". " + project.getProjectName());
                    }
                    int projectIndex = Integer.parseInt(br.readLine());
                    int projectID = projectList.get(projectIndex - 1).getId();

                    System.out.println("Для добавления нового разработчика введите его имя:");
                    String name = br.readLine();
                    System.out.println("Введите фамилию:");
                    String surname = br.readLine();
                    System.out.println("Введите зарплату разработчика за месяц:");
                    int salary = Integer.parseInt(br.readLine());

                    System.out.println("Выберите номер навыка для разработчика из списка:");
                    List<Skills> fullSkillsList = skillController.readAllTable();
                    i = 1;
                    int skillIndex = -1;
                    List<Skills> devSkillsList = new ArrayList<>();
                    while (skillIndex != 0) {
                        for (Skills skills : fullSkillsList) {
                            System.out.println(i++ + ". " + skills.getSkillName());
                        }
                        i = 1;
                        skillIndex = Integer.parseInt(br.readLine());
                        if (skillIndex != 0) {
                            Skills devSkills = fullSkillsList.get(skillIndex - 1);
                            devSkillsList.add(devSkills);
                        }
                        System.out.println("Для сохранения и выхода нажмите 0, для дальнейшего добавления навыков выберите навык:");
                    }

                    Developer developer = developerController.addData(name, surname, salary, devSkillsList, projectID);
                    System.out.println("Новый разработчик " + developer.getName() + " " + developer.getSurname() + " успешно сохранен!");
                    break;


                case 5:
                    System.out.println("Для добавления нового навыка введите его название:");
                    String skillName = br.readLine();
                    Skills skills = skillController.addData(skillName);
                    System.out.println("Новый навык " + skills.getSkillName() + " успешно сохранен!");
                    break;


                case 0:
                    break;

                default:
                    System.out.println("Не верный номер операции! Повторите попытку! \nДля выхода нажмите \"0\"");
            }
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода данных!");
            logger.error(e);
        } catch (NoSuchElementException | NumberFormatException n) {
            System.out.println("Команда введена неверно! Повторите выбор!" + " \nДля выхода нажмите \"0\"");
        }
    }

    @Override
    public void updateOperations(int tableNumber) {
        try {
            switch (tableNumber) {

                case 1:
                    System.out.println("Выберите номер заказчика из списка для изменения его данных");
                    List<Customer> customerList = customerController.readAllTable();
                    int i = 1;
                    for (Customer customer : customerList) {
                        System.out.println(i++ + ". " + customer);
                    }
                    int customerIndex = Integer.parseInt(br.readLine());
                    Customer oldCustomer = customerList.get(customerIndex - 1);
                    System.out.println("Название компании заказчика " + oldCustomer.getCustomerName() + " изменяем на:");
                    String newCustomerName = br.readLine();
                    customerController.update(oldCustomer.getId(), newCustomerName);
                    System.out.println("Изменения сохранены!");
                    break;


                case 2:
                    System.out.println("Выберите номер компании из списка для изменения ее данных");
                    List<Company> companyList = companyController.readAllTable();
                    i = 1;
                    for (Company company : companyList) {
                        System.out.println(i++ + ". " + company);
                    }
                    int companyIndex = Integer.parseInt(br.readLine());
                    Company oldCompany = companyList.get(companyIndex - 1);
                    System.out.println("Название компании " + oldCompany.getCompanyName() + " изменяем на:");
                    String newCompanyName = br.readLine();
                    companyController.update(oldCompany.getId(), newCompanyName);
                    System.out.println("Изменения сохранены!");
                    break;


                case 3:
                    System.out.println("Выберите номер проекта из списка для изменения его данных");
                    List<Project> newProjectList = projectController.readAllTable();
                    i = 1;
                    for (Project project : newProjectList) {
                        System.out.println(i++ + ". " + project.getProjectName());
                    }
                    int forProjectIndex = Integer.parseInt(br.readLine());
                    Project oldProject = newProjectList.get(forProjectIndex - 1);
                    System.out.println("Название проекта " + oldProject.getProjectName() + " изменяем на:");
                    String newProjectName = br.readLine();
                    System.out.println("Стоимость проекта " + oldProject.getProjectCost() + " изменяем на:");
                    int newProjectCost = Integer.parseInt(br.readLine());
                    System.out.println("Сейчас заказчик проекта " + oldProject.getCustomer().getCustomerName() + ". Для изменения выберите заказчика из списка в системе:");
                    List<Customer> customerListForShow = customerController.readAllTable();
                    i = 1;
                    for (Customer customer : customerListForShow) {
                        System.out.println(i++ + ". " + customer.getCustomerName());
                    }
                    int newCustomerIndex = Integer.parseInt(br.readLine());
                    Customer newCustomerForProject = customerListForShow.get(newCustomerIndex - 1);
                    System.out.println("Компания занимающаяся разработкой проекта " + oldProject.getCompany().getCompanyName() + ". Для изменения выберите компанию из списка в системе:");
                    List<Company> companyListForShow = companyController.readAllTable();
                    i = 1;
                    for (Company company : companyListForShow) {
                        System.out.println(i++ + ". " + company.getCompanyName());
                    }
                    int companyForProject = Integer.parseInt(br.readLine());
                    Company newCompanyForProjects = companyListForShow.get(companyForProject - 1);
                    projectController.update(oldProject.getId(), newProjectName, newProjectCost, newCustomerForProject, newCompanyForProjects, oldProject.getDeveloperList());
                    System.out.println("Изменения сохранены!");
                    break;


                case 4:
                    System.out.println("Выберите номер разработчика из списка для изменения его данных");
                    List<Developer> developerList = developerController.readAllTable();
                    i = 1;
                    for (Developer developer : developerList) {
                        System.out.println(i++ + ". " + developer);
                    }
                    int developerIndex = Integer.parseInt(br.readLine());
                    Developer developer = developerList.get(developerIndex - 1);
                    System.out.println("Имя разработчика " + developer.getName() + " изменяем на:");
                    String newDevName = br.readLine();
                    System.out.println("Фамилию разработчика " + developer.getSurname() + " изменяем на:");
                    String newDevSurname = br.readLine();
                    System.out.println("Зароботная плата " + developer.getSalary() + " изменяем на:");
                    int newSalary = Integer.parseInt(br.readLine());
                    Project project;
                    if (developer.getProjectID() != 0) {
                        project = projectController.readByID(developer.getProjectID());
                    } else project = projectController.readByID(developer.getProject().getId());
                    System.out.println("Разработчик работает над проектом " + project.getProjectName());
                    System.out.println("Если хотите изменить проект для разработчика нажмите 1, при нажатии любой другой команды все указанные данные по разработчику будут изменены!");
                    String answer = br.readLine();
                    if ("1".equals(answer)) {
                        List<Project> projectList = projectController.readAllTable();
                        i = 1;
                        for (Project projectFromAll : projectList) {
                            System.out.println(i++ + ". " + projectFromAll.getProjectName());
                        }
                        int projectIndex = Integer.parseInt(br.readLine());
                        Project projectFromDB = projectList.get(projectIndex - 1);
                        int newProjectID = projectList.get(projectIndex - 1).getId();
                        if (developer.getProjectID() != 0) {
                            developerController.update(developer.getId(), newDevName, newDevSurname, newSalary, developer.getSkillsList(), newProjectID);
                        } else
                            developerController.update(developer.getId(), newDevName, newDevSurname, newSalary, developer.getSkillsList(), projectFromDB);
                    } else if (developer.getProjectID() != 0) {
                        developerController.update(developer.getId(), newDevName, newDevSurname, newSalary, developer.getSkillsList(), developer.getProjectID());
                    } else
                        developerController.update(developer.getId(), newDevName, newDevSurname, newSalary, developer.getSkillsList(), project);
                    System.out.println("Изменения сохранены!");
                    break;


                case 5:
                    System.out.println("Выберите номер навыка из списка для изменения его данных");
                    List<Skills> skillsList = skillController.readAllTable();
                    i = 1;
                    for (Skills skills : skillsList) {
                        System.out.println(i++ + ". " + skills);
                    }
                    int skillIndex = Integer.parseInt(br.readLine());
                    Skills oldSkills = skillsList.get(skillIndex - 1);
                    System.out.println("Название навыка " + oldSkills.getSkillName() + " изменяем на:");
                    String newSkillName = br.readLine();
                    skillController.update(oldSkills.getId(), newSkillName);
                    System.out.println("Изменения сохранены!");
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Не верный номер операции! Повторите попытку! \nДля выхода нажмите \"0\"");
            }
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода данных!");
            logger.error(e);
        } catch (NoSuchElementException | NumberFormatException n) {
            System.out.println("Команда введена неверно! Повторите выбор!" + " \nДля выхода нажмите \"0\"");
        }
    }

    @Override
    public void deleteOperations(int tableName) {
        try {
            switch (tableName) {

                case 1:
                    System.out.println("Выберите номер заказчика из списка для удаления записи по нему");
                    List<Customer> customerList = customerController.readAllTable();
                    int i = 1;
                    for (Customer customer : customerList) {
                        System.out.println(i++ + ". " + customer);
                    }
                    int customerIndex = Integer.parseInt(br.readLine());
                    Customer customer = customerList.get(customerIndex - 1);
                    List<Project> customerProjectList = projectController.readProjectsByCustomerID(customer.getId());
                    System.out.println("Вы уверены что хотите удалить заказчика \"" + customer + "\"?");
                    System.out.println("Также вместе с ним будут удалены проекты и данные связанные с ними:");
                    customerProjectList.stream()
                            .map(Project::getProjectName)
                            .forEach((x) -> System.out.println("Проект " + x));
                    System.out.println("\nДля подтверждения выбора введите 1 или 0 для отмены и возврата в предидущее меню:");
                    System.out.println("Сделайте Ваш выбор:");
                    while (true) {
                        int choice = Integer.parseInt(br.readLine());
                        if (choice == 1) {
                            customerController.delete(customer.getId(), customerProjectList);
                            System.out.println("Заказчик и все данные связанные с ним удалены!");
                            break;
                        } else if (choice == 0) {
                            break;
                        }
                        System.out.println("Команда введена не верно, повторите попытку:");
                    }
                    break;

                case 2:
                    System.out.println("Выберите номер компании из списка для удаления записи по нему");
                    List<Company> companyList = companyController.readAllTable();
                    i = 1;
                    for (Company company : companyList) {
                        System.out.println(i++ + ". " + company);
                    }
                    int companyIndex = Integer.parseInt(br.readLine());
                    Company company = companyList.get(companyIndex - 1);
                    List<Project> companyProjectList = projectController.readProjectsByCompanyID(company.getId());
                    System.out.println("Вы уверены что хотите удалить заказчика \"" + company + "\"?");
                    System.out.println("Также вместе с ним будут удалены проекты и данные связанные с ними:");
                    companyProjectList.stream()
                            .map(Project::getProjectName)
                            .forEach((x) -> System.out.println("Проект " + x));
                    System.out.println("\nДля подтверждения выбора введите 1 или 0 для отмены и возврата в предидущее меню:");
                    System.out.println("Сделайте Ваш выбор:");
                    while (true) {
                        int choice = Integer.parseInt(br.readLine());
                        if (choice == 1) {
                            companyController.delete(company.getId(), companyProjectList);
                            System.out.println("Компания и все данные связанные с ней удалены!");
                            break;
                        } else if (choice == 0) {
                            break;
                        }
                        System.out.println("Команда введена не верно, повторите попытку:");
                    }
                    break;

                case 3:
                    System.out.println("Выберите номер проекта из списка для удаления записи по нему");
                    List<Project> projectList = projectController.readAllTable();
                    i = 1;
                    for (Project project : projectList) {
                        System.out.println(i++ + ". " + project.getProjectName());
                    }
                    int projectIndex = Integer.parseInt(br.readLine());
                    Project project = projectList.get(projectIndex - 1);
                    System.out.println("Вы уверены что хотите удалить проект \"" + project.getProjectName() + "\"?");
                    System.out.println("Также вместе с ним будут удалены разработчики которые закреплены за проектом:");
                    project.getDeveloperList()
                            .forEach(developer -> System.out.println("Имя " + developer.getName() + ", Фамилия " + developer.getSurname()));
                    System.out.println("\nДля подтверждения выбора введите 1 или 0 для отмены и возврата в предидущее меню:");
                    System.out.println("Сделайте Ваш выбор:");
                    while (true) {
                        int choice = Integer.parseInt(br.readLine());
                        if (choice == 1) {
                            projectController.delete(project);
                            System.out.println("Проект и все данные связанные с ним удалены!");
                            break;
                        } else if (choice == 0) {
                            break;
                        }
                        System.out.println("Команда введена не верно, повторите попытку:");
                    }
                    break;

                case 4:
                    System.out.println("Выберите номер разработчика из списка для удаления записи по нему");
                    List<Developer> developerList = developerController.readAllTable();
                    i = 1;
                    for (Developer developer : developerList) {
                        System.out.println(i++ + ". " + developer.getName() + " " + developer.getSurname());
                    }
                    int developerIndex = Integer.parseInt(br.readLine());
                    Developer developer = developerList.get(developerIndex - 1);
                    System.out.println("Вы уверены что хотите удалить разработчика \"" + developer.getName() + " " + developer.getSurname() + "\"?" + developer.getId());
                    System.out.println("\nДля подтверждения выбора введите 1 или 0 для отмены и возврата в предидущее меню:");
                    System.out.println("Сделайте Ваш выбор:");
                    while (true) {
                        int choice = Integer.parseInt(br.readLine());
                        if (choice == 1) {
                            developerController.delete(developer.getId());
                            System.out.println("Разработчик удален!");
                            break;
                        } else if (choice == 0) {
                            break;
                        }
                        System.out.println("Команда введена не верно, повторите попытку:");
                    }
                    break;

                case 5:
                    System.out.println("Выберите номер навыка из списка для удаления записи по нему");
                    List<Skills> skillsList = skillController.readAllTable();
                    i = 1;
                    for (Skills skills : skillsList) {
                        System.out.println(i++ + ". " + skills);
                    }
                    int skillIndex = Integer.parseInt(br.readLine());
                    Skills skills = skillsList.get(skillIndex - 1);
                    System.out.println("Вы уверены что хотите удалить разработчика \"" + skills + "\"?");
                    System.out.println("\nДля подтверждения выбора введите 1 или 0 для отмены и возврата в предидущее меню:");
                    System.out.println("Сделайте Ваш выбор:");
                    while (true) {
                        int choice = Integer.parseInt(br.readLine());
                        if (choice == 1) {
                            skillController.delete(skills.getId());
                            System.out.println("Навык удален!");
                            break;
                        } else if (choice == 0) {
                            break;
                        }
                        System.out.println("Команда введена не верно, повторите попытку:");
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Не верный номер операции! Повторите попытку! \nДля выхода нажмите \"0\"");
            }
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода данных!");
            logger.error(e);
        } catch (NoSuchElementException | NumberFormatException n) {
            System.out.println("Команда введена неверно! Повторите выбор!" + " \nДля выхода нажмите \"0\"");
        }
    }
}
