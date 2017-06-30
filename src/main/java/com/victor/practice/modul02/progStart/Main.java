package com.victor.practice.modul02.progStart;

import com.victor.practice.modul02.API.API;
import com.victor.practice.modul02.API.Application;
import com.victor.practice.modul02.consoleView.ConsoleHelper;
import com.victor.practice.modul02.controller.*;
import com.victor.practice.modul02.dao.*;
import com.victor.practice.modul02.dao.jpaHibernateDAO.*;
import com.victor.practice.modul02.dao.simpleLogger.ExceptionLogger;
import com.victor.practice.modul02.dao.sqlDAO.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Sonikb on 04.06.2017.
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        // Для запуска проекта на JDBC с использованием ConnectionPool!
        //---------------------------------------------------------------
        /*final String URL = "jdbc:mysql://localhost:3306/developers_info_db?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String USER = "root";
        final String PASSWORD = "root";

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(URL);
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASSWORD);
        basicDataSource.setInitialSize(5);

        CompanyDAO companyDAO = new CompanyDAOimpl(basicDataSource);
        CustomerDAO customerDAO = new CustomerDAOimpl(basicDataSource);
        DeveloperDAO developerDAO = new DeveloperDAOimpl(basicDataSource);
        ProjectDAO projectDAO = new ProjectDAOimpl(basicDataSource);
        SkillsDAO skillDAO = new SkillsDAOimpl(basicDataSource);*/
        //----------------------------------------------------------------

        // Для запуска проекта на основе JPA c использованием Hibernate в качестве провайдера!
        //----------------------------------------------------------------
        CompanyDAO companyDAO = new JpaCompanyDAO();
        CustomerDAO customerDAO = new JpaCustomerDAO();
        DeveloperDAO developerDAO = new JpaDeveloperDAO();
        ProjectDAO projectDAO = new JpaProjectDAO();
        SkillsDAO skillDAO = new JpaSkillsDAO();
        //----------------------------------------------------------------

        CompanyController companyController = new CompanyControllerImpl(companyDAO);
        CustomerController customerController = new CustomerControllerImpl(customerDAO);
        DeveloperController developerController = new DeveloperControllerImpl(developerDAO);
        ProjectController projectController = new ProjectControllerImpl(projectDAO);
        SkillController skillController = new SkillControllerImpl(skillDAO);

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            API api = new Application(companyController, customerController, developerController, projectController, skillController, bufferedReader);

            ConsoleHelper consoleHelper = new ConsoleHelper(api, bufferedReader);

            consoleHelper.chooseMainOperations();

        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода данных!");
            logger.error(e);
        }finally {
            JpaUtil.shutDown();
        }
    }
}
