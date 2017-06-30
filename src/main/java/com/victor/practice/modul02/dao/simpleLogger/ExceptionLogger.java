package com.victor.practice.modul02.dao.simpleLogger;

import java.io.*;
import java.util.Date;


/**
 * Created by Sonikb on 16.06.2017.
 */
public class ExceptionLogger {

    public static void initLogger(String exMessage) {
       /* try {
            String loggerFilePath = "src\\main\\resources\\LoggerFiles";
            PrintStream printStream = new PrintStream(new File(loggerFilePath, "Logger.txt"));
            System.setOut(printStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("src\\main\\resources\\LoggerFiles\\Logger.txt"), true)))) {
            Date nowDateTime = new Date();
            bw.write(nowDateTime.toString() + "\n" + exMessage + "\n");
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try (FileWriter fileWriter = new FileWriter("src\\main\\resources\\LoggerFiles\\Logger.txt",true)){
            fileWriter.write(exMessage + "\n");
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
