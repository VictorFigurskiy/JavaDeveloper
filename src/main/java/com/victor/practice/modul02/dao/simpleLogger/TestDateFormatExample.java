package com.victor.practice.modul02.dao.simpleLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sonikb on 16.06.2017.
 */
public class TestDateFormatExample {
    public static void main(String[] args) {
        // Make a new Date object. It will be initialized to the current time.
        Date now = new Date();

        // See what toString() returns
        System.out.println(" 1. " + now.toString());

        // Next, try the default DateFormat
        System.out.println(" 2. " + DateFormat.getInstance().format(now));

        // And the default time and date-time DateFormats
        System.out.println(" 3. " + DateFormat.getTimeInstance().format(now));
        System.out.println(" 4. " +
                DateFormat.getDateTimeInstance().format(now));

        // Next, try the short, medium and long variants of the
        // default time format
        System.out.println(" 5. " +
                DateFormat.getTimeInstance(DateFormat.SHORT).format(now));
        System.out.println(" 6. " +
                DateFormat.getTimeInstance(DateFormat.MEDIUM).format(now));
        System.out.println(" 7. " +
                DateFormat.getTimeInstance(DateFormat.LONG).format(now));

        // For the default date-time format, the length of both the
        // date and time elements can be specified. Here are some examples:
        System.out.println(" 8. " + DateFormat.getDateTimeInstance(
                DateFormat.SHORT, DateFormat.SHORT).format(now));
        System.out.println(" 9. " + DateFormat.getDateTimeInstance(
                DateFormat.MEDIUM, DateFormat.SHORT).format(now));
        System.out.println("10. " + DateFormat.getDateTimeInstance(
                DateFormat.LONG, DateFormat.LONG).format(now));


        /*
        > java DateFormatExample1
        1. Tue Nov 04 20:14:11 EST 2003
        2. 11/4/03 8:14 PM
        3. 8:14:11 PM
        4. Nov 4, 2003 8:14:11 PM
        5. 8:14 PM
        6. 8:14:11 PM
        7. 8:14:11 PM EST
        8. 11/4/03 8:14 PM
        9. Nov 4, 2003 8:14 PM
        10. November 4, 2003 8:14:11 PM EST
        */





        // Для получения текущего системного времени достаточно выполнить:
        long curTime = System.currentTimeMillis();

        // Хотите значение типа Date, с этим временем?
        Date curDate = new Date(curTime);

        // Хотите строку в формате, удобном Вам?
        String curStringDate = new SimpleDateFormat("dd.MM.yyyy").format(curTime);

        // Хотите Date из строки, в которой дата с известным шаблоном?
        try {
            Date parsedDate = new SimpleDateFormat("dd.MM.yyyy").parse("16.04.2004");
        } catch (ParseException e) {
            e.printStackTrace();
        }







        // Make a String that has a date in it, with MEDIUM date format
        // and SHORT time format.
        String dateString = "Nov 4, 2003 8:14 PM";

        // Get the default MEDIUM/SHORT DateFormat
        DateFormat format =
                DateFormat.getDateTimeInstance(
                        DateFormat.MEDIUM, DateFormat.SHORT);

        // Parse the date
        try {
            Date date = format.parse(dateString);
            System.out.println("Original string: " + dateString);
            System.out.println("Parsed date    : " +
                    date.toString());
        }
        catch(ParseException pe) {
            System.out.println("ERROR: could not parse date in string \"" +
                    dateString + "\"");
        }


        /*> java DateFormatExample2
        Original string: Nov 4, 2003 8:14 PM
        Parsed date    : Tue Nov 04 20:14:00 EST 2003*/






        // Get the default MEDIUM/SHORT DateFormat
        DateFormat format1 =
                DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                        DateFormat.SHORT);

        // Read and parse input, stopping on a blank input line
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("ENTER DATE STRING: ");
            String dateString1 = reader.readLine();

            while ((dateString1 != null) && (dateString1.length() > 0)) {
                // Parse the date
                try {
                    Date date = format1.parse(dateString1);
                    System.out.println("Original string: " + dateString1);
                    System.out.println("Parsed date    : " +
                            date.toString());
                    System.out.println(); // Skip a line
                }
                catch(ParseException pe) {
                    System.out.println(
                            "ERROR: could not parse date in string \"" +
                                    dateString1 + "\"");
                }

                // Read another string
                System.out.print("ENTER DATE STRING: ");
                dateString1 = reader.readLine();
            }
        }
        catch(IOException ioe) {
            System.out.println("I/O Exception: " + ioe);
        }

        /*> java DateFormatExample3
        ENTER DATE STRING: Nov 4, 2003 8:14 PM
        Original string: Nov 4, 2003 8:14 PM
        Parsed date    : Tue Nov 04 20:14:00 EST 2003

        ENTER DATE STRING: Nov 4, 2003 8:14
        ERROR: could not parse date in string "Nov 4, 2003 8:14 "

        ENTER DATE STRING: Nov 4, 2003 8:14 AM
        Original string: Nov 4, 2003 8:14 AM
        Parsed date    : Tue Nov 04 08:14:00 EST 2003

        ENTER DATE STRING: November 4, 2003 8:14 AM
        Original string: November 4, 2003 8:14 AM
        Parsed date    : Tue Nov 04 08:14:00 EST 2003

        ENTER DATE STRING: Nov 4, 2003 20:14 PM
        Original string: Nov 4, 2003 20:14 PM
        Parsed date    : Wed Nov 05 08:14:00 EST 2003

        ENTER DATE STRING: Nov 4, 2003 20:14
        ERROR: could not parse date in string "Nov 4, 2003 20:14"

        ENTER DATE STRING: */



    }
}
